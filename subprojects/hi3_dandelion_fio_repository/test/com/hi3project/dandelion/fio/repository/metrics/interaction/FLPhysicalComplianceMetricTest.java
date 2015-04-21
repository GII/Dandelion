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
import com.hi3project.dandelion.fio.repository.metrics.similarity.PhysicalSimilarity;
import com.hi3project.dandelion.fio.repository.metrics.similarity.basic.BasicPhysicalComplianceMetric;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Gervasio Varela <gervasio.varela@udc.es>
 */
public class FLPhysicalComplianceMetricTest
{
    
    private BasicPhysicalComplianceMetric metric;
    
    
    public FLPhysicalComplianceMetricTest()
    {
        this.metric = new BasicPhysicalComplianceMetric();
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
    
    
    
    private Set<PhysicalShape> createToyShape()
    {
        
        HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>();
        
        shapes.add(new PhysicalShape(3.5, PhysicalShapeType.toy));
        shapes.add(new PhysicalShape(2.0, PhysicalShapeType.custom));
        
        return shapes;
        
    }
    
    
    private Set<PhysicalShape> createDisplayShape()
    {
        
        HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>();
        
        shapes.add(new PhysicalShape(3.5, PhysicalShapeType.display));
        shapes.add(new PhysicalShape(2.0, PhysicalShapeType.surface));
        
        return shapes;
        
    }
    
    
    private FIODescription fioDescriptionInputTouchToy()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.input), 
                                        PropertyType.numberProperty, 1);
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
    
    
    private FIODescription fioDescriptionOutputDisplay()
    {

        InteractionSupport interactionSupport = 
                new InteractionSupport(EnumSet.of(InteractionType.output), 
                                        PropertyType.stringProperty, 1);
        InteractionCapability interactionCapabibility = 
                new InteractionCapability("1", interactionSupport, 
                                            new Modality(ModalityType.wimp, 1.0));
        
        ArrayList<InteractionCapability> interaction = 
                new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);
        
        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);
        
        PhysicalDescription physical = 
                new PhysicalDescription(1.0, 2.5, 50, EnumSet.of(PhysicalShapeType.display));
        
        return new FIODescription(physical, interaction, usage);
        
    }
    
    
    
    
    @Test
    public void calcDistanceTest()
    {
        
        PhysicalSpecification toySpecs =
                new PhysicalSpecification(1.2, 1.5, 100, createToyShape());
        PhysicalSimilarity calcDistance = 
                this.metric.calcSimilarity(toySpecs, fioDescriptionInputTouchToy());
        Assert.assertTrue(calcDistance.getValue() < 0.1);
        
        
        
        PhysicalSpecification displaySpecs =
                new PhysicalSpecification(1.7, 2.0, 30, createDisplayShape());
        calcDistance = 
                this.metric.calcSimilarity(displaySpecs, fioDescriptionInputTouchToy());
        Assert.assertTrue(calcDistance.getValue() > 0.5);
        
        calcDistance = 
                this.metric.calcSimilarity(displaySpecs, fioDescriptionOutputDisplay());
        Assert.assertTrue(calcDistance.getValue() < 0.15);
        
    }
    
}
