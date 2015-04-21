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
 
 package com.hi3project.dandelion.uib.remote.jms;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.modelaction.action.ModelAction;
import com.mytechia.commons.framework.modelaction.action.async.AsyncActionPool;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.IUserInterfaceBuilder;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.remote.ChangeContextRequest;
import com.hi3project.dandelion.uib.util.profiles.ProfileUtils;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
import java.util.logging.Level;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;


/** Description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-mar-2015
 */
public class JmsUserInterfaceBuilder implements IUserInterfaceBuilder, MessageListener
{
    
    private final Session mqSession;
    private Destination topic;
    private MessageConsumer msgConsumer;
    
    private AsyncActionPool actionPool;
    
    private final IUserInterfaceBuilder localUIB;
    
    
    private ApplicationMetadata appMetadata;
    private IUserInterfaceController uic;
    
    private final Gson gson;

    
    public JmsUserInterfaceBuilder(Session jmsSession, 
                                   IUserInterfaceBuilder localUIB)
    {
        this.mqSession = jmsSession;
        this.localUIB = localUIB;
        this.gson = new Gson();
    }
    
    
    @Override
    public void init() throws InternalErrorException
    {
        
        if (this.actionPool == null) {

            try {
                
                this.localUIB.init();
                
                this.actionPool = new AsyncActionPool(1);
                
                String uibTopic = localUIB.getId();
                Logging.logger.log(Level.INFO, "Starting User Interface Builder on topic: {0}", uibTopic);
                this.topic = this.mqSession.createTopic(uibTopic);
                this.msgConsumer = this.mqSession.createConsumer(this.topic);
                this.msgConsumer.setMessageListener(this);
                
            } catch (JMSException ex) {
                throw new InternalErrorException(ex);
            }
            
        }
        
    }

    @Override
    public String getId()
    {
        return this.localUIB.getId();
    }

    @Override
    public void buildUI(ApplicationMetadata appMetadata, IUserInterfaceController dandelionUIC, UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        this.appMetadata = appMetadata;
        this.uic = dandelionUIC;
        this.localUIB.buildUI(appMetadata, dandelionUIC, user, environment, scene);
    }

    @Override
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        
        if (this.appMetadata == null || this.uic == null) {
            throw new UnableToBuildUIException("Unable to rebuild without a first call to build().");
        }
        
        this.localUIB.buildUI(this.appMetadata, this.uic, user, environment, scene);
        
    }

    
    
    @Override
    public void onMessage(Message msg)
    {
        
        try {
            
            TextMessage txtMsg = (TextMessage) msg;
            
            ChangeContextRequest request = 
                    this.gson.fromJson(txtMsg.getText(), ChangeContextRequest.class);
            
            
            this.actionPool.addAction(new ProcessMessageAction(request), null);
            
            
        } catch (JMSException ex) {
            Logging.logger.warning("Error receiving a context-change request: "+ex.getLocalizedMessage());
        } catch (JsonSyntaxException ex) {
            Logging.logger.warning("Error receiving a context-change request: "+ex.getLocalizedMessage());
        }
        
    }
    
    
    
    private class ProcessMessageAction extends ModelAction
    {

        private final ChangeContextRequest request;

        
        public ProcessMessageAction(ChangeContextRequest msg)
        {
            this.request = msg;
        }

        
        @Override
        public Object execute() throws Exception
        {
            processMessage(request);
            return null;
        }

        
        private void processMessage(ChangeContextRequest msg) throws MessageCodeDecodeErrorException, JMSException   
        {

            UserProfile user = ProfileUtils.createUserProfile(request.getContextProfiles());
            EnvironmentProfile environment = ProfileUtils.createEnvironmentProfile(request.getContextProfiles());
            SceneProfile sceneProfile = ProfileUtils.createSceneProfile(request.getContextProfiles());
            
            try {
                
                rebuildUI(user, environment, sceneProfile);
                
            } catch (UnableToBuildUIException ex) {
                Logging.logger.warning("Error while rebuilding the UI: "+ex.getLocalizedMessage());
            }

        }

    }
    

}
