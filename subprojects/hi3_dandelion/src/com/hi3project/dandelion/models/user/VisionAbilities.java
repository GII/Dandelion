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


/** Description of the visual aptitudes of a user
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class VisionAbilities 
{
    
    private static final String NAME_VISUAL_ACUITY = "user_visual_acuity";
    private static final String NAME_VISION_FIELD = "user_vision_field";
    
    
    private final FuzzyVariable visualAcuity;
    private final FuzzyVariable visionField;
    private ColourPerception colourPerception;
    
    
    public VisionAbilities(double visualAcuity, double visioField, ColourPerception colorPerception)
    {
        this.visualAcuity = new FuzzyVariable(NAME_VISUAL_ACUITY);
        this.visualAcuity.setValue(visualAcuity);
        
        this.visionField = new FuzzyVariable(NAME_VISION_FIELD);
        this.visionField.setValue(visioField);        
        
        this.colourPerception = colorPerception;
    }
    
    
    public void setColourPerception(ColourPerception colourPerception)
    {
        this.colourPerception = colourPerception;        
    }
    
    public ColourPerception getColourPerception()
    {
        return this.colourPerception;
    }
    
    
    
    public void setVisualAcuity(double visualAcuity)
    {
        this.visualAcuity.setValue(visualAcuity);
    }
    
    public double getVisualAcuity()
    {
        return this.visualAcuity.getValue();
    }
    
    
    public void setVisionField(double visionField)
    {
        this.visionField.setValue(visionField);
    }
    
    public double getVisionField()
    {
        return this.visionField.getValue();
    }
    

}
