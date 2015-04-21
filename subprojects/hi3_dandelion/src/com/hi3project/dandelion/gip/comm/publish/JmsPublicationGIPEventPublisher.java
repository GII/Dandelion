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
 
 package com.hi3project.dandelion.gip.comm.publish;


import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.DataEvent;
import com.hi3project.dandelion.gip.event.Event;
import com.hi3project.dandelion.gip.event.EventType;
import com.hi3project.dandelion.gip.event.SelectionEvent;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.Collection;
import java.util.logging.Level;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


/** Realization of the IGIPEventPublisher interface using JMS
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1

 Changelog:
      21-mar-2013 -- Initial version
 */
public class JmsPublicationGIPEventPublisher implements IGIPEventPublisher
{
      
    private final String sourceId; //the id of this source of GIP events
    
    private final Session mqSession;
    private Destination topic;
    private MessageProducer msgProducer;
    
    private final String topicName; //the same as the id of the client
    
    
    private final IGIPCodec gipCodec;
    
    private boolean active = true;
    


    public JmsPublicationGIPEventPublisher(
            String sourceId, String topicName, 
            Session mqSession, IGIPCodec gipCodec)
    {
        this.sourceId = sourceId;
        this.topicName = topicName;
        this.mqSession = mqSession;
        this.gipCodec = gipCodec;
    }
    
    
    /** Creates the JMS topic and the producer to send message to that topic
     * 
     * @throws JMSException if there is a problem with the JMS session
     */
    public void init() throws JMSException
    {
        this.topic = this.mqSession.createTopic(topicName);
        this.msgProducer = this.mqSession.createProducer(topic);
    }

    
    private void sendEvent(Event event) throws CommunicationErrorException
    {
        
        if (active) { //if the publisher has not been stopped
            
            try {            

                Logging.logger.log(Level.FINER, "Sending message to {0}.", this.topicName);

                TextMessage msg = this.mqSession.createTextMessage(this.gipCodec.codeGIPEvent(event));
                this.msgProducer.send(msg);

            } 
            catch (JMSException ex) {
                throw new CommunicationErrorException(ex);
            } 
            catch (GIPEventCodeDecodeErrorException ex) {
                throw new CommunicationErrorException(ex);
            }
            
        }
        
    }
    
    
    private void sendDataEvent(EventType type, String interactionId, Collection<Property> propertySet, Collection<FuzzyVariable> fuzzyHints) 
            throws CommunicationErrorException
    {
        
        DataEvent de = new DataEvent(this.sourceId, interactionId, type, propertySet);
        de.setFuzzyHints(fuzzyHints);
        
        sendEvent(de);
        
    }
    
    
    @Override
    public void input(String interactionId, Collection<Property> propertySet, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        sendDataEvent(EventType.input, interactionId, propertySet, fuzzyHints);
        
    }


    @Override
    public void output(String interactionId, Collection<Property> propertySet, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        sendDataEvent(EventType.output, interactionId, propertySet, fuzzyHints);
        
    }

    
    @Override
    public void selection(String interactionId, Collection<Property> selectionList, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        SelectionEvent se = new SelectionEvent(this.sourceId, interactionId, selectionList, null);
        se.setFuzzyHints(fuzzyHints);
        
        sendEvent(se);
        
    }


    @Override
    public void selection(String interactionId, Collection<Property> selectionList, Property selectedItem, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        SelectionEvent se = new SelectionEvent(this.sourceId, interactionId, selectionList, selectedItem);
        se.setFuzzyHints(fuzzyHints);
        
        sendEvent(se);
        
    }


    @Override
    public void focus(String interactionId, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        Event fe = new Event(this.sourceId, interactionId, EventType.focus);
        
        sendEvent(fe);
        
    }


    @Override
    public void action(String interactionId, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
        
        Event ae = new Event(this.sourceId, interactionId, EventType.action);
        
        sendEvent(ae);
        
    }

    @Override
    public void stopPublishing() 
    {
       this.active = false;
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
