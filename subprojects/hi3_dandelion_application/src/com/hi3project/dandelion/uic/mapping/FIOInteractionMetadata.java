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
 
 package com.hi3project.dandelion.uic.mapping;

import com.hi3project.dandelion.fio.FIOMetadata;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-mar-2015
 */
public class FIOInteractionMetadata 
{
    
    private final FIOMetadata fio;
    private final String interactionId;



    public FIOInteractionMetadata(FIOMetadata fio, String interactionId)
    {
        this.fio = fio;
        this.interactionId = interactionId;
    }

    public FIOMetadata getFio()
    {
        return fio;
    }

    public String getInteractionId()
    {
        return interactionId;
    }
    
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + (this.fio != null ? this.fio.hashCode() : 0);
        hash = 83 * hash + (this.interactionId != null ? this.interactionId.hashCode() : 0);
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
        final FIOInteractionMetadata other = (FIOInteractionMetadata) obj;
        if (this.fio != other.fio && (this.fio == null || !this.fio.equals(other.fio))) {
            return false;
        }
        if ((this.interactionId == null) ? (other.interactionId != null) : !this.interactionId.equals(other.interactionId)) {
            return false;
        }
        return true;
    }
    
}
