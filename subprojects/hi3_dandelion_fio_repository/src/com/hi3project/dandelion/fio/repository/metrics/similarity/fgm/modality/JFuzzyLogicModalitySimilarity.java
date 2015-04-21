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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.modality;

import com.hi3project.dandelion.util.jfl.AbstractJFuzzyLogicFIS;
import com.hi3project.dandelion.util.jfl.JFuzzyLogicFISLoader;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class JFuzzyLogicModalitySimilarity extends AbstractJFuzzyLogicFIS
{
    
    
    private static final String CLASS_NAME="es.udc.gii.hi3.dandelion.fio.repository.metrics.similarity.fgm.modality.JFuzzyLogicModalitySimilarity";
    private static final String FCL_PATH="/es/udc/gii/hi3/dandelion/fio/repository/metrics/similarity/fgm/modality/modality_similarity.fcl";
    
    
    
    public static JFuzzyLogicModalitySimilarity instantiate() throws ErrorLoadingFISException
    {
        JFuzzyLogicFISLoader fisLoader = new JFuzzyLogicFISLoader("");
        try {
            
            JFuzzyLogicModalitySimilarity physicalDistance =
                    (JFuzzyLogicModalitySimilarity) fisLoader.loadFIS(CLASS_NAME, FCL_PATH);
            
            return physicalDistance;
            
        } catch (ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, FCL_PATH);
        }
    }
    
    
    public void setGranularity(double granularity)
    {
        getFIS().setVariable("modality_granularity", granularity);
    }
    
    public Variable getGranularityVariable()
    {
        return getFIS().getVariable("modality_granularity");
    }
    
    
}
