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
 
 package com.hi3project.dandelion.fio.repository;

import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.repository.metrics.FIOAdequateness;





/** 
 * 
 * Note: this class has a natural ordering that is inconsistent with equals.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-nov-2014
 */
public class FIORepositoryEntryCompliance implements Comparable<FIORepositoryEntryCompliance>
{
    
    
    private final FIORepositoryEntry entry;
    
    private final FIOAdequateness compliance;

    
    
    public FIORepositoryEntryCompliance(
            FIORepositoryEntry entry, 
            FIOAdequateness compliance)
    {
        this.entry = entry;
        this.compliance = compliance;
    }
   
    
    public FIORepositoryEntry getEntry()
    {
        return entry;
    }

    public FIOAdequateness getDistance()
    {
        return compliance;
    }

    
    @Override
    public int compareTo(FIORepositoryEntryCompliance o)
    {
        if (this.compliance.getValue() > o.compliance.getValue()) {
            return 1;
        }
        else if (this.compliance.getValue() < o.compliance.getValue()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + (this.entry != null ? this.entry.hashCode() : 0);
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
        final FIORepositoryEntryCompliance other = (FIORepositoryEntryCompliance) obj;
        if (this.entry != other.entry && (this.entry == null || !this.entry.equals(other.entry))) {
            return false;
        }
        return true;
    }
    

    
    

}
