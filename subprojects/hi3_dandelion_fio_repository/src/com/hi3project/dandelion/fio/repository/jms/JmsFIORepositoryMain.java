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

import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.util.log.Logging;
import java.util.logging.Level;
import javax.jms.Connection;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      16-abr-2014
 */
public class JmsFIORepositoryMain 
{
    
    
    public static void main(String [] args)
    {
        
        Logging.setLevel(Level.FINE);
        
        if (args.length == 1) {
        
            try {
                
                // Create a ConnectionFactory
                ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory(args[0]);

                // Create a Connection
                Connection jmsConnection = jmsConnectionFactory.createConnection();
                jmsConnection.start();

                Session jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                JmsFIORepositoryInstance repositoryInstance = 
                        new JmsFIORepositoryInstance(jmsSession,DandelionFIORepositoryUtils.DANDELION_REPOSITORY_NAME);

                repositoryInstance.start();

                while(true) {
                        Thread.sleep(500);
                }
                
            }
            catch(Exception ex) {
                Logging.logger.log(Level.SEVERE, "", ex);
            }
            
            
        }
        else {
            System.err.println("Two arguments required: fio-repository-id   jms-broker-uri");
        }
        
    }
    

}
