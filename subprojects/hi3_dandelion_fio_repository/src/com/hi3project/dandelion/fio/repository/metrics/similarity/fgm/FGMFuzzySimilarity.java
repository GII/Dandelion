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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm;

import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.exception.NotSameNumberOfAttributesException;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-feb-2015
 */
public class FGMFuzzySimilarity 
{
    
    private final Variable[] fObj1;
    private final Variable[] fObj2;

    
    public FGMFuzzySimilarity(Variable[] fObj1, Variable[] fObj2)
    {
        this.fObj1 = fObj1;
        this.fObj2 = fObj2;
    }
    
    
    public double similarityWeighted(double[] weights, double[] ks) throws NotSameNumberOfAttributesException
    {
        
        if ((weights.length != ks.length) || (weights.length != fObj1.length) || (weights.length != fObj2.length)) {
            throw new NotSameNumberOfAttributesException();
        }
        else {
            
            double sfSum = 0;
            double weightSum = 0;
            
            FGMAttributeSimilarity attSimilarity = null;
            for(int i=0; i<weights.length; i++) {
                
                attSimilarity = new FGMAttributeSimilarity(fObj1[i], fObj2[i]);
                
                sfSum += weights[i] * attSimilarity.sf(ks[i]);
                weightSum += weights[i];
                
            }
            
            return sfSum / weightSum;
            
        }
        
    }
    
    
    public double similarityMinimum(double[] ks) throws NotSameNumberOfAttributesException
    {
        
        if ((fObj1.length != fObj2.length) || (fObj1.length != ks.length)) {
            throw new NotSameNumberOfAttributesException();
        }
        else {
            
            double minSf = Double.MAX_VALUE;
            double attSim = 0;
            
            FGMAttributeSimilarity attSimilarity = null;
            for(int i=0; i<fObj1.length; i++) {
                
                attSimilarity = new FGMAttributeSimilarity(fObj1[i], fObj2[i]);
                
                attSim = attSimilarity.sf(ks[i]);
                if (attSim < minSf) {
                    minSf = attSim;
                }
                
            }
            
            return minSf; 
            
        }
        
    }
    

}
