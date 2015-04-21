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
 
 package com.hi3project.dandelion.tools.specbuilder.batch;

import com.hi3project.dandelion.uib.util.profiles.InputProfiles;
import com.google.gson.Gson;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.mytechia.commons.util.io.file.FilenameExtensionFilter;
import com.hi3project.dandelion.fio.description.interaction.InteractionSupport;
import com.hi3project.dandelion.fio.description.interaction.InteractionType;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.specification.DefaultFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.IFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.DefaultFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.IFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.DefaultFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.IFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalShapeSelector;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalShapeSelector;
import com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalSpecificationsBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.DefaultFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.IFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.modality.selector.jfl.JFuzzyLogicModalitySelector;
import static com.hi3project.dandelion.uib.util.profiles.ProfileUtils.createEnvironmentProfile;
import static com.hi3project.dandelion.uib.util.profiles.ProfileUtils.createSceneProfile;
import static com.hi3project.dandelion.uib.util.profiles.ProfileUtils.createUserProfile;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import com.hi3project.dandelion.util.properties.PropertyType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-feb-2015
 */
public class BatchIdealFIOBuilder 
{    
    
    public static void buildIdealFIO(
            File input, String interaction, String cardinality,
            Gson gson, 
            JmsRemoteFIORepositoryClient repoClient, 
            IFIOSpecificationBuilder idealFIOBuilder) 
            throws FileNotFoundException, InternalErrorException
    {
        
            InputStreamReader reader = new InputStreamReader(new FileInputStream(input));
            
            InputProfiles profiles = gson.fromJson(reader, InputProfiles.class);
            
            
            
            
            UserProfile userProfile = createUserProfile(profiles);

            EnvironmentProfile envProfile = createEnvironmentProfile(profiles);

            SceneProfile sceneProfile = createSceneProfile(profiles);

            InteractionType interactionType = paramInteractionType(interaction);
            int interactionCardinality = Integer.valueOf(cardinality);
            InteractionSpecification interactionSpecs =
                    new InteractionSpecification(new InteractionSupport(EnumSet.of(interactionType), PropertyType.stringProperty, interactionCardinality));
            
            
            //FIRST RUN --> RESULTS
            TimeCount queryTC = new TimeCount(); //queryTC.startRun();
            TimeCount specTC = new TimeCount(); //specTC.startRun();
            
            FIOSpecification fioSpecification = null;
//                    = idealFIOBuilder.buildQuery(null, interactionSpecs, sceneProfile, userProfile, envProfile);
//            specTC.endRun();
            
//            System.out.println("User = "+profiles.getUser_name());
//            System.out.println("Scenario = "+profiles.getEnv_name());
//            System.out.println("");
            
//            System.out.println(fioSpecification);
//            System.out.println("");
//            System.out.println("");
            
//            printIdealFIO(fioSpecification);
            
            System.out.println("-------------------------------");
            
            
            
//            repoClient.queryFIOsByDistance(fioSpecification, 10, "fgm-compliance", new RepositoryQueryCallback(1, queryTC, specTC));
            

            //SECOND RUN --> TIME COMPARISON
            queryTC = new TimeCount(); queryTC.startRun();
            specTC = new TimeCount();
            for(int i=0; i<500; i++) {
                                
                specTC.startRun();
                
                fioSpecification
                    = idealFIOBuilder.buildQuery(null, interactionSpecs, sceneProfile, userProfile, envProfile);
                
                specTC.endRun();
                
                repoClient.queryFIOsByDistance(fioSpecification, 10, "fgm-compliance", new RepositoryQueryCallback(500, queryTC, specTC));
            }
        
    }
    
    
    private static void printIdealFIO(FIOSpecification fioSpec)
    {
        
        System.out.println("modalities:");
        Collection<Modality> modalities = fioSpec.getModalitySpecification().getModalities();
        
        printModality(ModalityType.sound_production, modalities);
        printModality(ModalityType.speech_recognition, modalities);
        printModality(ModalityType.speech_production, modalities);
        printModality(ModalityType.touch, modalities);
        printModality(ModalityType.wimp, modalities);
        printModality(ModalityType.gesture, modalities);
        printModality(ModalityType.keyboard, modalities);
        printModality(ModalityType.symbol, modalities);
        printModality(ModalityType.video, modalities);
        
        System.out.println("");
        
        System.out.println("physical:");
        System.out.printf("%.3f\n",fioSpec.getPhysicalSpecification().getSize());
        System.out.printf("%.3f\n",fioSpec.getPhysicalSpecification().getStatus());
        System.out.printf("%.3f\n",fioSpec.getPhysicalSpecification().getDistance());
        
        System.out.println("");
        
        System.out.println("shapes:");
        Set<PhysicalShape> shapeList = fioSpec.getPhysicalSpecification().getShapeList();
        printShape(PhysicalShapeType.display, shapeList);
        printShape(PhysicalShapeType.button, shapeList);
        printShape(PhysicalShapeType.remote, shapeList);
        printShape(PhysicalShapeType.toy, shapeList);
        printShape(PhysicalShapeType.embedded, shapeList);
        printShape(PhysicalShapeType.keyboard, shapeList);
        printShape(PhysicalShapeType.surface, shapeList);
        
        System.out.println("");
        
        System.out.println("usage:");
        System.out.printf("%.3f\n",fioSpec.getUsageSpecification().getRecommendedUserAge());
        System.out.printf("%.3f\n",fioSpec.getUsageSpecification().getIctDifficulty());
        
        
    }
    
    
    private static void printModality(ModalityType type, Collection<Modality> modalities)
    {
        Modality m = getModality(type, modalities);
        if (m != null) System.out.printf("%.3f\n", m.getGranularity());
        else System.out.println("0.0");
    }
    
    
    private static Modality getModality(ModalityType type, Collection<Modality> modalities)
    {
        for(Modality m : modalities) {
            if (m.getType() == type) return m;
        }
        
        return null;
    }
    
    private static void printShape(PhysicalShapeType type, Collection<PhysicalShape> shapes)
    {
        PhysicalShape m = getShape(type, shapes);
        if (m != null) System.out.printf("%.3f\n", m.getGranularity());
        else System.out.println("0.0");
    }
    
    
    private static PhysicalShape getShape(PhysicalShapeType type, Collection<PhysicalShape> shapes)
    {
        for(PhysicalShape m : shapes) {
            if (m.getShapeType() == type) return m;
        }
        
        return null;
    }
    
    
    public static void main(String[] args)
    {

        try {
            
            if (args.length != 4) {
                
                System.err.println("ERROR: path");
                System.exit(-1);
                
            }   
            
            
            JmsRemoteFIORepositoryClient repoClient = initRepositoryClient(args[0]);
            IFIOSpecificationBuilder idealFIOBuilder = instantiateFIOSpecBuilder();
            
            Gson gson = new Gson();
            
            File input = new File(args[1]);
            
            if (!input.isDirectory()) {
                
                buildIdealFIO(input, args[2], args[3], gson, repoClient, idealFIOBuilder);
                
            }
            else {
                
                File[] files = input.listFiles(new FilenameExtensionFilter(new String[]{"txt"}, input));
                for(File f : files) {
                    System.out.println("*********************************************");
                    buildIdealFIO(f, args[2], args[3], gson, repoClient, idealFIOBuilder);                    
                    Thread.sleep(500);
                }
                
            }
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BatchIdealFIOBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(BatchIdealFIOBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(BatchIdealFIOBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BatchIdealFIOBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        
        
        
    }
    
    
    private static InteractionType paramInteractionType(String arg)
    {
        switch(arg.charAt(0)) {
            case 'a':
                return InteractionType.action;
            case 's':
                return InteractionType.selection;
            case 'o':
                return InteractionType.output;
            case 'f':
                return InteractionType.focus;
            case 'i':
                return InteractionType.input;
            default:
                return InteractionType.action;
        }
    }
    
    
    
    private static JmsRemoteFIORepositoryClient initRepositoryClient(String jmsUri)
            throws InternalErrorException, JMSException
    {
        
        // Create a ConnectionFactory
        ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory(jmsUri);

        // Create a Connection
        Connection jmsConnection = jmsConnectionFactory.createConnection();
        jmsConnection.start();

        Session jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        JmsRemoteFIORepositoryClient repoClient = new JmsRemoteFIORepositoryClient(
                "1",
                DandelionFIORepositoryUtils.DANDELION_REPOSITORY_NAME,
                new JsonFIORepositoryProtocolCodec(),
                jmsSession);

        repoClient.start();
        
        return repoClient;
    }
    

    
    
    
    
    
    private static IFIOSpecificationBuilder instantiateFIOSpecBuilder()
    {
        try {
            
            JFuzzyLogicModalitySelector fuzzyModalitySelector = new JFuzzyLogicModalitySelector();
            fuzzyModalitySelector.init();
            IFIOModalitySpecificationBuilder modalitySpecBuilder = new DefaultFIOModalitySpecificationBuilder(fuzzyModalitySelector);
            
            IFIOPhysicalShapeSelector shapeSelector = JFuzzyLogicFIOPhysicalShapeSelector.instantiate();
            IFIOPhysicalSpecificationBuilder physicalSpecBuilder = JFuzzyLogicFIOPhysicalSpecificationsBuilder.instantiate(shapeSelector);
            
            IFIOInteractionSpecificationBuilder interactionSpecbuilder = new DefaultFIOInteractionSpecificationBuilder();
            IFIOUsageSpecificationBuilder usageSpecBuilder = new DefaultFIOUsageSpecificationBuilder();
            
            return new DefaultFIOSpecificationBuilder(modalitySpecBuilder, physicalSpecBuilder, usageSpecBuilder);            
            
        } catch (ErrorLoadingFISException ex) {
            System.err.println("CRITICAL ERROR: ");
            ex.printStackTrace();
            return null;
        }
        
    }
    
    
    private static class RepositoryQueryCallback implements IFIORepositoryCallback
    {

        private TimeCount queryTC, specTC;
        private int totalRuns;

        public RepositoryQueryCallback(int totalRuns, TimeCount queryTC, TimeCount specTC)
        {
            this.queryTC = queryTC;
            this.specTC = specTC;
            this.totalRuns = totalRuns;
        }
        
        
        @Override
        public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
        {

            if (totalRuns == 1) {
                for (FIORepositoryEntryCompliance frec : response) {
                    System.out.println("-------------------------------");
                    System.out.print(frec.getEntry().getFioMetadata().getId() + " - " + frec.getDistance());
                    System.out.print("("+frec.getDistance().getInteractionSimilarity().getValue()+", "+
                            frec.getDistance().getModalitySimilarity().getValue()+", "+
                            frec.getDistance().getPhysicalSimilarity().getValue()+", "+
                            frec.getDistance().getUsageSimilarity().getValue()+")");
                    System.out.println("");
                    
                    DecimalFormat df = new DecimalFormat("#.###");
                    System.out.println(df.format(frec.getDistance().getValue()));
                    System.out.println(df.format(frec.getDistance().getInteractionSimilarity().getValue()));
                    System.out.println(df.format(frec.getDistance().getModalitySimilarity().getValue()));
                    System.out.println(df.format(frec.getDistance().getPhysicalSimilarity().getValue()));
                    System.out.println(df.format(frec.getDistance().getUsageSimilarity().getValue()));
                    
                }
                System.out.println("");
            }
            else if (totalRuns == specTC.getRuns()) {
                queryTC.endRun();
                System.out.println("Time elapsed specs+query (total "+specTC.getRuns()+" runs): "+queryTC.getTotalTime());
                System.out.println("Time elapsed specs+query (median "+specTC.getRuns()+" runs): :" +queryTC.getTotalTime()/(specTC.getRuns()*1.0));
                System.out.println("");
                System.out.println("Time elapsed specs (total "+specTC.getRuns()+" runs): "+specTC.getTotalTime());
                System.out.println("Time elapsed specs (median "+specTC.getRuns()+" runs): :" +specTC.getTotalTime()/(specTC.getRuns()*1.0));
                System.out.println("");
            }
            
        }

        @Override
        public void notifyFIORepositoryError(InternalErrorException error)
        {
            error.printStackTrace();
        }
    }
    
    
    
    private static class TimeCount
    {
        
        private long totalTime=0;
        private long runStart=0;
        private long runs=0;
        
        public void startRun()
        {
            this.runStart=System.currentTimeMillis();
        }
        
        public long endRun()
        {
            runs++;
            long runTime = System.currentTimeMillis() - runStart;
            this.totalTime += runTime;
            return runTime;
        }
        
        public long getTotalTime()
        {
            return this.totalTime;
        }
        
        public long getRuns()
        {
            return this.runs;
        }
        
    }


}
