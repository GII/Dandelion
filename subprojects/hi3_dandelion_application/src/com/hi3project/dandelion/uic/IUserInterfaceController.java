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

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.uic.callback.IDandelionActionCallback;
import com.hi3project.dandelion.uic.callback.IDandelionInputCallback;
import com.hi3project.dandelion.uic.callback.IDandelionSelectionCallback;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.uic.mapping.AIUInteractionElement;
import com.hi3project.dandelion.uic.mapping.FIOInteractionMetadata;
import java.util.ArrayList;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** Internal API of the Dandelion User Interface Controller
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public interface IUserInterfaceController extends IUserInterfaceControllerPublicAPI
{


    public ArrayList<AIUInteractionElement> getAllAssociatedAIUs();
    
    
    
    public ArrayList<FIOInteractionMetadata> getAssociatedFIOs(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction);
    
    
    public ArrayList<AIUInteractionElement> getAssociatedAIUs(FIOMetadata fio, String interactionId);
    
    
    
    
    
    public void addAIU2FIOMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction, 
            FIOMetadata fio, String interactionId) throws MappingNotAvailableException;
    
    
    public void removeAIU2FIOMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction, 
            FIOMetadata fio, String interactionId) throws MappingNotAvailableException;
    
    
    
    
    public ArrayList<IDandelionInputCallback> getAssociatedInputCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction);
    
    public ArrayList<IDandelionActionCallback> getAssociatedActionCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction);
    
    public ArrayList<IDandelionSelectionCallback> getAssociatedSelectionCallbacks(
            AbstractInteractionUnit aiu, InteractionSupportElement interaction);
    
    
    
}
