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
 
 package com.hi3project.dandelion.fio.description.interaction;

import com.hi3project.dandelion.fio.description.modality.Modality;


/** A concrete interaction capability of a FIO
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class InteractionCapability
{
    
    private String id;
    private final InteractionSupport interactionSupport;
    private final Modality modality;

    public InteractionCapability(
            String id,
            InteractionSupport interSupport, Modality modality)
    {
        this.id = id;
        this.interactionSupport = interSupport;
        this.modality = modality;
    }

    
    public String getId()
    {
        return id;
    }
        

    public InteractionSupport getInteractionSupport()
    {
        return interactionSupport;
    }
    
    
    public Modality getModality()
    {
        return this.modality;
    }


}
