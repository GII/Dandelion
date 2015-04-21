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

/** Complete descrition of the characteristics an environment
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class EnvironmentProfile
{
    
    private final String name;
    private String location;
    
    
    private final Noise noise;
    private final Climate climate;
    private final Visibility visibility;
    private final Motion motion;
    private final Space space;
    private final EnvironmentType type;
    private final EnvironmentSituation situation;

    
    
    
    public EnvironmentProfile()
    {
        this.name = "default";
        this.location = "default";
        this.noise = new Noise(0);
        this.climate = new Climate(20, 50, 0);
        this.visibility = new Visibility(new Lighting(10, LightingType.WARMLIGHT), 10, 3);
        this.motion = new Motion(0, 0);
        this.space = new Space(5);
        this.type = EnvironmentType.stationary;
        this.situation = EnvironmentSituation.indoor;
    }

    
    
    public EnvironmentProfile(
            String name, String location, 
            Noise noise, Climate climate, 
            Visibility visibility, Motion motion, 
            Space space, EnvironmentType type, EnvironmentSituation situation)
    {
        this.name = name;
        this.location = location;
        this.noise = noise;
        this.climate = climate;
        this.visibility = visibility;
        this.motion = motion;
        this.space = space;
        this.type = type;
        this.situation = situation;
    }

    
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public Noise getNoise()
    {
        return noise;
    }

    public Climate getClimate()
    {
        return climate;
    }

    public Visibility getVisibility()
    {
        return visibility;
    }

    public Motion getMotion()
    {
        return motion;
    }

    public Space getSpace()
    {
        return space;
    }

    public EnvironmentType getType()
    {
        return type;
    }    
    
    public EnvironmentSituation getSituation()
    {
        return situation;
    }

}
