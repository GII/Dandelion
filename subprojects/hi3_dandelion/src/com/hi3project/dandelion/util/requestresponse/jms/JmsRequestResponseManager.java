/*******************************************************************************
 *   
 *   Copyright 2014 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright 2014 Gervasio Varela <gervasio.varela@udc.es>
 * 
 *   This file is part of Mytechia Commons.
 *
 *   Mytechia Commons is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Mytechia Commons is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with Mytechia Commons.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/


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
 
 package com.hi3project.dandelion.util.requestresponse.jms;

import com.hi3project.dandelion.util.requestresponse.IRequestResponseCallback;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.util.requestresponse.PendingRequest;
import com.hi3project.dandelion.util.requestresponse.RequestResponseMessage;
import com.mytechia.commons.util.pending.IPendingOperationsManagerListener;
import com.mytechia.commons.util.pending.PendingOperationNotFoundException;
import com.mytechia.commons.util.pending.PendingOperationsManager;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCodec;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseManager;
import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;


/** Manages the send and reception of request-response messages using JMS topics
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      22-may-2014
 */
public class JmsRequestResponseManager<T> implements IRequestResponseManager<T>, MessageListener, IPendingOperationsManagerListener<PendingRequest<T>>
{
    
    private final String topicId;
    private final String clientId;
    
    
    private final PendingOperationsManager<PendingRequest> pendingOperations;
    
    private final IRequestResponseCodec codec;
    
    private final Session mqSession;
    private MessageConsumer msgConsumer;
    private MessageProducer msgProducer;
    private Topic jmsTopic;

    
    
    public JmsRequestResponseManager(
            String clientId, String topicId, long expirationCheckPeriod,
            IRequestResponseCodec codec,
            Session mqSession)
    {
        this.clientId = clientId;
        this.topicId = topicId;
        this.pendingOperations = new PendingOperationsManager<PendingRequest>(expirationCheckPeriod);
        this.mqSession = mqSession;
        this.codec = codec;
    }
    
    
    public void start() throws InternalErrorException
    {
        try {
            this.jmsTopic = this.mqSession.createTopic(this.topicId);
            this.msgConsumer = this.mqSession.createConsumer(this.jmsTopic);
            this.msgConsumer.setMessageListener(this);
            this.msgProducer = this.mqSession.createProducer(this.jmsTopic);
        } catch (JMSException ex) {
            throw new InternalErrorException(ex);
        }
        
    }
    
    
    public void stop() throws InternalErrorException
    {
        try {
            this.msgConsumer.close();
        } catch (JMSException ex) {
            throw new InternalErrorException(ex);
        }
    }
    
    
    @Override
    public void sendRequest(
            RequestResponseMessage request, T requestData, IRequestResponseCallback callback, long expirationTime) 
            throws InternalErrorException
    {
        
        this.pendingOperations.addPendingOperation(
                request.getId(), new PendingRequest(request, requestData, callback), expirationTime, this);
        
        try {
            String codedMsg = this.codec.codeRequestResponseMessage(request);
            TextMessage msg = this.mqSession.createTextMessage(codedMsg);
            this.msgProducer.send(msg);
        }
        catch(JMSException ex) {
            throw new InternalErrorException(ex);
        } catch (MessageCodeDecodeErrorException ex) {
            throw new InternalErrorException(ex);
        }
        
    }
    
    

    @Override
    public void onMessage(Message msg)
    {
        
        
        try {
            
            TextMessage txtMsg = (TextMessage) msg;
            
            RequestResponseMessage responseMsg = 
                    this.codec.decodeRequestResponseMessage(txtMsg.getText());
            
            if (responseMsg.getDestination().equals(this.clientId)) {
            
                PendingRequest pendingOperation = 
                        this.pendingOperations.removePendingOperation(responseMsg.getId());
                RequestResponseMessage requestMsg = pendingOperation.getRequest();

                if (responseMsg.getSource().equals(pendingOperation.getRequest().getDestination())) {                            

                    pendingOperation.getCallback().notifyRequestResponse(requestMsg, responseMsg, pendingOperation.getRequestData());

                }
                
            }
            
            //else {
            //    discard message
            //}
            
        }
        catch(PendingOperationNotFoundException ex) {
            Logging.logger.log(Level.WARNING, "Received response without request.");
        }
        catch(MessageCodeDecodeErrorException ex) {
            Logging.logger.log(Level.WARNING, "Error decoding msg: ", ex);
        }
        catch(JMSException ex) {
            Logging.logger.log(Level.SEVERE, "Communication error: ", ex);
        }
        catch(Exception ex) {
            Logging.logger.log(Level.SEVERE, "Error while processing message on topic : ", ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        
    }

    
    @Override
    public void notifyPendingOperationExpiration(long pendingOperationId, PendingRequest<T> pendingOperationData)
    {
        RequestResponseMessage request = pendingOperationData.getRequest();
        T requestData = pendingOperationData.getRequestData();
        pendingOperationData.getCallback().notifyRequestExpiration(request, requestData);
    }
    
    

}
