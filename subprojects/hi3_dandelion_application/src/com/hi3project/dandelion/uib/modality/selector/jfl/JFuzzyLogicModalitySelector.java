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
 
 package com.hi3project.dandelion.uib.modality.selector.jfl;

import com.hi3project.dandelion.util.jfl.JFuzzyLogicFISLoader;
import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.modality.selector.IModalitySelector;
import com.hi3project.dandelion.util.jfl.AbstractJFuzzyLogicFIS;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.usixml.model.aui.AbstractInteractionUnit;


/** Implementation of a Modality selector using fuzzy logic with 
 * the JFuzzyLogic library.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-jul-2014
 */
public class JFuzzyLogicModalitySelector implements IModalitySelector
{
    
    private static final String KEY_FIS = "dandelion.modality.selector.fis.";
    public static final String JFUZZYLIGC_MODALITY_SELECTOR_CONFIG_FILE = "dandelion.modality.selector.config";
    private static final String DEFAULT_CONFIG_FILE = "/com/hi3project/dandelion/uib/modality/selector/jfl/fis/DandelionModalitySelectorConfig.properties";
    
    
    private JFuzzyLogicFISLoader fisLoader;
    
    private final Collection<AbstractModalitySelectionFIS> fisCollection;

    
    
    
    public JFuzzyLogicModalitySelector()
    {
        this.fisCollection = new ArrayList<AbstractModalitySelectionFIS>(10);
    }
    
    
    public void init() throws ErrorLoadingFISException
    {
        
        InputStream configFile = null;
        String configFilePath = System.getProperty(JFUZZYLIGC_MODALITY_SELECTOR_CONFIG_FILE);
        if (configFilePath == null) {
            configFile = getClass().getResourceAsStream(DEFAULT_CONFIG_FILE);
        }
        else {
            try {
                configFile = new FileInputStream(configFilePath);
            } catch (FileNotFoundException ex) {
                throw new ErrorLoadingFISException(ex, "Unable to read config file.");
            }
        }
        
        
        this.fisLoader = new JFuzzyLogicFISLoader(KEY_FIS);
        try {
            
            for(AbstractJFuzzyLogicFIS fis : this.fisLoader.loadFISCollection(configFile)) {
                this.fisCollection.add((AbstractModalitySelectionFIS) fis);
            }
            
        } catch (IOException ex) {
            throw new ErrorLoadingFISException(ex, "Error reading config file.");
        } catch(ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, "Unsupported modality selection FIS.");
        }
        
        
    }
    
    
    @Override
    public Collection<Modality> selectModalities(AbstractInteractionUnit aiu, SceneProfile scene)
    {
        return selectModalities(aiu, scene, null, null);
    }
    
    
    @Override
    public Collection<Modality> selectModalities(AbstractInteractionUnit aiu, UserProfile user)
    {
        return selectModalities(aiu, null, user, null);
    }

    @Override
    public Collection<Modality> selectModalities(AbstractInteractionUnit aiu, EnvironmentProfile env)
    {
        return selectModalities(aiu, null, null, env);
    }

    @Override
    public Collection<Modality> selectModalities(AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, EnvironmentProfile env)
    {
        
        ArrayList<Modality> result = new ArrayList<Modality>(fisCollection.size());
        
        for(AbstractModalitySelectionFIS fis : fisCollection) {
            
            fis.evaluate(aiu, user, env, scene);
            
            result.add(fis.getResult());
            
        }
        
        return result;
        
    }
    
    
    
    

}
