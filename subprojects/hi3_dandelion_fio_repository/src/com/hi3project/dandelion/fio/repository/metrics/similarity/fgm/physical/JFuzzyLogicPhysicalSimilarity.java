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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.physical;

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
public class JFuzzyLogicPhysicalSimilarity extends AbstractJFuzzyLogicFIS
{
    
    
    private static final String CLASS_NAME="es.udc.gii.hi3.dandelion.fio.repository.metrics.similarity.fgm.physical.JFuzzyLogicPhysicalSimilarity";
    private static final String FCL_PATH="/es/udc/gii/hi3/dandelion/fio/repository/metrics/similarity/fgm/physical/physical_similarity.fcl";
    
    
    
    public static JFuzzyLogicPhysicalSimilarity instantiate() throws ErrorLoadingFISException
    {
        JFuzzyLogicFISLoader fisLoader = new JFuzzyLogicFISLoader("");
        try {
            
            JFuzzyLogicPhysicalSimilarity physicalDistance =
                    (JFuzzyLogicPhysicalSimilarity) fisLoader.loadFIS(CLASS_NAME, FCL_PATH);
            
            return physicalDistance;
            
        } catch (ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, FCL_PATH);
        }
    }
    
    
    public void setSize(double size)
    {
        getFIS().setVariable("physical_size", size);
    }
    
    public Variable getSizeVariable()
    {
        return getFIS().getVariable("physical_size");
    }
    
    
    public void setStatus(double status)
    {
        getFIS().setVariable("physical_status", status);
    }
    
    public Variable getStatusVariable()
    {
        return getFIS().getVariable("physical_status");
    }
    
    
    public void setDistance(double distance)
    {
        getFIS().setVariable("physical_distance", distance);
    }
    
    public Variable getDistanceVariable()
    {
        return getFIS().getVariable("physical_distance");
    }
    
    
    public void setShapeSimilarity(double similarity)
    {
        getFIS().setVariable("physical_shape_similarity", similarity);
    }
    
    public Variable getShapeSimilarity()
    {
        return getFIS().getVariable("physical_shape_similarity");
    }
    

}
