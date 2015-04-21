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
 
 package com.hi3project.dandelion.uib.local;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.IUserInterfaceBuilder;
import com.hi3project.dandelion.uib.exception.UnableToBuildUIException;
import com.hi3project.dandelion.uib.local.xml.UIBJaxbReader;
import com.hi3project.dandelion.uib.xml.jaxb.AIU2FIOMapping;
import com.hi3project.dandelion.uib.xml.jaxb.AssociatedFIO;
import com.hi3project.dandelion.uib.xml.jaxb.DandelionApplicationFinalUIMapping;
import com.hi3project.dandelion.uic.IUserInterfaceController;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.util.log.Logging;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Level;
import javax.jms.Session;
import javax.xml.bind.JAXBException;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.InteractionSupportElement;


/** Implementation of an UI Builder that reads the mapping from a local
 * XML file and uses the FIO repository to find the specified FIOs.
 * It manages the associations AIU-to-FIO statically (only at start time)
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      23-abr-2014
 */
public class LocalFileFIORepoDandelionUIBuilder implements IUserInterfaceBuilder
{
    
    public static final String BUILDER_ID = "Dandelion.ui.builder.";
    
    
    private ApplicationMetadata appMetadata;
    private IUserInterfaceController dandelionUIC;
    
    private final String appUiBuilderFilePath;
    private DandelionApplicationFinalUIMapping uiMapping;

    private final Session jmsSession;
    private JmsRemoteFIORepositoryClient repositoryClient;
    
    private final String builderId;
    private final boolean continueOnFail;
    
    
    
    /**
     * 
     * @param appUiBuilderFilePath
     * @param jmsSession
     * @param continueOnMappingFail wether the application should continue when a AIU-FIO mapping was unavailable
     */
    public LocalFileFIORepoDandelionUIBuilder(
            String uibID,
            String appUiBuilderFilePath,
            Session jmsSession,
            boolean continueOnMappingFail)
    {
        this.appUiBuilderFilePath = appUiBuilderFilePath;
        Random r = new Random(System.currentTimeMillis());
        this.builderId = uibID;
        this.jmsSession = jmsSession;
        this.continueOnFail = continueOnMappingFail;
    }
    
    public String getId()
    {
        return BUILDER_ID+this.builderId;
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
            
        }
    }
    
    

    @Override
    public void buildUI(
            ApplicationMetadata appMetadata, IUserInterfaceController dandelionUIC,
            UserProfile user, EnvironmentProfile environment, SceneProfile scene)
            throws UnableToBuildUIException
    {
        
        if (repositoryClient == null) {
            try {
                init();
            } catch (InternalErrorException ex) {
                throw new UnableToBuildUIException(ex);
            }
        }
        
        this.appMetadata = appMetadata;
        this.dandelionUIC = dandelionUIC;
        
        UIBJaxbReader reader = new UIBJaxbReader();
        
        try {
            
            DandelionApplicationFinalUIMapping uIMapping = reader.readUIBConfigFile(new FileInputStream(appUiBuilderFilePath));
        
        
            for(AIU2FIOMapping mapping : uIMapping.getAIU2FIOMapping()) {

                AbstractInteractionUnit aiu = 
                        appMetadata.getAbstractUI().getAbstractInteractionUnitById(mapping.getAbstractInteractionUnitID());               
                
                if (aiu != null) {
                    
                    InteractionSupportElement ise = aiu.getInteractionSupportElementById(mapping.getInteractionSupportElementID());
                    
                    if (ise != null) {
                    
                        for(AssociatedFIO fio : mapping.getAssociatedFIO()) {

                            //only FIOs registered in the repository can be used
                            Logging.logger.log(Level.FINER, "Asking FIO repository for {0} to associate it with AIU {1}.", new String[] {fio.getID(), aiu.getId()});

                            this.repositoryClient.findFIOById(fio.getID(), new FindFIOByIdCallback(aiu, ise, fio.getID(), fio.getInteractionID()));                        

                        }
                        
                    }
                }
                else {
                    Logging.logger.log(Level.WARNING, "Specified AIU ({0}) was not found.", mapping.getAbstractInteractionUnitID());
                }

            }
        
        } catch (FileNotFoundException ex) {
            throw new UnableToBuildUIException(ex);
        } catch (JAXBException ex) {
            throw new UnableToBuildUIException(ex);
        } catch (InternalErrorException ex) {
            throw new UnableToBuildUIException(ex);
        }
        
    }
    
    
    private void associateAIU2FIO(AbstractInteractionUnit aiu, InteractionSupportElement ise, String fioId, String interactionId) throws MappingNotAvailableException
    {
        
        FIOMetadata fio = new FIOMetadata(fioId);
        dandelionUIC.addAIU2FIOMapping(aiu, ise, fio, interactionId);
        
    }

    
    
    
    @Override
    public void rebuildUI(UserProfile user, EnvironmentProfile environment, SceneProfile scene) throws UnableToBuildUIException
    {
        //rebuild is not support by this implementation of UIB
    }
    
    

    
    private class FindFIOByIdCallback implements IFIORepositoryCallback
    {
        
        private final AbstractInteractionUnit aiu;
        private final InteractionSupportElement ise;
        private final String fioID;
        private final String interactionId;

        
        public FindFIOByIdCallback(AbstractInteractionUnit aiu, InteractionSupportElement ise, String fioID, String interactionId)
        {
            this.aiu = aiu;
            this.ise = ise;
            this.fioID = fioID;
            this.interactionId = interactionId;
        }
        

        
        @Override
        public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
        {

            if (fioEntry != null) { //the fio was found
                try {

                    associateAIU2FIO(aiu, ise, fioID, interactionId);

                    Logging.logger.log(Level.FINE, "Mapping AIU({0}) to FIO({1}).", new Object[]{aiu.getId(), fioID});

                } catch (Exception ex) {
                    Logging.logger.log(Level.WARNING, "Mapping not available with FIO: " + fioID);
                }
                
            }
            else {
                Logging.logger.log(Level.SEVERE, "AIU-FIO mapping unavailable: unable to find the FIO {0} in the repository.", fioID);
                if (!continueOnFail) { //a mapping was not available --> end execution
                    System.exit(-1); //TODO --> SALIR BIEN!
                }
            }

        }

        @Override
        public void notifyFIORepositoryError(InternalErrorException error)
        {
            Logging.logger.log(Level.SEVERE, "AIU-FIO mapping unavailable: unable to find the FIO {0} in the repository.", fioID);
        }

        @Override
        public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    

}
