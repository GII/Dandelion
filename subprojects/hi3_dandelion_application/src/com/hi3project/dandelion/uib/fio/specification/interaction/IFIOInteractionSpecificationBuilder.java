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
 
 package com.hi3project.dandelion.uib.fio.specification.interaction;

import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.DataSelectionSupport;
import org.usixml.model.aui.event.TriggerSupport;


/** Implementations of this interfaces build InteractionSpecification objects
 * from the information available in a AbstractInteractionUnit object.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-sep-2014
 */
public interface IFIOInteractionSpecificationBuilder 
{
    
    
    public InteractionSpecification buildInteractionSpecification(
            DataInputOutputSupport interaction, AbstractInteractionUnit aiu);
    
    
    public InteractionSpecification buildInteractionSpecification(
            DataSelectionSupport interaction, AbstractInteractionUnit aiu);
    
    
    public InteractionSpecification buildInteractionSpecification(
            TriggerSupport interaction, AbstractInteractionUnit aiu);
    

}