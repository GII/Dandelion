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
 
 package com.hi3project.dandelion.uic.mapping;

import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** A combination of AIU and one of its InteractioSupport elements
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      05-dic-2014
 */
public class AIUInteractionElement 
{

    
    private final AbstractInteractionUnit aiu;
    private final InteractionSupportElement interactionSupport;

    public AIUInteractionElement(AbstractInteractionUnit aiu, InteractionSupportElement interactionSupport)
    {
        this.aiu = aiu;
        this.interactionSupport = interactionSupport;
    }

    
    public AbstractInteractionUnit getAIU()
    {
        return aiu;
    }

    
    public InteractionSupportElement getInteractionSupport()
    {
        return interactionSupport;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + (this.aiu != null ? this.aiu.hashCode() : 0);
        hash = 43 * hash + (this.interactionSupport != null ? this.interactionSupport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AIUInteractionElement other = (AIUInteractionElement) obj;
        if (this.aiu != other.aiu && (this.aiu == null || !this.aiu.equals(other.aiu))) {
            return false;
        }
        if (this.interactionSupport != other.interactionSupport) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
