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
 
package org.usixml.aui.test;

import java.io.InputStream;
import org.usixml.model.UserInterfaceModel;
import org.usixml.model.aui.xml.AUIJaxbReader;
import org.usixml.model.exception.UIModelIOException;


/** description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      10-jul-2012 -- Initial version
 */
public class AUIReaderTest 
{

    
    public static void main(String [] args)
    {
        try {
            
            AUIJaxbReader reader = new AUIJaxbReader();
            
            InputStream is = AUIJaxbReader.class.getResourceAsStream("/org/usixml/aui/test/auiTest1.xml");
            
            UserInterfaceModel model = reader.readUIModel(is);
            
            System.out.println("");

        } catch (UIModelIOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
