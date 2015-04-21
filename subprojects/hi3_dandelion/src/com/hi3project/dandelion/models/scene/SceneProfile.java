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
 
 package com.hi3project.dandelion.models.scene;

import com.hi3project.dandelion.models.scene.activity.AbilityRequisites;
import com.hi3project.dandelion.models.scene.activity.Activity;
import com.hi3project.dandelion.models.scene.activity.ActivityMode;
import com.hi3project.dandelion.models.scene.activity.ActivityStyle;
import com.hi3project.dandelion.models.scene.activity.ActivityType;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      05-ago-2014
 */
public class SceneProfile 
{
    
    private final Activity activity;
    
    private final int userCount;

    
    public SceneProfile()
    {
        this.userCount = 1;
        this.activity = new Activity(
                "default", 
                ActivityStyle.individual, 
                ActivityType.leisure, 
                ActivityMode.stationary, 
                new AbilityRequisites(0, 0, 0));
    }

        
    public SceneProfile(Activity activity, int userCount)
    {
        this.activity = activity;
        this.userCount = userCount;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public int getUserCount()
    {
        return userCount;
    }
       

}
