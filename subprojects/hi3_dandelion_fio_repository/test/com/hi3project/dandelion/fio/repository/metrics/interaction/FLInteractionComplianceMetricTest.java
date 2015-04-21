
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
import com.hi3project.dandelion.fio.repository.metrics.similarity.InteractionSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicInteractionComplianceMetric;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
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
public class FLInteractionComplianceMetricTest
{
    
    private BasicInteractionComplianceMetric metric;
    
    public FLInteractionComplianceMetricTest()
    {
        this.metric = new BasicInteractionComplianceMetric();
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
    
    
    private InteractionSpecification interactionSpecificationQuiteSimilar()
    {
        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input), 
                                        PropertyType.numberProperty, 
                                        3);
        
        return new InteractionSpecification(interactionSupport);
    }
    
    private InteractionSpecification interactionSpecificationVeryDifferent()
    {
        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.output), 
                                        PropertyType.stringProperty, 
                                        1);
        
        return new InteractionSpecification(interactionSupport);
    }
    
    private InteractionSpecification interactionSpecificationPerfect()
    {
        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.output, InteractionType.focus), 
                                        PropertyType.numberProperty, 
                                        3);
        
        return new InteractionSpecification(interactionSupport);
    }
    
    private InteractionSpecification interactionSpecificationJustSimilar()
    {
        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus), 
                                        PropertyType.numberProperty, 
                                        1);
        
        return new InteractionSpecification(interactionSupport);
    }
    
    
    private FIODescription fioDescription()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus, InteractionType.output), 
                                        PropertyType.numberProperty, 3);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.wimp, 1.0));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        return new FIODescription(null, interaction, null);
        
    }


    @Test
    public void calcDistanceTest()
    {
        
        FIODescription fioDesc = fioDescription();
        
        
        InteractionSpecification interactionSpecs1 = interactionSpecificationQuiteSimilar();
        InteractionSimilarity calcDistance = 
                this.metric.calcSimilarity(interactionSpecs1, fioDesc);
        assertTrue(calcDistance.getValue() < 0.15);
        
        
        InteractionSpecification interactionSpecs2 = interactionSpecificationVeryDifferent();
        calcDistance = 
                this.metric.calcSimilarity(interactionSpecs2, fioDesc);
        assertTrue(calcDistance.getValue() > 0.9);
        
        
        InteractionSpecification interactionSpecs3 = interactionSpecificationPerfect();
        calcDistance = 
                this.metric.calcSimilarity(interactionSpecs3, fioDesc);
        assertTrue(calcDistance.getValue() == 0.0);
        
        
        InteractionSpecification interactionSpecs4 = interactionSpecificationJustSimilar();
        calcDistance = 
                this.metric.calcSimilarity(interactionSpecs4, fioDesc);
        assertTrue(calcDistance.getValue() < 0.3);
        
        
    }
}
