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
 
 package com.hi3project.dandelion.gip.event;

import com.hi3project.dandelion.util.properties.Property;
import java.util.ArrayList;
import java.util.Collection;


/** A selection event containing the items to select from (list of properties)
 * and, if it represents a selection event, the selected item.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-mar-2013 -- Initial version
 */
public class SelectionEvent extends Event
{

    private ArrayList<Property> availableItems;
    private Property selectedItem;


    public SelectionEvent(String id, String interactionId, Collection<Property> availableItems, Property selectedItem)
    {
        super(id, interactionId, EventType.selection);
        this.availableItems = new ArrayList<Property>(availableItems);
        this.selectedItem = selectedItem;
    }


    public ArrayList<Property> getAvailableItems()
    {
        return new ArrayList<Property>(availableItems);
    }


    public Property getSelectedItem()
    {
        return selectedItem;
    }


    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + (this.availableItems != null ? this.availableItems.hashCode() : 0);
        hash = 59 * hash + (this.selectedItem != null ? this.selectedItem.hashCode() : 0);
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
        final SelectionEvent other = (SelectionEvent) obj;
        if (this.availableItems != other.availableItems && (this.availableItems == null || !this.availableItems.equals(other.availableItems))) {
            return false;
        }
        if (this.selectedItem != other.selectedItem && (this.selectedItem == null || !this.selectedItem.equals(other.selectedItem))) {
            return false;
        }
        return true;
    }
    
    
}
