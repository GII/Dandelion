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
 
 package com.hi3project.dandelion.fio.repository.metrics.similarity;

import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;



/** Distance between a modality specification of a FIO query and the
 * modalities supported by a FIO description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-sep-2014
 */
public class ModalityInteractionSimilarity extends ModalitySimilarity
{

    private static final double INTERACTION_WEIGHT = 0.65;
    private static final double MODALITY_WEIGHT = 0.35;
    
    private final InteractionSimilarity interactionSimilarity;
    private final InteractionCapability interactionFacet;

    
    public ModalityInteractionSimilarity(
            InteractionSimilarity interactionSimilarity, 
            InteractionCapability interactionFacet,
            double modalitySimilarity)
    {
        super(modalitySimilarity);
        this.interactionFacet = interactionFacet;
        this.interactionSimilarity = interactionSimilarity;
    }

    
    public InteractionSimilarity getInteractionSimilarity()
    {
        return interactionSimilarity;
    }

    
    public InteractionCapability getInteractionFacet()
    {
        return interactionFacet;
    }
        
    
    public double getModalitySimilarity()
    {
        return super.getValue();
    }

    
    
    @Override
    public double getValue()
    {
        return INTERACTION_WEIGHT*interactionSimilarity.getValue() + MODALITY_WEIGHT*super.getValue();
    }


}
