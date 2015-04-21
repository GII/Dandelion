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
import org.usixml.model.UserInterfaceModel;


/** Describes a user interface in a way independent from the concrete interaction
 * units available in the target platform.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Adapted to W3C AUI
 *      02-jul-2012 -- Initial version
 */
public class AbstractUserInterfaceModel extends UserInterfaceModel
{
    
    private String useCase;
    
    private final ArrayList<AbstractInteractionUnit> abstractInteractionUnits;


    public AbstractUserInterfaceModel(
            String useCase,
            ArrayList<AbstractInteractionUnit> abstractInteractionUnits)
    {
        this.useCase = useCase;
        this.abstractInteractionUnits = abstractInteractionUnits;
    }
    
    
    public ArrayList<AbstractInteractionUnit> getAbstractInteractionUnits()
    {
        return this.abstractInteractionUnits;
    }
    
    
    public String getUseCase()
    {
        return this.useCase;
    }


    public int getAbstractInteractionUnitCount()
    {
        int aiuCount = 0;
        
        for(AbstractInteractionUnit aiu : this.abstractInteractionUnits) {
            aiuCount++;
            for(Composition aiuComp : aiu.getCompositions()) {
                for(AbstractInteractionUnit aiuCompUnit : aiuComp.getAgregatedAIUs()) {
                    aiuCount++;
                }
            }
        }
        
        return aiuCount;
    }


    
    @Override
    public AbstractInteractionUnit getAbstractInteractionUnit(String id)
    {               
        return getAbstractInteractionUnitById(id, this.abstractInteractionUnits);
    }
    
    
    private AbstractInteractionUnit getAbstractInteractionUnitById(
            String id, Collection<AbstractInteractionUnit> aiuList) 
    {
        
        AbstractInteractionUnit result = null;
        
        for(AbstractInteractionUnit aiu : aiuList) {
            
            if (aiu.getId().equals(id)) {
                result = aiu;
            }
            else {
                for(Composition aiuComp : aiu.getCompositions()) {
                    result = getAbstractInteractionUnitById(id, aiuComp.getAgregatedAIUs());
                    if (result != null)
                        break;
                }
            }
            
            if (result != null)
                break;
            
        }
        
        return result;
        
    }


}
