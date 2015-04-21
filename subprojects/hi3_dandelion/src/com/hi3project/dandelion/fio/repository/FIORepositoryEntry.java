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

import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.location.Location;


/** An entry in the FIO repository database
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class FIORepositoryEntry
{
    
    private FIOExtendedMetadata fioMetadata;
    
    private Location location;
    
    private String fioCommChannel;
    
    private int usageCount;

    
    
    public FIORepositoryEntry(
            FIOExtendedMetadata fioMetadata, Location location, String fioCommChannel)
    {
        this.fioMetadata = fioMetadata;
        this.location = location;
        this.fioCommChannel = fioCommChannel;
        this.usageCount = 0;
    }
    
    public FIORepositoryEntry(
            FIOExtendedMetadata fioMetadata, String fioCommChannel)
    {
        this(fioMetadata, Location.UNKNOWN, fioCommChannel);
    }
    

    public FIOExtendedMetadata getFioMetadata()
    {
        return fioMetadata;
    }

    public void setFioMetadata(FIOExtendedMetadata fioMetadata)
    {
        this.fioMetadata = fioMetadata;
    }
    
    public Location getLocation()
    {
        return this.location;
    }
    
    public void setLocation(Location location)
    {
        this.location = location;
    }

    public String getFioCommChannel()
    {
        return fioCommChannel;
    }

    public void setFioCommChannel(String fioCommChannel)
    {
        this.fioCommChannel = fioCommChannel;
    }

    public int getUsageCount()
    {
        return usageCount;
    }
    
    
    public void incrementUsageCount()
    {
        this.usageCount++;
    }
    
    
    public void decrecementUsageCount()
    {
        this.usageCount--;
    }

    
    @Override
    public FIORepositoryEntry clone() throws CloneNotSupportedException
    {
        return new FIORepositoryEntry(fioMetadata, location, fioCommChannel);
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + (this.fioMetadata != null ? this.fioMetadata.hashCode() : 0);
        hash = 83 * hash + (this.location != null ? this.location.hashCode() : 0);
        hash = 83 * hash + (this.fioCommChannel != null ? this.fioCommChannel.hashCode() : 0);
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
        final FIORepositoryEntry other = (FIORepositoryEntry) obj;
        if (this.fioMetadata != other.fioMetadata && (this.fioMetadata == null || !this.fioMetadata.equals(other.fioMetadata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "FIORepositoryEntry{" + "fioMetadata=" + fioMetadata + ", location=" + location + ", fioCommChannel=" + fioCommChannel + ", usageCount=" + usageCount + '}';
    }


}
