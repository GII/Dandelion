/*******************************************************************************
 *   
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
 
package org.usixml.model.aui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.usixml.model.aui.data.PresentationSupport;
import org.usixml.model.aui.event.EventSupport;


/** The basic unit for expressing any piece of interaction in a recursive
 *  decomposition of an AUI in abstract terms
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class AbstractInteractionUnit 
{
    
    
    /** a unique idenfier */
    private String id; 
        
    /** a logical (no necessary unique) name for the AIU */
    private String name;
    
    /** the role played by the AIU in the AUI */
    private String role;
    
    /** the level of importance of the AIU on five-point scale */
    private int importanceLevel;
    
    /** the sate of the AIU */
    private String state;
    
    /** frequency of usage per time unit (user defined) ex: 5/hour */
    private double frequency;
    
    /** how many times the AIU is repetead in a composition with a parent AIU*/
    private int repetitionFactor;
    
    /** a reference to a domain object */
    private String domaingReference;
    
   
        
    private Set<Composition> compositions;
    
    
    private Set<PresentationSupport> presentationInteractionResources;
    
    private Set<EventSupport> eventInteractionResources;
    


    public AbstractInteractionUnit(String id, String name, int importanceLevel)
    {
        this.id = id;
        this.name = name;
        this.importanceLevel = importanceLevel;
        this.compositions = new HashSet<Composition>();
        this.presentationInteractionResources = new HashSet<PresentationSupport>();
        this.eventInteractionResources = new HashSet<EventSupport>();
    }

            
    public AbstractInteractionUnit(
            String id, String name, String role, int importanceLevel, String state, 
            double frequency, int repetitionFactor, String domaingReference, 
            Set<Composition> compositions, 
            Set<PresentationSupport> dataInteractionResources, 
            Set<EventSupport> eventInteractionResources)
    {
        this(id, name, importanceLevel);
        this.role = role;
        this.state = state;
        this.frequency = frequency;
        this.repetitionFactor = repetitionFactor;
        this.domaingReference = domaingReference;
        this.compositions.addAll(compositions);
        this.presentationInteractionResources.addAll(dataInteractionResources);
        this.eventInteractionResources.addAll(eventInteractionResources);
    }
    

    public String getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public String getRole()
    {
        return role;
    }


    public int getImportanceLevel()
    {
        return importanceLevel;
    }


    public String getState()
    {
        return state;
    }


    public double getFrequency()
    {
        return frequency;
    }


    public int getRepetitionFactor()
    {
        return repetitionFactor;
    }


    public String getDomaingReference()
    {
        return domaingReference;
    }


    
    public InteractionSupportElement getInteractionSupportElementById(String id)
    {
        InteractionSupportElement ise = getPresentationSupportById(id);
        if (ise == null) {
            ise = getEventSupportById(id);
        }
        
        return ise; 
        
    }
    
    
    public Collection<PresentationSupport> getPresentationSupportList()
    {
        return new ArrayList<PresentationSupport>(this.presentationInteractionResources);
    }
    
    public PresentationSupport getPresentationSupportById(String id)
    {
        for(PresentationSupport ps : this.presentationInteractionResources) {
            if (ps.getId().equals(id)) {
                return ps;
            }
        }
        
        return null;
    }
    
    
    public void addPresentationSupport(PresentationSupport dataSupport)
    {
        this.presentationInteractionResources.add(dataSupport);
    }
    
    
    public void removePresentationSupport(PresentationSupport dataSupport)
    {
        this.presentationInteractionResources.remove(dataSupport);
    }
    
    
    public Collection<EventSupport> getEventSupportList()
    {
        return new ArrayList<EventSupport>(this.eventInteractionResources);
    }
    
    public EventSupport getEventSupportById(String id)
    {
        for(EventSupport es : this.eventInteractionResources) {
            if (es.getId().equals(id)) {
                return es;
            }
        }
        
        return null;
    }
    
    
    public void addEventSupport(EventSupport eventSupport)
    {
        this.eventInteractionResources.add(eventSupport);
    }
    
    
    public void removeEventSupport(EventSupport eventSupport)
    {
        this.eventInteractionResources.remove(eventSupport);
    }
    
    
    public boolean isCompound()
    {
        return this.compositions.isEmpty();
    }
    
    
    public void addComposition(Composition composition)
    {
        this.compositions.add(composition);
    }
    
    
    public void removeComposition(Composition composition)
    {
        this.compositions.remove(composition);
    }
    
    
    public Collection<Composition> getCompositions()
    {
        return new ArrayList<Composition>(this.compositions);
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractInteractionUnit other = (AbstractInteractionUnit) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    
    
    
    

}
