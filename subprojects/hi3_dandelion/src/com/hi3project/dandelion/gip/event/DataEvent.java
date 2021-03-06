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
 
 package com.hi3project.dandelion.gip.event;

import com.hi3project.dandelion.util.properties.Property;
import java.util.ArrayList;
import java.util.Collection;


/** A GIP data event containing a set of properties with the information
 * about the input/output data produced
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-mar-2013 -- Initial version
 */
public class DataEvent extends Event
{
    
    private ArrayList<Property> propertySet;


    public DataEvent(String sourceId, String interactionId, EventType type, Collection<Property> propertySet)
    {
        super(sourceId, interactionId, type);
        this.propertySet = new ArrayList<Property>(propertySet);
    }


    public ArrayList<Property> getPropertySet()
    {
        return new ArrayList<Property>(propertySet);
    }


    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + (this.propertySet != null ? this.propertySet.hashCode() : 0);
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
        final DataEvent other = (DataEvent) obj;
        if (this.propertySet != other.propertySet && (this.propertySet == null || !this.propertySet.equals(other.propertySet))) {
            return false;
        }
        return true;
    }

    
    

}
