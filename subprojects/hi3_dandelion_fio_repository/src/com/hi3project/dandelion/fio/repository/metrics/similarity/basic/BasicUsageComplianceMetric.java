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
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      26-sep-2014
 */
public class BasicUsageComplianceMetric
{
    
    private static final double AGE_WEIGHT = 0.6;
    private static final double ICT_WEIGTH = 0.4;
    
    
    
    
    public BasicUsageComplianceMetric()
    {
        
    }

    
    
    
    public UsageSimilarity calcSimilarity(UsageSpecification fioSpecs, FIODescription fioDesc)
    {
        
        double distance = calcUsageSimilarity(fioSpecs.getRecommendedUserAge(), fioSpecs.getIctDifficulty(), fioDesc.getUsageCharacteristics());
        return new UsageSimilarity(distance);
        
    }
    
    
    
    
    private double calcUsageSimilarity(
            double userAge, double ict, 
            UsageCharacteristics fioDesc)
    {

        double ageDistance = Math.abs(userAge - fioDesc.getRecommendedUserAge()) / 110;
        double ictDistance = Math.abs(ict - fioDesc.getIctDifficulty()) / 4.0;
        
              
        return 1 - (AGE_WEIGHT*ageDistance + ICT_WEIGTH*ictDistance);
        
    }
    

}
