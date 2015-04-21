/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.modality.selector.IModalitySelector;
import java.util.Collection;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-sep-2014
 */
public class DefaultFIOModalitySpecificationBuilder implements IFIOModalitySpecificationBuilder
{
    
    
    private final IModalitySelector modalitySelector;

    
    
    public DefaultFIOModalitySpecificationBuilder(IModalitySelector modalitySelector)
    {
        this.modalitySelector = modalitySelector;
    }
    
    
    
    @Override
    public ModalitySpecification buildModalitySpecification(AbstractInteractionUnit aiu, SceneProfile scene)
    {
        Collection<Modality> modalities = this.modalitySelector.selectModalities(aiu, scene);
        return new ModalitySpecification(modalities);
    }

    @Override
    public ModalitySpecification buildModalitySpecification(AbstractInteractionUnit aiu, UserProfile user)
    {
        Collection<Modality> modalities = this.modalitySelector.selectModalities(aiu, user);
        return new ModalitySpecification(modalities);
    }

    @Override
    public ModalitySpecification buildModalitySpecification(AbstractInteractionUnit aiu, EnvironmentProfile environment)
    {
        Collection<Modality> modalities = this.modalitySelector.selectModalities(aiu, environment);
        return new ModalitySpecification(modalities);
    }

    @Override
    public ModalitySpecification buildModalitySpecification(AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, EnvironmentProfile environment)
    {
        Collection<Modality> modalities = this.modalitySelector.selectModalities(aiu, scene, user, environment);
        return new ModalitySpecification(modalities);
    }
    

}
