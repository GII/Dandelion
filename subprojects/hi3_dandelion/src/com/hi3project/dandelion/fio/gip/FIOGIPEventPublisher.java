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
 
 package com.hi3project.dandelion.fio.gip;

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.gip.comm.publish.IGIPEventPublisher;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;

/** Implements the GIP publishing requirements of Final Interaction Objects
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-feb-2014 -- Initial version
 */
public class FIOGIPEventPublisher 
{
    
    private final FIOMetadata fioMetadata;
    private final IGIPEventPublisher gipPublisher;

    
    public FIOGIPEventPublisher(FIOMetadata fioMetadata, IGIPEventPublisher gipPublisher) 
    {
        this.fioMetadata = fioMetadata;
        this.gipPublisher = gipPublisher;
    }
    
    
    public void inputHappened(String interactionId, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
                
        gipPublisher.input(interactionId, propertySet, fuzzyHints);
        
    }
    
    
    public void selectionHappened(String interactionId, Property selected, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
                
        gipPublisher.selection(interactionId, propertySet, selected, fuzzyHints);
        
    }
    
    
    public void actionHappened(String interactionId, ArrayList<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException
    {
                
        gipPublisher.action(interactionId, fuzzyHints);
        
    }
     
    
}
