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

import com.hi3project.dandelion.uic.callback.IDandelionActionCallback;
import com.hi3project.dandelion.uic.callback.IDandelionInputCallback;
import com.hi3project.dandelion.uic.callback.IDandelionSelectionCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.DataSelectionSupport;
import org.usixml.model.aui.event.TriggerSupport;

/**
 * Stores de associations between the interaction elements of an AIU (input, output, selection, etc.)
 * and their callbacks.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog: 03-dic-2014
 */
public class ManagedAbstractInteractionUnit
{

    private final HashMap<InteractionSupportElement, ArrayList<IDandelionInputCallback>> inputOutputSupport;
    private final HashMap<InteractionSupportElement, ArrayList<IDandelionSelectionCallback>> selectionSupport;
    private final HashMap<InteractionSupportElement, ArrayList<IDandelionActionCallback>> triggerSupport;

    private final AbstractInteractionUnit aiu;
    
    

    public ManagedAbstractInteractionUnit(AbstractInteractionUnit aiu)
    {

        this.aiu = aiu;

        this.inputOutputSupport = new HashMap<InteractionSupportElement, ArrayList<IDandelionInputCallback>>(2);
        this.selectionSupport = new HashMap<InteractionSupportElement, ArrayList<IDandelionSelectionCallback>>(2);
        this.triggerSupport = new HashMap<InteractionSupportElement, ArrayList<IDandelionActionCallback>>(2);

    }

    public AbstractInteractionUnit getAiu()
    {
        return aiu;
    }
    
    
    public void manageDataInputOutputSupport(DataInputOutputSupport dataios)
    {
        
        if (!this.inputOutputSupport.containsKey(dataios)) {
            this.inputOutputSupport.put(dataios, new ArrayList<IDandelionInputCallback>(2));
        }
        
    }
    
    public void manageDataSelectionSupport(DataSelectionSupport datass)
    {
        
        if (!this.selectionSupport.containsKey(datass)) {
            this.selectionSupport.put(datass, new ArrayList<IDandelionSelectionCallback>(2));
        }
        
    }
    
    public void manageTriggerSupport(TriggerSupport ts)
    {
        
        if (!this.triggerSupport.containsKey(ts)) {
            this.triggerSupport.put(ts, new ArrayList<IDandelionActionCallback>(2));
        }
        
    }
    
    
    
    public void addInputCallback(DataInputOutputSupport dataios, IDandelionInputCallback inCallback)
    {
        ArrayList<IDandelionInputCallback> inCallbackList = this.inputOutputSupport.get(dataios);
        if (inCallbackList != null) {
            inCallbackList.add(inCallback);
        }
        else {
            manageDataInputOutputSupport(dataios);
            addInputCallback(dataios, inCallback);
        }
    }
    
    
    public void addSelectionCallback(DataSelectionSupport datass, IDandelionSelectionCallback selCallback)
    {
        ArrayList<IDandelionSelectionCallback> selectionCallbackList = this.selectionSupport.get(datass);
        if (selectionCallbackList != null) {
            selectionCallbackList.add(selCallback);
        }
        else {
            manageDataSelectionSupport(datass);
            addSelectionCallback(datass, selCallback);
        }
    }
    
    
    public void addActionCallback(TriggerSupport ts, IDandelionActionCallback actionCallback)
    {
        ArrayList<IDandelionActionCallback> actionCallbackList = this.triggerSupport.get(ts);
        if (actionCallbackList != null) {
            actionCallbackList.add(actionCallback);
        }
        else {
            manageTriggerSupport(ts);
            addActionCallback(ts, actionCallback);
        }
    }
    
    
    public ArrayList<DataInputOutputSupport> getManagedDataInputSupportList()
    {
        Set<InteractionSupportElement> keySet = this.inputOutputSupport.keySet();
        ArrayList<DataInputOutputSupport> result = new ArrayList<DataInputOutputSupport>(keySet.size());
        
        for(InteractionSupportElement ise : keySet) {
            //only DataSelectionSupport object are added to this.triggerSupport collection
            result.add((DataInputOutputSupport) ise);
        }
        return result;
    }
    
    public ArrayList<IDandelionInputCallback> getInputCallbackList(
            InteractionSupportElement interaciton)
    {
        return this.inputOutputSupport.get(interaciton);
    }
    
    
    
    public ArrayList<DataSelectionSupport> getManagedDataSelectionSupportList()
    {
        Set<InteractionSupportElement> keySet = this.selectionSupport.keySet();
        ArrayList<DataSelectionSupport> result = new ArrayList<DataSelectionSupport>(keySet.size());
        
        for(InteractionSupportElement ise : keySet) {
            //only DataSelectionSupport object are added to this.triggerSupport collection
            result.add((DataSelectionSupport) ise);
        }
        return result;
    }
    
    public ArrayList<IDandelionSelectionCallback> getSelectionCallbackList(
            InteractionSupportElement interaction)
    {
        return this.selectionSupport.get(interaction);
    }
    
    
    
    public ArrayList<TriggerSupport> getManagedTriggerSupportList()
    {
        Set<InteractionSupportElement> keySet = this.triggerSupport.keySet();
        ArrayList<TriggerSupport> result = new ArrayList<TriggerSupport>(keySet.size());
        
        for(InteractionSupportElement ise : keySet) {
            //only TriggerSupport object are added to this.triggerSupport collection
            result.add((TriggerSupport) ise); 
        }
        
        return result;
    }
    
    public ArrayList<IDandelionActionCallback> getActionCallbackList(
            InteractionSupportElement interaction)
    {
        return this.triggerSupport.get(interaction);
    }
    

}
