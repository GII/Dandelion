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
 
package com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.usage;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.FGMFuzzySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.exception.NotSameNumberOfAttributesException;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.uib.fio.specification.usage.jfl.JFuzzyLogicUsageSpecsBuilder;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import net.sourceforge.jFuzzyLogic.rule.Variable;


/** Description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      17-feb-2015
 */
public class FGMUsageComplianceMetric
{
    
    
    private final JFuzzyLogicUsageSpecsBuilder FA;
    private final JFuzzyLogicUsageSpecsBuilder FB;
    

    
    public FGMUsageComplianceMetric() throws ErrorLoadingFISException
    {
        this.FA = JFuzzyLogicUsageSpecsBuilder.instantiate();
        this.FB = JFuzzyLogicUsageSpecsBuilder.instantiate();
    }
    
    
    
    public UsageSimilarity calcSimilarity(UsageSpecification fioSpecs, FIODescription fioDesc)
    {
        
        double similarity = 0;
        
        FA.setRecommendedUserAge(fioSpecs.getRecommendedUserAge());
        //if the user supports a greater difficulty than specified by the FIO --> ICT similarity = 1.0
        if (fioSpecs.getIctDifficulty() > fioDesc.getUsageCharacteristics().getIctDifficulty())           
            FA.setICTLiteracy(1.0);
        else
            FA.setICTLiteracy(fioSpecs.getIctDifficulty());
        Variable[] FAAttributes = new Variable[2];
        FAAttributes[0] = FA.getRecommendedUserAgeVariable();
        FAAttributes[1] = FA.getICTLiteracyVariable();
        
        FB.setRecommendedUserAge(fioDesc.getUsageCharacteristics().getRecommendedUserAge());
        //if the user supports a greater difficulty than specified by the FIO --> ICT similarity = 1.0
        if (fioSpecs.getIctDifficulty() > fioDesc.getUsageCharacteristics().getIctDifficulty())           
            FB.setICTLiteracy(1.0);
        else
            FB.setICTLiteracy(fioDesc.getUsageCharacteristics().getIctDifficulty());
        Variable[] FBAttributes = new Variable[2];
        FBAttributes[0] = FB.getRecommendedUserAgeVariable();
        FBAttributes[1] = FB.getICTLiteracyVariable();
        
        FGMFuzzySimilarity fuzzySimilarity = new FGMFuzzySimilarity(FAAttributes, FBAttributes);
        try {
            
            similarity = fuzzySimilarity.similarityWeighted(new double[]{0.2, 0.8}, new double[]{1, 1});
            
        } catch (NotSameNumberOfAttributesException ex) {
            similarity = 0;
        }
        
        return new UsageSimilarity(similarity);
        
    }

}
