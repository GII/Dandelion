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

/** Provides support for filtering a data to be selected. A filter is defined as a higher­order
 *  function that processes a data structure (typically a list) in order to produce a new data structure
 *  containing exactly those elements of the original data structure for which a given filter condition is
 *  satisfied
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class DataFilterSupport extends DataSelectionSupport
{

    private final EnumFilterType filterType;
    
    private final String filterCondition;
    
    private final String filterProperty;


    public DataFilterSupport(
            String id, 
            EnumFilterType filterType, String filterCondition, String filterProperty, 
            int start, int end, int step, EnumDataType dataType, 
            EnumSelectionType selectionType, boolean isExpandable, boolean isContinuous, 
            boolean isRating, boolean isSecret)
    {
        super(id, start, end, step, dataType, selectionType, isExpandable, isContinuous, isRating, isSecret);
        this.filterType = filterType;
        this.filterCondition = filterCondition;
        this.filterProperty = filterProperty;
    }


    public DataFilterSupport(
            String id, 
            EnumFilterType filterType, String filterCondition, String filterProperty, 
            int start, int end, int step, EnumDataType dataType, EnumSelectionType selectionType,
            boolean isExpandable)
    {
        this(id, filterType, filterCondition, filterProperty, start, end, step, dataType, 
                selectionType, isExpandable, false, false, false);
    }


    public DataFilterSupport(
            String id, 
            EnumFilterType filterType, String filterCondition, String filterProperty,
            EnumDataType dataType, EnumSelectionType selectionType, boolean isExpandable)
    {
        this(id, filterType, filterCondition, filterProperty, 0, 0, 0, dataType, 
                selectionType, isExpandable, false, false, false);
    }


    public EnumFilterType getFilterType()
    {
        return filterType;
    }


    public String getFilterCondition()
    {
        return filterCondition;
    }


    public String getFilterProperty()
    {
        return filterProperty;
    }

    
}
