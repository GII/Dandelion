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
 
package com.hi3project.dandelion.fio.repository.metrics.manager;

import com.hi3project.dandelion.fio.repository.metrics.similarity.IFIOAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.exception.FIOMetricNotFoundErrorException;
import java.util.Collection;
import java.util.HashMap;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      05-nov-2014
 */
public class PrototypeMetricsManager implements IFIOMetricsManager
{

    private final HashMap<String, IFIOAdequatenessMetric> complianceMetrics;

    
    
    public PrototypeMetricsManager()
    {
        this.complianceMetrics = new HashMap<String, IFIOAdequatenessMetric>(5);
    }
    

    
    @Override
    public IFIOAdequatenessMetric getComplianceMetricInstance(String metricId) 
            throws FIOMetricNotFoundErrorException
    {
        
        IFIOAdequatenessMetric metric = complianceMetrics.get(metricId);
        if (metric != null) {
            return metric.clone();
        }
        else {
            throw new FIOMetricNotFoundErrorException(metricId);
        }
        
    }

    
    @Override
    public Collection<String> getAllAvailableMetrics()
    {
        return this.complianceMetrics.keySet();
    }

    @Override
    public void addMetricInstance(IFIOAdequatenessMetric metricInstance)
    {
        this.complianceMetrics.put(metricInstance.getMetricId(), metricInstance);
    }
    
    

}
