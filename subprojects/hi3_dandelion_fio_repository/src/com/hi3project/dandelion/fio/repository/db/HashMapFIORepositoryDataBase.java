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
 
package com.hi3project.dandelion.fio.repository.db;

import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.gip.FIOCommChannelUtil;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.metrics.similarity.IFIOAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.metrics.FIOAdequateness;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.location.Location;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Level;


/** FIO Database
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class HashMapFIORepositoryDataBase implements IFIORepositoryDataBase
{
    
    
    private final HashMap<String, FIORepositoryEntry> fioMap;   //FIO-ID --> FIO-entry
    
    
    public HashMapFIORepositoryDataBase()
    {
        this.fioMap = new HashMap<String, FIORepositoryEntry>();
    }
    
    
    @Override
    public synchronized FIORepositoryEntry registerFIO(FIOExtendedMetadata fioMetadata, Location location)
    {
        
        FIORepositoryEntry fioEntry = this.fioMap.get(fioMetadata.getId());
        
        if (fioEntry != null) { 
            //already in the database --> update it
            fioEntry.setFioMetadata(fioMetadata);
            fioEntry.setFioCommChannel(FIOCommChannelUtil.getCommChannel(fioMetadata));            
        }
        else { 
            //new FIO --> add it
            fioEntry = new FIORepositoryEntry(fioMetadata, location, FIOCommChannelUtil.getCommChannel(fioMetadata));
            this.fioMap.put(fioMetadata.getId(), fioEntry);
        }
        
        Logging.logger.log(Level.INFO, "Registering new FIO with ID {0}", fioMetadata.getId());
        
        try {
            
            return fioEntry.clone();
        
        } catch (CloneNotSupportedException ex) {
            //not possible
            Logging.logger.log(Level.SEVERE, "Unable to clone entry: ", ex);
            return null;
        }
        
    }
    
    
    @Override
    public synchronized FIORepositoryEntry registerFIO(FIOExtendedMetadata fioMetadata)
    {
        return registerFIO(fioMetadata, Location.UNKNOWN);
    }
    
    
    @Override
    public synchronized void deregisterFIO(FIOExtendedMetadata fioMetadata) throws FIONotFoundException
    {
        
        if (this.fioMap.remove(fioMetadata.getId()) != null) {
            Logging.logger.log(Level.INFO, "DEregistering FIO with ID {0}", fioMetadata.getId());
        }
        else {
            throw new FIONotFoundException(fioMetadata.getId());
        }
        
    }
    
    
    @Override
    public synchronized FIORepositoryEntry findFIOById(String fioId) throws FIONotFoundException
    {
        
        FIORepositoryEntry fioEntry = this.fioMap.get(fioId);
        
        if (fioEntry != null) {
            return fioEntry;
        }
        else {
            throw new FIONotFoundException(fioId);
        }
        
    }

    
    @Override
    public synchronized Collection<FIORepositoryEntry> getAllFIOs()
    {
        
        ArrayList<FIORepositoryEntry> allFios = new ArrayList<FIORepositoryEntry>(this.fioMap.size());
        
        try {
            for(FIORepositoryEntry fioEntry : this.fioMap.values()) {
                allFios.add(fioEntry.clone());
            }
        }
        catch(CloneNotSupportedException ex) {
            //not possible
            Logging.logger.log(Level.SEVERE, "Unable to clone entry: ", ex);
        }
        
        return allFios;
        
    }

    @Override
    public Collection<FIORepositoryEntry> findFIOsByLocation(Location location)
    {
        
        ArrayList<FIORepositoryEntry> allFios = new ArrayList<FIORepositoryEntry>();
        
        try {
            for(FIORepositoryEntry fioEntry : this.fioMap.values()) {
                if (fioEntry.getLocation().equals(location)) {
                    allFios.add(fioEntry.clone());
                }
            }
        }
        catch(CloneNotSupportedException ex) {
            //not possible
            Logging.logger.log(Level.SEVERE, "Unable to clone entry: ", ex);
        }
        
        return allFios;
        
    }

    
    
    private Collection<FIORepositoryEntryCompliance> findFIOsByDistance(
            FIOSpecification fioSpecs, 
            double maxDistance, int maxCount, 
            IFIOAdequatenessMetric metric,
            Collection<FIORepositoryEntry> allFIOs)
    {
        
        ArrayList<FIORepositoryEntryCompliance> allEntries = 
                new ArrayList<FIORepositoryEntryCompliance>();
        
        FIOAdequateness calcDistance;
        
        //filter entries by distance
        for(FIORepositoryEntry entry : allFIOs) {
            
            calcDistance = metric.calcAdequateness(fioSpecs, entry.getFioMetadata().getDescription());
            
            
            if (calcDistance.getValue() <= maxDistance) {
                allEntries.add(new FIORepositoryEntryCompliance(entry, calcDistance));
            }
            
        }
        
        ArrayList<FIORepositoryEntryCompliance> result;
        if (allEntries.size() > maxCount) {
            //return a maximun of maxCount elements
            result = new ArrayList<FIORepositoryEntryCompliance>();
            Iterator<FIORepositoryEntryCompliance> iterator = allEntries.iterator();
            int currentCount = 0;
            while(iterator.hasNext() && currentCount < maxCount) {
                result.add(iterator.next());
                currentCount++;
            }
        }
        else {
            result = allEntries;
        }
        
        Collections.sort(result);
        
        return result;
       
    }
    
    
    @Override
    public Collection<FIORepositoryEntryCompliance> findFIOsByDistance(FIOSpecification fioSpecs, int maxCount, IFIOAdequatenessMetric metric)
    {
        return findFIOsByDistance(fioSpecs, 1.1, maxCount, metric, getAllFIOs());
    }
    
    @Override
    public Collection<FIORepositoryEntryCompliance> findFIOsByDistance(FIOSpecification fioSpecs, double maxDistance, int maxCount, IFIOAdequatenessMetric metric)
    {
        return findFIOsByDistance(fioSpecs, maxDistance, maxCount, metric, getAllFIOs());
    } 
    
    
    
    @Override
    public Collection<FIORepositoryEntryCompliance> findFIOsByDistanceInLocation(FIOSpecification fioSpecs, int maxCount, IFIOAdequatenessMetric metric, Location location)
    {
        return findFIOsByDistance(fioSpecs, 1.1, maxCount, metric, findFIOsByLocation(location));
    }

    
    @Override
    public Collection<FIORepositoryEntryCompliance> findFIOsByDistanceInLocation(FIOSpecification fioSpecs, double maxDistance, int maxCount, IFIOAdequatenessMetric metric, Location location)
    {
        return findFIOsByDistance(fioSpecs, maxDistance, maxCount, metric, findFIOsByLocation(location));
    }

    
    

}
