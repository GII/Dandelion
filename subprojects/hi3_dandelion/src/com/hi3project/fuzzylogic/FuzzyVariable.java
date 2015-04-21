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
 
 package com.hi3project.fuzzylogic;

/** Representes a fuzzy variable and its current value.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014 -- Added the name of the variable
 *      21-mar-2013 -- Initial version
 */
public class FuzzyVariable 
{
    
    private static final String DEFAULT_VALUE_LABEL = "NONE";
    
    
    
    private final String name;
    private String value_label;
    private double value_raw;


    
    public FuzzyVariable(String name)
    {
        this.name = name;
        this.value_label = DEFAULT_VALUE_LABEL;
        this.value_raw = 0.0;
    }

    public FuzzyVariable(String name, String value_label, double value_raw)
    {
        this.name = name;
        this.value_label = value_label;
        this.value_raw = value_raw;
    }

    
    public String getName()
    {
        return this.name;
    }
    

    public String getValueLabel()
    {
        return this.value_label;
    }
    
    
    public void setValueLabel(String valueLabel)
    {
        this.value_label = valueLabel;
    }


    public double getValue()
    {
        return this.value_raw;
    }
    
    
    public void setValue(double value)
    {
        this.value_raw = value;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.value_label != null ? this.value_label.hashCode() : 0);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.value_raw) ^ (Double.doubleToLongBits(this.value_raw) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FuzzyVariable other = (FuzzyVariable) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.value_label == null) ? (other.value_label != null) : !this.value_label.equals(other.value_label)) {
            return false;
        }
        if (Double.doubleToLongBits(this.value_raw) != Double.doubleToLongBits(other.value_raw)) {
            return false;
        }
        return true;
    }

    

}
