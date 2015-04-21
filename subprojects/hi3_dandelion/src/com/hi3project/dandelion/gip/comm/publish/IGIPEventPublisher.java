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
 
 package com.hi3project.dandelion.gip.comm.publish;

import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.Collection;



/** Implementations of this interfaces provide a way to publish GIP messages
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-mar-2013 -- Initial version
 */
public interface IGIPEventPublisher
{

    /** Publishes an input event occurred in a FIO
     * 
     * @param propertySet the propertis with the input data
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void input(String interactionId, Collection<Property> propertySet, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    
    /** Publishes an output event occurred in the application logic
     * 
     * @param propertySet the propertis with the input data
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void output(String interactionId, Collection<Property> propertySet, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    /** Publishes a request event (of an application) to show a selection in a FIO
     * 
     * @param selectionList items available for selection
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void selection(String interactionId, Collection<Property> selectionList, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    /** Publishes selection event occurred in a FIO
     * 
     * @param selectionList items available for selection
     * @param selectedItem item selected by the user
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void selection(String interactionId, Collection<Property> selectionList, Property selectedItem, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    /** Publishes request event (from an application) to put the focus in a FIO
     * 
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void focus(String interactionId, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    /** Publishes an action event indicating that the user has activated a FIO
     * 
     * @param fuzzyHints fuzzy hints associated to the event
     */
    public void action(String interactionId, Collection<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException;
    
    
    /** After the execution of this method the publisher will be unable to
     * publish new events.
     */
    public void stopPublishing();
    
    
    @Override
    public boolean equals(Object object);
    
    
}
