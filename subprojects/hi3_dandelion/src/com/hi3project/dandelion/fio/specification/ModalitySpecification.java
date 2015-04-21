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
 
 package com.hi3project.dandelion.fio.specification;

import com.hi3project.dandelion.fio.description.modality.Modality;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/** Sorted (best to worse) list of modalities and their associated granularities
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class ModalitySpecification 
{

    private final ArrayList<Modality> modalities;

    public ModalitySpecification(Collection<Modality> modalities)
    {
        this.modalities = new ArrayList<Modality>(modalities);
        Collections.sort(this.modalities, new ModalitySortingComparator());
    }
    
    
    public Collection<Modality> getModalities()
    {
        return new ArrayList<Modality>(this.modalities);
    }
    
    
    
    private class ModalitySortingComparator implements Comparator<Modality>
    {

        @Override
        public int compare(Modality o1, Modality o2)
        {
            
            return (int) Math.ceil(o1.getGranularity() - o2.getGranularity());
            
        }
        
    }

    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        for(Modality m : modalities) {
            sb.append(m.getType());
            sb.append(" - ");
            sb.append(m.getGranularity());
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    
    
    
    
}
