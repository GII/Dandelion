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
import com.hi3project.dandelion.fio.repository.db.IFIORepositoryDataBase;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.fio.repository.metrics.similarity.IFIOAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.exception.FIOMetricNotFoundErrorException;
import com.hi3project.dandelion.fio.repository.metrics.manager.IFIOMetricsManager;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.location.Location;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;


/** FIO repository
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class FIORepository implements IFIORepositoryInternalAPI
{

    private final String repositoryId;
    
    private final IFIORepositoryDataBase fioDB;
    
    private final IFIOMetricsManager metricsManager;

    
    
    public FIORepository(
            String repositoryId, 
            IFIORepositoryDataBase fioDB,
            IFIOMetricsManager metricsManager)
    {
        this.repositoryId = repositoryId;
        this.fioDB = fioDB;
        this.metricsManager = metricsManager;
    }
    
    
    
    @Override
    public FIORepositoryEntry registerFIO(FIOExtendedMetadata fioMetadata)
    {
        return this.fioDB.registerFIO(fioMetadata);
    }

    @Override
    public void deregisterFIO(FIOExtendedMetadata fioMetadata) throws FIONotFoundException
    {
        this.fioDB.deregisterFIO(fioMetadata);
    }

    @Override
    public FIORepositoryEntry findFIOById(String fioId) throws FIONotFoundException
    {        
        return this.fioDB.findFIOById(fioId);
    }

    
    public Collection<FIORepositoryEntry> getAllFIOs()
    {
        return this.fioDB.getAllFIOs();
    }
    
    @Override
    public Collection<FIORepositoryEntry> findFIOsByLocation(Location location)
    {
        return this.fioDB.findFIOsByLocation(location);
    }
    

    @Override
    public FIOEntryResponse processRegisterFIOAction(RegisterFIOAction action)
    {
        FIORepositoryEntry fioEntry = this.registerFIO(action.getFioMetadata());
        return new FIOEntryResponse(action, this.repositoryId, fioEntry);
    }

    @Override
    public void processDeregisterFIOAction(DeregisterFIOAction action)
    {
        try {
            this.deregisterFIO(action.getFioMetadata());
        } catch (FIONotFoundException ex) { 
            //do nothing            
        }
    }

    @Override
    public FIOEntryResponse processFindFIOByIdAction(FindFIOByIdAction action)
    {
        try {                        
            
            FIORepositoryEntry fioEntry = this.findFIOById(action.getFioID());            
            Logging.logger.log(Level.FINE, "Processing: find FIO {0} --> found", action.getFioID());            
            return new FIOEntryResponse(action, repositoryId, fioEntry);
            
        } catch (FIONotFoundException ex) {
            
            //not found --> null
            Logging.logger.log(Level.FINE, "Processing: find FIO {0} --> not found", action.getFioID());
            return new FIOEntryResponse(action, repositoryId, null);
            
        }
    }
    
    
    @Override
    public FIOQueryResponse processQueryFIOsByDistance(QueryFIOsByDistanceAction action)
    {
        
        Logging.logger.log(Level.FINE, "Processing: query FIOs by distance");  
        
        ArrayList<FIORepositoryEntryCompliance> result = 
                new ArrayList<FIORepositoryEntryCompliance>(action.getMaxCount());
        
        try {
            if (action.getLocation() != null && action.getMaxDistance() > 0) {
                result.addAll(this.queryFIOsByDistanceInLocation(
                        action.getFioSpecs(), action.getMaxDistance(), action.getMaxCount(), action.getMetricId(), action.getLocation()));
            }
            else if (action.getLocation() != null) {
                result.addAll(this.queryFIOsByDistanceInLocation(
                        action.getFioSpecs(),action.getMaxCount(), action.getMetricId(), action.getLocation()));
            }
            else if (action.getMaxDistance() > 0) {
                result.addAll(this.queryFIOsByDistance(
                        action.getFioSpecs(), action.getMaxDistance(), action.getMaxCount(), action.getMetricId()));
            }
            else {
                result.addAll(this.queryFIOsByDistance(
                        action.getFioSpecs(), action.getMaxCount(), action.getMetricId()));
            }
        }
        catch(FIOMetricNotFoundErrorException ex) {
            Logging.logger.log(Level.FINE, "Processing: query FIOs by distance --> unavailable metric {0}", action.getMetricId());
            return new FIOQueryResponse(action, FIOQueryResponse.Error.unavailabeMetric, repositoryId);
        }

        Logging.logger.log(Level.FINE, "Processing: query FIOs by distance --> found {0}", result.size());
        
        return new FIOQueryResponse(action, result, repositoryId);
        
    }
    

    @Override
    public String getFIORepositoryId()
    {
        return this.repositoryId;
    }
    
    
    
    @Override
    public Collection<String> getAvailableMetrics()
    {
        return this.metricsManager.getAllAvailableMetrics();
    }
    
    
    private IFIOAdequatenessMetric getMetricInstace(String metricId) throws FIOMetricNotFoundErrorException
    {
        return this.metricsManager.getComplianceMetricInstance(metricId);
    }
    

    @Override
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistance(
            FIOSpecification fioSpecs, int maxCount, String metricId)
            throws FIOMetricNotFoundErrorException
    {
        return this.fioDB.findFIOsByDistance(fioSpecs, maxCount, getMetricInstace(metricId));
    }

    @Override
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistance(
            FIOSpecification fioSpecs, double maxDistance, int maxCount, String metricId)
            throws FIOMetricNotFoundErrorException
    {
        return this.fioDB.findFIOsByDistance(fioSpecs, maxDistance, maxCount, getMetricInstace(metricId));
    }

    @Override
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistanceInLocation(
            FIOSpecification fioSpecs, int maxCount, String metricId, Location location) 
            throws FIOMetricNotFoundErrorException
    {
        return this.fioDB.findFIOsByDistanceInLocation(fioSpecs, maxCount, getMetricInstace(metricId), location);
    }

    @Override
    public Collection<FIORepositoryEntryCompliance> queryFIOsByDistanceInLocation(
            FIOSpecification fioSpecs, double maxDistance, int maxCount, 
            String metricId, Location location) 
            throws FIOMetricNotFoundErrorException
    {
        return this.fioDB.findFIOsByDistanceInLocation(fioSpecs, maxDistance, maxCount, getMetricInstace(metricId), location);
    }

    

}
