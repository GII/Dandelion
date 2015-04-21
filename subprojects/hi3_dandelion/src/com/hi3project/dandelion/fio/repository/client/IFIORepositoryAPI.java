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
 
 package com.hi3project.dandelion.fio.repository.client;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.repository.exception.FIOMetricNotFoundErrorException;
import com.hi3project.dandelion.fio.repository.exception.FIONotFoundException;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.location.Location;


/** FIO repository public interface
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public interface IFIORepositoryAPI
{
    
    
    
    public void registerFIO(FIOExtendedMetadata fioMetadata, IFIORepositoryCallback callback)
            throws InternalErrorException;
    
    
    
    public void deregisterFIO(FIOExtendedMetadata fioMetadata, IFIORepositoryCallback callback) 
            throws FIONotFoundException, InternalErrorException;
    
    
    
    public void findFIOById(String fioId, IFIORepositoryCallback callback) 
            throws FIONotFoundException, InternalErrorException;
    
    
    

    public void queryFIOsByDistance(
            FIOSpecification fioSpecs,
            int maxCount, String metricId,
            IFIORepositoryCallback callback)
            throws InternalErrorException;
    
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs,
            int maxDistance, int maxCount, String metricId,
            IFIORepositoryCallback callback)
            throws InternalErrorException;
    
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs,
            int maxCount, String metricId,
            Location location,
            IFIORepositoryCallback callback)
            throws InternalErrorException;    
    
    public void queryFIOsByDistance(
            FIOSpecification fioSpecs,
            int maxDistance, int maxCount, String metricId,
            Location location,
            IFIORepositoryCallback callback)
            throws InternalErrorException;
    
    
}
