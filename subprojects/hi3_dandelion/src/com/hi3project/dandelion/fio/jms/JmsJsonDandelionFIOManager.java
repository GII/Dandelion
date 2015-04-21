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
 
 package com.hi3project.dandelion.fio.jms;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.framework.exception.ModelException;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.IDandelionFIOManager;
import com.hi3project.dandelion.fio.gip.FIOGIPManager;
import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntryCompliance;
import com.hi3project.dandelion.fio.repository.client.IFIORepositoryCallback;
import com.hi3project.dandelion.fio.repository.client.jms.JmsRemoteFIORepositoryClient;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.gip.AMQJmsGIPManager;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.util.log.Logging;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import javax.jms.JMSException;


/** A FIO manager that uses JMS for communications (GIP and FIO repository)
 * and JSON as the codec for messages.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      22-abr-2014
 */
public class JmsJsonDandelionFIOManager implements IDandelionFIOManager
{
    
    
    private final String jmsBrokerUri;
    
    private IGIPCodec jsonCodec;
    private AMQJmsGIPManager gipManager;
    private JmsRemoteFIORepositoryClient repositoryClient;
    
    private final ArrayList<FIOExtendedMetadata> fioList;
    

    public JmsJsonDandelionFIOManager(String jmsBrokerUri)
    {
        this.jmsBrokerUri = jmsBrokerUri;
        this.fioList = new ArrayList<FIOExtendedMetadata>();
    }
        
    

    @Override
    public void init() throws InternalErrorException
    {

        if (this.jsonCodec == null) {
            
            this.jsonCodec = new JSONGIPCodec();

            this.gipManager = new AMQJmsGIPManager(jmsBrokerUri, jsonCodec);
            this.gipManager.init();    

            Runtime.getRuntime().addShutdownHook(new FIOShutdownHook());
            
        }
                
    }


    
    @Override
    public FIOGIPManager startFIO(
            FIOExtendedMetadata fioMetadata,
            Collection<IOutputAction> outputActions,
            Collection<IFocusAction> focusActions, 
            Collection<ISelectionAction> selectionActions) throws InternalErrorException
    {
                    
            
        //gip manager
        FIOGIPManager fioGipManager = new FIOGIPManager(
                fioMetadata, gipManager);
        
        fioGipManager.addOutputActions(outputActions);
        fioGipManager.addFocusActions(focusActions);
        fioGipManager.addSelectionActions(selectionActions);

        fioGipManager.init();        


        //repository client
        if (repositoryClient == null) {
            repositoryClient = new JmsRemoteFIORepositoryClient(
                    fioMetadata.getId(),
                    DandelionFIORepositoryUtils.DANDELION_REPOSITORY_NAME,
                    new JsonFIORepositoryProtocolCodec(),
                    this.gipManager.getJmsSession());

            repositoryClient.start();
        }

        repositoryClient.registerFIO(fioMetadata, new IFIORepositoryCallback()
        {

            @Override
            public void notifyFIORepositoryResponse(FIORepositoryEntry fioEntry, ModelException modelException)
            {
                Logging.logger.log(
                        Level.INFO,
                        "Successfull registration of FIO {0} on Dandelion FIO repository.",
                        fioEntry.getFioMetadata().getId());
            }

            @Override
            public void notifyFIORepositoryError(InternalErrorException error)
            {
                Logging.logger.log(
                        Level.WARNING,
                        "Unable to register FIO on repository:", error);
                Logging.logger.log(Level.WARNING, "Continuing withtout FIO repository.");
            }

            @Override
            public void notifyFIORepositoryQueryResponse(Collection<FIORepositoryEntryCompliance> response, ModelException modelException)
            {
                Logging.logger.log(
                        Level.WARNING,
                        "Unexpected response received from fio repository.");
            }

        });


        
        this.fioList.add(fioMetadata);
        
        return fioGipManager;

    }
    
    
    
    /** Cleans the FIO repository when the FIO VM is shutdown.
     */
    private class FIOShutdownHook extends Thread
    {
        
        @Override
        public void run()
        {
            
            for(FIOExtendedMetadata fioMeta : fioList) {
                try {
                    
                    repositoryClient.deregisterFIO(fioMeta, null);
                    
                } catch (InternalErrorException ex) {
                    Logging.logger.log(Level.WARNING, "Unable to deregister FIO: ", ex);
                }
            }
            
        }
        
        
    }
    

}
