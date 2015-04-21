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
 
package org.usixml.model.aui.data;

/** Represents a way to interact with the interface by selecting zero, one or 
 * many items among the options available in a list
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class DataSelectionSupport extends PresentationSupport
{
    
    /** starting index */
    private int start;
    
    /** ending index */
    private int end;
    
    /** interval between two successive items, by starting by start and ending by end*/
    private int step;
    
    private final EnumDataType dataType; 
    
    private final EnumSelectionType selectionType;
    
    /** specifies if the user can add item in the selection. */
    private final boolean isExpandable;
    
    /** specifies if the selection is continuous */
    private boolean isContinuous;
    
    private boolean isRating;
    
    /** to define whether the values should be hidden */
    private boolean isSecret;


    public DataSelectionSupport(
            String id, int start, int end, int step, EnumDataType dataType, 
            EnumSelectionType selectionType, 
            boolean isExpandable, boolean isContinuous, 
            boolean isRating, boolean isSecret)
    {
        super(id);
        this.start = start;
        this.end = end;
        this.step = step;
        this.dataType = dataType;
        this.selectionType = selectionType;
        this.isExpandable = isExpandable;
        this.isContinuous = isContinuous;
        this.isRating = isRating;
        this.isSecret = isSecret;
    }


    public DataSelectionSupport(
            String id, int start, int end, int step, EnumDataType dataType, 
            EnumSelectionType selectionType, boolean isExpandable)
    {
        super(id);
        this.start = start;
        this.end = end;
        this.step = step;
        this.dataType = dataType;
        this.selectionType = selectionType;
        this.isExpandable = isExpandable;
    }


    public DataSelectionSupport(
            String id, EnumDataType dataType, EnumSelectionType selectionType, 
            boolean isExpandable)
    {
        super(id);
        this.dataType = dataType;
        this.selectionType = selectionType;
        this.isExpandable = isExpandable;
    }


    public int getStart()
    {
        return start;
    }


    public int getEnd()
    {
        return end;
    }


    public int getStep()
    {
        return step;
    }

    
    public EnumDataType getDataType()
    {
        return this.dataType;
    }

    public EnumSelectionType getSelectionType()
    {
        return selectionType;
    }


    public boolean isIsExpandable()
    {
        return isExpandable;
    }


    public boolean isIsContinuous()
    {
        return isContinuous;
    }


    public boolean isIsRating()
    {
        return isRating;
    }


    public boolean isIsSecret()
    {
        return isSecret;
    }


}
