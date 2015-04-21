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

import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;


/** A generic GIP event
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1

 Changelog:
      21-mar-2013 -- Initial version
 */
public class Event
{
    
    private final String sourceId;
    private final String interactionId;
    private final EventType type;
    
    private final ArrayList<FuzzyVariable> fuzzyHints;


    public Event(String sourceId, String interactionId, EventType type)
    {
        this.sourceId = sourceId;
        this.interactionId = interactionId;
        this.type = type;
        this.fuzzyHints = new ArrayList<FuzzyVariable>(0);
    }
    
    
    public String getInteractionId()
    {
        return interactionId;
    }


    public String getSourceId()
    {
        return sourceId;
    }


    public EventType getType()
    {
        return type;
    }


    public Collection<FuzzyVariable> getFuzzyHints()
    {
        return new ArrayList<FuzzyVariable>(fuzzyHints);
    }
    
    
    public void setFuzzyHints(Collection<FuzzyVariable> fuzzyHints)
    {
        this.fuzzyHints.clear();
        this.fuzzyHints.addAll(fuzzyHints);
    }


    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 61 * hash + (this.sourceId != null ? this.sourceId.hashCode() : 0);
        hash = 61 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 61 * hash + (this.fuzzyHints != null ? this.fuzzyHints.hashCode() : 0);
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
        final Event other = (Event) obj;
        if ((this.sourceId == null) ? (other.sourceId != null) : !this.sourceId.equals(other.sourceId)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.fuzzyHints != other.fuzzyHints && (this.fuzzyHints == null || !this.fuzzyHints.equals(other.fuzzyHints))) {
            return false;
        }
        return true;
    }
    
    
    

}
