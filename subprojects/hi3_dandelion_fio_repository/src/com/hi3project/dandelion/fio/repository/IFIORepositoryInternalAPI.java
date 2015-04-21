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
 
package com.hi3project.dandelion.fio.repository;

import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.repository.comm.action.DeregisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.action.FindFIOByIdAction;
import com.hi3project.dandelion.fio.repository.comm.action.QueryFIOsByDistanceAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.response.FIOEntryResponse;
import com.hi3project.dandelion.fio.repository.comm.response.FIOQueryResponse;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.fio.repository.exception.FIOMetricNotFoundErrorException;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.location.Location;
import java.util.Collection;


/** Internal API for the FIO repository
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public interface IFIORepositoryInternalAPI 
{

    
    public FIORepositoryEntry registerFIO(FIOExtendedMetadata fioMetadata);
       
    public void deregisterFIO(FIOExtendedMetadata fioMetadata) throws FIONotFoundException;
    
    
    
    
    public FIORepositoryEntry findFIOById(String fioId) throws FIONotFoundException;
    
    public Collection<FIORepositoryEntry> findFIOsByLocation(Location location);
    
    
    
    
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxCount, String metricId)
             throws FIOMetricNotFoundErrorException;
    
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistance(
            FIOSpecification fioSpecs, double maxDistance, int maxCount, String metricId)
             throws FIOMetricNotFoundErrorException;
    
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistanceInLocation(
            FIOSpecification fioSpecs, int maxCount, String metricId, Location location)
             throws FIOMetricNotFoundErrorException;
    
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistanceInLocation(
            FIOSpecification fioSpecs, double maxDistance, int maxCount, 
            String metricId, Location location)
             throws FIOMetricNotFoundErrorException;
    
    
    
    public Collection<String> getAvailableMetrics();
    
    
    
    
    String getFIORepositoryId();
    
    
    FIOEntryResponse processRegisterFIOAction(RegisterFIOAction action);
    
    
    void processDeregisterFIOAction(DeregisterFIOAction action);
    
    
    FIOEntryResponse processFindFIOByIdAction(FindFIOByIdAction action);
    
    FIOQueryResponse processQueryFIOsByDistance(QueryFIOsByDistanceAction action);
    
    
}
