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
 
 package com.hi3project.dandelion.uic.callback;

import java.util.ArrayList;
import org.usixml.model.aui.AbstractInteractionUnit;

/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-feb-2014
 */
public class Input2AIUAssociation 
{
    
    private final AbstractInteractionUnit aiu;
    private final ArrayList<IDandelionInputCallback> callbacks;

    
    
    public Input2AIUAssociation(AbstractInteractionUnit aiu) 
    {
        this.aiu = aiu;
        this.callbacks = new ArrayList<IDandelionInputCallback>(2);
    }
    
    
    public void addCallback(IDandelionInputCallback callback)
    {
        this.callbacks.add(callback);
    }
    
    
    public void removeCallback(IDandelionInputCallback callback)
    {
        this.callbacks.remove(callback);
    }

    public AbstractInteractionUnit getAiu() 
    {
        return aiu;
    }

    public ArrayList<IDandelionInputCallback> getCallbacks() 
    {
        return callbacks;
    }
    

}
