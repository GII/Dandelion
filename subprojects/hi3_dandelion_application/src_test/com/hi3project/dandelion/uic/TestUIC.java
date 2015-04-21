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
 
 package com.hi3project.dandelion.uic;

import com.hi3project.dandelion.uic.DandelionUserInterfaceController;
import com.hi3project.dandelion.application.ApplicationMetadata;
import com.hi3project.dandelion.application.aui.ApplicationAUI;
import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.gip.AMQJmsGIPManager;
import com.hi3project.dandelion.gip.codec.IGIPCodec;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import com.hi3project.dandelion.uic.exception.MappingNotAvailableException;
import com.hi3project.dandelion.util.properties.StringProperty;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.usixml.model.aui.AbstractInteractionUnit;
import org.usixml.model.aui.AbstractUserInterfaceModel;
import org.usixml.model.aui.data.DataInputOutputSupport;
import org.usixml.model.aui.data.EnumDataType;

/** 
 *
 * @author gervasio
 */
public class TestUIC {
    
    
    
    
    public TestUIC() {
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
            
            ArrayList<AbstractInteractionUnit> auiUnits = new ArrayList<AbstractInteractionUnit>();
            AbstractInteractionUnit outputAiu = new AbstractInteractionUnit("1", "output", 1);
            DataInputOutputSupport dataios = new DataInputOutputSupport("1", 0, 1, EnumDataType.TEXT, true, false, true);
            outputAiu.addPresentationSupport(dataios);
            auiUnits.add(outputAiu);
            AbstractUserInterfaceModel auiModel = new AbstractUserInterfaceModel("caso de ejemplo", auiUnits);
            ApplicationAUI aui = new ApplicationAUI(auiModel);
            ApplicationMetadata app = new ApplicationMetadata("app1", "v0.1", "GII", aui);
            
            DandelionUserInterfaceController uic = new DandelionUserInterfaceController(app, gipManager);
            
            FIOMetadata fio1 = new FIOMetadata("FIO-1");
            FIOMetadata fio2 = new FIOMetadata("FIO-2");
            
            uic.addAIU2FIOMapping(outputAiu, dataios, fio1, "1");
            uic.addAIU2FIOMapping(outputAiu, dataios, fio2, "1");
            
            String texto = "texto";
            
            for(int i=0; i<10; i++) {
                
                uic.showOutput(new StringProperty(texto+"-"+i), outputAiu, dataios, new HashSet<FuzzyVariable>());
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestUIC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        } catch (CommunicationErrorException ex) {
            fail("Communication error.");
            Logger.getLogger(TestUIC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MappingNotAvailableException ex) {
            Logger.getLogger(TestUIC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
