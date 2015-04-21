/*******************************************************************************
 *   
 *   Copyright (C) 2015 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright (C) 2015 Gervasio Varela <gervarela@picandocodigo.com>
 * 
 *   This file is part of Dandelion.
 *
 *   Dandelion is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Dandelion is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with Dandelion.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/
 
 package com.hi3project.dandelion.uib.local.specbuilder;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.mapping.FinalUIMapping;
import com.hi3project.dandelion.uib.fio.selection.IFinalFIOSelectionStrategy;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.util.log.Logging;
import java.util.Collection;
import java.util.logging.Level;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      04-dic-2014
 */
public class UISpecBuilderRepositoryCallback implements IFIORepositoryCallback
{

    private final ApplicationMetadata app;
    
    private final AbstractInteractionUnit aiu;
    private final InteractionSupportElement ise;
    
    private final UserProfile user;
    private final EnvironmentProfile environment;
    private final SceneProfile scene;
    
    private final IUserInterfaceController uic;

    
    private final IFinalFIOSelectionStrategy finalFIOSelectionStrategy;
    private final FinalUIMapping finalUIMapping;
    
    
    
    public UISpecBuilderRepositoryCallback(
            ApplicationMetadata app, 
            AbstractInteractionUnit aiu, InteractionSupportElement ise, 
            UserProfile user, EnvironmentProfile environment, SceneProfile scene,
            IUserInterfaceController uic,
            IFinalFIOSelectionStrategy finalFIOSelectionStrategy,
            FinalUIMapping finalUIMapping)
    {
        this.app = app;
        this.aiu = aiu;
        this.ise = ise;
        this.user = user;
        this.environment = environment;
        this.scene = scene;
        this.uic = uic;
        this.finalFIOSelectionStrategy = finalFIOSelectionStrategy;
        this.finalUIMapping = finalUIMapping;
    }
    
    
    
    
    
    
    
    @Override
    public void notifyFIORepositoryResponse(
            FIORepositoryEntry fioEntry, ModelException modelException)
    {
        Logging.logger.log(Level.WARNING, "Unsupported FIO repository respose.");
    }

    
    @Override
    public void notifyFIORepositoryQueryResponse(
            Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
    {

        Logging.logger.log(Level.INFO, "Received {0} options for AIU-ISE {1}-{2}.", new String[]{String.valueOf(response.size()), this.aiu.getId(), this.ise.getId()});

        FIORepositoryEntryCompliance selectedFIO = 
                this.finalFIOSelectionStrategy.selectFIO(app, aiu, ise, user, environment, scene, response, finalUIMapping);

        if (selectedFIO != null) {
            try {
                
                FIOMetadata fio = selectedFIO.getEntry().getFioMetadata();
                InteractionCapability fioFacet = selectedFIO.getDistance().getModalitySimilarity().getInteractionFacet();
                
                associateAIU2FIO(aiu, ise, fio.getId(), fioFacet.getId());
                
            } catch (MappingNotAvailableException ex) {
                Logging.logger.log(Level.WARNING, "Unable to find a suitable map for AIU-ISE {0}-{1}", new String[]{this.aiu.getId(), this.ise.getId()});
            }
        }
        else {
            Logging.logger.log(Level.WARNING, "Unable to find a suitable map for AIU-ISE {0}-{1}", new String[]{this.aiu.getId(), this.ise.getId()});
        }

    }

    
    private void associateAIU2FIO(AbstractInteractionUnit aiu, InteractionSupportElement ise, String fioId, String interactionId) throws MappingNotAvailableException
    {
        
        FIOMetadata fio = new FIOMetadata(fioId);
        uic.addAIU2FIOMapping(aiu, ise, fio, interactionId);
        
    }
    
    
    @Override
    public void notifyFIORepositoryError(
            InternalErrorException error)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
