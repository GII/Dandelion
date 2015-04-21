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
 
 package com.hi3project.dandelion.uib.fio.specification.physical;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.models.scene.SceneProfile;
import java.util.Collection;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Implementations of this interface provides concrete logic to
 * build a Physical Specification of a FIO from the AIU, USER and/or ENV 
 * characteristics.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public interface IFIOPhysicalSpecificationBuilder 
{
    
    
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, Collection<Modality> modalitiesGranularity);
    
    
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, Collection<Modality> modalitiesGranularity);
    
    
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, EnvironmentProfile env, Collection<Modality> modalitiesGranularity);
    
    
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, EnvironmentProfile env, Collection<Modality> modalitiesGranularity);

    
}
