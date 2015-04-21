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
 
 package com.hi3project.dandelion.fio.repository.comm.response;

import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType;
import com.hi3project.dandelion.fio.repository.comm.action.QueryFIOsByDistanceAction;
import java.util.ArrayList;
import java.util.Collection;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-nov-2014
 */
public class FIOQueryResponse extends FIORepositoryMessage
{
    
    private final ArrayList<FIORepositoryEntryCompliance> entries;
    
    private final Error error;
    

    public FIOQueryResponse(
            QueryFIOsByDistanceAction request,
            Collection<FIORepositoryEntryCompliance> entries, 
            String sourceId)
    {
        super(request.getId(), FIORepositoryMessageType.FIOQueryReponse, sourceId, request.getSource());
        this.entries = new ArrayList<FIORepositoryEntryCompliance>(entries);
        this.error = null;
    }
    
    
    public FIOQueryResponse(
            QueryFIOsByDistanceAction request,
            Error error, 
            String sourceId)
    {
        super(request.getId(), FIORepositoryMessageType.FIOQueryReponse, sourceId, request.getSource());
        this.entries = new ArrayList<FIORepositoryEntryCompliance>();
        this.error = error;
    }
    

    
    public ArrayList<FIORepositoryEntryCompliance> getEntries()
    {
        return entries;
    }

    public Error getError()
    {
        return error;
    }
    
    
    
    
    
    
    public enum Error
    {
        unavailabeMetric
    }

}
