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
 
 package com.hi3project.dandelion.uib.fio.specification.usage;

import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Default implementation using direct values extracted from the User profile.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      04-ago-2014
 */
public class DefaultFIOUsageSpecificationBuilder implements IFIOUsageSpecificationBuilder
{
    
    
    @Override
    public UsageSpecification buildUsageSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene)
    {
        return new UsageSpecification(0, 0);
    }
    

    @Override
    public UsageSpecification buildUsageSpecification(
            AbstractInteractionUnit aiu, UserProfile user)
    {
        return new UsageSpecification(user.getAge(), user.getIct().getLiteracy());
    }

    
    @Override
    public UsageSpecification buildUsageSpecification(AbstractInteractionUnit aiu, EnvironmentProfile env)
    {
        return new UsageSpecification(0, 0);
    }

    
    @Override
    public UsageSpecification buildUsageSpecification(AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, EnvironmentProfile env)
    {
        return new UsageSpecification(user.getAge(), user.getIct().getLiteracy());
    }
    
    

}
