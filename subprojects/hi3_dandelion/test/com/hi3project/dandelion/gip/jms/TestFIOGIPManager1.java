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
 
 package com.hi3project.dandelion.gip.jms;

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.fio.gip.FIOGIPManager;
import com.hi3project.dandelion.gip.AMQJmsGIPManager;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/** 
 *
 * @author gervasio
 */
public class TestFIOGIPManager1 {
    
    
    
    
    public TestFIOGIPManager1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testPublishReceive() 
    {
                 
        //TEST DE ENVIO Y RECEPCION DE EVENTOS GIP CON JMS
        
        try {

            IGIPCodec jsonCodec = new JSONGIPCodec();
            AMQJmsGIPManager gipManager = new AMQJmsGIPManager("tcp://localhost:61616", jsonCodec);
            
            gipManager.init();
            
            FIOMetadata fio = new FIOMetadata("FIO-2");
            FIOGIPManager fioGipManager = new FIOGIPManager(
                    fio, gipManager);
            fioGipManager.addOutputAction(new TestFIOOutputAction());
            
            fioGipManager.init();
                                    
            while(true) {
                Thread.sleep(1000);
            }
            
            //gipManager.stop();
            
        } catch (CommunicationErrorException ex) {
            fail("Communication error.");
            Logger.getLogger(TestFIOGIPManager1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            fail();
            Logger.getLogger(TestFIOGIPManager1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
