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
 
package com.hi3project.dandelion.fio.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.description.FIODescription;
import com.hi3project.dandelion.fio.repository.comm.FIORepositoryMessage;
import com.hi3project.dandelion.fio.repository.comm.action.FindFIOByIdAction;
import com.hi3project.dandelion.fio.repository.comm.action.RegisterFIOAction;
import com.hi3project.dandelion.fio.repository.comm.codec.json.JsonFIORepositoryProtocolCodec;
import com.hi3project.dandelion.fio.repository.comm.response.FIOEntryResponse;
import com.hi3project.dandelion.fio.repository.FIORepositoryEntry;
import com.hi3project.dandelion.util.requestresponse.exception.MessageCodeDecodeErrorException;
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
public class TestJsonFIORepositoryProtocolCodec
{
    
    public TestJsonFIORepositoryProtocolCodec()
    {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void testFIOEntryResponseCodec()
    {
        
        JsonFIORepositoryProtocolCodec codec = new JsonFIORepositoryProtocolCodec();
        
        FIODescription fIODescription = new FIODescription();
        
        FIOExtendedMetadata fioMetadata = new FIOExtendedMetadata(fIODescription, "FIO-1");
               
        FIORepositoryEntry fioEntry = new FIORepositoryEntry(fioMetadata, "Fio-Comm-Channel");
        FindFIOByIdAction findAction = new FindFIOByIdAction(20, "fiofio", "fiofio", "repo");
        FIOEntryResponse entryResponse = new FIOEntryResponse(findAction, "repo-ID", fioEntry);
        
        String codedMsg=null;        
        try {
        
            codedMsg = codec.codeRequestResponseMessage(entryResponse);
            
            FIORepositoryMessage msg = (FIORepositoryMessage) codec.decodeRequestResponseMessage(codedMsg);
            
            assertEquals(entryResponse, msg);

        } catch (MessageCodeDecodeErrorException ex) {
            fail(ex.getLocalizedMessage());
        }

        
    }
    
    
    @Test
    public void testNullFIOEntryResponseCodec()
    {
        
        JsonFIORepositoryProtocolCodec codec = new JsonFIORepositoryProtocolCodec();
        
        FindFIOByIdAction findAction = new FindFIOByIdAction(20, "fiofio", "fiofio","repo");
        FIOEntryResponse entryResponse = new FIOEntryResponse(findAction, "repo-ID", null);
        
        String codedMsg=null;        
        try {
        
            codedMsg = codec.codeRequestResponseMessage(entryResponse);
            
            FIORepositoryMessage msg = (FIORepositoryMessage) codec.decodeRequestResponseMessage(codedMsg);
            
            assertEquals(entryResponse, msg);

        } catch (MessageCodeDecodeErrorException ex) {
            fail(ex.getLocalizedMessage());
        }

        
    }
    
    
    
    @Test
    public void testRegisterFIOActionCodec()
    {
        
        JsonFIORepositoryProtocolCodec codec = new JsonFIORepositoryProtocolCodec();
        
        FIODescription fIODescription = new FIODescription();
        
        FIOExtendedMetadata fioMetadata = new FIOExtendedMetadata(fIODescription, "FIO-1");
               
        RegisterFIOAction registerAction = new RegisterFIOAction(10, fioMetadata, "fiofio","repo_ID");
        
        String codedMsg=null;
        try {
        
            codedMsg = codec.codeRequestResponseMessage(registerAction);
            
            FIORepositoryMessage msg = (FIORepositoryMessage) codec.decodeRequestResponseMessage(codedMsg);
            
            assertEquals(registerAction, msg);

        } catch (MessageCodeDecodeErrorException ex) {
            fail(ex.getLocalizedMessage());
        }

        
    }
    
    
    @Test
    public void testFindFIOByIdCodec()
    {
        
        JsonFIORepositoryProtocolCodec codec = new JsonFIORepositoryProtocolCodec();
        

        FindFIOByIdAction findAction = new FindFIOByIdAction(20, "fiofio", "fiofio","repo");
        
        String codedMsg=null;
        try {
        
            codedMsg = codec.codeRequestResponseMessage(findAction);
            
            FIORepositoryMessage msg = (FIORepositoryMessage) codec.decodeRequestResponseMessage(codedMsg);
            
            assertEquals(findAction, msg);

        } catch (MessageCodeDecodeErrorException ex) {
            fail(ex.getLocalizedMessage());
        }

        
    }
}
