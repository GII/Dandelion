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
 
package com.hi3project.dandelion.fio.repository.jms;

import com.hi3project.dandelion.fio.repository.FIORepository;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.db.HashMapFIORepositoryDataBase;
import com.hi3project.dandelion.fio.repository.exception.UnableToStartFIORepositoryException;
import com.hi3project.dandelion.fio.repository.metrics.manager.DefaultMetricsManagerPopulator;
import com.hi3project.dandelion.fio.repository.metrics.manager.IFIOMetricsManager;
import com.hi3project.dandelion.fio.repository.metrics.manager.PrototypeMetricsManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Session;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class JmsFIORepositoryInstance 
{

    
    private FIORepository fioRepository;
    private JmsFIORepositoryComm fioRepositoryComm;
    
    
    private final Session mqSession;
    private final String repositoryId;

    
    public JmsFIORepositoryInstance(Session mqSession, String repositoryId)
    {
        this.mqSession = mqSession;
        this.repositoryId = repositoryId;
    }
    
    
    
    public void start() throws UnableToStartFIORepositoryException
    {
        
        HashMapFIORepositoryDataBase fioDB = new HashMapFIORepositoryDataBase();
        
        IFIOMetricsManager metricsManager = new PrototypeMetricsManager();
        DefaultMetricsManagerPopulator metricsPopulator = 
                new DefaultMetricsManagerPopulator(metricsManager);
        metricsPopulator.populate();
        
        this.fioRepository = new FIORepository(repositoryId, fioDB, metricsManager);
        
        JsonFIORepositoryProtocolCodec codec = new JsonFIORepositoryProtocolCodec();
        this.fioRepositoryComm = new JmsFIORepositoryComm(fioRepository, codec, mqSession);
        try {
            this.fioRepositoryComm.start();
        } catch (JMSException ex) {
            throw new UnableToStartFIORepositoryException(ex);
        }
        
    }
    
    
    public void stop() throws UnableToStartFIORepositoryException
    {
        try {
            this.fioRepositoryComm.stop();
        } catch (JMSException ex) {
            throw new UnableToStartFIORepositoryException(ex);
        }
    }
    

}
