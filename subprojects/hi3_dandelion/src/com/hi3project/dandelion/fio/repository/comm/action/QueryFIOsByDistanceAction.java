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
 
 package com.hi3project.dandelion.fio.repository.comm.action;

import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessageType;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.location.Location;


/** Queries FIOs by their distance to a FIO specification.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-nov-2014
 */
public class QueryFIOsByDistanceAction extends FIORepositoryMessage
{
    
    private final FIOSpecification fioSpecs;
    
    private final double maxDistance;
    
    private final int maxCount;
    
    private final Location location;
    
    private final String metricId;
    
    
    
    public QueryFIOsByDistanceAction(
            FIOSpecification fioSpecs, int maxCount, double maxDistance, String metricId, Location location,
            long sequenceNumber, String clientId, String fioRepoId)
    {
        super(sequenceNumber, FIORepositoryMessageType.QueryFIOByDistanceAction, clientId, fioRepoId);
        this.fioSpecs = fioSpecs;
        this.maxCount = maxCount;
        this.metricId = metricId;
        this.maxDistance = maxDistance;
        this.location = location;
    }
    
    
    public QueryFIOsByDistanceAction(
            FIOSpecification fioSpecs, int maxCount, String metricId,
            long sequenceNumber, String clientId, String fioRepoId)
    {
        this(fioSpecs, maxCount, -1, metricId, null, sequenceNumber, clientId, fioRepoId);
    }
    
    
    public QueryFIOsByDistanceAction(
            FIOSpecification fioSpecs, int maxCount,  String metricId, Location location,
            long sequenceNumber, String clientId, String fioRepoId)
    {
        this(fioSpecs, maxCount, -1, metricId, location, sequenceNumber, clientId, fioRepoId);
    }
    
    
    
    public QueryFIOsByDistanceAction(
            FIOSpecification fioSpecs, int maxCount, double maxDistance,  String metricId,
            long sequenceNumber, String clientId, String fioRepoId)
    {
        this(fioSpecs, maxCount, maxDistance, metricId, null, sequenceNumber, clientId, fioRepoId);
    }
    

    
    public FIOSpecification getFioSpecs()
    {
        return fioSpecs;
    }

    
    public double getMaxDistance()
    {
        return maxDistance;
    }

    
    public int getMaxCount()
    {
        return maxCount;
    }
    

    public Location getLocation()
    {
        return location;
    }

    public String getMetricId()
    {
        return metricId;
    }
      

}
