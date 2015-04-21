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


/** Description of the light characteristics of an environment
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class Lighting
{
    
    private static final String NAME_INTENSITY = "environment_lighting_intensity";
    
    
    private final FuzzyVariable intensity;
    private LightingType type;

    
    public Lighting(double intensity, LightingType type)
    {
        this.intensity = new FuzzyVariable(NAME_INTENSITY);
        this.intensity.setValue(intensity);
        this.type = type;
    }

    
    public double getIntensity()
    {
        return intensity.getValue();
    }

    public void setIntensity(double intensity)
    {
        this.intensity.setValue(intensity);
    }

    
    public void setType(LightingType type)
    {
        this.type = type;
    }
    
    public LightingType getType()
    {
        return type;
    }
    

}
