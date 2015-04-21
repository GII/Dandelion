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
 
 package com.hi3project.dandelion.uib.fio.specification.usage.jfl;

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
 *      17-feb-2015
 */
public class JFuzzyLogicUsageSpecsBuilder extends AbstractJFuzzyLogicFIS
{

    private static final String CLASS_NAME="es.udc.gii.hi3.dandelion.uib.fio.specification.usage.jfl.JFuzzyLogicUsageSpecsBuilder";
    private static final String FCL_PATH="/es/udc/gii/hi3/dandelion/uib/fio/specification/usage/jfl/fio_usage_specs_builder.fcl";
    
    
    
    public static JFuzzyLogicUsageSpecsBuilder instantiate() throws ErrorLoadingFISException
    {
        JFuzzyLogicFISLoader fisLoader = new JFuzzyLogicFISLoader("");
        try {
            JFuzzyLogicUsageSpecsBuilder usageSpecsBuilder =
                    (JFuzzyLogicUsageSpecsBuilder) fisLoader.loadFIS(CLASS_NAME, FCL_PATH);
            return usageSpecsBuilder;
        } catch (ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, FCL_PATH);
        }
    }
    
    
    /** Sets the 'user_age' value for the FIS
     * 
     * @param age 
     */
    public void setRecommendedUserAge(double age)
    {                
        getFIS().setVariable("user_age", age);                
    }
    
    /** Gets the 'user_age' fuzzy variable from the FIS
     * 
     * @return 
     */
    public Variable getRecommendedUserAgeVariable()
    {
        return getFIS().getVariable("user_age");
    }
    
    
    /** Sets the 'user_ict_literacy' value for the FIS
     * 
     * @param literacy 
     */
    public void setICTLiteracy(double literacy)
    {
        getFIS().setVariable("user_ict_literacy", literacy);
    }
    
    /** Gets the 'user_ict_literacy' fuzzy variable from the FIS
     * 
     * @return 
     */
    public Variable getICTLiteracyVariable()
    {
        return getFIS().getVariable("user_ict_literacy");
    }
    
    
}
