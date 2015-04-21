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
 
 package com.hi3project.dandelion.fio.description.physical;

/** FIO physical shape description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      04-ago-2014
 */
public class PhysicalShape 
{
    
    private final double granularity;
    private final PhysicalShapeType shapeType;

    
    public PhysicalShape(double granularity, PhysicalShapeType shapeType)
    {
        this.granularity = granularity;
        this.shapeType = shapeType;
    }

    public double getGranularity()
    {
        return granularity;
    }

    public PhysicalShapeType getShapeType()
    {
        return shapeType;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + (this.shapeType != null ? this.shapeType.hashCode() : 0);
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
        final PhysicalShape other = (PhysicalShape) obj;
        if (this.shapeType != other.shapeType) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString()
    {
        return shapeType + " - " + granularity;
    }
    

}
