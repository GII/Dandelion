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
 
 package com.hi3project.dandelion.uib.test;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.client.remote.StompRemoteUserInterfaceBuilderClient;
import java.util.logging.Level;
import java.util.logging.Logger;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      20-mar-2015
 */
public class RemoteUIBClientTest 
{
    
    public static void main(String[] args)
    {
        
        try {
            
            StompRemoteUserInterfaceBuilderClient uib = new StompRemoteUserInterfaceBuilderClient(61613, "localhost", "", "", "music-player");
            uib.init();
            
                        
            uib.rebuildUI(new UserProfile(), new EnvironmentProfile(), new SceneProfile());
            
            
        } catch (InternalErrorException ex) {
            Logger.getLogger(RemoteUIBClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
