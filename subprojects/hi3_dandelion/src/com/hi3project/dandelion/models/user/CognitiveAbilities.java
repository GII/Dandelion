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


/** Description of the cognitive abilities of a user
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class CognitiveAbilities 
{
    
    private static final String NAME_LANG_RECEPTION = "cognitive_lang_reception";
    private static final String NAME_LANG_PRODUCTION = "cognitive_lang_production";
    private static final String NAME_ABSRTACT_SIGNS = "cognitive_abstract_signs";
    private static final String NAME_ATTETION = "cognitive_attention";
    private static final String NAME_PROCESSING_SPEED = "cognitive_processing_speed";
    private static final String NAME_WORKING_MEMORY = "cognitive_working_memory";
    private static final String NAME_LONGTERM_MEMORY = "cognitive_longterm_memory";
    
    
    
    private final FuzzyVariable langReception;
    private final FuzzyVariable langProduction;
    private final FuzzyVariable abstractSigns;
    private final FuzzyVariable attention;
    private final FuzzyVariable processingSpeed;
    private final FuzzyVariable workingMemory;
    private final FuzzyVariable longtermMemory;
    
    
    public CognitiveAbilities(
            double langReception, 
            double langProduction,
            double abstractSigns,
            double attention,
            double processingSpeed,
            double workingMemory,
            double longtermMemory)
    {
        
        this.langReception = new FuzzyVariable(NAME_LANG_RECEPTION);
        this.langReception.setValue(langReception);
        
        this.langProduction = new FuzzyVariable(NAME_LANG_PRODUCTION);
        this.langProduction.setValue(langProduction);
        
        this.abstractSigns = new FuzzyVariable(NAME_ABSRTACT_SIGNS);
        this.abstractSigns.setValue(abstractSigns);
        
        this.attention = new FuzzyVariable(NAME_ATTETION);
        this.attention.setValue(attention);
        
        this.processingSpeed = new FuzzyVariable(NAME_PROCESSING_SPEED);
        this.processingSpeed.setValue(processingSpeed);
        
        this.workingMemory = new FuzzyVariable(NAME_WORKING_MEMORY);
        this.workingMemory.setValue(workingMemory);
        
        this.longtermMemory = new FuzzyVariable(NAME_LONGTERM_MEMORY);
        this.longtermMemory.setValue(longtermMemory);

    }
    
    
    
    public void setLanguageReception(double langReception)
    {        
        this.langReception.setValue(langReception);
    }
    
    public double getLanguageReception()
    {
        return this.langReception.getValue();
    }
    
    
    public void setLanguageProduction(double langProduction)
    {
        this.langProduction.setValue(langProduction);
    }
    
    public double getLanguageProduction()
    {
        return this.langProduction.getValue();
    }
    
    
    public void setAbstractSigns(double abstractSigns)
    {
        this.abstractSigns.setValue(abstractSigns);
    }
    
    public double getAbstractSigns()
    {
        return this.abstractSigns.getValue();
    }
    
    
    public void setAttention(double attention)
    {
        this.attention.setValue(attention);
    }
    
    public double getAttention()
    {
        return this.attention.getValue();
    }
    
    
    public void setProcessingSpeed(double processingSpeed)
    {
        this.processingSpeed.setValue(processingSpeed);
    }
    
    public double getProcessingSpeed()
    {
        return this.processingSpeed.getValue();
    }
    
    
    public void setWorkingMemory(double workingMemory)
    {
        this.workingMemory.setValue(workingMemory);
    }
    
    public double getWorkingMemory()
    {
        return this.workingMemory.getValue();
    }
    
    
    public void setLongtermMemory(double longtermMemory)
    {
        this.longtermMemory.setValue(longtermMemory);
    }
    
    public double getLongtermMemory()
    {
        return this.longtermMemory.getValue();
    }
    

}
