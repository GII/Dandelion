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


/** An elementary AIU responsible for data I/O
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-jun-2013 -- Initial version
 */
public class DataInputOutputSupport extends PresentationSupport
{

    private final int minCardinality;
    private final int maxCardinality;
    
    private String defaultValue;
    
    /** defines the data type required for the data manipulated in the AIU */
    private final EnumDataType dataType;
    
    private int dataLength;
    
    /** defines a restriction for a specific format for the data through a mask
    expressed as a regular expression */
    private String dataFormat;
    
    /** defines an interval for the data */
    private String range;
    
    private final boolean isDynamic;
    
    /** defines a restriction for the data (e.g. through a regex) */
    private String patterntMatching;


    private final boolean inputSupport;
    private final boolean outputSupport;
    
    
    
    public DataInputOutputSupport(
            String id, int minCardinality, int maxCardinality, 
            EnumDataType dataType, boolean isDynamic,
            boolean inputSupport, boolean outputSupport)
    {
        this(id, minCardinality, maxCardinality, "", 
                dataType, 0, null, null, 
                isDynamic, null, inputSupport, outputSupport);
    }

    
    public DataInputOutputSupport(
            String id, int minCardinality, int maxCardinality, String defaultValue, 
            EnumDataType dataType, int dataLength, String dataFormat, 
            String range, boolean isDynamic, String patterntMatching,
            boolean inputSupport, boolean outputSupport)
    {
        super(id);
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        this.defaultValue = defaultValue;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.dataFormat = dataFormat;
        this.range = range;
        this.isDynamic = isDynamic;
        this.patterntMatching = patterntMatching;
        this.inputSupport = inputSupport;
        this.outputSupport = outputSupport;
    }


    public int getMinCardinality()
    {
        return minCardinality;
    }


    public int getMaxCardinality()
    {
        return maxCardinality;
    }


    public String getDefaultValue()
    {
        return defaultValue;
    }


    public EnumDataType getDataType()
    {
        return dataType;
    }


    public int getDataLength()
    {
        return dataLength;
    }


    public String getDataFormat()
    {
        return dataFormat;
    }


    public String getRange()
    {
        return range;
    }


    public boolean isIsDynamic()
    {
        return isDynamic;
    }


    public String getPatterntMatching()
    {
        return patterntMatching;
    }

    public boolean isInputSupport()
    {
        return inputSupport;
    }

    public boolean isOutputSupport()
    {
        return outputSupport;
    }
    
    
}
