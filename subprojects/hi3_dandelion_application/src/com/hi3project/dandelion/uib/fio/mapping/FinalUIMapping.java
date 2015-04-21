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
 
 package com.hi3project.dandelion.uib.fio.mapping;

import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import java.util.ArrayList;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      26-feb-2015
 */
public class FinalUIMapping 
{
    
    ArrayList<FinalInteractionFacetMapping> mappingList;

    public FinalUIMapping()
    {
        this.mappingList = new ArrayList<FinalInteractionFacetMapping>();
    }
    
    
    public FinalInteractionFacetMapping getFacetMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement facet)
    {
        for(FinalInteractionFacetMapping facetMapping : mappingList) {
            if (facetMapping.getAiu().getId().equals(aiu.getId())
                && facetMapping.getInteractionFacet().getId().equals(facet.getId())) {
                return facetMapping;
            }
        }
        return null;
    }
    
    
    public synchronized void addMapping(FinalInteractionFacetMapping facetMapping)
    {
        
        FinalInteractionFacetMapping fm = 
                getFacetMapping(facetMapping.getAiu(), facetMapping.getInteractionFacet());
        
        if (fm != null) {
            this.mappingList.remove(fm);
        }
        
        this.mappingList.add(facetMapping);
        
    }
    
    
    public synchronized void addMapping(
            AbstractInteractionUnit aiu, InteractionSupportElement facet,
            FIORepositoryEntryCompliance fio)
    {
        FinalInteractionFacetMapping fm = 
                getFacetMapping(aiu, facet);
        
        if (fm != null) {
            this.mappingList.remove(fm);
        }
        
        this.mappingList.add(new FinalInteractionFacetMapping(aiu, facet, fio));
        
    }
    
    
    public synchronized void removeMapping(AbstractInteractionUnit aiu, InteractionSupportElement facet)
    {
        FinalInteractionFacetMapping fm = 
                getFacetMapping(aiu, facet);
        
        if (fm != null) {
            this.mappingList.remove(fm);
        }
    }
    
    public synchronized ArrayList<FinalInteractionFacetMapping> getMapping()
    {
        return new ArrayList<FinalInteractionFacetMapping>(this.mappingList);
    }

}
