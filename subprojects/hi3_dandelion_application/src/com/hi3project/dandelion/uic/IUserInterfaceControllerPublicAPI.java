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
 
 package com.hi3project.dandelion.uic;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.uic.callback.IDandelionActionCallback;
import com.hi3project.dandelion.uic.callback.IDandelionInputCallback;
import com.hi3project.dandelion.uic.callback.IDandelionSelectionCallback;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.DataSelectionSupport;
import org.usixml.model.aui.event.TriggerSupport;


/** This is the publict API of the Dandelion User Interface Controller.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public interface IUserInterfaceControllerPublicAPI 
{
    
    
    public void start() throws InternalErrorException;
    
    
    /** Retrieves the ApplicationMetadata object for the applicaiton
     * managed by this controller.
     * 
     * @return the ApplicationMetadata object for the applicaiton
     * managed by this controller
     */
    public ApplicationMetadata getApplicationMetadata();
    
    
    
    /** Marks an AbstractInteractionUnit to be managed by this UIC. 
     * For every managed AIU, the IUC will manage the mapping between
     * the AIU and a FIO implementing the AIU.
     * 
     * @param interaction
     * @param aiu the AIU to manage
     */
    public void manageAbstractInteractionUnit(
            DataInputOutputSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints);
    
    public void manageAbstractInteractionUnit(
            DataSelectionSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints);
    
    public void manageAbstractInteractionUnit(
            TriggerSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints);
    
    
    
    /** Retrieve the collection of AIUs managed by this UIC
     * 
     * @return the collection of AIUs managed by this UIC
     */
    public Collection<AbstractInteractionUnit> getManagedInteractionUnits();
    
    public InteractionSpecification getManagedAIUInteractionSpecification(
            AbstractInteractionUnit aiu, InteractionSupportElement ise);
    
    
    

    /** Registers a callback object in order to receive notifications of 
     * user input in the specified AIU
     * 
     * @param inputAIU the AIU to be monitored for inputs
     * @param ios the particular I/O element of the AIU associated to the callback
     * @param inputCallback the callback object to be notified
     */
    public void registerInputCallback(AbstractInteractionUnit inputAIU, 
                                      DataInputOutputSupport ios,
                                      IDandelionInputCallback inputCallback);
    
    
        
    /** Registers a callback object in order to receive notifications of
     * user actions in the specified AIU
     * 
     * @param actionAIU the AIU to be monitored for actions
     * @param ts the particular Trigger element associated to the callback
     * @param actionCallback the callback object to be notified
     */
    public void registerActionCallback(AbstractInteractionUnit actionAIU,
                                       TriggerSupport ts,
                                       IDandelionActionCallback actionCallback);
    
    
    
    /** Registers a callback object in order to receive notifications of
     * user selections in the specified AIU
     * 
     * @param actionAIU the AIU to be monitored for actions
     * @param dss the particular selection elemento associated to the callback
     * @param selectionCallback the callback object to be notified
     */
    public void registerSelectionCallback(AbstractInteractionUnit actionAIU,
                                          DataSelectionSupport dss,
                                          IDandelionSelectionCallback selectionCallback);
    
    
    
    
    

    
    /** Sends a FOCUS event to all the FIOs mapped to the specified AIU
     * 
     * @param aiu the AIU that requires focus
     * @param interaction
     * @param interactionHints set of interaction hints to customize the focus
     * @throws com.hi3project.dandelion.gip.exception.CommunicationErrorException
     */
    public void gainFocus(AbstractInteractionUnit aiu, 
                          InteractionSupportElement interaction,
                          Set<FuzzyVariable> interactionHints)
            throws CommunicationErrorException;
    
    
    /** Sends an OUTPUT event to all the FIOs mapped to the specified AIU
     * 
     * @param pojo the POJO object with the information to output
     * @param aiu the AIU to perform the output
     * @param dataios
     * @param interactionHints set of interaction hints to customize the output
     * @throws com.hi3project.dandelion.gip.exception.CommunicationErrorException
     */
    public void showOutput(Property pojo, 
                           AbstractInteractionUnit aiu, DataInputOutputSupport dataios,
                           Set<FuzzyVariable> interactionHints)
            throws CommunicationErrorException;
    
    
    /** Sends a SELECTION event to all the FIOs mapped to the specified AIU
     * 
     * @param defaultElement the element selected by default
     * @param elementList the list of elements of the selection
     * @param aiu
     * @param datass
     * @param interactionHints set of interaction hints to customize the interaction
     * @throws com.hi3project.dandelion.gip.exception.CommunicationErrorException
     */
    public void showSelection(Property defaultElement, ArrayList<Property> elementList, 
                              AbstractInteractionUnit aiu, DataSelectionSupport datass,
                              Set<FuzzyVariable> interactionHints)
            throws CommunicationErrorException;
    
}
