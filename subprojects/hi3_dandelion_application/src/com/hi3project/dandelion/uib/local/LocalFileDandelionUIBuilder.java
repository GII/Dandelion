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
 
 package com.hi3project.dandelion.uib.local;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.IUserInterfaceBuilder;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.local.xml.UIBJaxbReader;
import com.hi3project.dandelion.uib.xml.jaxb.AIU2FIOMapping;
import com.hi3project.dandelion.uib.xml.jaxb.AssociatedFIO;
import com.hi3project.dandelion.uib.xml.jaxb.DandelionApplicationFinalUIMapping;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.util.log.Logging;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.xml.bind.JAXBException;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** Implementation of an UI Builder that reads the mapping from a local
 * XML file and manages them statically (only at start time)
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      15-abr-2014
 */
public class LocalFileDandelionUIBuilder implements IUserInterfaceBuilder
{
    
    private ApplicationMetadata appMetadata;
    private IUserInterfaceController dandelionUIC;
    
    private final String appUiBuilderFilePath;
    private DandelionApplicationFinalUIMapping uiMapping;
    
    private String id;

    
    public LocalFileDandelionUIBuilder(String appUiBuilderFilePath, String id)
    {
        this.appUiBuilderFilePath = appUiBuilderFilePath;
        this.id = id;
    }

    
    public String getId()
    {
        return LocalFileFIORepoDandelionUIBuilder.BUILDER_ID+this.id;
    }
    

    @Override
    public void buildUI(
            ApplicationMetadata appMetadata, IUserInterfaceController dandelionUIC,
            UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws UnableToBuildUIException
    {
        
        this.appMetadata = appMetadata;
        this.dandelionUIC = dandelionUIC;
        
        UIBJaxbReader reader = new UIBJaxbReader();
        
        try {
            
            DandelionApplicationFinalUIMapping uIMapping = 
                    reader.readUIBConfigFile(new FileInputStream(appUiBuilderFilePath));
        
        
            for(AIU2FIOMapping mapping : uIMapping.getAIU2FIOMapping()) {

                AbstractInteractionUnit aiu = 
                        appMetadata.getAbstractUI().getAbstractInteractionUnitById(mapping.getAbstractInteractionUnitID());

                if (aiu != null) {
                    
                    InteractionSupportElement ise = aiu.getInteractionSupportElementById(mapping.getInteractionSupportElementID());
                    
                    if (ise != null) {
                    
                        for(AssociatedFIO fio : mapping.getAssociatedFIO()) {

                            try {

                                associateAIU2FIO(aiu, ise, fio.getID(), fio.getInteractionID());

                                Logging.logger.log(Level.FINE, "Mapping AIU({0}) to FIO({1}).", new Object[]{aiu.getId(), fio.getID()});

                            } catch (MappingNotAvailableException ex) {
                                Logging.logger.log(Level.WARNING, "Mapping not available with FIO: {0] ", fio.getID());
                            }

                        }
                    }
                }
                else {
                    Logging.logger.log(Level.WARNING, "Specified AIU ({0}) was not found.", mapping.getAbstractInteractionUnitID());
                }

            }
        
        } catch (FileNotFoundException ex) {
            throw new UnableToBuildUIException(ex);
        } catch (JAXBException ex) {
            throw new UnableToBuildUIException(ex);
        }
        
    }
    
    
    private void associateAIU2FIO(AbstractInteractionUnit aiu, InteractionSupportElement ise, String fioId, String interactionId) throws MappingNotAvailableException
    {
        
        FIOMetadata fio = new FIOMetadata(fioId);
        dandelionUIC.addAIU2FIOMapping(aiu, ise, fio, interactionId);
        
    }

    
    
    
    @Override
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        //rebuild is not support by this implementation of UIB
    }

    
    
    @Override
    public void init() throws InternalErrorException
    {
        
    }

}
