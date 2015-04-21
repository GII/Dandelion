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
 
 package com.hi3project.dandelion.gip.worker;

import com.hi3project.dandelion.gip.comm.receive.EventReceived;
import com.hi3project.dandelion.gip.comm.receive.GIPEventReceiver;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.util.log.Logging;
import java.util.LinkedList;
import java.util.logging.Level;


/** Provides a worker thread to process GIP events
 *
 * @author gervasio - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      14-feb-2014
 */
public class GIPEventWorker extends Thread
{

    private final LinkedList<EventReceived> eventQueue;
        
    private boolean active = true;
    
    private final Integer semaphore = new Integer(0);
    
    
    public GIPEventWorker() 
    {
        super("GIP-Event-Worker");
        this.eventQueue = new LinkedList<EventReceived>();
    }                     
    
    
    protected void processEvent(EventReceived eventReceived)
    {
        
        boolean unsupported = true;
        
        try {
            
            //receives the event and sends it to its appropriate processor callback
            //some of these callbacks could be unsupported (==null)
            
            Event event = eventReceived.getEvent();
            GIPEventReceiver gipReceiver = eventReceived.getGipReceiver();
            
            switch(event.getType()) {
                case input:
                    if (gipReceiver.getInputCallback() != null) {
                        gipReceiver.getInputCallback().event((DataEvent) event);
                        unsupported = false;
                    }
                    break;
                case output:
                    if (gipReceiver.getOutputCallback() != null) {
                        gipReceiver.getOutputCallback().event((DataEvent) event);
                        unsupported = false;
                    }
                    break;
                case selection:
                    if (gipReceiver.getSelectionCallback() != null) {
                        gipReceiver.getSelectionCallback().event((SelectionEvent) event);
                        unsupported = false;
                    }
                    break;
                case focus:
                    if (gipReceiver.getFocusCallback() != null) {
                        gipReceiver.getFocusCallback().event(event);
                        unsupported = false;
                    }
                    break;
                case action:
                    if (gipReceiver.getActionCallback() != null) {
                        gipReceiver.getActionCallback().event(event);
                        unsupported = false;
                    }
                    break;
            }
            
            if (unsupported) {
                Logging.logger.log(Level.INFO, "An unsupported GIP event has been received: "+event.getType());
            }
            
        }
        catch(ClassCastException cce) {
            //ignore the message
            Logging.logger.log(Level.WARNING, "Something that isn't a GIP event has been received.");
        }
        
    }

    
    public void stopProcessing()
    {
        this.active = false;
    }
    
    
    public void notifyReception(Event event, GIPEventReceiver gipReceiver)
    {   
        synchronized(eventQueue) {
            this.eventQueue.push(new EventReceived(event, gipReceiver));
        }
        
        synchronized(semaphore) {            
            semaphore.notify();
        }
    }
    
    
    @Override
    public void run() 
    {
                
        while(active) {
            
            EventReceived event = null;
            synchronized(eventQueue) {
                event = this.eventQueue.poll();
            }

            if (event != null) {
                processEvent(event);
            }
            else {
                synchronized(semaphore) {
                    try {
                        semaphore.wait();
                    } catch (Exception ex) { 
                        ex.printStackTrace(); //////////
                    }
                }
            }
            
        }
        
    }        
    
    
}
