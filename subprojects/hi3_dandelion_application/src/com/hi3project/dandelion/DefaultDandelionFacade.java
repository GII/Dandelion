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
 
 package com.hi3project.dandelion;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.gip.AMQJmsGIPManager;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.uib.IUserInterfaceBuilder;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uib.client.local.LocalUserInterfaceBuilderClient;
import com.hi3project.dandelion.uib.client.remote.StompRemoteUserInterfaceBuilderClient;
import com.hi3project.dandelion.uib.local.LocalFileFIORepoDandelionUIBuilder;
import com.hi3project.dandelion.uib.remote.jms.JmsUserInterfaceBuilder;
import com.hi3project.dandelion.uic.DandelionUserInterfaceController;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import javax.jms.Session;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      18-nov-2014
 */
public class DefaultDandelionFacade implements IDandelionFacade
{

    private final ApplicationMetadata app;
    private final String jmsBrokerUri;
    
    private IUserInterfaceController uic;
    private IUserInterfaceBuilderClient uib;
    
    private AMQJmsGIPManager gipManager;
    private Session jmsSession;
    
    
    public DefaultDandelionFacade(
            ApplicationMetadata app,
            String jmsBrokerUri)
    {
        
        this.app = app;
        this.jmsBrokerUri = jmsBrokerUri;
        
    }            
    
    
    
    @Override
    public IUserInterfaceController getUserInterfaceController()
            throws InternalErrorException
    {
        
        IGIPCodec jsonCodec = new JSONGIPCodec();
        this.gipManager = new AMQJmsGIPManager(jmsBrokerUri, jsonCodec);
            
        gipManager.init();
            
        
        this.uic = new DandelionUserInterfaceController(app, gipManager);        
        
        this.jmsSession = gipManager.getJmsSession();
        
        return this.uic;
        
    }

    
    @Override
    public IUserInterfaceBuilderClient getUserInterfaceBuilderClient(
            String uiMappingFilePath, String uibID) 
            throws InternalErrorException
    {
        
        IUserInterfaceBuilder uibLocal = new LocalFileFIORepoDandelionUIBuilder(
                uibID, uiMappingFilePath, jmsSession, true);
        uibLocal.init();
        
        IUserInterfaceBuilder uibRemote = new JmsUserInterfaceBuilder(jmsSession, uibLocal);
        uibRemote.init();
        
        this.uib = new LocalUserInterfaceBuilderClient(uibRemote);
                
        return this.uib;
        
    }
    
    
    @Override
    public IUserInterfaceBuilderClient getRemoteUserInterfaceBuilderClient(
            int brokerPort, String brokerHost, String brokerUser, String brokerPass, String uibID) 
            throws InternalErrorException
    {
        
        StompRemoteUserInterfaceBuilderClient uibClient = 
                new StompRemoteUserInterfaceBuilderClient(brokerPort, brokerHost, brokerUser, brokerPass, uibID);
        
        uibClient.init();
        
        return uibClient;
        
    }


}
