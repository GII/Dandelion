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

import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.EnumSet;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.DataSelectionSupport;
import org.usixml.model.aui.data.EnumDataType;
import org.usixml.model.aui.event.TriggerSupport;


/** This implementatio does not support composite AIUs, so it only build
 * an InteractionSpecification using the information of Presentation and Event
 * support of the specified aiu object.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-sep-2014
 */
public class DefaultFIOInteractionSpecificationBuilder implements IFIOInteractionSpecificationBuilder
{

    public DefaultFIOInteractionSpecificationBuilder()
    {
    }

    
    
    private InteractionSupport createInteractionSupport(DataInputOutputSupport io)
    {
        
        PropertyType propertyType = convertDataType2PropertyType(io.getDataType());
        return new InteractionSupport(createInteractionType(io, true), propertyType, io.getMaxCardinality());
        
    }
    
    
    private InteractionSupport createInteractionSupport(DataSelectionSupport selection)
    {
        
        EnumSet<InteractionType> interactionType = EnumSet.of(InteractionType.selection, InteractionType.focus);
        PropertyType propertyType = convertDataType2PropertyType(selection.getDataType());
        return new InteractionSupport(interactionType, propertyType, selection.getEnd() - selection.getStart());
        
    }
    
    
    private InteractionSupport createInteractionSupport(TriggerSupport trigger)
    {
        
        return new InteractionSupport(EnumSet.of(InteractionType.action, InteractionType.focus), null, 1);
        
    }
    
    
    private EnumSet<InteractionType> createInteractionType(DataInputOutputSupport io, boolean focus)
    {
        
        EnumSet<InteractionType> result;
        
        if (io.isInputSupport() && io.isOutputSupport()) {
            result = EnumSet.of(InteractionType.input, InteractionType.output);
        }
        else if (io.isInputSupport()) {
            result = EnumSet.of(InteractionType.input);
        }
        else if (io.isOutputSupport()) {
            result = EnumSet.of(InteractionType.output);
        }
        else {
            result = EnumSet.of(InteractionType.undefined);
        }
        
        if (focus) result.add(InteractionType.focus);
        
        return result;
        
    }
    
    
    private PropertyType convertDataType2PropertyType(EnumDataType dataType)
    {
        
        switch(dataType) {
            
            case BOOLEAN:
                return PropertyType.booleanProperty;
            case INTEGER:
                return PropertyType.numberProperty;
            case NATURAL:
                return PropertyType.numberProperty;
            case REAL:
                return PropertyType.numberProperty;
            case TEXT:
                return PropertyType.stringProperty;
            case DATE:
                return PropertyType.stringProperty;
            case HOUR:
                return PropertyType.stringProperty;
            case SECRET:
                return PropertyType.stringProperty;
            case MEDIA_IMAGE:
                return PropertyType.imageProperty;
            default:
                return PropertyType.stringProperty;
            
        }
        
    } 

    @Override
    public InteractionSpecification buildInteractionSpecification(DataInputOutputSupport interaction, AbstractInteractionUnit aiu)
    {
        return new InteractionSpecification(createInteractionSupport(interaction));
    }

    @Override
    public InteractionSpecification buildInteractionSpecification(DataSelectionSupport interaction, AbstractInteractionUnit aiu)
    {
        return new InteractionSpecification(createInteractionSupport(interaction));
    }

    @Override
    public InteractionSpecification buildInteractionSpecification(TriggerSupport interaction, AbstractInteractionUnit aiu)
    {
        return new InteractionSpecification(createInteractionSupport(interaction));
    }
           

}
