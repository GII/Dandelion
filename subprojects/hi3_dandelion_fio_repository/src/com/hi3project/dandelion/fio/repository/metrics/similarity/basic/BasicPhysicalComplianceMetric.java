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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.basic;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalDescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import java.util.EnumSet;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      26-sep-2014
 */
public class BasicPhysicalComplianceMetric 
{
    
    private static final double SIZE_WEIGHT = 0.15;
    private static final double STATUS_WEIGHT = 0.25;
    private static final double DISTANCE_WEIGHT = 0.10;
    private static final double SHAPE_WEIGHT = 0.50;
    

    
    public BasicPhysicalComplianceMetric()
    {
        
    }

    
    
    
    public PhysicalSimilarity calcSimilarity(PhysicalSpecification fioSpecs, FIODescription fioDesc)
    {
        
        double similarity = 1.0, tempSimilarity = 1.0;
        for(PhysicalShape shape : fioSpecs.getShapeList()) {
            
            tempSimilarity = calcPhysicalSimilarity(fioSpecs.getSize(), fioSpecs.getStatus(), fioSpecs.getDistance(), shape, fioDesc.getPhysicalProperties());
            if (similarity < tempSimilarity) similarity = tempSimilarity;
            
        }
        
        return new PhysicalSimilarity(similarity);
        
    }
    
    
    
    private double calcPhysicalSimilarity(
            double size, double status, double distance, PhysicalShape shape, 
            PhysicalDescription fioDesc)
    {

        double sizeDistance = Math.abs(size - fioDesc.getSize()) / 4.0;
        double statusDistance = Math.abs(status - fioDesc.getStatus()) / 4.0;
        double distanceDistance = Math.abs(distance - fioDesc.getUsage_distance()) / 10000;
        double shapeDistance = 0.0; //TODO --> ARREGLAR
        
        return 1 - (SIZE_WEIGHT*sizeDistance + STATUS_WEIGHT*statusDistance + DISTANCE_WEIGHT*distanceDistance + SHAPE_WEIGHT*shapeDistance);
        
    }
    
    
}
