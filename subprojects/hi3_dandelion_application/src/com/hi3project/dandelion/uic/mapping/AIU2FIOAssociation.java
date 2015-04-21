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

import com.hi3project.dandelion.fio.FIOMetadata;
import java.util.ArrayList;
import org.usixml.model.aui.AbstractInteractionUnit;

/** Maps an Abstract Interaction Unit to a list of Final Interaction Objects
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      18-feb-2014
 */
public class AIU2FIOAssociation 
{
    
    private final AIUInteractionElement aiu;
    private final ArrayList<FIOInteractionMetadata> fioList;

    
    
    public AIU2FIOAssociation(AIUInteractionElement aiu) 
    {
        this.aiu = aiu;
        this.fioList = new ArrayList<FIOInteractionMetadata>();
    }

    
    public AIUInteractionElement getAIUInteractionSupport() {
        return aiu;
    }

    
    public ArrayList<FIOInteractionMetadata> getFioList() 
    {
        return new ArrayList<FIOInteractionMetadata>(fioList);
    }
    
    
    public void addFIO(FIOInteractionMetadata fio)
    {
        this.fioList.add(fio);
    }
    
    
    public void removeFIO(FIOInteractionMetadata fio)
    {
        this.fioList.remove(fio);
    }
    
    
    public boolean hasFIO(FIOInteractionMetadata fio)
    {
        return this.fioList.contains(fio);
    }

    
    public boolean isAIUInteractionSupport(AIUInteractionElement aiu)
    {
        return this.aiu.equals(aiu);
    }

}
