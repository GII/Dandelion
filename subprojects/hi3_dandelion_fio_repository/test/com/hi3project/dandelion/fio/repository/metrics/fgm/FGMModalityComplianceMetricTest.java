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
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalityInteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.fgm.modality.FGMModalityComplianceMetric;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.ArrayList;
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
public class FGMModalityComplianceMetricTest
{
    
    public FGMModalityComplianceMetricTest()
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
            
            HashSet<Modality> modalities = new HashSet<Modality>(2);
            modalities.add(new Modality(ModalityType.wimp, 1.5));
            modalities.add(new Modality(ModalityType.gesture, 0.9));
            ModalitySpecification query = new ModalitySpecification(modalities);
            
            ArrayList<InteractionCapability> interactionCapabilities = new ArrayList<InteractionCapability>();
            interactionCapabilities.add(new InteractionCapability("1", null, new Modality(ModalityType.gesture, 3.2)));
            FIODescription fioDesc = new FIODescription(null, interactionCapabilities, null);
            
            FGMModalityComplianceMetric metric = new FGMModalityComplianceMetric();
            
            ModalitySimilarity calcSimilarity = metric.calcSimilarity(query, fioDesc);
            
            System.out.println("Not similar = " + calcSimilarity.getValue() + " - " +calcSimilarity);
            assertTrue(calcSimilarity.getValue() < 0.3);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMModalityComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @Test
    public void testQuiteSimilar()
    {
        
        
        try {
            
            HashSet<Modality> modalities = new HashSet<Modality>(2);
            modalities.add(new Modality(ModalityType.wimp, 1.5));
            modalities.add(new Modality(ModalityType.gesture, 2.0));
            ModalitySpecification query = new ModalitySpecification(modalities);
            
            ArrayList<InteractionCapability> interactionCapabilities = new ArrayList<InteractionCapability>();
            interactionCapabilities.add(new InteractionCapability("1", null, new Modality(ModalityType.gesture, 2.6)));
            FIODescription fioDesc = new FIODescription(null, interactionCapabilities, null);
            
            FGMModalityComplianceMetric metric = new FGMModalityComplianceMetric();
            
            ModalitySimilarity calcSimilarity = metric.calcSimilarity(query, fioDesc);
            
            System.out.println("Quite similar = " + calcSimilarity.getValue() + " - " +calcSimilarity);
            assertTrue(calcSimilarity.getValue() > 0.5);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMModalityComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @Test
    public void testVerySimilar()
    {
        
        try {
            
            HashSet<Modality> modalities = new HashSet<Modality>(2);
            modalities.add(new Modality(ModalityType.wimp, 1.5));
            modalities.add(new Modality(ModalityType.gesture, 3.3));
            ModalitySpecification query = new ModalitySpecification(modalities);
            
            ArrayList<InteractionCapability> interactionCapabilities = new ArrayList<InteractionCapability>();
            interactionCapabilities.add(new InteractionCapability("1", null, new Modality(ModalityType.gesture, 3.0)));
            FIODescription fioDesc = new FIODescription(null, interactionCapabilities, null);
            
            FGMModalityComplianceMetric metric = new FGMModalityComplianceMetric();
            
            ModalitySimilarity calcSimilarity = metric.calcSimilarity(query, fioDesc);
            
            System.out.println("Very similar = " + calcSimilarity.getValue() + " - " +calcSimilarity);
            assertTrue(calcSimilarity.getValue() > 0.8);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMModalityComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Test
    public void testVerySimilarInteraction()
    {
        
        try {
            
            HashSet<Modality> modalities = new HashSet<Modality>(2);
            modalities.add(new Modality(ModalityType.wimp, 1.5));
            modalities.add(new Modality(ModalityType.gesture, 3.3));
            ModalitySpecification query = new ModalitySpecification(modalities);
            InteractionSpecification interQuery = new InteractionSpecification(new InteractionSupport(EnumSet.of(InteractionType.input), PropertyType.numberProperty, 1));
            
            ArrayList<InteractionCapability> interactionCapabilities = new ArrayList<InteractionCapability>();
            interactionCapabilities.add(new InteractionCapability("1", new InteractionSupport(EnumSet.of(InteractionType.output, InteractionType.input), PropertyType.numberProperty, 1), new Modality(ModalityType.gesture, 3.5)));
            FIODescription fioDesc = new FIODescription(null, interactionCapabilities, null);
            
            FGMModalityComplianceMetric metric = new FGMModalityComplianceMetric();
            
            ModalityInteractionSimilarity calcSimilarity = metric.calcSimilarity(query, interQuery, fioDesc);
            
            System.out.println("Very similar interaction = " + calcSimilarity.getValue() + " - " +calcSimilarity);
            assertTrue(calcSimilarity.getValue() > 0.8);
            
        } catch (ErrorLoadingFISException ex) {
            ex.printStackTrace();
            Logger.getLogger(FGMModalityComplianceMetricTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
