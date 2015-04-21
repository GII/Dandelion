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
import com.hi3project.dandelion.gip.comm.receive.IActionEventCallback;
import com.hi3project.dandelion.gip.comm.receive.ISelectionEventCallback;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.uic.DandelionUserInterfaceController;
import com.hi3project.dandelion.uic.callback.IDandelionSelectionCallback;
import com.hi3project.dandelion.uic.mapping.AIUInteractionElement;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.Property;
import java.util.ArrayList;
import java.util.logging.Level;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.data.DataSelectionSupport;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      28-feb-2014
 */
public class UICSelectionEventHandler implements ISelectionEventCallback
{

    private final DandelionUserInterfaceController dandelionUIC;

    
    
    public UICSelectionEventHandler(DandelionUserInterfaceController dandelionUIC)
    {
        this.dandelionUIC = dandelionUIC;
    }
    
    
    
    @Override
    public void event(SelectionEvent event)
    {
        
        String sourceId = event.getSourceId();   
        String interactionId = event.getInteractionId();
        FIOMetadata sourceFIO = new FIOMetadata(sourceId);
        
        if (event.getType() == EventType.selection) {
        
            ArrayList<Property> availableProperties = event.getAvailableItems();
            
            ArrayList<AIUInteractionElement> associatedAIUs = 
                    this.dandelionUIC.getAssociatedAIUs(sourceFIO, interactionId);

            for(AIUInteractionElement aiuIe : associatedAIUs) {

                ArrayList<IDandelionSelectionCallback> selectionCallback = 
                        this.dandelionUIC.getAssociatedSelectionCallbacks(aiuIe.getAIU(), aiuIe.getInteractionSupport());

                for(IDandelionSelectionCallback selectCback : selectionCallback) {
                    selectCback.notifySelection(
                            event.getSelectedItem(), availableProperties, null, 
                            aiuIe.getAIU(), (DataSelectionSupport) aiuIe.getInteractionSupport()); //TODO: falta el user
                }

            }
            
        }
        else {
            Logging.logger.log(Level.WARNING, "GIP Selection Handler: Unable to handle a not-selection GIP event received from %s", sourceId);
        }        
        
    }

    
}
