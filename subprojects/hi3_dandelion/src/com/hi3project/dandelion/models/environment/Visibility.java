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


/** Description of the visibility characteristics of an environment
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class Visibility 
{
    
    private static final String NAME_VISILIBTY = "environment_visiblity_visiblity";
    private static final String NAME_CONTRAST = "environment_visibility_contrast";
    
    
    private Lighting lighting;
    private final FuzzyVariable visibility;
    private final FuzzyVariable contrast;

    
    
    public Visibility(Lighting lighting, double visibility, double contrast)
    {
        
        this.lighting = lighting;
        
        this.visibility = new FuzzyVariable(NAME_VISILIBTY);
        this.visibility.setValue(visibility);
        
        this.contrast = new FuzzyVariable(NAME_CONTRAST);
        this.contrast.setValue(contrast);
    }

    public Lighting getLighting()
    {
        return lighting;
    }

    public void setLighting(Lighting lighting)
    {
        this.lighting = lighting;
    }

    public void setVisilibity(double visibility)
    {
        this.visibility.setValue(visibility);
    }
    
    public double getVisilibity()
    {
        return this.visibility.getValue();
    }

    public void setContrast(double contrast)
    {
        this.contrast.setValue(contrast);
    }
    
    public double getContrast()
    {
        return this.contrast.getValue();
    }
    

}
