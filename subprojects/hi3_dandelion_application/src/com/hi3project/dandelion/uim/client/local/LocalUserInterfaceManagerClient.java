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
 
 package com.hi3project.dandelion.uim.client.local;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.application.history.EnvironmentHistoryEvent;
import com.hi3project.dandelion.application.history.SceneHistoryEvent;
import com.hi3project.dandelion.application.history.UserHistoryEvent;
import com.hi3project.dandelion.uim.IUserInterfaceManager;
import com.hi3project.dandelion.uim.client.AbstractUserInterfaceManagerClient;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uic.IUserInterfaceControllerPublicAPI;
import java.util.Collection;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public class LocalUserInterfaceManagerClient extends AbstractUserInterfaceManagerClient
{

    private final IUserInterfaceManager appManager;

    public LocalUserInterfaceManagerClient(
            IUserInterfaceManager appManager, 
            int applicationId, ApplicationMetadata appMetadata)
    {
        super(applicationId, appMetadata);
        this.appManager = appManager;
    }


    @Override
    public void stopManagingApplication()
    {
        this.appManager.stopManagingApplication(getApplicationId());
    }

    @Override
    public Collection<UserProfile> listAvailableUsers()
    {
        return this.appManager.listUsers();
    }

    @Override
    public Collection<EnvironmentProfile> listAvailableEnvironments()
    {
        return this.appManager.listEnvironments();
    }
    
    @Override
    public Collection<SceneProfile> listAvailableScenes()
    {
        return this.appManager.listScenes();
    }
    

    @Override
    public Collection<UserHistoryEvent> getUserHistory()
    {
        return this.appManager.getUserHistory(getApplicationId());
    }

    @Override
    public Collection<EnvironmentHistoryEvent> getEnvironmentHistory()
    {
        return this.appManager.getEnvironmentHistory(getApplicationId());
    }

    @Override
    public Collection<SceneHistoryEvent> getSceneHistoryEvent()
    {
        return this.appManager.getSceneHistoryEvent(getApplicationId());
    }

    @Override
    public void changeContex(UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws InternalErrorException
    {
        this.appManager.changeContex(getApplicationId(), user, environment, scene);
    }        

    @Override
    public void changeContex(String user, String environment, String scene) throws InternalErrorException
    {
        this.appManager.changeContex(getApplicationId(), user, environment, scene);
    }

    
    @Override
    public IUserInterfaceControllerPublicAPI getUserInterfaceController() throws InternalErrorException
    {
        return this.appManager.getApplicationUIC(getApplicationId());
    }
    
    
    
}
