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
import com.hi3project.dandelion.fio.repository.metrics.similarity.ModalitySimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicModalityComplianceMetric;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
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
public class FLModalityComplianceMetricTest
{
    
    private BasicModalityComplianceMetric metric;
    
    
    public FLModalityComplianceMetricTest()
    {
        this.metric = new BasicModalityComplianceMetric();
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
    
    
    
    private FIODescription fioDescriptionInputTouch()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus), 
                                        PropertyType.numberProperty, 3);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.touch, 3.0));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        return new FIODescription(null, interaction, null);
        
    }
    
    
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
        
        return new FIODescription(null, interaction, null);
        
    }
    
    
    private FIODescription fioDescriptionInputGesture()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus), 
                                        PropertyType.booleanProperty, 1);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.gesture, 3.0));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        return new FIODescription(null, interaction, null);
        
    }
    
    
    private ModalitySpecification createModalitySpecificationGesture()
    {
        
        ArrayList<Modality> modalities = new ArrayList<Modality>(5);
        
        modalities.add(new Modality(ModalityType.wimp, 0.6));
        modalities.add(new Modality(ModalityType.touch, 0.9));
        modalities.add(new Modality(ModalityType.gesture, 3.5));
        
        return new ModalitySpecification(modalities);
        
    }
    
    
    private ModalitySpecification createModalitySpecificationTouch()
    {
        
        ArrayList<Modality> modalities = new ArrayList<Modality>(5);
        
        modalities.add(new Modality(ModalityType.symbol, 1.6));
        modalities.add(new Modality(ModalityType.touch, 2.5));
        modalities.add(new Modality(ModalityType.gesture, 0.5));
        
        return new ModalitySpecification(modalities);
        
    }
    
    
    @Test
    public void calcDistanceTest()
    {
        
        //fio modality = gesture | specification = gesture
        FIODescription fioDesc = fioDescriptionInputGesture();                
        ModalitySimilarity calcDistance = 
                this.metric.calcSimilarity(createModalitySpecificationGesture(), fioDesc);        
        assertTrue(calcDistance.getValue() < 0.2);
        
        
        //fio modality = gesture | specification = touch
        calcDistance = 
                this.metric.calcSimilarity(createModalitySpecificationTouch(), fioDesc);        
        assertTrue(calcDistance.getValue() > 0.5);
        
        
        //fio modality = touch | specification = touch
        fioDesc = fioDescriptionInputTouch();
        calcDistance = 
                this.metric.calcSimilarity(createModalitySpecificationTouch(), fioDesc);        
        assertTrue(calcDistance.getValue() < 0.2);
        
    }
    
    
    
}
