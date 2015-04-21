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
 
 package com.hi3project.dandelion.fio.specification;

/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-sep-2014
 */
public class FIOSpecification 
{
    
    private final InteractionSpecification interactionSpecification;
    
    private final ModalitySpecification modalitySpecification;
    
    private final PhysicalSpecification physicalSpecification;
    
    private final UsageSpecification usageSpecification;

    
    
    public FIOSpecification(
            InteractionSpecification interactionSpecification,
            ModalitySpecification modalitySpecification, 
            PhysicalSpecification physicalSpecification, 
            UsageSpecification usageSpecification)
    {
        this.interactionSpecification = interactionSpecification;
        this.modalitySpecification = modalitySpecification;
        this.physicalSpecification = physicalSpecification;
        this.usageSpecification = usageSpecification;
    }
    
    
    public boolean hasInteractionSpecification()
    {
        return getInteractionSpecification() != null;
    }
    
    public boolean hasModalitySpecification()
    {
        return getModalitySpecification() != null;
    }    
    
    public boolean hasPhysicalSpecification()
    {
        return getPhysicalSpecification() != null;
    }    
    
    
    public boolean hasUsageSpecification()
    {
        return getUsageSpecification() != null;
    }

    
    public InteractionSpecification getInteractionSpecification()
    {
        return interactionSpecification;
    }
    
    public ModalitySpecification getModalitySpecification()
    {
        return modalitySpecification;
    }

    public PhysicalSpecification getPhysicalSpecification()
    {
        return physicalSpecification;
    }


    public UsageSpecification getUsageSpecification()
    {
        return usageSpecification;
    }    

    
    @Override
    public String toString()
    {
        return "FIOSpecification \n{\n" + 
                "interactionSpecs=" + interactionSpecification + "\n\n"
                + "modalitySpecs:\n" + modalitySpecification + "\n\n"
                + "physicalSpecs:\n" + physicalSpecification + "\n\n"
                + "usageSpecification:\n" + usageSpecification + "\n"
                + "}";
    }

    
    

}
