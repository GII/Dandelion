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

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;



/** Physical description of a FIO
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class PhysicalDescription 
{
    
    
    private final double size;
    private final double status;
    private final double usage_distance;
    private final EnumSet<PhysicalShapeType> shapes;

    
    
    public PhysicalDescription()
    {
        this.shapes = EnumSet.of(PhysicalShapeType.unknown);
        this.shapes.add(PhysicalShapeType.unknown);
        this.size = 0.0;
        this.status = 0.0;
        this.usage_distance = 0.0;
    }

    public PhysicalDescription(
            double size, double status, 
            double usage_distance, EnumSet<PhysicalShapeType> shapes)
    {
        this.size = size;
        this.status = status;
        this.usage_distance = usage_distance;
        this.shapes = EnumSet.copyOf(shapes);
    }

    public double getSize()
    {
        return size;
    }

    public double getStatus()
    {
        return status;
    }

    public double getUsage_distance()
    {
        return usage_distance;
    }

    public EnumSet<PhysicalShapeType> getShapeTypes()
    {
        return EnumSet.copyOf(shapes);
    }

}
