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
 
 package com.hi3project.dandelion.uib.fio.specification.modality;

import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Implementations of this interface provides concrete logic to
 * build a Modality Specification of a FIO from the AIU, USER and/or ENV 
 * characteristics.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public interface IFIOModalitySpecificationBuilder 
{
    
    
    
    public ModalitySpecification buildModalitySpecification(
            AbstractInteractionUnit aiu, SceneProfile scene);
    
    public ModalitySpecification buildModalitySpecification(
            AbstractInteractionUnit aiu, UserProfile user);
    
    public ModalitySpecification buildModalitySpecification(
            AbstractInteractionUnit aiu, EnvironmentProfile environment);
    
    public ModalitySpecification buildModalitySpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, 
            UserProfile user, EnvironmentProfile environment);
    
    
}
