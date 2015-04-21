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

import com.hi3project.dandelion.application.aui.ApplicationAUI;
import com.hi3project.dandelion.fio.FIOMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;

/** Manages the mapping between AbstractInteractionUnits and FIOs and their
 * reverse mapping.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public class FIOMap 
{
    
    
    private final HashMap<AIUInteractionElement, AIU2FIOAssociation> aiu2fioMap; //forward mapping
    private final HashMap<FIOInteractionMetadata, FIO2AIUAssociation> fio2aiuMap; //reverse mapping

    
    public FIOMap(ApplicationAUI appAUI) 
    {
        
        //init the map to the number of AIUs in the model
        this.aiu2fioMap = 
                new HashMap<AIUInteractionElement, AIU2FIOAssociation>(
                    (int)Math.ceil(appAUI.getAbstractInteractionUnitCount()*1.5));        
        this.fio2aiuMap = 
                new HashMap<FIOInteractionMetadata, FIO2AIUAssociation>(
                    (int)Math.ceil(appAUI.getAbstractInteractionUnitCount()*2.5));
        
    }
    
    
    /** Establishes and stores a mapping between an AIU interaction support
     * and a FIO and viceversa.
     * @param aiu
     * @param interaction
     * @param fioMetadata
     * @param interactionId
     */
    public void mapAIU2FIO(
            AbstractInteractionUnit aiu, 
            InteractionSupportElement interaction, 
            FIOMetadata fioMetadata, String interactionId)
    {
        
        AIUInteractionElement aiuInteraction = new AIUInteractionElement(aiu, interaction);
        FIOInteractionMetadata fio = new FIOInteractionMetadata(fioMetadata, interactionId);
        
        AIU2FIOAssociation aiu2fio = this.aiu2fioMap.get(aiuInteraction);
        FIO2AIUAssociation fio2aiu = this.fio2aiuMap.get(fio);
        
        //check if the aiu/fio already has some mapping
        if (aiu2fio == null) {
            aiu2fio = new AIU2FIOAssociation(aiuInteraction);
            this.aiu2fioMap.put(aiuInteraction, aiu2fio);
        }
        if (fio2aiu == null) {
            fio2aiu = new FIO2AIUAssociation(fio);
            this.fio2aiuMap.put(fio, fio2aiu);
        }
        
        
        //check if the new mapping was already known
        if (!aiu2fio.hasFIO(fio)) {
            aiu2fio.addFIO(fio);
        }        
        if (!fio2aiu.hasAIU(aiuInteraction)) {
            fio2aiu.addAIU(aiuInteraction);
        }
        
    }
    
    
    /** Removes the mapping between an AIU and a FIO, viceversa
     * @param aiu
     * @param interaction
     * @param fioMetadata
     * @param interactionId
     */
    public void unmapAIU2FIO(
            AbstractInteractionUnit aiu,
            InteractionSupportElement interaction,
            FIOMetadata fioMetadata, String interactionId)
    {
        
        AIUInteractionElement aiuInteraction = new AIUInteractionElement(aiu, interaction);
        FIOInteractionMetadata fio = new FIOInteractionMetadata(fioMetadata, interactionId);
        
        AIU2FIOAssociation aiu2fio = this.aiu2fioMap.get(aiuInteraction);
        FIO2AIUAssociation fio2aiu = this.fio2aiuMap.get(fio);
        
        if (aiu2fio != null) {
            aiu2fio.removeFIO(fio);
            aiu2fio.getFioList().isEmpty(); //not mapped anymore
            this.aiu2fioMap.remove(aiuInteraction);
        }
        if (fio2aiu != null) {
            fio2aiu.removeAIU(aiuInteraction);
            fio2aiu.getAiuList().isEmpty(); //not mapped anymore
            this.fio2aiuMap.remove(fio);
        }        
        
    }
    
    
    public ArrayList<AIUInteractionElement> getAssociatedAIUs(FIOMetadata fioMetadata, String interactionId)
    {
        
        FIOInteractionMetadata fio = new FIOInteractionMetadata(fioMetadata, interactionId);
        
        FIO2AIUAssociation fio2aiu = this.fio2aiuMap.get(fio);
        if (fio2aiu != null) {
            return new ArrayList<AIUInteractionElement>(fio2aiu.getAiuList());
        }
        else {
            return new ArrayList<AIUInteractionElement>(0);
        }
    }
    
    
    public ArrayList<FIOInteractionMetadata> getAssociatedFIOs(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction)
    {
        
        AIUInteractionElement aiuInteraction = new AIUInteractionElement(aiu, interaction);
        
        AIU2FIOAssociation aiu2fio = this.aiu2fioMap.get(aiuInteraction);
        if (aiu2fio != null) {
            return new ArrayList<FIOInteractionMetadata>(aiu2fio.getFioList());
        }
        else {
            return new ArrayList<FIOInteractionMetadata>(0);
        }
    }
    

}
