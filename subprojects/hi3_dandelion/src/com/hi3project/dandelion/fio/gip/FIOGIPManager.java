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
import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.gip.IGIPManager;
import com.hi3project.dandelion.gip.comm.publish.IGIPEventPublisher;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/** This class manages the GIP protocol for Final Interaction Object implementations
 *
 * @author gervasio - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      13-feb-2014
 */
public class FIOGIPManager
{
  
    private final FIOMetadata fioMetadata;
    
    private final IGIPManager gipManager;
    
    private FIOGIPEventPublisher fioGipPublisher;
    private FIOGIPEventHandler fioGipReceiver;
    
    private final HashMap<String, IOutputAction> outputActions;
    private final HashMap<String, IFocusAction> focusActions;
    private final HashMap<String, ISelectionAction> selectionActions;

    
    public FIOGIPManager(
            FIOMetadata fioMetadata,
            IGIPManager gipManager) 
    {
        this.fioMetadata = fioMetadata;                
        
        this.gipManager = gipManager;

        this.outputActions = new HashMap<String, IOutputAction>();
        this.focusActions = new HashMap<String, IFocusAction>();
        this.selectionActions = new HashMap<String, ISelectionAction>();
        
    }
    
    
    public void addOutputAction(IOutputAction output)
    {
        this.outputActions.put(output.getId(), output);
    }
    
    public void addOutputActions(Collection<IOutputAction> outputs)
    {
        for(IOutputAction o : outputs) {
            this.outputActions.put(o.getId(), o);
        }
    }
    
    
    public void addFocusAction(IFocusAction focus)
    {
        this.focusActions.put(focus.getId(), focus);
    }
    
    public void addFocusActions(Collection<IFocusAction> focuss)
    {
        for(IFocusAction f : focuss) {
            this.focusActions.put(f.getId(), f);
        }
    }
    
    
    public void addSelectionAction(ISelectionAction selection)
    {
        this.selectionActions.put(selection.getId(), selection);
    }
    
    public void addSelectionActions(Collection<ISelectionAction> selections)
    {
        for(ISelectionAction s : selections) {
            this.selectionActions.put(s.getId(), s);
        }
    }
    
    
    public IOutputAction getOutputAction(String id)
    {
        return this.outputActions.get(id);
    }
    
    public IFocusAction getFocusAction(String id)
    {
        return this.focusActions.get(id);
    }
    
    public ISelectionAction getSelectionAction(String id)
    {
        return this.selectionActions.get(id);
    }
    
    
    
    public void init() throws CommunicationErrorException
    {
        
        //set-up the GIP publisher
        IGIPEventPublisher gipPublisher = this.gipManager.startGIPPublisher(
                fioMetadata.getId(), FIOCommChannelUtil.getCommChannel(fioMetadata), false);        
        this.fioGipPublisher = new FIOGIPEventPublisher(fioMetadata, gipPublisher);        
        
        //set-up the GIP receiver
        this.fioGipReceiver = new FIOGIPEventHandler(this);
        this.gipManager.startGIPReceiver(
                FIOCommChannelUtil.getCommChannel(fioMetadata), false, null, fioGipReceiver, null, fioGipReceiver, fioGipReceiver);
        
    }
    
    

    public void inputHappened(String interactionId, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints) 
            throws CommunicationErrorException 
    {
        this.fioGipPublisher.inputHappened(interactionId, propertySet, fuzzyHints);
    }


    public void selectionHappened(String interactionId, Property selected, ArrayList<Property> propertySet, ArrayList<FuzzyVariable> fuzzyHints)
            throws CommunicationErrorException 
    {
        this.fioGipPublisher.selectionHappened(interactionId, selected, propertySet, fuzzyHints);
    }


    public void actionHappened(String interactionId, ArrayList<FuzzyVariable> fuzzyHints) throws CommunicationErrorException 
    {
        this.fioGipPublisher.actionHappened(interactionId, fuzzyHints);
    }

    
}
