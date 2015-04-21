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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/** Defines how a composite AbstractInteractionUnit is composed
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class Composition 
{

    /** describes the rationale of the composition*/
    private final String rational;
    
    /* describes the role of the composition */
    private final String role;
    
    
    private final Set<AbstractInteractionUnit> agregatedAIUs;
    


    public Composition(
            String rational, String role, 
            Collection<AbstractInteractionUnit> agregatedAIUs)
    {
        this.rational = rational;
        this.role = role;
        this.agregatedAIUs = new HashSet<AbstractInteractionUnit>(agregatedAIUs);
    }


    public String getRational()
    {
        return rational;
    }


    public String getRole()
    {
        return role;
    }


    public Set<AbstractInteractionUnit> getAgregatedAIUs()
    {
        return new HashSet<AbstractInteractionUnit>(agregatedAIUs);
    }

    
}
