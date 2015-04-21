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

import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.comm.publish.IGIPEventPublisher;
import com.hi3project.dandelion.gip.comm.publish.JmsPublicationGIPEventPublisher;
import com.hi3project.dandelion.gip.comm.receive.GIPEventReceiver;
import com.hi3project.dandelion.gip.comm.receive.IActionEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IFocusEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IInputEventCallback;
import com.hi3project.dandelion.gip.comm.receive.IOutputEventCallback;
import com.hi3project.dandelion.gip.comm.receive.ISelectionEventCallback;
import com.hi3project.dandelion.gip.comm.receive.JmsGIPEventReceiver;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.gip.worker.GIPEventWorker;
import com.hi3project.dandelion.util.log.Logging;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQTopic;


/** A GIP Manager that produces publisher/receivers that use JMS for communications
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-feb-2014 -- Initial version
 */
public class AMQJmsGIPManager implements IGIPManager
{
    
    private final ArrayList<IGIPEventPublisher> gipPublisherList;
    private final ArrayList<GIPEventReceiver> gipReceiverList;
    
    private final IGIPCodec gipCodec;
    
    private String brokerUrl = "tcp://localhost:61616";
    private ActiveMQConnectionFactory jmsConnectionFactory;
    private Connection jmsConnection;
    private Session jmsSession;
    private DestinationSource amqMonitoring;
    
    private final GIPEventWorker gipWorker;

    

    
    
    public AMQJmsGIPManager(String brokerUrl, IGIPCodec gipCodec) 
    {
        this.brokerUrl = brokerUrl;
        this.gipCodec = gipCodec;
        this.gipPublisherList = new ArrayList<IGIPEventPublisher>();
        this.gipReceiverList = new ArrayList<GIPEventReceiver>();
        this.gipWorker = new GIPEventWorker();
    }
    
    
    public void init() throws CommunicationErrorException
    {
        
        try {
            
            // Create a ConnectionFactory
            this.jmsConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
            
            // Create a Connection
            this.jmsConnection = this.jmsConnectionFactory.createConnection();
            this.jmsConnection.start();
            
            this.amqMonitoring = new DestinationSource(jmsConnection);
            this.amqMonitoring.start();
            
            // Create a Session
            this.jmsSession = this.jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            // Start the GIP WORKER THREAD
            this.gipWorker.start();
            
        } catch (JMSException ex) {
            throw new CommunicationErrorException(ex);
        }
        
    }
    
    
    public ActiveMQConnectionFactory getJmsConnectionFactory()
    {
        return jmsConnectionFactory;
    }

    public Connection getJmsConnection()
    {
        return jmsConnection;
    }

    public Session getJmsSession()
    {
        return jmsSession;
    }
    
    
    public void stop() throws CommunicationErrorException
    {
        
        for(GIPEventReceiver ger : this.gipReceiverList) {
            ger.stopReceiving();
        }
        
        for(IGIPEventPublisher gep : this.gipPublisherList) {
            gep.stopPublishing();
        }
        
        
        try {
            this.jmsSession.close();
            this.amqMonitoring.stop();
            this.jmsConnection.close();
        }
        catch(JMSException ex) {
            throw new CommunicationErrorException(ex);
        }
        
    }
    
    
    private boolean topicExists(String channelId) throws JMSException
    {
     
        boolean result = false;
        for(ActiveMQTopic topic : this.amqMonitoring.getTopics()) {
            if (topic.getTopicName().equals(channelId)){
                result = true;
                break;
            }
        }
        
        return result;
        
    }
    

    @Override
    public IGIPEventPublisher startGIPPublisher(
            String sourceId, String channelId, boolean create) 
            throws CommunicationErrorException
    {
        try {
            
            if (!create && !topicExists(channelId)) {
                //throw new CommunicationErrorException("JMS topic '"+channelId+"' not available.");
                Logging.logger.log(Level.WARNING, "JMS topic '{0}' does not exists already (it will be created now) so the other side of the communication may not be running.", channelId);
            }
            
            JmsPublicationGIPEventPublisher gipPublisher =
                    new JmsPublicationGIPEventPublisher(sourceId, channelId, this.jmsSession, this.gipCodec);
            
            gipPublisher.init();
            
            this.gipPublisherList.add(gipPublisher);
            
            return gipPublisher;
            
        } catch (JMSException ex) {
            throw new CommunicationErrorException(ex);
        }
        
    }

    @Override
    public void stopGIPPublisher(IGIPEventPublisher gipPublisher) 
    {
        
        gipPublisher.stopPublishing();
        this.gipPublisherList.remove(gipPublisher);
        
    }

    
    
    
    @Override
    public GIPEventReceiver startGIPReceiver(
            String channelId, boolean create,
            IInputEventCallback input, IOutputEventCallback output, 
            IActionEventCallback action, ISelectionEventCallback selection, 
            IFocusEventCallback focus)
            throws CommunicationErrorException
    {
        try {
            
            if (!create && !topicExists(channelId)) {
                //throw new CommunicationErrorException("JMS topic '"+channelId+"' not available.");
                Logging.logger.log(Level.WARNING, "JMS topic '{0}' does not exists already (it will be created now) so the other side of the communication may not be running.", channelId);
            }
            
            JmsGIPEventReceiver gipReceiver =
                    new JmsGIPEventReceiver(
                            channelId, jmsSession, gipCodec,
                            input, output, selection, focus, action, gipWorker);
            
            gipReceiver.init();
            
            gipReceiver.startReceiving();
            
            this.gipReceiverList.add(gipReceiver);
            
            return gipReceiver;
            
        } catch (JMSException ex) {
            throw new CommunicationErrorException(ex);
        }
                
    }

    
    @Override
    public void stopGIPReceiver(GIPEventReceiver gipReceiver) 
    {       
        
        gipReceiver.stopReceiving();
        
        this.gipReceiverList.remove(gipReceiver);        
        
    }
    

    
}
