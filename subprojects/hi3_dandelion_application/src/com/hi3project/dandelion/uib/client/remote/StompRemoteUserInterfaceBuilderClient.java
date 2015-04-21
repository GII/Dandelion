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
 
 package com.hi3project.dandelion.uib.client.remote;

import com.google.gson.Gson;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.local.LocalFileFIORepoDandelionUIBuilder;
import com.hi3project.dandelion.uib.remote.ChangeContextRequest;
import com.hi3project.dandelion.uib.util.profiles.InputProfiles;
import com.hi3project.dandelion.uib.util.profiles.ProfileUtils;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.util.log.Logging;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import net.ser1.stomp.Client;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-mar-2015
 */
public class StompRemoteUserInterfaceBuilderClient implements IUserInterfaceBuilderClient
{

    private final int brokerPort;
    private final String brokerHost, brokerUser, brokerPass;
    private final String uibID;

    private Client stompClient;
    
    private final Gson gson;
    
    
    public StompRemoteUserInterfaceBuilderClient(
            int brokerPort, String brokerHost, String brokerUser, String brokerPass, String uibID)
    {
        this.brokerPort = brokerPort;
        this.brokerHost = brokerHost;
        this.brokerUser = brokerUser;
        this.brokerPass = brokerPass;
        this.uibID = uibID;
        this.gson = new Gson();
    }
    
    
    public String getUIBTopic()
    {
        return "/topic/"+LocalFileFIORepoDandelionUIBuilder.BUILDER_ID+this.uibID;
    }
    
    public void init() throws InternalErrorException
    {
        
        try {    
            
            this.stompClient = new Client(brokerUser, brokerPort, brokerPass, brokerUser);
            
            Logging.logger.info("UIB client connected to remote broker.");
            
        } catch (IOException ex) {
            throw new InternalErrorException(ex);
        } catch (LoginException ex) {
            throw new InternalErrorException(ex);
        }
        
    }
    
    
    
    
    @Override
    public void buildUI(ApplicationMetadata appMetadata, IUserInterfaceController dandelionUIC, UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        throw new UnsupportedOperationException("Not supported in remote UIBs."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        
        InputProfiles profiles = ProfileUtils.createInputProfiles(user, environment, scene);
        
        ChangeContextRequest ccr = new ChangeContextRequest(profiles);
        
        String msg = this.gson.toJson(ccr);
        
        this.stompClient.send(getUIBTopic(), msg);
        
    }

}
