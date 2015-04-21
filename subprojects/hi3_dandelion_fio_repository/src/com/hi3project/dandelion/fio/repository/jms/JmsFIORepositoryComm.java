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
 
package com.hi3project.dandelion.fio.repository.jms;

import com.mytechia.commons.framework.modelaction.action.ModelAction;
import com.mytechia.commons.framework.modelaction.action.async.AsyncActionPool;
import com.hi3project.dandelion.fio.repository.IFIORepositoryInternalAPI;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.action.DeregisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.FindFIOByIdAction;
import com.hi3project.dandelion.fio.repository.comm.action.QueryFIOsByDistanceAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.requestresponse.IRequestResponseCodec;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
import java.util.logging.Level;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class JmsFIORepositoryComm implements MessageListener
{
    
    
    
    private final IFIORepositoryInternalAPI fioRepository;
    private final IRequestResponseCodec codec;

    
    private final Session mqSession;
    private Destination topic;
    private MessageProducer msgProducer;
    private MessageConsumer msgConsumer;

    
    private AsyncActionPool actionPool;
    
    
    
    public JmsFIORepositoryComm(
            IFIORepositoryInternalAPI fioRepository, 
            IRequestResponseCodec codec, 
            Session mqSession)
    {
        this.fioRepository = fioRepository;
        this.codec = codec;
        this.mqSession = mqSession;
    }
           

    
    public void start() throws JMSException
    {
        
        this.actionPool = new AsyncActionPool(1);
        
        String repositoryJmsTopic = JmsRemoteFIORepositoryClient.getFIORepositoryTopicName(fioRepository.getFIORepositoryId());
        Logging.logger.log(Level.INFO, "Starting FIO repository on topic: {0}", repositoryJmsTopic);
        this.topic = this.mqSession.createTopic(repositoryJmsTopic);
        this.msgConsumer = this.mqSession.createConsumer(this.topic);
        this.msgConsumer.setMessageListener(this);
        this.msgProducer = this.mqSession.createProducer(topic);
    }
    
    
    public void stop() throws JMSException
    {
        this.msgConsumer.close();
    }
    
    
    
    @Override
    public void onMessage(Message msg)
    {
        
        try {
            
            TextMessage txtMsg = (TextMessage) msg;
            
            FIORepositoryMessage repositoryMessage = 
                    (FIORepositoryMessage) this.codec.decodeRequestResponseMessage(txtMsg.getText());
            
            this.actionPool.addAction(new ProcessMessageAction(repositoryMessage), null);
            
        }
        catch(JMSException ex) {
            Logging.logger.log(Level.SEVERE, "Communication error: ", ex);
        }
        catch(Exception ex) {
            Logging.logger.log(Level.SEVERE, "Error while processing message on topic : ", ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        
    }
    
    private class ProcessMessageAction extends ModelAction
    {

        private final FIORepositoryMessage msg;

        
        public ProcessMessageAction(FIORepositoryMessage msg)
        {
            this.msg = msg;
        }

        
        @Override
        public Object execute() throws Exception
        {
            processMessage(msg);
            return null;
        }

        
        private void processMessage(FIORepositoryMessage msg) throws MessageCodeDecodeErrorException, JMSException   
        {

            FIORepositoryMessage response = null;
            
            switch (msg.getType()) {

                case DeregisterFIOAction:
                    fioRepository.processDeregisterFIOAction((DeregisterFIOAction) msg);
                    response = null;
                    break;
                case RegisterFIOAction:
                    response = fioRepository.processRegisterFIOAction((RegisterFIOAction) msg);
                    break;
                case FindFIOByIdAction:
                    response = fioRepository.processFindFIOByIdAction((FindFIOByIdAction) msg);
                    break;
                case QueryFIOByDistanceAction:
                    response = fioRepository.processQueryFIOsByDistance((QueryFIOsByDistanceAction) msg);
                    break;

            }

            if (response != null) {
                //send back the response
                String responseString = codec.codeRequestResponseMessage(response);
                TextMessage responseMsg = mqSession.createTextMessage(responseString);
                msgProducer.send(responseMsg);                
            }

        }

    }
    
    
    
    

}
