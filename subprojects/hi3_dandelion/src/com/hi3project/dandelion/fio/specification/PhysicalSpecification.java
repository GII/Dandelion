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
 
 package com.hi3project.dandelion.fio.specification;

import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/** Physical description of a FIO
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class PhysicalSpecification 
{
   
    private final double size;
    private final double status;
    private final double distance;
    
    private final HashSet<PhysicalShape> shapeList;

    
    
    public PhysicalSpecification(
            double size, double status, double distance, 
            Set<PhysicalShape> shapeList)
    {
        this.size = size;
        this.status = status;
        this.distance = distance;
        this.shapeList = new HashSet<PhysicalShape>(shapeList);
    }

    public double getSize()
    {
        return size;
    }

    public double getStatus()
    {
        return status;
    }
    
    public double getDistance()
    {
        return distance;
    }

    public Set<PhysicalShape> getShapeList()
    {
        return shapeList;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("sz=" + size + ",\n");
        sb.append("st=" + status + ",\n");
        sb.append("ds=" + distance + ",\n");
        sb.append("shapes: \n");
        
        for(PhysicalShape ps : shapeList) {
            sb.append(ps);
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    
}
