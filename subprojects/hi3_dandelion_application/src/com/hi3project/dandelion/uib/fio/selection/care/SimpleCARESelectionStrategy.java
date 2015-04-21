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
 
 package com.hi3project.dandelion.uib.fio.selection.care;

import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.mapping.FinalUIMapping;
import com.hi3project.dandelion.uib.fio.selection.IFinalFIOSelectionStrategy;
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
 *      26-feb-2015
 */
public class SimpleCARESelectionStrategy implements IFinalFIOSelectionStrategy
{

    
    @Override
    public FIORepositoryEntryCompliance selectFIO(
            ApplicationMetadata app, 
            AbstractInteractionUnit aiu, 
            InteractionSupportElement ise, 
            UserProfile user, 
            EnvironmentProfile environment, 
            SceneProfile scene, 
            Collection<FIORepositoryEntryCompliance> availableFIOs, 
            FinalUIMapping finalUI)
    {
        
        FIORepositoryEntryCompliance bestEntry = null;
        for (FIORepositoryEntryCompliance entry : availableFIOs) {

            Logging.logger.log(Level.FINE, "Distance = {0}", entry.getDistance());
            if (bestEntry != null) {
                if (bestEntry.getDistance().getValue() < entry.getDistance().getValue()) {
                    bestEntry = entry;
                }
            }
            else {
                bestEntry = entry;
            }

        }
        
        return bestEntry;
        
    }
    
    

}
