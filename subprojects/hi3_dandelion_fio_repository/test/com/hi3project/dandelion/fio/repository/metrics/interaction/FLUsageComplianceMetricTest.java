/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 
package com.hi3project.dandelion.fio.repository.metrics.interaction;

import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import com.hi3project.dandelion.fio.repository.metrics.similarity.UsageSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicUsageComplianceMetric;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.ArrayList;
import java.util.EnumSet;
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
public class FLUsageComplianceMetricTest
{
    
    private BasicUsageComplianceMetric metric;
    
    
    
    public FLUsageComplianceMetricTest()
    {
        this.metric = new BasicUsageComplianceMetric();
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
    
    
    private FIODescription fioDescriptionOutputSpeech()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.output), 
                                        PropertyType.numberProperty, 1);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.speech_production, 2.9));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);
        
        return new FIODescription(null, interaction, usage);
        
    }
    
    
    @Test
    public void calcDistanceTest()
    {
        
        UsageSpecification usageSpecs = new UsageSpecification(20, 1.0);
        UsageSimilarity calcDistance =
                this.metric.calcSimilarity(usageSpecs, fioDescriptionOutputSpeech());        
        assertTrue(calcDistance.getValue() < 0.1);
        
        
        usageSpecs = new UsageSpecification(40, 1.5);
        calcDistance =
                this.metric.calcSimilarity(usageSpecs, fioDescriptionOutputSpeech());        
        assertTrue(calcDistance.getValue() < 0.3);
        
        
        usageSpecs = new UsageSpecification(80, 2.5);
        calcDistance =
                this.metric.calcSimilarity(usageSpecs, fioDescriptionOutputSpeech());        
        assertTrue(calcDistance.getValue() > 0.5);
        
    }
    
}
