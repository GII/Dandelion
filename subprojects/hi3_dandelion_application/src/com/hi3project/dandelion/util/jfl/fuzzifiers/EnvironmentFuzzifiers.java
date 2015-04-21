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

import com.hi3project.dandelion.models.environment.EnvironmentSituation;
import com.hi3project.dandelion.models.environment.EnvironmentType;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-ago-2014
 */
public class EnvironmentFuzzifiers 
{
    
    public static double fuzzyEnvironmentType(EnvironmentType etype)
    {
        
        double result = 0;
        
        switch(etype) {
            case mobile:
                result = 3.75;
                break;
            case stationary:
                result = 8;
                break;                
        }
        
        return result;
        
    }
    
    
    public static double fuzzyEnvironmentSituation(EnvironmentSituation esituation)
    {
        
        double result = 0;
        
        switch(esituation) {
            case indoor:
                result = 3.75;
                break;
            case outdoor:
                result = 8;
                break;                
        }
        
        return result;
        
    }

}
