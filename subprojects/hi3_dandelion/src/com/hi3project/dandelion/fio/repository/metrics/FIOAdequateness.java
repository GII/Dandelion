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
 
 package com.hi3project.dandelion.fio.repository.metrics;

import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.Similarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;



/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-sep-2014
 */
public class FIOAdequateness implements Similarity
{
    
    private static final double INTERACION_WEIGHT = 0.10;
    private static final double MODALITY_WEIGHT = 0.65;
    private static final double PHYSICAL_WEIGHT = 0.15;
    private static final double USAGE_WEIGHT = 0.10;
    
    
    private final InteractionSimilarity interactionSimilarity;
    private final ModalityInteractionSimilarity modalitySimilarity;
    private final PhysicalSimilarity physicalSimilarity;
    private final UsageSimilarity usageSimilarity;

    
    public FIOAdequateness(InteractionSimilarity interactionSimilarity, ModalityInteractionSimilarity modalitySimilarity, PhysicalSimilarity physicalSimilarity, UsageSimilarity usageSimilarity)
    {
        this.interactionSimilarity = interactionSimilarity;
        this.modalitySimilarity = modalitySimilarity;
        this.physicalSimilarity = physicalSimilarity;
        this.usageSimilarity = usageSimilarity;
    }

    public InteractionSimilarity getInteractionSimilarity()
    {
        return interactionSimilarity;
    }

    public ModalityInteractionSimilarity getModalitySimilarity()
    {
        return modalitySimilarity;
    }

    public PhysicalSimilarity getPhysicalSimilarity()
    {
        return physicalSimilarity;
    }

    public UsageSimilarity getUsageSimilarity()
    {
        return usageSimilarity;
    }
    

    @Override
    public double getValue()
    {
        
        double adequateness = 0.0;
        
        if (interactionSimilarity.getValue() >= 0.25){
            adequateness += INTERACION_WEIGHT * interactionSimilarity.getValue();
            adequateness += MODALITY_WEIGHT * modalitySimilarity.getValue();
            adequateness += PHYSICAL_WEIGHT * physicalSimilarity.getValue();
            adequateness += USAGE_WEIGHT * usageSimilarity.getValue();
        }
        else {
            adequateness = interactionSimilarity.getValue();
        }
        
        
        return adequateness;
        
    }
    

    @Override
    public String toString()
    {
        return String.valueOf(getValue());
    }
    
    
    
    

}
