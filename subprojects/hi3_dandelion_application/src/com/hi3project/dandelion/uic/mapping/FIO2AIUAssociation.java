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

/** Maps an Abstract Interaction Unit to a list of Final Interaction Objects
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      18-feb-2014
 */
public class FIO2AIUAssociation 
{
    
    private final FIOInteractionMetadata fio;
    private ArrayList<AIUInteractionElement> aiuList;

    
    
    public FIO2AIUAssociation(FIOInteractionMetadata fio) 
    {
        this.fio = fio;
        this.aiuList = new ArrayList<AIUInteractionElement>();
    }

    
    public FIOInteractionMetadata getFio() {
        return this.fio;
    }

    
    public ArrayList<AIUInteractionElement> getAiuList() 
    {
        return new ArrayList<AIUInteractionElement>(this.aiuList);
    }
    
    
    public void addAIU(AIUInteractionElement aiu)
    {
        this.aiuList.add(aiu);
    }
    
    
    public void removeAIU(AIUInteractionElement aiu)
    {
        this.aiuList.remove(aiu);
    }
    
    
    public boolean hasAIU(AIUInteractionElement aiu)
    {
        return this.aiuList.contains(aiu);
    }

    
    public boolean isFIO(FIOMetadata fio)
    {
        return this.fio.equals(fio);
    }

}
