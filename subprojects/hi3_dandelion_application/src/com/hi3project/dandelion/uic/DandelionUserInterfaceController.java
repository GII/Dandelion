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
import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.gip.IGIPManager;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.uib.fio.specification.interaction.DefaultFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.IFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uic.callback.IDandelionActionCallback;
import com.hi3project.dandelion.uic.callback.IDandelionInputCallback;
import com.hi3project.dandelion.uic.callback.IDandelionSelectionCallback;
import com.hi3project.dandelion.uic.exception.CommunicationErrorWithFIOsException;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.uic.gip.UserInterfaceControllerGIPManager;
import com.hi3project.dandelion.uic.gip.handlers.UICActionEventHandler;
import com.hi3project.dandelion.uic.gip.handlers.UICInputEventHandler;
import com.hi3project.dandelion.uic.gip.handlers.UICSelectionEventHandler;
import com.hi3project.dandelion.uic.mapping.AIUInteractionElement;
import com.hi3project.dandelion.uic.mapping.FIOInteractionMetadata;
import com.hi3project.dandelion.uic.mapping.FIOMap;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.DataSelectionSupport;
import org.usixml.model.aui.event.TriggerSupport;

/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public class DandelionUserInterfaceController implements IUserInterfaceController
{

    
    private int applicationId;
    private final ApplicationMetadata application;
    
    private final FIOMap fioMap;
    

    private final HashMap<AbstractInteractionUnit, ManagedAbstractInteractionUnit> managedAIUs;
    
    private final UserInterfaceControllerGIPManager uicGipManager;
    
    private final IFIOInteractionSpecificationBuilder interactionSpecBuilder;
    
    
    
    /** Builds a new UIC controller for an application.
     * 
     * @param application the applicaition metadata
     * @param gipManager an implementation of the IGIPManager interface to support the
     * communication between the UIC and the FIOs
     */
    public DandelionUserInterfaceController(
            ApplicationMetadata application, IGIPManager gipManager)
    {
        
        this.application = application;
        
        this.fioMap = new FIOMap(this.application.getAbstractUI());
        
        this.interactionSpecBuilder = new DefaultFIOInteractionSpecificationBuilder();        
        
        this.managedAIUs = new HashMap<AbstractInteractionUnit, ManagedAbstractInteractionUnit>();
        
        this.uicGipManager = new UserInterfaceControllerGIPManager(
                "uic-"+this.application.getName()+"-"+System.currentTimeMillis(), 
                gipManager,
                new UICActionEventHandler(this),
                new UICInputEventHandler(this),
                new UICSelectionEventHandler(this));
        
    }
    
    
    
    @Override
    public void start() throws InternalErrorException
    {
        
        Logging.logger.log(Level.INFO, "Starting Dandelion UIC for application {0}-{1}", new Object[]{this.application.getName(), this.applicationId});
        
    }

    
    @Override
    public ApplicationMetadata getApplicationMetadata()
    {
        return this.application;
    }

    
    private ManagedAbstractInteractionUnit manageAbstractInteractionUnit(AbstractInteractionUnit aiu)
    {
        
        ManagedAbstractInteractionUnit result = this.managedAIUs.get(aiu);
        
        if (result == null) {
            result = new ManagedAbstractInteractionUnit(aiu);
            this.managedAIUs.put(aiu, result);
        }
        
        return result;
        
    }
    
    
    @Override
    public void manageAbstractInteractionUnit(DataInputOutputSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints)
    {
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(aiu);
        managedAiu.manageDataInputOutputSupport(interaction);
    }

    @Override
    public void manageAbstractInteractionUnit(DataSelectionSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints)
    {
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(aiu);
        managedAiu.manageDataSelectionSupport(interaction);
    }

    @Override
    public void manageAbstractInteractionUnit(TriggerSupport interaction, AbstractInteractionUnit aiu, Set<FuzzyVariable> interactionHints)
    {
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(aiu);
        managedAiu.manageTriggerSupport(interaction);
    }

    
    


    @Override
    public Collection<AbstractInteractionUnit> getManagedInteractionUnits()
    {
        return new ArrayList<AbstractInteractionUnit>(this.managedAIUs.keySet());
    }

    
    @Override
    public InteractionSpecification getManagedAIUInteractionSpecification(
            AbstractInteractionUnit aiu, InteractionSupportElement ise)
    {
                
        ManagedAbstractInteractionUnit mAiu = this.managedAIUs.get(aiu);
        
        for(DataInputOutputSupport dataios : mAiu.getManagedDataInputSupportList()) {
            
            if (dataios.equals(ise)) {
                return this.interactionSpecBuilder.buildInteractionSpecification(dataios, aiu);
            }            
            
        }
        
        for(DataSelectionSupport dss : mAiu.getManagedDataSelectionSupportList()) {
            
            if (dss.equals(ise)) {
                return this.interactionSpecBuilder.buildInteractionSpecification(dss, aiu);
            } 
            
        }
        
        for(TriggerSupport ts : mAiu.getManagedTriggerSupportList()) {
            
            if (ts.equals(ise)) {
                return this.interactionSpecBuilder.buildInteractionSpecification(ts, aiu);
            } 
            
        }
        
        return null;
        
    }
    
   
    
    
    @Override
    public void registerInputCallback(
            AbstractInteractionUnit inputAIU,
            DataInputOutputSupport ios, 
            IDandelionInputCallback inputCallback)
    {
        
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(inputAIU);
        
        managedAiu.addInputCallback(ios, inputCallback);        
        
    }

    @Override
    public void registerActionCallback(
            AbstractInteractionUnit actionAIU, 
            TriggerSupport ts,
            IDandelionActionCallback actionCallback)
    {
        
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(actionAIU);
        
        managedAiu.addActionCallback(ts, actionCallback);
        
    }

    @Override
    public void registerSelectionCallback(
            AbstractInteractionUnit selectionAIU, 
            DataSelectionSupport dss,
            IDandelionSelectionCallback selectionCallback)
    {
        
        ManagedAbstractInteractionUnit managedAiu = manageAbstractInteractionUnit(selectionAIU);
        
        managedAiu.addSelectionCallback(dss, selectionCallback);
        
    }
    
    
    
    
    @Override
    public ArrayList<IDandelionInputCallback> getAssociatedInputCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction)
    {
        
        ManagedAbstractInteractionUnit mAIU = this.managedAIUs.get(aiu);
        ArrayList<IDandelionInputCallback> result = new ArrayList<IDandelionInputCallback>();
        
        if (mAIU != null) {
            ArrayList<IDandelionInputCallback> inCbacks = mAIU.getInputCallbackList(interaction);
            if (inCbacks != null) {
                result.addAll(inCbacks);
            }
        }
        
        return result;
        
    }
    

    @Override
    public ArrayList<IDandelionActionCallback> getAssociatedActionCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction)
    {
        
        ManagedAbstractInteractionUnit mAIU = this.managedAIUs.get(aiu);
        ArrayList<IDandelionActionCallback> result = new ArrayList<IDandelionActionCallback>();
        
        if (mAIU != null) {
            ArrayList<IDandelionActionCallback> inCbacks = mAIU.getActionCallbackList(interaction);
            if (inCbacks != null) {
                result.addAll(inCbacks);
            }
        }
        
        return result;
        
    }
    

    @Override
    public ArrayList<IDandelionSelectionCallback> getAssociatedSelectionCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction)
    {
        
        ManagedAbstractInteractionUnit mAIU = this.managedAIUs.get(aiu);
        ArrayList<IDandelionSelectionCallback> result = new ArrayList<IDandelionSelectionCallback>();
        
        if (mAIU != null) {
            ArrayList<IDandelionSelectionCallback> inCbacks = mAIU.getSelectionCallbackList(interaction);
            if (inCbacks != null) {
                result.addAll(inCbacks);
            }
        }
        
        return result;
        
    }

    
    
    
    
    @Override
    public void gainFocus(
            AbstractInteractionUnit aiu, 
            InteractionSupportElement interaction,
            Set<FuzzyVariable> interactionHints) 
            throws CommunicationErrorWithFIOsException
    {
        ArrayList<FIOInteractionMetadata> associatedFIOs = this.fioMap.getAssociatedFIOs(aiu, interaction);
        this.uicGipManager.gainFocus(associatedFIOs, interactionHints);
    }

    @Override
    public void showOutput(Property pojo, 
                           AbstractInteractionUnit aiu, DataInputOutputSupport dataios,
                           Set<FuzzyVariable> interactionHints) 
            throws CommunicationErrorWithFIOsException
    {
        ArrayList<FIOInteractionMetadata> associatedFIOs = this.fioMap.getAssociatedFIOs(aiu, dataios);
        this.uicGipManager.showOutput(pojo, associatedFIOs, interactionHints);       
    }

    @Override
    public void showSelection(Property defaultElement, 
                              ArrayList<Property> elementList,
                              AbstractInteractionUnit aiu, DataSelectionSupport datass,
                              Set<FuzzyVariable> interactionHints) 
            throws CommunicationErrorWithFIOsException
    {
        ArrayList<FIOInteractionMetadata> associatedFIOs = this.fioMap.getAssociatedFIOs(aiu, datass);
        this.uicGipManager.showSelection(defaultElement, elementList, associatedFIOs, interactionHints);
    }

    
    
    
    
    @Override
    public ArrayList<FIOInteractionMetadata> getAssociatedFIOs(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction)
    {
        return this.fioMap.getAssociatedFIOs(aiu, interaction);
    }

    @Override
    public ArrayList<AIUInteractionElement> getAssociatedAIUs(FIOMetadata fio, String interactionId)
    {
        return this.fioMap.getAssociatedAIUs(fio, interactionId);
    }

    @Override
    public void addAIU2FIOMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction,
            FIOMetadata fio, String interactionId) throws MappingNotAvailableException
    {
        
        this.fioMap.mapAIU2FIO(aiu, interaction, fio, interactionId);     
        
        try {
            this.uicGipManager.suscribeToFIO(fio);
        }
        catch(CommunicationErrorException commEx) {
            throw new MappingNotAvailableException(commEx, "Unable to establish communication with FIO");
        }
        
    }

    @Override
    public void removeAIU2FIOMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction,
            FIOMetadata fio, String interactionId)
    {
        
        this.fioMap.unmapAIU2FIO(aiu, interaction, fio, interactionId);
        this.uicGipManager.unsuscribeFromFIO(fio);         
        
    }

    @Override
    public ArrayList<AIUInteractionElement> getAllAssociatedAIUs()
    {
        ArrayList<AIUInteractionElement> aiuIeList = new ArrayList<AIUInteractionElement>();
        
        for(ManagedAbstractInteractionUnit mAIU : this.managedAIUs.values()) {
            
            AbstractInteractionUnit aiu = mAIU.getAiu();
            
            for(DataInputOutputSupport dataios : mAIU.getManagedDataInputSupportList()) {
                aiuIeList.add(new AIUInteractionElement(aiu, dataios));
            }
            
            for(DataSelectionSupport datass : mAIU.getManagedDataSelectionSupportList()) {
                aiuIeList.add(new AIUInteractionElement(aiu, datass));
            }
            
            for(TriggerSupport ts : mAIU.getManagedTriggerSupportList()) {
                aiuIeList.add(new AIUInteractionElement(aiu, ts));
            }
            
        }
        
        return aiuIeList;        
        
    }

  
    
}
