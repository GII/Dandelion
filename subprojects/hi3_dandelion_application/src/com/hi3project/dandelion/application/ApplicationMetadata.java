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
 
 package com.hi3project.dandelion.application;

import com.hi3project.dandelion.application.aui.ApplicationAUI;


/** Holds metainformation about the IU of an application. 
 * 
 * It includes references to the models of the IU.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      28-ago-2012 -- Initial version
 */
public class ApplicationMetadata 
{

    private final String name;
    private final String version;
    private final String vendor;
    private final ApplicationAUI abstractUI;


    public ApplicationMetadata(String name, String version, String vendor, ApplicationAUI abstractUI)
    {
        this.name = name;
        this.version = version;
        this.vendor = vendor;
        this.abstractUI = abstractUI;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String getVendor()
    {
        return vendor;
    }


    public ApplicationAUI getAbstractUI()
    {
        return this.abstractUI;
    }


}
