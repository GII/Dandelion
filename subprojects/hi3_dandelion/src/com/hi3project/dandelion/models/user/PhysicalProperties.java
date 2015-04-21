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
 
 package com.hi3project.dandelion.models.user;

import com.hi3project.fuzzylogic.FuzzyVariable;


/** Physical description of a user
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class PhysicalProperties 
{
    
    private static final String NAME_HEIGHT = "user_height";
    private static final String NAME_WEIGHT = "user_weight";
    
    
    private final FuzzyVariable height;
    private final FuzzyVariable weight;
    
    
    public PhysicalProperties(double height, double weight)
    {
        this.height = new FuzzyVariable(NAME_HEIGHT);
        this.height.setValue(height);
        
        this.weight = new FuzzyVariable(NAME_WEIGHT);
        this.weight.setValue(weight);        
    }
    
    
    
    public void setHeight(double height)
    {        
        this.height.setValue(height);
    }
    
    public double getHeight()
    {
        return this.height.getValue();
    }
    
    
    public void setWeight(double weight)
    {
        this.weight.setValue(weight);
    }
    
    public double getWeight()
    {
        return this.weight.getValue();
    }
    

}
