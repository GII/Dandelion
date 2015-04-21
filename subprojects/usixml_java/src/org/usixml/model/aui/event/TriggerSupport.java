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
 
package org.usixml.model.aui.event;

/** A interaction resource that support triggering events
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class TriggerSupport extends EventSupport
{

    private String triggerExpresion;
    
    private EnumTriggerType triggerType;
    
    private boolean requiresConfirmation;


    public TriggerSupport(
            String id, String triggerExpresion, EnumTriggerType triggerType, boolean requiresConfirmation)
    {
        super(id);
        this.triggerExpresion = triggerExpresion;
        this.triggerType = triggerType;
        this.requiresConfirmation = requiresConfirmation;
    }


    public TriggerSupport(String id, EnumTriggerType triggerType)
    {
        this(id, null, triggerType, false);
    }


    public String getTriggerExpresion()
    {
        return triggerExpresion;
    }


    public EnumTriggerType getTriggerType()
    {
        return triggerType;
    }


    public boolean isRequiresConfirmation()
    {
        return requiresConfirmation;
    }
    
    
}
