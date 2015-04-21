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
 
 package com.hi3project.dandelion.fio.stomp;

import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ser1.stomp.Listener;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-mar-2015
 */
public class StompListener implements Listener
{

    private StompFIOManager fioManager;
    
    private final IGIPCodec gipCodec;


    
    public StompListener(StompFIOManager fioManager, IGIPCodec gipCodec)
    {
        this.fioManager = fioManager;
        this.gipCodec = gipCodec;
    }
    
    
    
    @Override
    public void message(Map map, String string)
    {
        
        try {
            
            Event gipEvent = this.gipCodec.decodeGIPEvent(string);
            
            switch(gipEvent.getType()) {
                case output:
                    IOutputAction outputAction = this.fioManager.getOutputAction(gipEvent.getInteractionId());
                    if (outputAction != null) {
                        DataEvent outputEvent = (DataEvent) gipEvent;
                        outputAction.doOutput(outputEvent.getPropertySet(), outputEvent.getFuzzyHints());
                    }
                    break;
                case focus:
                    IFocusAction focusAction = this.fioManager.getFocusAction(gipEvent.getInteractionId());
                    if (focusAction != null) {
                        focusAction.doFocus(gipEvent.getFuzzyHints());    
                    }                    
                    break;
                case selection:
                    ISelectionAction selectionAction = this.fioManager.getSelectionAction(gipEvent.getInteractionId());
                    if (selectionAction != null) {
                        SelectionEvent se = (SelectionEvent) gipEvent;
                        selectionAction.doSelection(se.getSelectedItem(), se.getAvailableItems(), se.getFuzzyHints());
                    }
                    break;
                default:
                    break;
            }
            
        } catch (GIPEventCodeDecodeErrorException ex) {
            Logger.getLogger(StompListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
