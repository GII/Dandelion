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
 
 package com.hi3project.dandelion.gip.comm.receive;

import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.worker.GIPEventWorker;


/** Receives GIP events by listening to JMS topics
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-mar-2013 -- Initial version
 */
public abstract class GIPEventReceiver
{

    private IInputEventCallback inputCallback;
    private IOutputEventCallback outputCallback;
    private ISelectionEventCallback selectionCallback;
    private IFocusEventCallback focusCallback;
    private IActionEventCallback actionCallback;
    
    private GIPEventWorker gipWorker;
    

    public GIPEventReceiver(
            IInputEventCallback inputCallback, IOutputEventCallback outputCallback, 
            ISelectionEventCallback selectionCallback, IFocusEventCallback focusCallback, 
            IActionEventCallback actionCallback,
            GIPEventWorker gipWorker)
    {
        this(gipWorker);
        this.inputCallback = inputCallback;
        this.outputCallback = outputCallback;
        this.selectionCallback = selectionCallback;
        this.focusCallback = focusCallback;
        this.actionCallback = actionCallback;
    }
    
    
    public GIPEventReceiver(GIPEventWorker gipWorker)
    {                
        this.gipWorker = gipWorker;        
        
        this.inputCallback = null;
        this.outputCallback = null;
        this.selectionCallback = null;
        this.focusCallback = null;
        this.actionCallback = null;        
    }

    
    
    public void setInputCallback(IInputEventCallback inputCallback) 
    {
        this.inputCallback = inputCallback;
    }

    public void setOutputCallback(IOutputEventCallback outputCallback) 
    {
        this.outputCallback = outputCallback;
    }

    public void setSelectionCallback(ISelectionEventCallback selectionCallback) 
    {
        this.selectionCallback = selectionCallback;
    }

    public void setFocusCallback(IFocusEventCallback focusCallback) 
    {
        this.focusCallback = focusCallback;
    }

    public void setActionCallback(IActionEventCallback actionCallback) 
    {
        this.actionCallback = actionCallback;
    }

    public IInputEventCallback getInputCallback()
    {
        return inputCallback;
    }

    public IOutputEventCallback getOutputCallback()
    {
        return outputCallback;
    }

    public ISelectionEventCallback getSelectionCallback()
    {
        return selectionCallback;
    }

    public IFocusEventCallback getFocusCallback()
    {
        return focusCallback;
    }

    public IActionEventCallback getActionCallback()
    {
        return actionCallback;
    }
    
    

    /** Starts the reception and processing of GIP events.
     */
    public void startReceiving()
    {
                        
    }
    
    
    /** After the execution of this method the receiver will be unable to
     * receive new events.
     */
    public void stopReceiving()
    {
        
    }
    
    
    @Override
    public abstract boolean equals(Object object);
    
    
    
    protected void eventReceived(Event event)
    {
        
        if (this.gipWorker != null) {
            this.gipWorker.notifyReception(event, this);
        }
        
    }
    
}
