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

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.gip.FIOCommChannelUtil;
import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.fio.repository.comm.action.DeregisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCodec;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.security.auth.login.LoginException;
import net.ser1.stomp.Client;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-mar-2015
 */
public class StompFIOManager
{
    
    private Client stompClient;
    private StompListener stompListener;
    
    private final FIOExtendedMetadata fioMetadata;

    private final IRequestResponseCodec codec;
    private final IGIPCodec gipCodec;
    
    private final String fioRepositoryId;
    
    
    private final HashMap<String, IOutputAction> outputActions;
    private final HashMap<String, IFocusAction> focusActions;
    private final HashMap<String, ISelectionAction> selectionActions;
    
    
    public StompFIOManager(
            IGIPCodec gipCodec, 
            IRequestResponseCodec codec, String fioRepositoryId, 
            FIOExtendedMetadata fioMetadata)
    {
        this.gipCodec = gipCodec;
        this.codec = codec;
        this.fioMetadata = fioMetadata;
        this.fioRepositoryId = fioRepositoryId;
        this.outputActions = new HashMap<String, IOutputAction>();
        this.focusActions = new HashMap<String, IFocusAction>();
        this.selectionActions = new HashMap<String, ISelectionAction>();
    }
    
    
    public void addOutputAction(IOutputAction action)
    {
        this.outputActions.put(action.getId(), action);
    }
    
    public IOutputAction getOutputAction(String id)
    {
        return this.outputActions.get(id);
    }
    
    public void addFocusAction(IFocusAction action)
    {
        this.focusActions.put(action.getId(), action);
    }
    
    public IFocusAction getFocusAction(String id)
    {
        return this.focusActions.get(id);
    }
    
    public void addSelectionAction(ISelectionAction action)
    {
        this.selectionActions.put(action.getId(), action);
    }
    
    public ISelectionAction getSelectionAction(String id)
    {
        return this.selectionActions.get(id);
    }
    
    
    
    public void start(String host, int port, String user, String passwd) throws InternalErrorException
    {
        
        if (this.stompClient == null) {
            try {
                
                this.stompClient = new Client(host, port, user, passwd);
                
                this.stompListener = new StompListener(this, gipCodec);
                
                String commChannel = FIOCommChannelUtil.getCommChannel(fioMetadata);
                
                this.stompClient.subscribe("/topic/"+commChannel, stompListener);
                
                registerFIO(fioMetadata);
                
            } catch (IOException ex) {
                throw new InternalErrorException(ex);
            } catch (LoginException ex) {
                throw new InternalErrorException(ex);
            }
        }
        
    }
    
    
    public void stop() throws InternalErrorException
    {
        
        try {
            
            deregisterFIO(fioMetadata);
            
        }
        catch(FIONotFoundException ex) {
            throw new InternalErrorException(ex);
        }
        
        this.stompClient.disconnect();
        
    }
    
    

    private void registerFIO(FIOExtendedMetadata fioMetadata) throws InternalErrorException
    {
        
        try {
            
            RegisterFIOAction registerFIO = new RegisterFIOAction(1, fioMetadata, this.fioMetadata.getId(), this.fioRepositoryId);
            
            String msg = this.codec.codeRequestResponseMessage(registerFIO);
            
            this.stompClient.send("/topic/"+DandelionFIORepositoryUtils.FIO_REPOSITORY_TOPIC_PREFIX+this.fioRepositoryId, msg);
            
        } catch (MessageCodeDecodeErrorException ex) {
            throw new InternalErrorException(ex);
        }
        
    }


    
    private void deregisterFIO(FIOExtendedMetadata fioMetadata) throws FIONotFoundException, InternalErrorException
    {
        
        try {
            
            DeregisterFIOAction deregisterFIO = new DeregisterFIOAction(1, fioMetadata, this.fioMetadata.getId(), this.fioRepositoryId);
            
            String msg = this.codec.codeRequestResponseMessage(deregisterFIO);
            
            this.stompClient.send("/topic/"+DandelionFIORepositoryUtils.FIO_REPOSITORY_TOPIC_PREFIX+this.fioRepositoryId, msg);
            
        } catch (MessageCodeDecodeErrorException ex) {
            throw new InternalErrorException(ex);
        }
    }
    
    
    private String getFIOTopic()
    {
        return "/topic/"+FIOCommChannelUtil.getCommChannel(fioMetadata);
    }
    
    
    public void acctionHappened(String actionId, ArrayList<FuzzyVariable> fuzzyHints)
            throws InternalErrorException
    {       
        Event ae = new Event(this.fioMetadata.getId(), actionId, EventType.action);
        ae.setFuzzyHints(fuzzyHints);
        String msg = this.gipCodec.codeGIPEvent(ae);
        this.stompClient.send(getFIOTopic(), msg);
    }
    
    public void inputHappened(String inputId, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints) 
            throws InternalErrorException 
    {
        DataEvent de = new DataEvent(this.fioMetadata.getId(), inputId, EventType.input, propertySet);
        de.setFuzzyHints(fuzzyHints);
        String msg = this.gipCodec.codeGIPEvent(de);
        this.stompClient.send(getFIOTopic(), msg);        
    }


    public void selectionHappened(String selectionId, Property selected, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints)
            throws InternalErrorException 
    {
        SelectionEvent se = new SelectionEvent(this.fioMetadata.getId(), selectionId, propertySet, selected);
        se.setFuzzyHints(fuzzyHints);
        String msg = this.gipCodec.codeGIPEvent(se);
        this.stompClient.send(getFIOTopic(), msg);        
    }
    
    
}
