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
 
 package com.hi3project.dandelion.uic.gip;

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.fio.gip.FIOCommChannelUtil;
import com.hi3project.dandelion.gip.IGIPManager;
import com.hi3project.dandelion.gip.comm.publish.IGIPEventPublisher;
import com.hi3project.dandelion.gip.comm.receive.GIPEventReceiver;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.uic.exception.CommunicationErrorWithFIOsException;
import com.hi3project.dandelion.uic.gip.handlers.UICActionEventHandler;
import com.hi3project.dandelion.uic.gip.handlers.UICInputEventHandler;
import com.hi3project.dandelion.uic.gip.handlers.UICSelectionEventHandler;
import com.hi3project.dandelion.uic.mapping.FIOInteractionMetadata;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.dandelion.util.properties.PropertyType;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;


/** Provides GIP access to the UIC
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public class UserInterfaceControllerGIPManager 
{
    
    
    private final IGIPManager gipManager;
    
    private final Map<FIOMetadata, IGIPEventPublisher> publishers;
    private final Map<FIOMetadata, GIPEventReceiver> receivers;
    
    private final UICActionEventHandler actionHandler;
    private final UICInputEventHandler inputHandler;
    private final UICSelectionEventHandler selectionHandler;
    
    private final String uicId;

    
    public UserInterfaceControllerGIPManager(
            String uicId,
            IGIPManager gipManager,
            UICActionEventHandler actionHandler,
            UICInputEventHandler inputHandler,
            UICSelectionEventHandler selectionHandler)
    {
        this.uicId = uicId;
        this.gipManager = gipManager;
        this.actionHandler = actionHandler;
        this.inputHandler = inputHandler;
        this.selectionHandler = selectionHandler;
        this.receivers = new HashMap<FIOMetadata, GIPEventReceiver>();
        this.publishers = new HashMap<FIOMetadata, IGIPEventPublisher>();
    }
    
    
    
    
    private IGIPEventPublisher getFIOGIPPublisher(FIOMetadata fio)
    {
        return this.publishers.get(fio);
    }
    
    

    /** Sends a FOCUS event to all the FIOs of the list
     * @param fios
     * @param interactionHints
     * @throws com.hi3project.dandelion.uic.exception.CommunicationErrorWithFIOsException
     */
    public void gainFocus(ArrayList<FIOInteractionMetadata> fios, Set<FuzzyVariable> interactionHints) throws CommunicationErrorWithFIOsException
    {
        IGIPEventPublisher publisher;
        ArrayList<FIOMetadata> failedFIOs = new ArrayList<FIOMetadata>(1);
        
        for(FIOInteractionMetadata fio : fios) {
            
            publisher = this.publishers.get(fio.getFio());         
            
            if (publisher != null) {
                try {
                    
                    publisher.focus(fio.getInteractionId(), interactionHints);
                    
                }
                catch(CommunicationErrorException commEx) {
                    failedFIOs.add(fio.getFio());
                    Logging.logger.log(Level.WARNING, "Unable to send FOCUS event to FIO '{0}': Communication error.", fio);
                }
            }
            else {
                //publisher unavailable? quite extrange...
                failedFIOs.add(fio.getFio());
                Logging.logger.log(Level.WARNING, "Unable to send FOCUS event to FIO '{0}': GIP Publisher unavailable.", fio);
            }
            
        }
        
        if (!failedFIOs.isEmpty()) {
            throw new CommunicationErrorWithFIOsException(failedFIOs);
        }
        
    }


    public void showOutput(Property pojo, ArrayList<FIOInteractionMetadata> fios, Set<FuzzyVariable> interactionHints) throws CommunicationErrorWithFIOsException
    {        
        IGIPEventPublisher publisher;
        ArrayList<FIOMetadata> failedFIOs = new ArrayList<FIOMetadata>(1);
        
        for(FIOInteractionMetadata fio : fios) {
            
            publisher = this.publishers.get(fio.getFio());
            ArrayList<Property> properties = new ArrayList<Property>(1);
            properties.add(new Property(pojo.getType(), pojo.getLabel(), pojo.getValue()));
            
            if (publisher != null) {
                
                try {
                    
                    publisher.output(fio.getInteractionId(), properties, interactionHints);
                
                }
                catch(CommunicationErrorException commEx) {
                    failedFIOs.add(fio.getFio());
                    Logging.logger.log(Level.WARNING, "Unable to send OUTPUT event to FIO '{0}': Communication error.", fio);
                }
                
            }
            else {
                //publisher unavailable? quite extrange...
                failedFIOs.add(fio.getFio());
                Logging.logger.log(Level.WARNING, "Unable to send OUTPUT event to FIO '{0}'", fio);
            }
            
        }
        
        if (!failedFIOs.isEmpty()) {
            throw new CommunicationErrorWithFIOsException(failedFIOs);
        }
        
    }


    public void showSelection(Property defaultElement, ArrayList<Property> elementList, ArrayList<FIOInteractionMetadata> fios, Set<FuzzyVariable> interactionHints) throws CommunicationErrorWithFIOsException
    {                
        IGIPEventPublisher publisher;
        ArrayList<FIOMetadata> failedFIOs = new ArrayList<FIOMetadata>(1);
        
        for(FIOInteractionMetadata fio : fios) {
            publisher = this.publishers.get(fio.getFio());
            
            String label = null;
            ArrayList<Property> properties = new ArrayList<Property>(elementList.size());
            for(Property obj : elementList) {
                if (obj.equals(defaultElement)) {
                    label = "selected";
                }
                else {
                    label = "element";
                }
                properties.add(new Property(PropertyType.stringProperty, label, obj.getValue()));
            }
            
            
            if (publisher != null) {
                try {
                    
                    publisher.selection(fio.getInteractionId(), properties, interactionHints);
                    
                }
                catch(CommunicationErrorException commEx) {
                    failedFIOs.add(fio.getFio());
                    Logging.logger.log(Level.WARNING, "Unable to send SELECTION event to FIO '%s': Communication error.", fio);
                }
            }
            else {
                failedFIOs.add(fio.getFio());
                Logging.logger.log(Level.WARNING, "Unable to send SELECTION event to FIO '%s'", fio);
            }
        }
        
        if (!failedFIOs.isEmpty()) {
            throw new CommunicationErrorWithFIOsException(failedFIOs);
        }
        
    }
    
    
    
    
    /** Suscribe the UIC to the events of a FIO
     * 
     * @param fio
     * @throws CommunicationErrorException 
     * @return true if the FIO was succesfully suscribed
     */
    public boolean suscribeToFIO(FIOMetadata fio) throws CommunicationErrorException
    {
        
        if (!isSuscribedTo(fio)) {
            
            GIPEventReceiver gipReceiver = this.gipManager.startGIPReceiver(
                    FIOCommChannelUtil.getCommChannel(fio), true, inputHandler, 
                    null, actionHandler, selectionHandler, null);
            
            IGIPEventPublisher gipPublisher = this.gipManager.startGIPPublisher(
                    this.uicId, FIOCommChannelUtil.getCommChannel(fio), true);

            //save them for future uses
            this.receivers.put(fio, gipReceiver);
            this.publishers.put(fio, gipPublisher);
        }
        
        return isSuscribedTo(fio);
        
    }
    
    
    
    /** Unsuscribe the UIC from the events of a FIO
     * 
     * @param fio 
     * @return true if the FIO was succesfully unsuscribed
     */
    public boolean unsuscribeFromFIO(FIOMetadata fio)
    {
        
        if (isSuscribedTo(fio)) {
            GIPEventReceiver gipReceiver = this.receivers.remove(fio);
            IGIPEventPublisher gipPublisher = this.publishers.remove(fio);

            if (gipReceiver != null) {
                gipReceiver.stopReceiving();
            }

            if (gipPublisher != null) {
                gipPublisher.stopPublishing();
            }
        }
        
        return !isSuscribedTo(fio);
        
    }
    
    
    
    public boolean isSuscribedTo(FIOMetadata fio)
    {
        return this.receivers.containsKey(fio);
    }

    
    
    

}
