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
import com.hi3project.dandelion.application.Application;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.application.history.EnvironmentHistoryEvent;
import com.hi3project.dandelion.application.history.HistoryEvent;
import com.hi3project.dandelion.application.history.HistoryEventType;
import com.hi3project.dandelion.application.history.SceneHistoryEvent;
import com.hi3project.dandelion.application.history.UserHistoryEvent;
import com.hi3project.dandelion.uim.exception.ErrorChangingUIContextException;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.IUserInterfaceControllerPublicAPI;
import com.hi3project.dandelion.util.log.Logging;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public class DefaultUserInterfaceManager implements IUserInterfaceManager
{
    
    
    private HashMap<String, UserProfile> userList;
    private HashMap<String, EnvironmentProfile> environmentList;
    private HashMap<String, SceneProfile> sceneList;
    
    private HashMap<Integer, Application> applicationList;

    
    private int currentId = 0;
    
    /**
     *
     * @param appMetadata
     * @param uic
     * @param uib
     * @param user
     * @param environment
     * @param scene
     * @return
     * @throws InternalErrorException If there was a problem while building the UI
     */
    @Override
    public int startManagingApplication(ApplicationMetadata appMetadata, 
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib,
                                        UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws InternalErrorException
    {
        
        if (user == null || environment == null || scene == null) {
            throw new UnableToBuildUIException("One or more of the context profiles (user, environment or scene) are null.");
        }
        
        Application app = new Application(this.currentId, appMetadata, uic, uib);
        this.currentId++;
        
        this.applicationList.put(app.getId(), app);
        
        uib.buildUI(appMetadata, uic, user, environment, scene);
        
        return app.getId();
        
    }

    @Override
    public int startManagingApplication(ApplicationMetadata appMetadata, 
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib,
                                        String userName, String environmentName, String sceneName) 
            throws InternalErrorException
    {
        
        UserProfile user = this.userList.get(userName);
        EnvironmentProfile environment = this.environmentList.get(environmentName);
        SceneProfile scene = this.sceneList.get(sceneName);
        
        return startManagingApplication(appMetadata, uic, uib, user, environment, scene);
        
    }
    
    
    @Override
    public int startManagingApplication(ApplicationMetadata appMetadata,
                                        IUserInterfaceController uic,
                                        IUserInterfaceBuilderClient uib) 
            throws InternalErrorException
    {
        
        UserProfile user = new UserProfile();
        EnvironmentProfile environment = new EnvironmentProfile();
        SceneProfile scene = new SceneProfile();
        
        return startManagingApplication(appMetadata, uic, uib, user, environment, scene);
        
    }
    

    @Override
    public void stopManagingApplication(int appId)
    {
        this.applicationList.remove(appId);
    }

    @Override
    public Collection<ApplicationMetadata> listApplications()
    {
        ArrayList<ApplicationMetadata> result = new ArrayList<ApplicationMetadata>(this.applicationList.size());
        for(Application app : this.applicationList.values()) {
            result.add(app.getMetadata());
        }
        return result;
    }

    @Override
    public Collection<UserProfile> listUsers()
    {
        return new ArrayList<UserProfile>(this.userList.values());
    }

    @Override
    public Collection<EnvironmentProfile> listEnvironments()
    {
        return new ArrayList<EnvironmentProfile>(this.environmentList.values());
    }
    
    public Collection<SceneProfile> listScenes()
    {
        return new ArrayList<SceneProfile>(this.sceneList.values());
    }
    

    @Override
    public Collection<UserHistoryEvent> getUserHistory(int appId)
    {
        Application app = this.applicationList.get(appId);
        ArrayList<UserHistoryEvent> result = new ArrayList<UserHistoryEvent>();
        for(HistoryEvent he : app.getHistory()) {
            if (he.getType() == HistoryEventType.user) {
                try {
                    result.add((UserHistoryEvent) he);
                }
                catch(ClassCastException cce) {
                    //ignore the event
                    Logging.logger.log(Level.FINE, "Error processing User History Events", cce);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<EnvironmentHistoryEvent> getEnvironmentHistory(int appId)
    {
        Application app = this.applicationList.get(appId);
        ArrayList<EnvironmentHistoryEvent> result = new ArrayList<EnvironmentHistoryEvent>();
        for(HistoryEvent he : app.getHistory()) {
            if (he.getType() == HistoryEventType.environment) {
                try {
                    result.add((EnvironmentHistoryEvent) he);
                }
                catch(ClassCastException cce) {
                    //ignore the event
                    Logging.logger.log(Level.FINE, "Error processing Environment History Events", cce);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<SceneHistoryEvent> getSceneHistoryEvent(int appId)
    {
        Application app = this.applicationList.get(appId);
        ArrayList<SceneHistoryEvent> result = new ArrayList<SceneHistoryEvent>();
        for(HistoryEvent he : app.getHistory()) {
            if (he.getType() == HistoryEventType.scene) {
                try {
                    result.add((SceneHistoryEvent) he);
                }
                catch(ClassCastException cce) {
                    //ignore the event
                    Logging.logger.log(Level.FINE, "Error processing Scene History Events", cce);
                }
            }
        }
        return result;
    }


    @Override
    public void changeContex(int appId, UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws ErrorChangingUIContextException
    {
        
        Application app = this.applicationList.get(appId);
        
        if (app != null) {
            try {
                app.getUserInterfaceBuilder().rebuildUI(user, environment, scene);
            } catch (UnableToBuildUIException ex) {
                 throw new ErrorChangingUIContextException(ex);
            }
        }
        else {
            throw new ErrorChangingUIContextException("The application with id "+appId+" is not longer managed by this manager.");
        }
        
    }
    
    
    @Override
    public void changeContex(int appId, String userName, String environmentName, String sceneName)
            throws ErrorChangingUIContextException
    {
        
        Application app = this.applicationList.get(appId);
        
        if (app != null) {
            try {

                UserProfile user = this.userList.get(userName);
                EnvironmentProfile environment = this.environmentList.get(environmentName);
                SceneProfile scene = this.sceneList.get(sceneName);

                app.getUserInterfaceBuilder().rebuildUI(user, environment, scene);

            } catch (UnableToBuildUIException ex) {
                 throw new ErrorChangingUIContextException(ex);
            }
        }
        else {
            throw new ErrorChangingUIContextException("The application with id "+appId+" is not longer managed by this manager.");
        }
        
    }

    @Override
    public IUserInterfaceControllerPublicAPI getApplicationUIC(int appId)
    {
        Application app = this.applicationList.get(appId);
        if (app != null) {
            return app.getUserInterfaceController();
        }
        else {
            return null;
        }
    }

    @Override
    public IUserInterfaceBuilderClient getApplicationUIB(int appId)
    {
        Application app = this.applicationList.get(appId);
        if (app != null) {
            return app.getUserInterfaceBuilder();
        }
        else {
            return null;
        }
    }

    
   
    

}
