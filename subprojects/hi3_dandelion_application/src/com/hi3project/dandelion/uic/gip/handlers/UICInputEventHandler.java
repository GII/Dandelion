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
 
 package com.hi3project.dandelion.uic.gip.handlers;

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.gip.comm.receive.IInputEventCallback;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.uic.DandelionUserInterfaceController;
import com.hi3project.dandelion.uic.callback.IDandelionInputCallback;
import com.hi3project.dandelion.uic.mapping.AIUInteractionElement;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.Property;
import java.util.ArrayList;
import java.util.logging.Level;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.data.DataInputOutputSupport;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      28-feb-2014
 */
public class UICInputEventHandler implements IInputEventCallback
{

    private final DandelionUserInterfaceController dandelionUIC;

    
    
    public UICInputEventHandler(DandelionUserInterfaceController dandelionUIC)
    {
        this.dandelionUIC = dandelionUIC;
    }
    
    
    
    @Override
    public void event(DataEvent event)
    {
        
        String sourceId = event.getSourceId();    
        String interactionId = event.getInteractionId();
        FIOMetadata sourceFIO = new FIOMetadata(sourceId);
        
        if (event.getType() == EventType.input) {
        
            ArrayList<Property> propertySet = event.getPropertySet();
            ArrayList<String> dataCollection = new ArrayList<String>(propertySet.size());
            for(Property prop : propertySet) {
                dataCollection.add(prop.getValue());
            }
            
            ArrayList<AIUInteractionElement> associatedAIUs = 
                    this.dandelionUIC.getAssociatedAIUs(sourceFIO, interactionId);

            for(AIUInteractionElement aiuIe : associatedAIUs) {

                ArrayList<IDandelionInputCallback> inputCallbacks = 
                        this.dandelionUIC.getAssociatedInputCallbacks(aiuIe.getAIU(), aiuIe.getInteractionSupport());

                for(IDandelionInputCallback inCback : inputCallbacks) {
                    inCback.notifyInput(dataCollection, null, aiuIe.getAIU(), (DataInputOutputSupport) aiuIe.getInteractionSupport()); //TODO: falta el user
                }

            }
            
        }
        else {
            Logging.logger.log(Level.WARNING, "GIP Input Handler: Unable to handle a not-input GIP event received from %s", sourceId);
        }        
        
    }

    
}
