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
 
 package com.hi3project.dandelion.uib.fio.specification;

import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.specification.modality.IFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.IFIOUsageSpecificationBuilder;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Default implementation of a FIO query builder. It uses a combination
 * of elements (Modality Selector, Physical Specs Selector and Usage Spec selector)
 * to build the specs of a FIO that best suits the needs of a combination
 * of User, Environment and Scene profiles.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      11-ago-2014
 */
public class DefaultFIOSpecificationBuilder implements IFIOSpecificationBuilder
{

    
    private final IFIOModalitySpecificationBuilder modalitySpecBuilder;
    private final IFIOPhysicalSpecificationBuilder physicalSpecBuilder;
    private final IFIOUsageSpecificationBuilder usageSpecBuilder;
    

    
    public DefaultFIOSpecificationBuilder(
            IFIOModalitySpecificationBuilder modalitySpecBuilder,
            IFIOPhysicalSpecificationBuilder physicalSpecBuilder, 
            IFIOUsageSpecificationBuilder usageSpecBuilder)
    {
        this.modalitySpecBuilder = modalitySpecBuilder;
        this.physicalSpecBuilder = physicalSpecBuilder;
        this.usageSpecBuilder = usageSpecBuilder;
    }

    
    @Override
    public FIOSpecification buildQuery(AbstractInteractionUnit aiu, InteractionSpecification interaction, SceneProfile scene)
    {
        InteractionSpecification interactionSpec = interaction;
        ModalitySpecification modalitySpecs = this.modalitySpecBuilder.buildModalitySpecification(aiu, scene);
        PhysicalSpecification physicalSpecs = this.physicalSpecBuilder.buildPhysicalSpecification(aiu, scene, modalitySpecs.getModalities());
        UsageSpecification usageSpecs = this.usageSpecBuilder.buildUsageSpecification(aiu, scene);
        return new FIOSpecification(interactionSpec, modalitySpecs, physicalSpecs, usageSpecs);
    }

    @Override
    public FIOSpecification buildQuery(AbstractInteractionUnit aiu, InteractionSpecification interaction, SceneProfile scene, UserProfile user)
    {
        InteractionSpecification interactionSpec = interaction;
        ModalitySpecification modalitySpecs = this.modalitySpecBuilder.buildModalitySpecification(aiu, user);
        PhysicalSpecification physicalSpecs = this.physicalSpecBuilder.buildPhysicalSpecification(aiu, scene, user, modalitySpecs.getModalities());
        UsageSpecification usageSpecs = this.usageSpecBuilder.buildUsageSpecification(aiu, user);
        return new FIOSpecification(interactionSpec, modalitySpecs, physicalSpecs, usageSpecs);
    }

    @Override
    public FIOSpecification buildQuery(AbstractInteractionUnit aiu, InteractionSpecification interaction, SceneProfile scene, EnvironmentProfile environment)
    {
        InteractionSpecification interactionSpec = interaction;
        ModalitySpecification modalitySpecs = this.modalitySpecBuilder.buildModalitySpecification(aiu, environment);
        PhysicalSpecification physicalSpecs = this.physicalSpecBuilder.buildPhysicalSpecification(aiu, scene, environment, modalitySpecs.getModalities());
        UsageSpecification usageSpecs = this.usageSpecBuilder.buildUsageSpecification(aiu, environment);
        return new FIOSpecification(interactionSpec, modalitySpecs, physicalSpecs, usageSpecs);
    }

    @Override
    public FIOSpecification buildQuery(AbstractInteractionUnit aiu, InteractionSpecification interaction, SceneProfile scene, UserProfile user, EnvironmentProfile environment)
    {
        InteractionSpecification interactionSpec = interaction;
        ModalitySpecification modalitySpecs = this.modalitySpecBuilder.buildModalitySpecification(aiu, scene, user, environment);
        PhysicalSpecification physicalSpecs = this.physicalSpecBuilder.buildPhysicalSpecification(aiu, scene, user, environment, modalitySpecs.getModalities());
        UsageSpecification usageSpecs = this.usageSpecBuilder.buildUsageSpecification(aiu, scene, user, environment);
        return new FIOSpecification(interactionSpec, modalitySpecs, physicalSpecs, usageSpecs);
    }
    
    
}
