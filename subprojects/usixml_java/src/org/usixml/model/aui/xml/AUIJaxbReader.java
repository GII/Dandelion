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
 
package org.usixml.model.aui.xml;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.usixml.model.UIModelReader;
import org.usixml.model.aui.AbstractUserInterfaceModel;
import org.usixml.model.aui.xml.jaxb.UserInterfaceModel;
import org.usixml.model.exception.UIModelIOException;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      10-jul-2012 -- Initial version
 */
public class AUIJaxbReader implements UIModelReader
{

    @Override
    public AbstractUserInterfaceModel readUIModel(InputStream inputStream) throws UIModelIOException
    {
        try {
            
            JAXBContext context = JAXBContext.newInstance("org.usixml.model.aui.xml.jaxb");
            
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            UserInterfaceModel model = (UserInterfaceModel) unmarshaller.unmarshal(inputStream);
            
            AUIJaxbConverter converter = new AUIJaxbConverter();
            
            return converter.convert(model);            
            
        } catch (JAXBException ex) {
            throw new UIModelIOException(ex);
        }
        
    }
        
}
