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
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uim.client.IUserInterfaceManagerClient;


/** A facade to facilitate the creation of Dandelion manager objects
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      18-nov-2014
 */
public interface IDandelionFacade 
{
    
    
    
    /** Builds a default Dandelion UIC using JMS communications
     * 
     * @return a new Dandelion UIC
     * @throws InternalErrorException
     */
    public IUserInterfaceController getUserInterfaceController() throws InternalErrorException;
    
    
    
    
    
    
    /** Builds a local User Interface Builder associated
     * to the specified uic (or reuses a previous one)
     * 
     * @param uiMappingFilePath
     * @return a new Dandelion UIB
     * @throws InternalErrorException 
     */
    public IUserInterfaceBuilderClient getUserInterfaceBuilderClient(
            String uiMappingFilePath, String uibID) throws InternalErrorException;      
    
    
    /** Builds a remote User Interface Builder associated to the specified ID
     * 
     * @param jmsBrokerUri optional: only required if the UIB is a different JMS server
     * @return a new Dandelion UIB
     * @throws InternalErrorException 
     */
    public IUserInterfaceBuilderClient getRemoteUserInterfaceBuilderClient(
            int brokerPort, String brokerHost, String brokerUser, String brokerPass, String uibID) throws InternalErrorException;
    

}
