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
 
 package com.hi3project.dandelion.util.jfl;

import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;


/** This class is in charge of dynamically loading the FIS classes and FLCs
 * from a configurtion file.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-jul-2014
 */
public class JFuzzyLogicFISLoader 
{
    
    //private static final String KEY_FIS = "dandelion.modality.selector.fis.";
    private static final String KEY_FIS_CLASS = ".class";
    private static final String KEY_FIS_FCL = ".fcl";
    
    private final Properties propertiesFile;
    
    private final String baseKey;
    
    
    public JFuzzyLogicFISLoader(String baseKey)
    {
        this.propertiesFile = new Properties();
        this.baseKey = baseKey;
    }
    
    
    public AbstractJFuzzyLogicFIS loadFIS(String fisClass, String fclPath) 
            throws ErrorLoadingFISException
    {

        AbstractJFuzzyLogicFIS fis = null;

        if (fisClass != null && fclPath != null) {

            try {

                fis = (AbstractJFuzzyLogicFIS) Class.forName(fisClass).newInstance();

                InputStream inputFcl = getClass().getResourceAsStream(fclPath);

                fis.loadFCL(inputFcl);

            } catch (ClassNotFoundException ex) {
                throw new ErrorLoadingFISException(ex, fisClass);
            } catch (InstantiationException ex) {
                throw new ErrorLoadingFISException(ex, fisClass);
            } catch (IllegalAccessException ex) {
                throw new ErrorLoadingFISException(ex, fisClass);
            }

        }

        return fis;
        
    }
    
    
    public Collection<AbstractJFuzzyLogicFIS> loadFISCollection(InputStream configFile) 
            throws IOException, ErrorLoadingFISException
    {
        
        this.propertiesFile.load(configFile);
        
        ArrayList<AbstractJFuzzyLogicFIS> fisList = new ArrayList<AbstractJFuzzyLogicFIS>();
        
        int c = 0;
        String className, fclPath;
        do {
            
            className = this.propertiesFile.getProperty(baseKey+c+KEY_FIS_CLASS);
            fclPath = this.propertiesFile.getProperty(baseKey+c+KEY_FIS_FCL);
            
            if (className != null && fclPath != null) {
                
                try {

                    AbstractJFuzzyLogicFIS fis = 
                            (AbstractJFuzzyLogicFIS) Class.forName(className).newInstance();

                    InputStream inputFcl = getClass().getResourceAsStream(fclPath);

                    fis.loadFCL(inputFcl);

                    fisList.add(fis);

                } catch (ClassNotFoundException ex) {
                    throw new ErrorLoadingFISException(ex, className);
                } catch (InstantiationException ex) {
                    throw new ErrorLoadingFISException(ex, className);
                } catch (IllegalAccessException ex) {
                    throw new ErrorLoadingFISException(ex, className);
                }
            
                c++;
                
            }
            
        }
        while(className != null && fclPath !=null);
        
        return fisList;
        
    }
    
    

}
