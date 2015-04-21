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
 
 package com.hi3project.dandelion.fio.repository.test;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
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
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.fio.specification.ModalitySpecification;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.fio.specification.UsageSpecification;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
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
public class FIORepositoryClientTest
{
    
    private  JmsRemoteFIORepositoryClient fioRepo;
    
    private int fioCount = 0;
            
            
    
    public FIORepositoryClientTest()
    {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection jmsConnection = jmsConnectionFactory.createConnection();
            jmsConnection.start();

            Session jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            fioRepo = new JmsRemoteFIORepositoryClient(
                    "client-id", "DandelionFIORepository", new JsonFIORepositoryProtocolCodec(), jmsSession);

            fioRepo.start();
        } catch (JMSException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (InternalErrorException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
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
    
    
    
    @Test
    public void testRegisterFindDeregister()
    {
        
        try {
            
            FIODescription fioDescription = new FIODescription();
            
            FIOExtendedMetadata fioMetadata = new FIOExtendedMetadata(fioDescription, "FIO-1");
            
            fioRepo.registerFIO(fioMetadata, new RegisterCallback());
            
            fioRepo.findFIOById("FIO-1", new FindFIOCallback(true));
            
            fioRepo.deregisterFIO(fioMetadata, new DeregisterCallback());
            
            fioRepo.findFIOById("FIO-1", new FindFIOCallback(false));
            
            
            Thread.sleep(500);
            
            
        } catch (InternalErrorException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (InterruptedException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
    }
    
    
    @Test
    public void testQueryByDistance()
    {
        
        try{
            
            FIOExtendedMetadata fioMetadataToy = new FIOExtendedMetadata(fioDescriptionInputTouchToy(), "FIO-2-ToyInput");

            fioRepo.registerFIO(fioMetadataToy, new RegisterCallback());

            FIOExtendedMetadata fioMetadataKeyb = new FIOExtendedMetadata(fioDescriptionInputKeyboard(), "FIO-2-KeybInput");

            fioRepo.registerFIO(fioMetadataKeyb, new RegisterCallback());

            FIOSpecification fioSpecs = fioSpecificationInputKeyboard();
            fioRepo.queryFIOsByDistance(fioSpecs, 5, "fl-compliance", new QueryCallback(2));

            fioRepo.deregisterFIO(fioMetadataToy, new DeregisterCallback());
            fioRepo.deregisterFIO(fioMetadataKeyb, new DeregisterCallback());

            fioRepo.queryFIOsByDistance(fioSpecs, 5, "fl-compliance", new QueryCallback(0));
                       
            Thread.sleep(500);
        
        } catch (InternalErrorException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (InterruptedException ex) {
            Logger.getLogger(FIORepositoryClientTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        
    }
    
    
    private static FIODescription fioDescriptionInputTouchToy()
    {

        InteractionSupport interactionSupport
                = new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus),
                        PropertyType.numberProperty, 2);
        InteractionCapability interactionCapabibility
                = new InteractionCapability("1", interactionSupport,
                        new Modality(ModalityType.touch, 2.9));

        ArrayList<InteractionCapability> interaction
                = new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);

        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);

        PhysicalDescription physical
                = new PhysicalDescription(1.0, 1.0, 50, EnumSet.of(PhysicalShapeType.toy));

        return new FIODescription(physical, interaction, usage);

    }

    private static FIODescription fioDescriptionInputKeyboard()
    {

        InteractionSupport interactionSupport
                = new InteractionSupport(EnumSet.of(InteractionType.input, InteractionType.focus),
                        PropertyType.numberProperty, 4);
        InteractionCapability interactionCapabibility
                = new InteractionCapability("1", interactionSupport,
                        new Modality(ModalityType.keyboard, 3.2));

        ArrayList<InteractionCapability> interaction
                = new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);

        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);

        PhysicalDescription physical
                = new PhysicalDescription(1.0, 1.0, 50, EnumSet.of(PhysicalShapeType.toy));

        return new FIODescription(physical, interaction, usage);

    }

    private static Set<PhysicalShape> createKeyboardShape()
    {

        HashSet<PhysicalShape> shapes = new HashSet<PhysicalShape>();

        shapes.add(new PhysicalShape(2.5, PhysicalShapeType.keyboard));

        return shapes;

    }

    private static InteractionSpecification interactionSpecificationQuiteSimilar()
    {
        InteractionSupport interactionSupport
                = new InteractionSupport(EnumSet.of(InteractionType.input),
                        PropertyType.numberProperty,
                        3);

        return new InteractionSpecification(interactionSupport);

    }

    private static ModalitySpecification createModalitySpecificationKeyboard()
    {

        ArrayList<Modality> modalities = new ArrayList<Modality>(5);

        modalities.add(new Modality(ModalityType.keyboard, 1.5));

        return new ModalitySpecification(modalities);

    }

    private static FIOSpecification fioSpecificationInputKeyboard()
    {

        PhysicalSpecification toySpecs
                = new PhysicalSpecification(0.2, 0.5, 10, createKeyboardShape());

        UsageSpecification usageSpecs = new UsageSpecification(40, 0.5);

        return new FIOSpecification(
                interactionSpecificationQuiteSimilar(),
                createModalitySpecificationKeyboard(),
                toySpecs,
                usageSpecs);

    }

    
    public class QueryCallback implements IFIORepositoryCallback
        {

            private int expectedCount;

        public QueryCallback(int expectedCount)
        {
            this.expectedCount = expectedCount;
        }
            
            
        
            @Override
            public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
            {
                if (modelException == null) {                    
                    System.out.println("FIO count = " + response.size());
                    for (FIORepositoryEntryCompliance fred : response) {
                        System.out.println("    " + fred.getEntry().getFioMetadata().getId() + ": " + fred.getDistance());
                    }                                        
                }
                else {
                    fioCount = 0;
                    System.out.println("Error: " + modelException.getLocalizedMessage());
                }
                
                assertTrue(this.expectedCount == response.size());
                
            }

            @Override
            public void notifyFIORepositoryError(InternalErrorException error)
            {
                System.err.println(error.getMessage());
            }

        }
    
    
    public static class FindFIOCallback implements IFIORepositoryCallback
    {

        private boolean mustByFound = false;

        public FindFIOCallback(boolean mustBeFound)
        {
            this.mustByFound = mustBeFound;
        }
        
        
        
        @Override
        public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
        {
            if (fioEntry != null) {
                System.out.print("FIO was found: ");
                System.out.println(fioEntry);
            }
            else {
                System.out.println("FIO NOT FOUND");
            }
            
            assertTrue(mustByFound == (fioEntry != null));
            
        }

        @Override
        public void notifyFIORepositoryError(InternalErrorException error)
        {
            System.err.println(error.getMessage());
        }

        @Override
        public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static class RegisterCallback implements IFIORepositoryCallback
    {

        @Override
        public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
        {
            System.out.println("FIO registered: " + fioEntry.getFioMetadata().getId());
        }

        @Override
        public void notifyFIORepositoryError(InternalErrorException error)
        {
            System.err.println(error.getMessage());
        }

        @Override
        public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static class DeregisterCallback implements IFIORepositoryCallback
    {

        @Override
        public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
        {
            System.out.println("FIO deregistered: " + fioEntry.getFioMetadata().getId());
        }

        @Override
        public void notifyFIORepositoryError(InternalErrorException error)
        {
            System.err.println(error.getMessage());
        }

        @Override
        public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
