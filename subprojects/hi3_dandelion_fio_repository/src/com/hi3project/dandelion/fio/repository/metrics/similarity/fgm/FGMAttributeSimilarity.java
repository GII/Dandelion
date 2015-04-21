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

import java.util.Iterator;
import java.util.Set;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** Calculates the normalized distance (dissimilarity) between two
 * fuzzy attributes according to the Fuzzy Geometric Model (FGM)
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-feb-2015
 */
public class FGMAttributeSimilarity 
{
    
    private final Variable A, B;

    
    public FGMAttributeSimilarity(Variable A, Variable B)
    {
        this.A = A;
        this.B = B;
    }
    
    
    /** The distance between two fuzzy sets in a particular term (two fuzzy values in the
     * same domain) using the Fuzzy Geometric Model.
     * 
     * @param term the term of which the distance if required
     * @return 
     */
    private double dis(String term)
    {
        
        return Math.abs(this.A.getMembership(term) - this.B.getMembership(term));
        
    }
    
    
    private double sumDisSquared(double[] disArray)
    {
        
        double sum = 0;
        
        for(int i=0; i<disArray.length; i++) {
            sum += disArray[i]*disArray[i];
        }
        
        return sum;
        
    }
    
    
    /** Calculates the normalized distance (dissimilarity) between the same two
     * fuzzy attributes in different objects. 
     * 
     * @return the normalized distance (dissimilarity) according to FGM
     */
    public double df()
    {
        
        Set<String> terms = this.A.getLinguisticTerms().keySet();
        
        double[] disArray = new double[terms.size()];
        
        Iterator<String> termsIter = terms.iterator();
        for(int i=0; i<disArray.length; i++) {
            disArray[i] = dis(termsIter.next());
        }
        
        return Math.sqrt(sumDisSquared(disArray)) / Math.sqrt(disArray.length);
        
    }
    
    
    /** Similarity between the pair of attributes A and B
     * 
     * @param kj
     * @return 
     */
    public double sf(double kj)
    {
        
        double df = df();
        
        double denominator = 1 - df;
        double divisor = 1 + kj * df;
        
        return denominator / divisor;
        
    }

}
