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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.repository.metrics.similarity.AbstractFIOAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.IFIOAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.metrics.FIOAdequateness;
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicInteractionComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.modality.FGMModalityComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.physical.FGMPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.usage.FGMUsageComplianceMetric;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      03-nov-2014
 */
public class FGMAdequatenessMetric extends AbstractFIOAdequatenessMetric
{
    
    private final BasicInteractionComplianceMetric interactionMetric;
    private final FGMModalityComplianceMetric modalityMetric;
    private final FGMPhysicalComplianceMetric physicalMetric;
    private final FGMUsageComplianceMetric usageMetric;

    
    
    public FGMAdequatenessMetric(
            BasicInteractionComplianceMetric interactionMetric, 
            FGMModalityComplianceMetric modalityMetric, 
            FGMPhysicalComplianceMetric physicalMetric, 
            FGMUsageComplianceMetric usageMetric, 
            String id)
    {
        super(id);
        this.interactionMetric = interactionMetric;
        this.modalityMetric = modalityMetric;
        this.physicalMetric = physicalMetric;
        this.usageMetric = usageMetric;
    }
    
    
    
    

    @Override
    public FIOAdequateness calcAdequateness(FIOSpecification fioSpecs, FIODescription fioDesc)
    {
        
        InteractionSimilarity interactionDistance = 
                this.interactionMetric.calcSimilarity(fioSpecs.getInteractionSpecification(), fioDesc);
        ModalityInteractionSimilarity modalityDistance = 
                this.modalityMetric.calcSimilarity(fioSpecs.getModalitySpecification(), fioSpecs.getInteractionSpecification(), fioDesc);
        PhysicalSimilarity physicalDistance = 
                this.physicalMetric.calcAdequateness(fioSpecs.getPhysicalSpecification(), fioDesc);
        UsageSimilarity usageDistance = 
                this.usageMetric.calcSimilarity(fioSpecs.getUsageSpecification(), fioDesc);
        
        return new FIOAdequateness(interactionDistance, modalityDistance, physicalDistance, usageDistance);
        
    }
    

    @Override
    public ModalitySimilarity calcSimilarity(ModalitySpecification modalSpecs, FIODescription fioDesc)
    {
        return this.modalityMetric.calcSimilarity(modalSpecs, fioDesc);
    }

    @Override
    public ModalityInteractionSimilarity calcSimilarity(InteractionSpecification interactionSpecs, ModalitySpecification modalitySpecs, FIODescription fioDesc)
    {
        return this.modalityMetric.calcSimilarity(modalitySpecs, interactionSpecs, fioDesc);
    }

    @Override
    public InteractionSimilarity calcSimilarity(InteractionSpecification interSpecs, FIODescription fioDesc)
    {
        return this.interactionMetric.calcSimilarity(interSpecs, fioDesc);
    }

    @Override
    public PhysicalSimilarity calcSimilarity(PhysicalSpecification physicalSpecs, FIODescription fioDesc)
    {
        return this.physicalMetric.calcAdequateness(physicalSpecs, fioDesc);
    }


    @Override
    public UsageSimilarity calcSimilarity(UsageSpecification usageSpecs, FIODescription fioDesc)
    {
        return this.usageMetric.calcSimilarity(usageSpecs, fioDesc);
    }


    @Override
    public IFIOAdequatenessMetric clone()
    {
        return this; //there's no problem sharing attributes in this implementation
    }
    
    

}
