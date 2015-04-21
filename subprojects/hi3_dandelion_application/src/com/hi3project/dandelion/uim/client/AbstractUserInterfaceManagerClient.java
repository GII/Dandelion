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
 
 package com.hi3project.dandelion.uim.client;

import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uic.IUserInterfaceController;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public abstract class AbstractUserInterfaceManagerClient implements IUserInterfaceManagerClient
{
    
    private final int applicationId;
    
    private final ApplicationMetadata appMetadata;
    
    
    public AbstractUserInterfaceManagerClient(
            int applicationId, ApplicationMetadata appMetadata)
    {
        this.applicationId = applicationId;
        this.appMetadata = appMetadata;
    }

    public int getApplicationId()
    {
        return applicationId;
    }

    public ApplicationMetadata getApplicationMetadata()
    {
        return appMetadata;
    }
    

}