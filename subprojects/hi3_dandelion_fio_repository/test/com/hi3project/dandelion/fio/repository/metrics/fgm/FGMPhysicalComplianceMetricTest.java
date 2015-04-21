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
import com.hi3project.dandelion.fio.description.physical.PhysicalDescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.physical.FGMPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.EnumSet;
import java.util.HashSet;
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
public class FGMPhysicalComplianceMetricTest
{
    
    public FGMPhysicalComplianceMetricTest()
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
            
            HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>(0);
            shapes.add(new PhysicalShape(2.5, PhysicalShapeType.display));
            PhysicalSpecification query = new PhysicalSpecification(1.25, 1.25, 5, shapes);
            
            PhysicalDescription desc = new PhysicalDescription(9.25, 9.25, 25, EnumSet.of(PhysicalShapeType.display));
            FIODescription fioDesc = new FIODescription(desc, null, null);
            
            FGMPhysicalComplianceMetric metric = new FGMPhysicalComplianceMetric();
            
            PhysicalSimilarity calcDistance = metric.calcAdequateness(query, fioDesc);
            
            System.out.println("Not similar = " + calcDistance.getValue() + " - " +calcDistance);
            assertTrue(calcDistance.getValue() < 0.3);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMPhysicalComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @Test
    public void testQuiteSimilar()
    {
        
        
        try {
            
            HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>(0);
            shapes.add(new PhysicalShape(3.25, PhysicalShapeType.button));
            shapes.add(new PhysicalShape(7.25, PhysicalShapeType.remote));
            PhysicalSpecification query = new PhysicalSpecification(2.25, 2.25, 5, shapes);
            
            PhysicalDescription desc = new PhysicalDescription(3.5, 3.5, 10, EnumSet.of(PhysicalShapeType.display, PhysicalShapeType.remote));
            FIODescription fioDesc = new FIODescription(desc, null, null);
            
            FGMPhysicalComplianceMetric metric = new FGMPhysicalComplianceMetric();
            
            PhysicalSimilarity calcDistance = metric.calcAdequateness(query, fioDesc);
            
            System.out.println("Quite similar = " + calcDistance.getValue() + " - " +calcDistance);
            assertTrue(calcDistance.getValue() > 0.60);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMPhysicalComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @Test
    public void testVerySimilar()
    {
        
        try {
            
            HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>(0);
            shapes.add(new PhysicalShape(8.5, PhysicalShapeType.display));
//            PhysicalSpecification query = new PhysicalSpecification(0.5, 1.5, 2.0, shapes);
//            
//            PhysicalDescription desc = new PhysicalDescription(0.6, 1.3, 1.75, new PhysicalShape(2.8, PhysicalShapeType.display));
            PhysicalSpecification query = new PhysicalSpecification(1.25, 3.75, 5.0, shapes);
            
            PhysicalDescription desc = new PhysicalDescription(1.5, 3.25, 4.375, EnumSet.of(PhysicalShapeType.display));
            FIODescription fioDesc = new FIODescription(desc, null, null);
            
            FGMPhysicalComplianceMetric metric = new FGMPhysicalComplianceMetric();
            
            PhysicalSimilarity calcDistance = metric.calcAdequateness(query, fioDesc);
            
            System.out.println("Very similar = " + calcDistance.getValue() + " - " +calcDistance);
            assertTrue(calcDistance.getValue() > 0.8);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMPhysicalComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
