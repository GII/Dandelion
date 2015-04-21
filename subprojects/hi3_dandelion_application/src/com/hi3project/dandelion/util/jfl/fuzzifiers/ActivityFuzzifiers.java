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
 
 package com.hi3project.dandelion.util.jfl.fuzzifiers;

import com.hi3project.dandelion.models.scene.activity.ActivityMode;
import com.hi3project.dandelion.models.scene.activity.ActivityStyle;
import com.hi3project.dandelion.models.scene.activity.ActivityType;


/** A simple tranformation to fuzzy values bearing in mind the definitions
 * in fio_physical_specs_builder.fcl
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-ago-2014
 */
public class ActivityFuzzifiers 
{
    
    public static double fuzzyActivityMode(ActivityMode amode)
    {
        
        double result = 0;
        
        switch(amode) {
            case on_the_go:
                result = 1.5;
                break;
            case stationary:
                result = 8.5;
                break;                
        }
        
        return result;
        
    }
    
    
    public static double fuzzyActivityStyle(ActivityStyle astyle)
    {
        
        double result = 0;
        
        switch(astyle) {
            case individual:
                result = 1.5;
                break;
            case social:
                result = 8.5;
                break;                
        }
        
        return result;
        
    }
    
    
    public static double fuzzyActivityType(ActivityType atype)
    {
        
        double result = 0;
        
        switch(atype) {
            case work:
                result = 0.5;
                break;
            case daily:
                result = 3.5;
                break;
            case fitness:
                result = 6.5;
                break;
            case leisure:
                result = 9.5;
                break;
        }
        
        return result;
        
    }

}
