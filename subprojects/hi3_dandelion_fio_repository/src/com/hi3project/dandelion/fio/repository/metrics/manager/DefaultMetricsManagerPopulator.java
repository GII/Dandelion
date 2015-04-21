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

import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicInteractionComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicModalityComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicUsageComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.FGMAdequatenessMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.modality.FGMModalityComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.physical.FGMPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.usage.FGMUsageComplianceMetric;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import com.hi3project.dandelion.util.log.Logging;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      06-nov-2014
 */
public class DefaultMetricsManagerPopulator 
{
    
    private final IFIOMetricsManager metricsManager;

    
    public DefaultMetricsManagerPopulator(IFIOMetricsManager metricsManager)
    {
        this.metricsManager = metricsManager;
    }
    
    
    public void populate()
    {
        
        BasicComplianceMetric basicMetric = new BasicComplianceMetric(
                new BasicInteractionComplianceMetric(), 
                new BasicModalityComplianceMetric(),
                new BasicPhysicalComplianceMetric(),
                new BasicUsageComplianceMetric(),
                "basic-linear-compliance");
        this.metricsManager.addMetricInstance(basicMetric);
        
        
        
        try {
            
            FGMAdequatenessMetric fgmMetric = new FGMAdequatenessMetric(
                    new BasicInteractionComplianceMetric(),
                    new FGMModalityComplianceMetric(),
                    new FGMPhysicalComplianceMetric(),
                    new FGMUsageComplianceMetric(),
                    "fgm-compliance");
            this.metricsManager.addMetricInstance(fgmMetric);
            
        } catch (ErrorLoadingFISException ex) {
            Logging.logger.warning("Unable to load FGM Compliance Metric.");
        }
        
        
    }
    

}
