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
 
 package com.hi3project.dandelion.application.aui;

import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.AbstractUserInterfaceModel;




/** Facade to manage/encapsulate the application AUI
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      08-may-2013 -- Initial version
 */
public class ApplicationAUI 
{
    
    
    private final AbstractUserInterfaceModel auiModel;


    public ApplicationAUI(AbstractUserInterfaceModel auiModel)
    {
        this.auiModel = auiModel;
    }
    
    
    public AbstractInteractionUnit getAbstractInteractionUnitById(String aiuId)
    {
        //search an AIU by id the in usixml tree
        return this.auiModel.getAbstractInteractionUnit(aiuId);
    }    
    
    
    public AbstractUserInterfaceModel getAbstractUserInterfaceModel()
    {
        return this.auiModel;
    }
    
    
    public int getAbstractInteractionUnitCount()
    {
        return this.auiModel.getAbstractInteractionUnitCount();
    }
    

}
