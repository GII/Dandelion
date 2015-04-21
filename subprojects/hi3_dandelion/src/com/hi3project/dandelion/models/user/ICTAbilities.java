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


/** Description of user's abilities related to the usage of 
 * Information and Communication Technologies
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class ICTAbilities 
{
    
    private static final String NAME_LITERACY = "user_ict_literacy";
    private static final String NAME_ANXIETY = "user_ict_anxiety";
    
    
    private final FuzzyVariable literacy;
    private final FuzzyVariable anxiety;
    
    
    public ICTAbilities(double literacy, double anxiety)
    {
        this.literacy = new FuzzyVariable(NAME_LITERACY);
        this.literacy.setValue(literacy);
        
        this.anxiety = new FuzzyVariable(NAME_ANXIETY);
        this.anxiety.setValue(anxiety);        
    }
    
    
    
    public void setLiteracy(double literacy)
    {        
        this.literacy.setValue(literacy);
    }
    
    public double getLiteracy()
    {
        return this.literacy.getValue();
    }
    
    
    public void setAnxiety(double anxiety)
    {
        this.anxiety.setValue(anxiety);
    }
    
    public double getAnxiety()
    {
        return this.anxiety.getValue();
    }
    

}
