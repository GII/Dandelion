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
 
 package com.hi3project.dandelion.gip;

import com.hi3project.dandelion.gip.comm.publish.IGIPEventPublisher;
import com.hi3project.dandelion.gip.comm.receive.GIPEventReceiver;
import com.hi3project.dandelion.gip.comm.receive.IActionEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IFocusEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IInputEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IOutputEventCallback;
import com.hi3project.dandelion.gip.comm.receive.ISelectionEventCallback;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;




/** Implementations of this interface provide the required logic to start/stop
 * the publishing and reception of GIP events
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      22-mar-2013 -- Initial version
 */
public interface IGIPManager 
{

    /** Starts a GIP Publisher for the channell "channelId"
     * @param sourceId The id of the source of the events
     * @param channelId the communication channel-id, for example the topic of a FIO
     * @param create if true, the channel will be created if it does not exists already
     * @return a GIP publisher that will publish events in the specified channel
     * @throws CommunicationErrorException if there was a communication problem
     */
    public IGIPEventPublisher startGIPPublisher(String sourceId, String channelId, boolean create)
            throws CommunicationErrorException;
    
    public void stopGIPPublisher(IGIPEventPublisher gipPublisher);
    
    
    
    /** Starts a GIP Receiver for the channell "channelId"    
     * 
     * @param channelId The communication channel-id, for example, the topic of a FIO
     * @param create If true, the channel will be created if it does not exists already
     * @param input processor for input events
     * @param output processor for output events
     * @param action processor for action events
     * @param selection processor for selection events
     * @param focus processor for focus events
     * @return a new GIP receiver that will receive events from the specified channel
     * @throws CommunicationErrorException if there was a communication problem
     */
    public GIPEventReceiver startGIPReceiver(
            String channelId, boolean create,
            IInputEventCallback input, 
            IOutputEventCallback output, 
            IActionEventCallback action, 
            ISelectionEventCallback selection, 
            IFocusEventCallback focus)
            throws CommunicationErrorException;
    
    public void stopGIPReceiver(GIPEventReceiver gipReceiver);
    
   
    
}
