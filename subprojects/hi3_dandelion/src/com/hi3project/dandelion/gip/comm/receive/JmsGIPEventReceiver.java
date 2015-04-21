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

import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.worker.GIPEventWorker;
import com.hi3project.dandelion.util.log.Logging;
import java.util.logging.Level;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;


/** Receives GIP events using JMS.
 * Each FIO has one JMS topic where it publishes/receives events.
 * Each application controller is suscribed to the topics of the FIOs associated 
 * to the application AUI elements.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-nov-2013 -- Initial version
 */
public class JmsGIPEventReceiver extends GIPEventReceiver implements MessageListener
{

    
    private final String topicName;
    private Session mqSession;
    private Destination topic;
    private MessageConsumer msgConsumer;
    
    private final IGIPCodec gipCodec;


    public JmsGIPEventReceiver(
            String topic, Session mqSession, IGIPCodec gipCodec,
            IInputEventCallback inputCallback, IOutputEventCallback outputCallback, 
            ISelectionEventCallback selectionCallback, IFocusEventCallback focusCallback, 
            IActionEventCallback actionCallback,
            GIPEventWorker gipWorker)
    {
        super(inputCallback, outputCallback, selectionCallback, focusCallback, actionCallback, gipWorker);
        this.topicName = topic;
        this.gipCodec = gipCodec;
        this.mqSession = mqSession;
    }

    
    /** Creates the JMS topic and consumer to receive messages
     * 
     * @throws JMSException if there is a problem with the JMS session
     */
    public void init() throws JMSException
    {
        this.topic = this.mqSession.createTopic(topicName);
        this.msgConsumer = this.mqSession.createConsumer(this.topic);
        this.msgConsumer.setMessageListener(this);
    }
    
    
    @Override
    public void onMessage(Message msg)
    {
        
        Logging.logger.log(Level.FINER, "Message received on topic '{0}'.", this.topicName);
       
        try {
            
            TextMessage txtMsg = (TextMessage) msg;
            
            Event gipEvent = this.gipCodec.decodeGIPEvent(txtMsg.getText());
            
            super.eventReceived(gipEvent);
            
        }
        catch(ClassCastException ex) {
            Logging.logger.log(Level.WARNING, "Incompatible event format received on topic '"+this.topicName+"'.");
        } 
        catch (GIPEventCodeDecodeErrorException ex) {
            Logging.logger.log(Level.WARNING, "Error while decoding gip event on topic '"+this.topicName+"': "+ex.getLocalizedMessage());
        } 
        catch (JMSException ex) {
            Logging.logger.log(Level.WARNING, "Communication error while receiving event on topic '"+this.topicName+"': "+ex.getLocalizedMessage());
        }    
        catch (Exception ex) {
            Logging.logger.log(Level.SEVERE, "Error while processing event on topic '"+this.topicName+"': ", ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        
    }

    @Override
    public void stopReceiving() 
    {
        
        super.stopReceiving();
        
        try {
            this.msgConsumer.close();
        } catch (JMSException ex) {
            Logging.logger.warning("Unable to close consumer for GIP events for topic '"+this.topicName+"'.");
        }
        
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        else {
            return this == obj;
        }
    }
    
    
}
