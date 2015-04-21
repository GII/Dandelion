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
 
 package com.hi3project.dandelion.tools.specbuilder.batch.fios;

import com.google.gson.Gson;
import com.mytechia.commons.framework.exception.InternalErrorException;
import com.mytechia.commons.util.io.file.FilenameExtensionFilter;
import com.hi3project.dandelion.fio.FIOExtendedMetadata;
import com.hi3project.dandelion.fio.IDandelionFIOManager;
import com.hi3project.dandelion.fio.gip.actions.IFocusAction;
import com.hi3project.dandelion.fio.gip.actions.IOutputAction;
import com.hi3project.dandelion.fio.gip.actions.ISelectionAction;
import com.hi3project.dandelion.fio.jms.JmsJsonDandelionFIOManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      02-mar-2015
 */
public class RegisterFIOsFromDirectory 
{
    
    
    private static IDandelionFIOManager fioManager;
    
    
    public static void main(String[] args)
    {
        
        try {
            
            if (args.length != 2) {
                System.err.println("ERROR:");
                System.exit(-1);
            }
            
            
            File directory = new File(args[1]);
            
            if (!directory.isDirectory()) {
                System.err.println("ERROR: Is not a directory.");
                System.exit(-1);
            }
            
            
            fioManager = new JmsJsonDandelionFIOManager(args[0]);
            fioManager.init();
            
            
            Gson gson = new Gson();
            
            File[] files = directory.listFiles(new FilenameExtensionFilter(new String[]{"fio"}, directory));
            
            FileReader reader;
            for(File f : files) {
                try {
                    
                    reader = new FileReader(f);
                    FIOExtendedMetadata fioMetadata = gson.fromJson(reader, FIOExtendedMetadata.class);
                    registerFIO(fioMetadata);
                    
                } catch (Exception ex) {
                    System.err.println("***Unable to read file '"+f.getAbsolutePath()+"'.");
                    ex.printStackTrace();
                }
                
            }
            
        } catch (InternalErrorException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    private static void registerFIO(FIOExtendedMetadata fioMetadata) throws InternalErrorException
    {
        
        fioManager.startFIO(fioMetadata, new ArrayList<IOutputAction>(), new ArrayList<IFocusAction>(), new ArrayList<ISelectionAction>());
        
    }


}
