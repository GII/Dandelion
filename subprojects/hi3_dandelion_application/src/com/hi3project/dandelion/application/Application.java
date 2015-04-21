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

import com.hi3project.dandelion.application.history.HistoryEvent;
import com.hi3project.dandelion.uib.client.IUserInterfaceBuilderClient;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import java.util.ArrayList;


/** The runtime metadata of a Dandelion application
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-nov-2014
 */
public class Application 
{
    
    private final int id;

    private final ArrayList<HistoryEvent> history;
    
    private final ApplicationMetadata metadata;
    
    private final IUserInterfaceController uic; //it can be remote
    private final IUserInterfaceBuilderClient uib; //it can be remote

    
    
    public Application(
            int id, ApplicationMetadata metadata,
            IUserInterfaceController uic,
            IUserInterfaceBuilderClient uib)
    {
        this.id = id;
        this.history = new ArrayList<HistoryEvent>();
        this.metadata = metadata;
        this.uic = uic;
        this.uib = uib;
    }

    
    
    public int getId()
    {
        return id;
    }

    public ApplicationMetadata getMetadata()
    {
        return metadata;
    }

    public ArrayList<HistoryEvent> getHistory()
    {
        return new ArrayList<HistoryEvent>(this.history);
    }

    public IUserInterfaceController getUserInterfaceController()
    {
        return uic;
    }

    public IUserInterfaceBuilderClient getUserInterfaceBuilder()
    {
        return uib;
    }
    

}
