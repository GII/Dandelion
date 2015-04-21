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
 
 package com.hi3project.dandelion.fio.gip;

import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.gip.comm.receive.IFocusEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IOutputEventCallback;
import com.hi3project.dandelion.gip.comm.receive.ISelectionEventCallback;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.SelectionEvent;

/** Receives GIP events for a FIO and uses callbacks to launch
 * the logic specific of each FIO implementation
 *
 * @author gervasio - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-feb-2014
 */
public class FIOGIPEventHandler implements 
        IOutputEventCallback, IFocusEventCallback, ISelectionEventCallback
{
    
    private final FIOGIPManager fioGip;

    
    public FIOGIPEventHandler(
            FIOGIPManager fioGipManager) 
    {
        this.fioGip = fioGipManager;
    }

    
    
        
    @Override
    public void event(DataEvent event) 
    {
        //OUTPUT EVENT
        IOutputAction outputAction = this.fioGip.getOutputAction(event.getInteractionId());
        if (outputAction != null) {
            outputAction.doOutput(event.getPropertySet(), event.getFuzzyHints());
        }
        //else ignore the event
    }

    @Override
    public void event(Event event) 
    {       
        //FOCUS EVENT
        IFocusAction focusAction = this.fioGip.getFocusAction(event.getInteractionId());
        if (focusAction != null) {
            focusAction.doFocus(event.getFuzzyHints());
        }
        //else ignore the event
    }

    @Override
    public void event(SelectionEvent event) 
    {
        //SELECTION EVENT
        ISelectionAction selectionAction = this.fioGip.getSelectionAction(event.getInteractionId());
        if (selectionAction != null) {
            selectionAction.doSelection(event.getSelectedItem(), event.getAvailableItems(), event.getFuzzyHints());
        }
        //else ignore the event
    }
    
    
    

}
