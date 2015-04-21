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
 
 package com.hi3project.dandelion.uib.local.specbuilder;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.fio.specification.FIOSpecification;
import com.hi3project.dandelion.fio.specification.InteractionSpecification;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.IUserInterfaceBuilder;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.fio.mapping.FinalUIMapping;
import com.hi3project.dandelion.uib.fio.selection.IFinalFIOSelectionStrategy;
import com.hi3project.dandelion.uib.fio.selection.care.SimpleCARESelectionStrategy;
import com.hi3project.dandelion.uib.fio.specification.DefaultFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.IFIOSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.IFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.interaction.DefaultFIOInteractionSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.DefaultFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.modality.IFIOModalitySpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalSpecificationsBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.DefaultFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.fio.specification.usage.IFIOUsageSpecificationBuilder;
import com.hi3project.dandelion.uib.local.LocalFileFIORepoDandelionUIBuilder;
import com.hi3project.dandelion.uib.modality.selector.jfl.JFuzzyLogicModalitySelector;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.mapping.AIUInteractionElement;
import com.hi3project.dandelion.util.log.Logging;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Level;
import javax.jms.Session;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      02-dic-2014
 */
public class FIOSpecificationQueryUIBuilder implements IUserInterfaceBuilder 
{
    
    private static final String QUERY_METRIC_ID = "fgm-compliance";
    
    private IFIOSpecificationBuilder fioSpecBuilder;
    
    private IFIOInteractionSpecificationBuilder interactionSpecBuilder;
    
    
    private final String builderId;
    private Session jmsSession;
    private boolean continueOnMappingFail;
    
    private JmsRemoteFIORepositoryClient repositoryClient;
    
    private ApplicationMetadata app;
    private IUserInterfaceController uic;
    
    private FinalUIMapping finalUIMapping;
    private IFinalFIOSelectionStrategy finalFIOSelectionStrategy;

    
    public FIOSpecificationQueryUIBuilder(
            String uibID,
            IFIOSpecificationBuilder fioSpecBuilder, 
            IFIOInteractionSpecificationBuilder interactionSpecBuilder,
            Session jmsSession, boolean continueOnMappingFail)
    {
        this.fioSpecBuilder = fioSpecBuilder;
        this.interactionSpecBuilder = interactionSpecBuilder;
        this.jmsSession = jmsSession;
        this.continueOnMappingFail = continueOnMappingFail;
        this.builderId = uibID;
        this.finalFIOSelectionStrategy = new SimpleCARESelectionStrategy();
        this.finalUIMapping = new FinalUIMapping();
    }  
    
    
    public FIOSpecificationQueryUIBuilder(
            String uibID,
            IFIOModalitySpecificationBuilder modalitySpecBuilder,
            IFIOPhysicalSpecificationBuilder physicalSpecBuilder, 
            IFIOUsageSpecificationBuilder usageSpecBuilder, 
            Session jmsSession, boolean continueOnMappingFail)
    {
        this(uibID, new DefaultFIOSpecificationBuilder(modalitySpecBuilder, physicalSpecBuilder, usageSpecBuilder),
             new DefaultFIOInteractionSpecificationBuilder(),
             jmsSession, continueOnMappingFail);
    }
    
    
    public FIOSpecificationQueryUIBuilder( 
            String uibID, Session jmsSession, boolean continueOnMappingFail)
    {
        
        this(uibID, null, null, jmsSession, continueOnMappingFail);
        
    }
    
    
    public String getId()
    {
        return LocalFileFIORepoDandelionUIBuilder.BUILDER_ID+this.builderId;
    }
    

    @Override
    public void init() throws InternalErrorException
    {
        
        if (repositoryClient == null) {
            
            repositoryClient = new JmsRemoteFIORepositoryClient(
                    this.builderId,
                    DandelionFIORepositoryUtils.DANDELION_REPOSITORY_NAME,
                    new JsonFIORepositoryProtocolCodec(),
                    this.jmsSession);
            
            repositoryClient.start();
            
            
            JFuzzyLogicModalitySelector modality = new JFuzzyLogicModalitySelector();
            modality.init();
            DefaultFIOModalitySpecificationBuilder modalitySpec = new DefaultFIOModalitySpecificationBuilder(modality);
            JFuzzyLogicFIOPhysicalSpecificationsBuilder physical = JFuzzyLogicFIOPhysicalSpecificationsBuilder.instantiate();
            DefaultFIOUsageSpecificationBuilder usage = new DefaultFIOUsageSpecificationBuilder();
            
            this.fioSpecBuilder = new DefaultFIOSpecificationBuilder(modalitySpec, physical, usage);
            this.interactionSpecBuilder = new DefaultFIOInteractionSpecificationBuilder();
            
        }
        
    }

    @Override
    public void buildUI(
            ApplicationMetadata appMetadata, 
            IUserInterfaceController dandelionUIC, 
            UserProfile user, 
            EnvironmentProfile environment, 
            SceneProfile scene) throws UnableToBuildUIException
    {
        
        this.app = appMetadata;
        this.uic = dandelionUIC;
        
        Collection<AIUInteractionElement> managedInteractionUnits = 
                dandelionUIC.getAllAssociatedAIUs();
        
        
        //we must find a FIO for each interaction element of each managed AIU
        //each AIU can have many interaction elements specified by InteractionSpecification objects
        for (AIUInteractionElement aiuIse : managedInteractionUnits) {
            
            InteractionSpecification interSpec = 
                    this.uic.getManagedAIUInteractionSpecification(
                            aiuIse.getAIU(), aiuIse.getInteractionSupport());

            FIOSpecification fioSpecs = 
                    this.fioSpecBuilder.buildQuery(
                            aiuIse.getAIU(), interSpec, scene, user, environment);

            try {
                
                this.repositoryClient.queryFIOsByDistance(
                        fioSpecs, 5, QUERY_METRIC_ID,
                        new UISpecBuilderRepositoryCallback(app, aiuIse.getAIU(), aiuIse.getInteractionSupport(), user, environment, scene, uic, finalFIOSelectionStrategy, finalUIMapping));
            
            } catch (InternalErrorException ex) {
                Logging.logger.log(Level.SEVERE, "Error querying FIO repository for an adequate FIO or AIU {0} and ISE {1}.", new String[] {aiuIse.getAIU().getId(), aiuIse.getInteractionSupport().getId()});
            }


        }
        
    }
    

    @Override
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
