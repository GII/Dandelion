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
 
 package com.hi3project.dandelion.fio.repository.comm;

import com.hi3project.dandelion.util.requestresponse.RequestResponseMessage;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class FIORepositoryMessage extends RequestResponseMessage
{
    
    private FIORepositoryMessageType type;


    
    public FIORepositoryMessage(
            long sequenceNumber, FIORepositoryMessageType type, 
            String sourceId, String destination)
    {
        super(sequenceNumber, sourceId, destination);
        this.type = type;
    }

    public FIORepositoryMessageType getType()
    {
        return type;
    }
        
    protected void setType(FIORepositoryMessageType type)
    {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 43 * hash + (this.type != null ? this.type.hashCode() : 0);
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
        final FIORepositoryMessage other = (FIORepositoryMessage) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
 

}
