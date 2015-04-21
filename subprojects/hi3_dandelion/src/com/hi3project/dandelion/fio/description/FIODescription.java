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
 
 package com.hi3project.dandelion.fio.description;

import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.physical.PhysicalDescription;
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import java.util.ArrayList;
import java.util.Collection;


/** Detailed description of a FIO (physical description, interactive description, etc.)
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class FIODescription 
{
    
    private final PhysicalDescription physicalProperties;
    
    private final ArrayList<InteractionCapability> interactionCapabilities;
    
    private final UsageCharacteristics usageCharacteristics;

    
    public FIODescription()
    {
        this.physicalProperties = new PhysicalDescription();
        this.usageCharacteristics = new UsageCharacteristics();
        this.interactionCapabilities = new ArrayList<InteractionCapability>(0);
    }
    
    
    public FIODescription(
            PhysicalDescription physicalProperties, 
            Collection<InteractionCapability> interactionCapabilities, 
            UsageCharacteristics usageCharacteristics)
    {
        this.physicalProperties = physicalProperties;
        this.interactionCapabilities = new ArrayList<InteractionCapability>();
        if (interactionCapabilities != null) {
            this.interactionCapabilities.addAll(interactionCapabilities);
        }
        this.usageCharacteristics = usageCharacteristics;
    }

    public PhysicalDescription getPhysicalProperties()
    {
        return physicalProperties;
    }

    public Collection<InteractionCapability> getInteractionCapabilities()
    {
        return new ArrayList<InteractionCapability>(this.interactionCapabilities);
    }

    public UsageCharacteristics getUsageCharacteristics()
    {
        return usageCharacteristics;
    }
    

}
