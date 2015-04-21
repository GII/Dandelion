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
 
 package com.hi3project.dandelion.uib;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uic.IUserInterfaceController;


/** An UI Builder is in charge of managing the associations between AIUs and
 * FIOs.
 * 
 * There can be multiples implementations that for example:
 *  - Read the mappings from a local file an manages it statically
 *  - Manage the mappings dinamically
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      15-abr-2014
 */
public interface IUserInterfaceBuilder
{
    
    
    /** Performs the initialization of the UI builder.
     * 
     * MUST BE CALLED BEFORE ANY OTHER METHOD
     * 
     * @throws InternalErrorException if there was an error while initializing the UI builder
     */
    public void init() throws InternalErrorException;

    
    
    public String getId();
    
    

    /** Builds the UI of an application.
     * Manages the asociations between AIUs and FIOs.
     * This management can be static or dynamic.
     * 
     * This method is expected to be asynchronous
     * 
     * @param appMetadata metadata information of the app
     * @param dandelionUIC the UIC associated to the app
     * @param user profile of the current user of the app (can be null)
     * @param environment profile of the current usage environment of the app (can be null)
     * @param scene profile of the current usage scene of the app (can be null)
     * @throws UnableToBuildUIException 
     */
    public void buildUI(
            ApplicationMetadata appMetadata, 
            IUserInterfaceController dandelionUIC,
            UserProfile user,
            EnvironmentProfile environment,
            SceneProfile scene) throws UnableToBuildUIException;
    
    
    /** Rebuilds the UI for a new user and environment profiles
     * 
     * This method is expected to be asynchronous
     * 
     * @param user profile of the current user of the app (can be null)
     * @param environment profile of the current usage environment of the app (can be null)
     * @param scene profile of the current usage scene of the app (can be null)
     * @throws UnableToBuildUIException 
     */
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws UnableToBuildUIException;
    
}
