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
 
 package com.hi3project.dandelion.uim;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.application.history.EnvironmentHistoryEvent;
import com.hi3project.dandelion.application.history.SceneHistoryEvent;
import com.hi3project.dandelion.application.history.UserHistoryEvent;
import com.hi3project.dandelion.uim.exception.ErrorChangingUIContextException;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.IUserInterfaceControllerPublicAPI;
import com.hi3project.location.Location;
import java.util.Collection;


/** Internal interface of the Dandelion Application Manager
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public interface IUserInterfaceManager 
{
    
    
    public int startManagingApplication(ApplicationMetadata appMetadata, 
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib,
                                        UserProfile user, 
                                        EnvironmentProfile environment, 
                                        SceneProfile scene)
            throws InternalErrorException;
    
    public int startManagingApplication(ApplicationMetadata appMetadata, 
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib,
                                        String userName, 
                                        String environmentName, 
                                        String sceneName)
            throws InternalErrorException;
    
    public int startManagingApplication(ApplicationMetadata appMetadata,
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib)
            throws InternalErrorException;
    
    
    public void stopManagingApplication(int appId);
    
    
    
    
    public Collection<ApplicationMetadata> listApplications();
    
    public Collection<UserProfile> listUsers();
    
    public Collection<EnvironmentProfile> listEnvironments();
    
    public Collection<SceneProfile> listScenes();
    
    
    
    
    
    public Collection<UserHistoryEvent> getUserHistory(int appId);
    
    public Collection<EnvironmentHistoryEvent> getEnvironmentHistory(int appId);
    
    public Collection<SceneHistoryEvent> getSceneHistoryEvent(int appId);
    
    
    
    
    
    public void changeContex(int appId, UserProfile user, EnvironmentProfile environment, SceneProfile scene) 
            throws ErrorChangingUIContextException;
    
    
    public void changeContex(int appId, String userName, String environmentName, String sceneName) 
            throws ErrorChangingUIContextException;
    
    
    public IUserInterfaceControllerPublicAPI getApplicationUIC(int appId);
    
    public IUserInterfaceBuilderClient getApplicationUIB(int appId);
    

}
