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
import com.hi3project.dandelion.fio.description.physical.PhysicalDescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import com.hi3project.dandelion.fio.repository.metrics.FIOAdequateness;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicInteractionComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicModalityComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicUsageComplianceMetric;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
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
public class FLComplianceMetricTest
{
    
    private BasicComplianceMetric metric;
    
    
    public FLComplianceMetricTest()
    {
        this.metric = this.metric = new BasicComplianceMetric(
                new BasicInteractionComplianceMetric(), 
                new BasicModalityComplianceMetric(),
                new BasicPhysicalComplianceMetric(),
                new BasicUsageComplianceMetric(),
                "fl-compliance");
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
    
    
    private FIODescription fioDescriptionInputTouchToy()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus), 
                                        PropertyType.numberProperty, 2);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.touch, 2.9));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);
        
        PhysicalDescription physical = 
                new PhysicalDescription(1.0, 1.0, 50, EnumSet.of(PhysicalShapeType.toy));
        
        return new FIODescription(physical, interaction, usage);
        
    }
    
    
    private Set<PhysicalShape> createToyShape()
    {
        
        HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>();
        
        shapes.add(new PhysicalShape(3.5, PhysicalShapeType.toy));
        shapes.add(new PhysicalShape(2.0, PhysicalShapeType.custom));
        
        return shapes;
        
    }
    
    private Set<PhysicalShape> createKeyboardShape()
    {
        
        HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>();
        
        shapes.add(new PhysicalShape(2.5, PhysicalShapeType.keyboard));
        
        return shapes;
        
    }
    
    
    private InteractionSpecification interactionSpecificationQuiteSimilar()
    {
        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input), 
                                        PropertyType.numberProperty, 
                                        3);
        
        return new InteractionSpecification(interactionSupport);
    
    }
    
    
    private ModalitySpecification createModalitySpecificationTouch()
    {
        
        ArrayList<Modality> modalities = new ArrayList<Modality>(5);
        
        modalities.add(new Modality(ModalityType.symbol, 1.6));
        modalities.add(new Modality(ModalityType.touch, 3.5));
        modalities.add(new Modality(ModalityType.gesture, 0.5));
        
        return new ModalitySpecification(modalities);
        
    }
    
    private ModalitySpecification createModalitySpecificationKeyboard()
    {
        
        ArrayList<Modality> modalities = new ArrayList<Modality>(5);
        
        modalities.add(new Modality(ModalityType.keyboard, 1.5));
        
        return new ModalitySpecification(modalities);
        
    }
    
    
    private FIOSpecification fioSpecificationInputToy()
    {
        
        PhysicalSpecification toySpecs =
                new PhysicalSpecification(1.2, 1.5, 100, createToyShape());
        
        UsageSpecification usageSpecs = new UsageSpecification(20, 1.0);
        
        return new FIOSpecification(
                interactionSpecificationQuiteSimilar(), 
                createModalitySpecificationTouch(), 
                toySpecs, 
                usageSpecs);
        
    }
    
    
    private FIOSpecification fioSpecificationInputKeyboard()
    {
        
        PhysicalSpecification toySpecs =
                new PhysicalSpecification(0.2, 0.5, 10, createKeyboardShape());
        
        UsageSpecification usageSpecs = new UsageSpecification(40, 0.5);
        
        return new FIOSpecification(
                interactionSpecificationQuiteSimilar(), 
                createModalitySpecificationKeyboard(), 
                toySpecs, 
                usageSpecs);
        
    }
    
    
    @Test
    public void testCalcDistance()
    {
        
        FIOAdequateness calcDistance = 
                this.metric.calcAdequateness(fioSpecificationInputToy(), fioDescriptionInputTouchToy());        
        assertTrue(calcDistance.getValue() < 0.2);
        
        
        calcDistance = 
                this.metric.calcAdequateness(fioSpecificationInputKeyboard(), fioDescriptionInputTouchToy());        
        assertTrue(calcDistance.getValue() > 0.5);
        
    }
    
    
    
}
