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
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.util.DandelionFIORepositoryUtils;
import com.hi3project.dandelion.fio.stomp.StompFIOManager;
import com.hi3project.dandelion.gip.codec.json.JSONGIPCodec;
import com.hi3project.dandelion.util.properties.Property;
import com.hi3project.fuzzylogic.FuzzyVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      12-mar-2015
 */
public class StompFIOReposityClientTest 
{
    
    public static void main(String [] args)
    {
        try {
            
            
            FIOExtendedMetadata fioMetadata = new FIOExtendedMetadata("EMP-Control");
            
            IOutputAction output = new IOutputAction()
            {

                @Override
                public void doOutput(Collection<Property> properties, Collection<FuzzyVariable> fuzzyHints)
                {
                    System.out.println("OUTPUT!");
                }

                @Override
                public String getId()
                {
                    return "output";
                }
            };
            
            
            IFocusAction focus = new IFocusAction()
            {

                @Override
                public void doFocus(Collection<FuzzyVariable> fuzzyHints)
                {
                    System.out.println("FOCUS!");
                }

                @Override
                public String getId()
                {
                    return "focus";
                }
                
                
            };
            
            
            ISelectionAction selection = new ISelectionAction()
            {

                @Override
                public void doSelection(Property defaultSelected, Collection<Property> properties, Collection<FuzzyVariable> fuzzyHints)
                {
                    System.out.println("SELECTION!");
                }

                @Override
                public String getId()
                {
                    return "selection";
                }

                
            };
            
            
            StompFIOManager fioManager = new StompFIOManager(
                    new JSONGIPCodec(),
                    new JsonFIORepositoryProtocolCodec(),
                    DandelionFIORepositoryUtils.DANDELION_REPOSITORY_NAME,
                    fioMetadata);
            
            
            fioManager.addOutputAction(output);
            fioManager.addFocusAction(focus);
            fioManager.addSelectionAction(selection);
            
            fioManager.start("localhost", 61613, "", "");
            
            fioManager.acctionHappened("oryeba", new ArrayList<FuzzyVariable>());
            
            System.out.println("");
            
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StompFIOReposityClientTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //fioManager.stop();
            
            
        } catch (InternalErrorException ex) {
            Logger.getLogger(StompFIOReposityClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
