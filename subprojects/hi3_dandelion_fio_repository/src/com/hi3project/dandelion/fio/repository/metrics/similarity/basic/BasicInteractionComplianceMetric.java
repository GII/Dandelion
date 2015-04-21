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
import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.Collection;
import java.util.EnumSet;


/** Calculates the similarity between two interaction specifications.
 * 
 * It uses a quite simple method:
 *   - If any required interaction type is not supported --> distance = 1
 *   - If the property type if different --> distance = 1
 *   - The distance starts in 0.0, and is increased by a weigh factor depending
 *      on the differentes in cardinality and the number of interactions supported.
 * 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-sep-2014
 */
public class BasicInteractionComplianceMetric 
{
    
    private static final double WEIGHT_CARDINALITY = 0.25;
    private static final double WEIGHT_INTERACTION_NUMBER_DIFF = 0.15;
    

    public InteractionSimilarity calcSimilarity(InteractionSpecification interactionSpecs, FIODescription fioDesc)
    {
        
        double tempSimilarity;
        double similarity = 0.0;
        
        Collection<InteractionCapability> interactionCapabilities = fioDesc.getInteractionCapabilities();        
        InteractionSupport interactionSupport = interactionSpecs.getInteractionSupport();
        
        for(InteractionCapability ic : interactionCapabilities) {
            
            tempSimilarity = calcInteractionCapabilitiySupport(ic, interactionSupport);
            if (tempSimilarity > similarity) {
                similarity = tempSimilarity;
            }
        }
        
        return new InteractionSimilarity(similarity);        
        
    }
    
    
    
    public double calcInteractionCapabilitiySupport(
            InteractionCapability interactionCapability, 
            InteractionSupport interactionSupport)
    {
        
        double tempDistance = 0.0;
        double distance = 1.0;
        
        InteractionSupport capabilityInteractionSupport = 
                interactionCapability.getInteractionSupport();
        EnumSet<InteractionType> type = capabilityInteractionSupport.getType();
        PropertyType dataType = capabilityInteractionSupport.getDataType();
        int maxCardinality = capabilityInteractionSupport.getMaxCardinality();
        
        if (type.containsAll(interactionSupport.getType())) {

            if (interactionSupport.getDataType() == dataType) {
                
                double interDiff = 1.0 - (double)interactionSupport.getType().size() / (double) type.size();
                if (interDiff >= 0) {
                    tempDistance += WEIGHT_INTERACTION_NUMBER_DIFF * interDiff;

                    double card = Math.abs(maxCardinality - interactionSupport.getMaxCardinality()) / (double) maxCardinality;
                    tempDistance += WEIGHT_CARDINALITY * card;

                    if (tempDistance < distance) {
                        distance = tempDistance;
                    }
                }
                //else not enough cardinality
            }
            
        }

        return 1.0 - distance;
        
    }
    
}
