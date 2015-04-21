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
 
 package com.hi3project.dandelion.models.environment;

import com.hi3project.fuzzylogic.FuzzyVariable;


/** Description of the motion/movement characteristics of an environment
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class Motion 
{
    
    private static final String NAME_MOTION = "environment_motion_motion";
    private static final String NAME_VIBRATION = "environment_motion_vibration";
    
    
    private final FuzzyVariable motion;
    private final FuzzyVariable vibration;

    
    
    public Motion(double motion, double vibration)
    {
        this.motion = new FuzzyVariable(NAME_MOTION);
        this.motion.setValue(motion);
        
        this.vibration = new FuzzyVariable(NAME_VIBRATION);
        this.vibration.setValue(vibration);
    }


    
    public void setVibration(double vibration)
    {
        this.vibration.setValue(vibration);
    }
    
    public double getVibration()
    {
        return this.vibration.getValue();
    }

    public void setMotion(double motion)
    {
        this.motion.setValue(motion);
    }
    
    public double getMotion()
    {
        return this.motion.getValue();
    }
    

}
