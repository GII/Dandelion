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
 
 package com.hi3project.dandelion.uib.local.xml;

import com.hi3project.dandelion.uib.xml.jaxb.DandelionApplicationFinalUIMapping;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


/** Reads the UI builder mappings from an XML file using JAXB
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      10-jul-2012 -- Initial version
 */
public class UIBJaxbReader
{

    public DandelionApplicationFinalUIMapping readUIBConfigFile(InputStream inputStream) throws JAXBException
    {

        JAXBContext context = JAXBContext.newInstance("es.udc.gii.hi3.dandelion.uib.xml.jaxb");

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (DandelionApplicationFinalUIMapping) unmarshaller.unmarshal(inputStream);

    }
        
}
