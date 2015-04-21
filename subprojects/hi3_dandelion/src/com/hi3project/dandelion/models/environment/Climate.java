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


/** Description of the climate characteristics of an environment
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class Climate 
{
    
    private static final String NAME_TEMPERATURE = "environment_climate_temperature";
    private static final String NAME_HUMIDITY = "environment_climate_humidity";
    private static final String NAME_WIND = "environment_climate_wind";
    
    
    private final FuzzyVariable temperature;
    private final FuzzyVariable humidity;
    private final FuzzyVariable wind;

    
    
    public Climate(double temperature, double humidity, double wind)
    {
        this.temperature = new FuzzyVariable(NAME_TEMPERATURE);
        this.temperature.setValue(temperature);
        
        this.humidity = new FuzzyVariable(NAME_HUMIDITY);
        this.humidity.setValue(humidity);
        
        this.wind = new FuzzyVariable(NAME_WIND);
        this.wind.setValue(wind);
    }


    
    public void setHumidity(double humidity)
    {
        this.humidity.setValue(humidity);
    }
    
    public double getHumidity()
    {
        return this.humidity.getValue();
    }

    public void setTemperature(double temperature)
    {
        this.temperature.setValue(temperature);
    }
    
    public double getTemperature()
    {
        return this.temperature.getValue();
    }
    
    public void setWind(double wind)
    {
        this.wind.setValue(wind);
    }
    
    public double getWind()
    {
        return this.wind.getValue();
    }

}
