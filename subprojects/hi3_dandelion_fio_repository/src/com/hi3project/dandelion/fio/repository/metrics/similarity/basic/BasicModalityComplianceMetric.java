
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
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import java.util.Collection;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      23-sep-2014
 */
public class BasicModalityComplianceMetric
{
    
    private static final double MAX_GRANULARITY_VALUE = 4.0;
    
    
    private final BasicInteractionComplianceMetric interactionMetric;

    
    public BasicModalityComplianceMetric()
    {
        this.interactionMetric = new BasicInteractionComplianceMetric();
    }
    

    
    public ModalitySimilarity calcSimilarity(
            ModalitySpecification modalitySpecification, FIODescription fioDesc)
    {
        
        double tempDistance;
        double distance = 1.0;
        
        Collection<InteractionCapability> interactionCapabilities = 
                fioDesc.getInteractionCapabilities();
        
        Modality m = null;
        for(InteractionCapability ic : interactionCapabilities) {
            m = findModalityByType(modalitySpecification, ic.getModality().getType());
            if (m != null) {
                tempDistance = 
                        Math.abs(m.getGranularity() - ic.getModality().getGranularity()) 
                        / MAX_GRANULARITY_VALUE;
                distance = (tempDistance < distance) ? tempDistance : distance;
            }
        }
        
        return new ModalitySimilarity(distance);
        
    }
    
    
    public ModalityInteractionSimilarity calcSimilarity(
            ModalitySpecification modalitySpecification, 
            InteractionSpecification interactionSpecification, 
            FIODescription fioDesc)
    {
        
        double interactionDistance = 1.0;
        double tempDistance;
        double distance = 1.0;
        
        Collection<InteractionCapability> interactionCapabilities = fioDesc.getInteractionCapabilities();
        
        Modality m = null;
        InteractionCapability tempIC = null;
        for(InteractionCapability ic : interactionCapabilities) {
            m = findModalityByType(modalitySpecification, ic.getModality().getType());
            if (m != null) {                
                tempDistance = Math.abs(m.getGranularity() - ic.getModality().getGranularity()) / MAX_GRANULARITY_VALUE;
                if (tempDistance < distance) {
                    tempIC = ic;
                    distance = tempDistance;
                    interactionDistance = interactionMetric.calcInteractionCapabilitiySupport(ic, interactionSpecification.getInteractionSupport());
                }
            }
        }
        
        return new ModalityInteractionSimilarity(
                new InteractionSimilarity(1-interactionDistance), tempIC, 1-distance);
        
    }
    
    
    private Modality findModalityByType(ModalitySpecification modalitySpecification, ModalityType modalityType)
    {
        
        for(Modality m : modalitySpecification.getModalities()) {
            
            if (m.getType() == modalityType) return m;
            
        }
        
        return null;
        
    }
    

}
