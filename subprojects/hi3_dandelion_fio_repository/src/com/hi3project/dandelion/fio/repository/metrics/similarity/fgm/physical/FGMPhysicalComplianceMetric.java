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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.physical;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.FGMFuzzySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.exception.NotSameNumberOfAttributesException;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.EnumSet;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** Description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-feb-2015
 */
public class FGMPhysicalComplianceMetric
{
    
    
    private final JFuzzyLogicPhysicalSimilarity FA;
    private final JFuzzyLogicPhysicalSimilarity FB;
    

    
    public FGMPhysicalComplianceMetric() throws ErrorLoadingFISException
    {
        this.FA = JFuzzyLogicPhysicalSimilarity.instantiate();
        this.FB = JFuzzyLogicPhysicalSimilarity.instantiate();
    }
    
    
    
    public PhysicalSimilarity calcAdequateness(PhysicalSpecification fioSpecs, FIODescription fioDesc)
    {
        
        double attrSimilarity = 0;
        
        FA.setSize(fioSpecs.getSize());
        FA.setStatus(fioSpecs.getStatus());
        FA.setDistance(fioSpecs.getDistance());
        Variable[] FAAttributes = new Variable[3];
        FAAttributes[0] = FA.getSizeVariable();
        FAAttributes[1] = FA.getStatusVariable();
        FAAttributes[2] = FA.getDistanceVariable();
        
        FB.setSize(fioDesc.getPhysicalProperties().getSize());
        FB.setStatus(fioDesc.getPhysicalProperties().getStatus());
        FB.setDistance(fioDesc.getPhysicalProperties().getUsage_distance());
        Variable[] FBAttributes = new Variable[3];
        FBAttributes[0] = FB.getSizeVariable();
        FBAttributes[1] = FB.getStatusVariable();
        FBAttributes[2] = FB.getDistanceVariable();
        
        FGMFuzzySimilarity fuzzySimilarity = new FGMFuzzySimilarity(FAAttributes, FBAttributes);
        try {
            
            attrSimilarity = fuzzySimilarity.similarityWeighted(new double[]{0.50, 0.35, 0.15}, new double[]{1, 1, 1});
            
        } catch (NotSameNumberOfAttributesException ex) {
            attrSimilarity = 0;
        }
        
        double shapeSimilarity = calcShapeAdequateness(fioSpecs, fioDesc);
        
        return new PhysicalSimilarity(attrSimilarity, shapeSimilarity);
        
    }
    
    
    private double calcShapeAdequateness(PhysicalSpecification fioSpecs, FIODescription fioDesc)
    {
     
        double shapeAdequateness = 0;
        
        EnumSet<PhysicalShapeType> shapeTypes = 
                fioDesc.getPhysicalProperties().getShapeTypes();
        
        double newAdequateness = 0;
        for(PhysicalShapeType type : shapeTypes) {
            newAdequateness = getShapeAdequateness(type, fioSpecs);
            if (newAdequateness > shapeAdequateness) {
                shapeAdequateness = newAdequateness;
            }
        }

        return shapeAdequateness;
        
    }
    
    
    private double getShapeAdequateness(PhysicalShapeType type, PhysicalSpecification fioSpecs)
    {
        
        double granularity = 0.0;
        
        for(PhysicalShape ps : fioSpecs.getShapeList()) {
            if (ps.getShapeType() == type) {
                
                granularity = ps.getGranularity();
                break;
                
            }
        }
        
        return granularity / 10.0;
        
    }

}
