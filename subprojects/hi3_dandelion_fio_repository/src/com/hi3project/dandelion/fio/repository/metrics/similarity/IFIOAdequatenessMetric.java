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

import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.FIOAdequateness;
import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;


/** A compliance metric provide measures of how an concrete FIO meets
 * the characteristics of a specification.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-sep-2014
 */
public interface IFIOAdequatenessMetric extends Cloneable
{
    
    
    public String getMetricId();
    
    
    
    public FIOAdequateness calcAdequateness(FIOSpecification fioSpecs, FIODescription fioDesc);
    
    
    
    public ModalitySimilarity calcSimilarity(ModalitySpecification fioSpecs, FIODescription fioDesc);
    
    public ModalityInteractionSimilarity calcSimilarity(
            InteractionSpecification interactionSpecs, ModalitySpecification modalitySpecs, FIODescription fioDesc);
    
    
    
    public InteractionSimilarity calcSimilarity(InteractionSpecification fioSpecs, FIODescription fioDesc);
    
    
    
    public PhysicalSimilarity calcSimilarity(PhysicalSpecification fioSpecs, FIODescription fioDesc);
    
    
    
    public UsageSimilarity calcSimilarity(UsageSpecification fioSpecs, FIODescription fioDesc);

    
    
    public IFIOAdequatenessMetric clone();
    
}
