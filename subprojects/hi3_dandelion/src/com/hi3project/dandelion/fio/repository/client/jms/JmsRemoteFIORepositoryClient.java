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
 
 package com.hi3project.dandelion.fio.repository.client.jms;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.modelaction.action.ModelAction;
import com.mytechia.commons.framework.modelaction.action.async.AsyncActionPool;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryAPI;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.action.DeregisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.FindFIOByIdAction;
import com.hi3project.dandelion.fio.repository.comm.action.QueryFIOsByDistanceAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.response.FIOEntryResponse;
import com.hi3project.dandelion.fio.repository.comm.response.FIOQueryResponse;
import com.hi3project.dandelion.fio.repository.exception.FIOMetricNotFoundErrorException;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
import com.hi3project.dandelion.fio.repository.exception.FIORepositoryNotRespondingErrorException;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCallback;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCodec;
import com.hi3project.dandelion.util.requestresponse.RequestResponseMessage;
import com.hi3project.dandelion.util.requestresponse.jms.JmsRequestResponseManager;
import com.hi3project.location.Location;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.Session;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class JmsRemoteFIORepositoryClient implements IFIORepositoryAPI, IRequestResponseCallback<IFIORepositoryCallback>
{
    
    private static final int DEFAULT_REPOSITORY_TIMEOUT_CHECK_TIME = 200;
    private static final int DEFAULT_REPOSITORY_REQUEST_TIMEOUT = 100000;
    
    public static String getFIORepositoryTopicName(String fioRepositoryId)
    {
        return DandelionFIORepositoryUtils.FIO_REPOSITORY_TOPIC_PREFIX + fioRepositoryId;
    }
    
    
    
    private long sequenceNumber = 0;
    
    private final String repositoryClientId;
    private final String fioRepositoryId;
    private final IRequestResponseCodec codec;


    private final JmsRequestResponseManager<IFIORepositoryCallback> rrManager;
    
    private AsyncActionPool actionPool;
    
    
    public JmsRemoteFIORepositoryClient(
            String repositoryClientId,
            String fioRepositoryId, 
            IRequestResponseCodec codec, 
            Session mqSession)
    {
        this.repositoryClientId = repositoryClientId;
        this.fioRepositoryId = fioRepositoryId;
        this.codec = codec;
        this.rrManager = new JmsRequestResponseManager<IFIORepositoryCallback>(
                repositoryClientId, getFIORepositoryTopicName(fioRepositoryId), 
                DEFAULT_REPOSITORY_REQUEST_TIMEOUT, this.codec, mqSession);
    }
    

    
    public void start() throws InternalErrorException
    {
        
        this.actionPool = new AsyncActionPool(1);
        
        String repositoryJmsTopic = getFIORepositoryTopicName(fioRepositoryId);
        Logging.logger.log(Level.INFO, "Starting FIO repository client on topic: {0}", repositoryJmsTopic);
        this.rrManager.start();
        
    }
    
    
    public void stop() throws InternalErrorException
    {
        this.rrManager.stop();
    }
    
    
    
    private long nextSequenceNumber()
    {
        
        if (this.sequenceNumber == Long.MAX_VALUE) {
            this.sequenceNumber = 0;
        }
        else {
            this.sequenceNumber++;
        }
        
        return this.sequenceNumber;
        
    }
    
    
    private void sendMessage(FIORepositoryMessage message, IFIORepositoryCallback callback) 
            throws MessageCodeDecodeErrorException, InternalErrorException
    {
        
        this.rrManager.sendRequest(message, callback, this, DEFAULT_REPOSITORY_TIMEOUT_CHECK_TIME);
        
    }

    
    @Override
    public void registerFIO(FIOExtendedMetadata fioMetadata, IFIORepositoryCallback callback) 
            throws InternalErrorException
    {

        RegisterFIOAction registerFIO = new RegisterFIOAction(nextSequenceNumber(), fioMetadata, this.repositoryClientId, this.fioRepositoryId);
        try {
            sendMessage(registerFIO, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
        
    }

    @Override
    public void deregisterFIO(FIOExtendedMetadata fioMetadata, IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        
        DeregisterFIOAction deregisterFIO = new DeregisterFIOAction(nextSequenceNumber(), fioMetadata, this.repositoryClientId, this.fioRepositoryId);
        try {
            sendMessage(deregisterFIO, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
        
    }

    @Override
    public void findFIOById(String fioId, IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        
        FindFIOByIdAction findFIO = 
                new FindFIOByIdAction(nextSequenceNumber(), fioId, this.repositoryClientId, this.fioRepositoryId);
        try {
            sendMessage(findFIO, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
        
    }
    
    
    
    @Override
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxCount, String metricId, 
            IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        
        QueryFIOsByDistanceAction action =
                new QueryFIOsByDistanceAction(
                        fioSpecs, maxCount, metricId, 
                        nextSequenceNumber(), this.repositoryClientId, this.fioRepositoryId);
        
        try {
            sendMessage(action, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
  
    }

    @Override
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxDistance, int maxCount, String metricId, 
            IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        QueryFIOsByDistanceAction action =
                new QueryFIOsByDistanceAction(
                        fioSpecs, maxCount, maxDistance, metricId, 
                        nextSequenceNumber(), this.repositoryClientId, this.fioRepositoryId);
        
        try {
            sendMessage(action, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
    }

    @Override
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxCount, String metricId, Location location, 
            IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        QueryFIOsByDistanceAction action =
                new QueryFIOsByDistanceAction(
                        fioSpecs, maxCount, metricId, location, 
                        nextSequenceNumber(), this.repositoryClientId, this.fioRepositoryId);
        
        try {
            sendMessage(action, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
    }

    @Override
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxDistance, int maxCount, String metricId, Location location, 
            IFIORepositoryCallback callback) 
            throws InternalErrorException
    {
        QueryFIOsByDistanceAction action =
                new QueryFIOsByDistanceAction(
                        fioSpecs, maxCount, maxDistance, metricId, location, 
                        nextSequenceNumber(), this.repositoryClientId, this.fioRepositoryId);
        
        try {
            sendMessage(action, callback);
        } catch (Exception ex) {
            throw new InternalErrorException(ex);
        }
    }

    
    
    

    @Override
    public void notifyRequestResponse(
            RequestResponseMessage request, RequestResponseMessage response,
            IFIORepositoryCallback callback)
    {
        
        try {
            
                ProcessMessageAction processMessageAction = 
                        new ProcessMessageAction((FIORepositoryMessage) response, callback);
                
                this.actionPool.addAction(processMessageAction, null);
            
        }
        catch(Exception ex) {
            Logging.logger.log(Level.SEVERE, "Error while processing message on topic : ", ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        
    }

    
    @Override
    public void notifyRequestExpiration(RequestResponseMessage request, IFIORepositoryCallback callback )
    {
        callback.notifyFIORepositoryError(new FIORepositoryNotRespondingErrorException("Operation timeout expiration."));
    }

    
    
    
    private class ProcessMessageAction extends ModelAction
    {
        
        private final FIORepositoryMessage message;
        private final IFIORepositoryCallback callback;

        
        public ProcessMessageAction(FIORepositoryMessage message, IFIORepositoryCallback callback)
        {
            super("FIO-Repository-client--ProcessMessageAction");
            this.message = message;
            this.callback = callback;
        }
                
        
        private void processMessage(FIORepositoryMessage msg) 
                throws MessageCodeDecodeErrorException, JMSException
        {

            if (msg.getDestination().equals(repositoryClientId)) {
            
                switch (msg.getType()) {

                    case FIOEntryResponse:
                        FIOEntryResponse response = (FIOEntryResponse) msg;
                        if (callback != null) {
                            if (response.getFioEntry() != null) {
                                callback.notifyFIORepositoryResponse(response.getFioEntry(), null);
                            }
                            else {
                                callback.notifyFIORepositoryResponse(null, new FIONotFoundException(""));
                            }
                        }
                        break;
                        
                    case FIOQueryReponse:
                        FIOQueryResponse queryResponse = (FIOQueryResponse) msg;
                        if (queryResponse.getError() == null) {
                            callback.notifyFIORepositoryQueryResponse(queryResponse.getEntries(), null);
                        }
                        else {
                            callback.notifyFIORepositoryQueryResponse(new ArrayList<FIORepositoryEntryCompliance>(0), new FIOMetricNotFoundErrorException(""));
                        }
                        break;
                        
                }
            
            }
            
        }

        
        @Override
        public Object execute() throws Exception
        {

            processMessage(message);
            return null;
                
        }
        
    }
    
      

}
