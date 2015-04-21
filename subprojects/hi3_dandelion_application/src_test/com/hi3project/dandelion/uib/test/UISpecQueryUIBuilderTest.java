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
 
 package com.hi3project.dandelion.uib.test;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.application.aui.ApplicationAUI;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.description.interaction.InteractionCapability;
import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.fio.description.physical.PhysicalDescription;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.description.usage.UsageCharacteristics;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.gip.AMQJmsGIPManager;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.models.environment.Climate;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.environment.EnvironmentSituation;
import com.hi3project.dandelion.models.environment.EnvironmentType;
import com.hi3project.dandelion.models.environment.Lighting;
import com.hi3project.dandelion.models.environment.LightingType;
import com.hi3project.dandelion.models.environment.Motion;
import com.hi3project.dandelion.models.environment.Noise;
import com.hi3project.dandelion.models.environment.Space;
import com.hi3project.dandelion.models.environment.Visibility;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.scene.activity.AbilityRequisites;
import com.hi3project.dandelion.models.scene.activity.Activity;
import com.hi3project.dandelion.models.scene.activity.ActivityMode;
import com.hi3project.dandelion.models.scene.activity.ActivityStyle;
import com.hi3project.dandelion.models.scene.activity.ActivityType;
import com.hi3project.dandelion.models.user.CognitiveAbilities;
import com.hi3project.dandelion.models.user.ColourPerception;
import com.hi3project.dandelion.models.user.HearingAbilities;
import com.hi3project.dandelion.models.user.ICTAbilities;
import com.hi3project.dandelion.models.user.MotorAbilities;
import com.hi3project.dandelion.models.user.PhysicalProperties;
import com.hi3project.dandelion.models.user.Religion;
import com.hi3project.dandelion.models.user.Sex;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.models.user.VisionAbilities;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.local.specbuilder.FIOSpecificationQueryUIBuilder;
import com.hi3project.dandelion.uic.DandelionUserInterfaceController;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.util.log.Logging;
import com.hi3project.dandelion.util.properties.PropertyType;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
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
import static org.junit.Assert.*;
import org.junit.Test;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.AbstractUserInterfaceModel;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.EnumDataType;


/**
 *
 * @author Gervasio Varela <gervasio.varela@udc.es>
 */
public class UISpecQueryUIBuilderTest
{
    
    private JmsRemoteFIORepositoryClient fioRepo;
    private FIOSpecificationQueryUIBuilder uib;
    private IUserInterfaceController uic;
    private ApplicationMetadata appMetadata;
    private AbstractInteractionUnit outputAiu;
    private DataInputOutputSupport dataios;

    
    public UISpecQueryUIBuilderTest()
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
            
            
            IGIPCodec jsonCodec = new JSONGIPCodec();
            AMQJmsGIPManager gipManager = new AMQJmsGIPManager("tcp://localhost:61616", jsonCodec);
            
            gipManager.init();
            
            this.appMetadata = createAppMetadata1();
            this.uic = new DandelionUserInterfaceController(this.appMetadata, gipManager);
            
            
            this.uib = new FIOSpecificationQueryUIBuilder("prueba", jmsSession, true);
            this.uib.init();
            
        } catch (JMSException ex) {
            Logger.getLogger(UISpecQueryUIBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (InternalErrorException ex) {
            Logger.getLogger(UISpecQueryUIBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    private ApplicationMetadata createAppMetadata1()
    {

        ArrayList<AbstractInteractionUnit> auiUnits = new ArrayList<AbstractInteractionUnit>();
        outputAiu = new AbstractInteractionUnit("1", "output", 1);
        dataios = new DataInputOutputSupport("1", 0, 1, EnumDataType.INTEGER, true, false, true);
        outputAiu.addPresentationSupport(dataios);
        auiUnits.add(outputAiu);
        AbstractUserInterfaceModel auiModel = new AbstractUserInterfaceModel("caso de ejemplo", auiUnits);
        ApplicationAUI aui = new ApplicationAUI(auiModel);
        return new ApplicationMetadata("app1", "v0.1", "GII", aui);

    }
    
    
    private UserProfile createUserProfile1()
    {
        
        UserProfile user = new UserProfile(
                "user1", 35, Sex.MALE, Religion.CATHOLIC, "Spain", "Spanish", 
                new PhysicalProperties(180, 85),
                new VisionAbilities(0.4, 0.2, ColourPerception.FULL_COLOR),
                new HearingAbilities(0.3),
                new CognitiveAbilities(0.1, 0, 0.4, 0.3, 0.1, 0.1, 0.1),
                new MotorAbilities(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2),
                new ICTAbilities(3.8, 0));
        
        return user;
        
    }
    
    
    private EnvironmentProfile createEnvironmentProfile1()
    {
        
        EnvironmentProfile env = new EnvironmentProfile(
                "car", "home", 
                new Noise(2), 
                new Climate(20, 75, 0),
                new Visibility(new Lighting(2, LightingType.SUNLIGHT), 1.0, 1.8),
                new Motion(1.8, 2), 
                new Space(3), EnvironmentType.mobile, EnvironmentSituation.indoor);
        
        return env;
        
    }
    
    
    private SceneProfile createSceneProfile1()
    {
        
        SceneProfile scene = new SceneProfile(new Activity(
                "driving", 
                ActivityStyle.individual, ActivityType.daily, ActivityMode.on_the_go,
                new AbilityRequisites(4.0, 7.0, 5.0)),
                1);
        
        return scene;
        
    }
    
    
    private static FIODescription fioDescriptionOutputToy()
    {

        InteractionSupport interactionSupport
                = new InteractionSupport(EnumSet.of(InteractionType.output, InteractionType.focus),
                        PropertyType.numberProperty, 2);
        InteractionCapability interactionCapabibility
                = new InteractionCapability("1", interactionSupport,
                        new Modality(ModalityType.speech_production, 2.9));

        ArrayList<InteractionCapability> interaction
                = new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);

        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);

        PhysicalDescription physical
                = new PhysicalDescription(1.0, 1.0, 50, EnumSet.of(PhysicalShapeType.toy));

        return new FIODescription(physical, interaction, usage);

    }

    private static FIODescription fioDescriptionOutputDisplay()
    {

        InteractionSupport interactionSupport
                = new InteractionSupport(EnumSet.of(InteractionType.output, InteractionType.focus),
                        PropertyType.numberProperty, 4);
        InteractionCapability interactionCapabibility
                = new InteractionCapability("1", interactionSupport,
                        new Modality(ModalityType.wimp, 3.2));

        ArrayList<InteractionCapability> interaction
                = new ArrayList<InteractionCapability>();
        interaction.add(interactionCapabibility);

        UsageCharacteristics usage = new UsageCharacteristics(21, 0.5);

        PhysicalDescription physical
                = new PhysicalDescription(1.0, 1.0, 50, EnumSet.of(PhysicalShapeType.display));

        return new FIODescription(physical, interaction, usage);

    }
    
    
    private void registerFIOs() throws InternalErrorException
    {
        
        FIOExtendedMetadata fioMetadataToy = new FIOExtendedMetadata(fioDescriptionOutputToy(), "FIO-2-ToyOutput");

        fioRepo.registerFIO(fioMetadataToy, new RegisterCallback());

        FIOExtendedMetadata fioMetadataKeyb = new FIOExtendedMetadata(fioDescriptionOutputDisplay(), "FIO-2-DisplayOutput");

        fioRepo.registerFIO(fioMetadataKeyb, new RegisterCallback());
        
    }
    

    @Test
    public void testBuildSimpeUI()
    {
        
        Logging.setLevel(Level.FINE);
        
        try {
            
            this.uic.manageAbstractInteractionUnit(dataios, outputAiu, new HashSet<FuzzyVariable>());
            
            registerFIOs();
            
            this.uib.buildUI(this.appMetadata, this.uic, createUserProfile1(), createEnvironmentProfile1(), createSceneProfile1());
        
            while(true) {
                Thread.sleep(1000);
            }
            
        } catch (UnableToBuildUIException ex) {
            fail();
            Logger.getLogger(UISpecQueryUIBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            fail();
            Logger.getLogger(UISpecQueryUIBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            fail();
            Logger.getLogger(UISpecQueryUIBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
