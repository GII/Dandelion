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
 
package com.hi3project.dandelion.fio.repository.metrics.fgm;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.usage.FGMUsageComplianceMetric;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Gervasio Varela <gervasio.varela@udc.es>
 */
public class FGMUsageComplianceMetricTest
{
    
    public FGMUsageComplianceMetricTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testNotSimilar()
    {
        
        try {
            
            UsageSpecification usageQuery = new UsageSpecification(80, 0.3);
            
            UsageCharacteristics usageDesc = new UsageCharacteristics(1, 4.0);
            FIODescription fioDesc = new FIODescription(null, null, usageDesc);
            
            FGMUsageComplianceMetric usageMetric = new FGMUsageComplianceMetric();
            
            UsageSimilarity calcDistance = usageMetric.calcSimilarity(usageQuery, fioDesc);
            
            System.out.println("Not similar = " + calcDistance.getValue());
            assertTrue(calcDistance.getValue() < 0.2);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMUsageComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @Test
    public void testQuiteSimilar()
    {
        
        try {
            
            UsageSpecification usageQuery = new UsageSpecification(25, 3.3);
            
            UsageCharacteristics usageDesc = new UsageCharacteristics(45, 3.2);
            FIODescription fioDesc = new FIODescription(null, null, usageDesc);
            
            FGMUsageComplianceMetric usageMetric = new FGMUsageComplianceMetric();
            
            UsageSimilarity calcDistance = usageMetric.calcSimilarity(usageQuery, fioDesc);
            
            System.out.println("Quite similar = "+ calcDistance.getValue());
            assertTrue(calcDistance.getValue() > 0.7);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMUsageComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    @Test
    public void testVerySimilar()
    {
        
        try {
            
            UsageSpecification usageQuery = new UsageSpecification(25, 3.3);
            
            UsageCharacteristics usageDesc = new UsageCharacteristics(30, 3.2);
            FIODescription fioDesc = new FIODescription(null, null, usageDesc);
            
            FGMUsageComplianceMetric usageMetric = new FGMUsageComplianceMetric();
            
            UsageSimilarity calcDistance = usageMetric.calcSimilarity(usageQuery, fioDesc);
            
            System.out.println("Very similar = "+ calcDistance.getValue());
            assertTrue(calcDistance.getValue() > 0.8);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMUsageComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
