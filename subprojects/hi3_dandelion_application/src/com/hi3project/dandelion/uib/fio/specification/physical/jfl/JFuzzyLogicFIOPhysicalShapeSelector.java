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
 
 package com.hi3project.dandelion.uib.fio.specification.physical.jfl;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.physical.PhysicalShape;
import com.hi3project.dandelion.fio.description.physical.PhysicalShapeType;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalShapeSelector;
import com.hi3project.dandelion.util.jfl.AbstractJFuzzyLogicFIS;
import com.hi3project.dandelion.util.jfl.JFuzzyLogicFISLoader;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.sourceforge.jFuzzyLogic.FIS;
import org.usixml.model.aui.AbstractInteractionUnit;


/** A FIO Physical Specification Builder using fuzzy logic.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class JFuzzyLogicFIOPhysicalShapeSelector extends AbstractJFuzzyLogicFIS implements IFIOPhysicalShapeSelector
{
    
    private static final String CLASS_NAME="com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalShapeSelector";
    private static final String FCL_PATH="/com/hi3project/dandelion/uib/fio/specification/physical/jfl/fio_physical_shape_selector.fcl";
    
    
    
    public static JFuzzyLogicFIOPhysicalShapeSelector instantiate() throws ErrorLoadingFISException
    {
        JFuzzyLogicFISLoader fisLoader = new JFuzzyLogicFISLoader("");
        try {
            JFuzzyLogicFIOPhysicalShapeSelector shapeSelector =
                    (JFuzzyLogicFIOPhysicalShapeSelector) fisLoader.loadFIS(CLASS_NAME, FCL_PATH);
            return shapeSelector;
        } catch (ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, FCL_PATH);
        }
    }
    
    
    protected void setPhysicalSpecs(double size, double status, double distance)
    {
        
        getFIS().setVariable("fio_physical_size", size);
        getFIS().setVariable("fio_physical_status", status);
        getFIS().setVariable("fio_physical_distance", distance);        
        
    }
    
    protected void setUser(UserProfile user)
    {
        double age = 0, literacy = 0;
        if (user != null) {
            age = user.getAge();
            literacy = user.getIct().getLiteracy();
        }
        getFIS().setVariable("user_age", age);
        getFIS().setVariable("user_ict_literacy", literacy);
    }
    

    protected void setEnvironment(EnvironmentProfile environmnet)
    {
        double motion = 0;
        if (environmnet != null) {
            motion = environmnet.getMotion().getMotion();
        }
        
        getFIS().setVariable("env_motion", motion);
    }
    
    
    protected void setModalities(Collection<Modality> modalities)
    {
        for(Modality m : modalities) {
            setModality(m);
        }
    }
    
    
    protected void setModality(Modality modality)
    {
        
        switch(modality.getType()) {
            case gesture:
                getFIS().setVariable("modality_gesture", modality.getGranularity());
                break;
            case keyboard:
                getFIS().setVariable("modality_keyboard", modality.getGranularity());
                break;
            case sound_production:
                getFIS().setVariable("modality_sound_production", modality.getGranularity());
                break;
            case speech_recognition:
                getFIS().setVariable("modality_speech_recognition", modality.getGranularity());
                break;
            case speech_production:
                getFIS().setVariable("modality_speech_production", modality.getGranularity());
                break;
            case symbol:
                getFIS().setVariable("modality_symbol", modality.getGranularity());
                break;
            case touch:
                getFIS().setVariable("modality_touch", modality.getGranularity());
                break;
            case video:
                getFIS().setVariable("modality_video", modality.getGranularity());
                break;
            case wimp:
                getFIS().setVariable("modality_wimp", modality.getGranularity());
                break;
        }
        
    }

    
    protected void setAIU(AbstractInteractionUnit aiu)
    {
        
    }
    
    
    

    @Override
    public Set<PhysicalShape> selectPhysicalShape(
            AbstractInteractionUnit aiu, Collection<Modality> modalities, UserProfile user,
            double size, double status, double distance)
    {
        setAIU(aiu);
        setUser(user);
        setEnvironment(null);
        setModalities(modalities);
        setPhysicalSpecs(size, status, distance);
        
        return getResult();
    }

    
    
    @Override
    public Set<PhysicalShape> selectPhysicalShape(
            AbstractInteractionUnit aiu, Collection<Modality> modalities, EnvironmentProfile env,
            double size, double status, double distance)
    {
        setAIU(aiu);
        setUser(null);
        setEnvironment(env);
        setModalities(modalities);
        setPhysicalSpecs(size, status, distance);
        
        return getResult();
    }

    
    
    @Override
    public Set<PhysicalShape> selectPhysicalShape(
            AbstractInteractionUnit aiu, Collection<Modality> modalities, UserProfile user, EnvironmentProfile env,
            double size, double status, double distance)
    {
        setAIU(aiu);
        setEnvironment(env);
        setUser(user);
        setModalities(modalities);
        setPhysicalSpecs(size, status, distance);
        
        return getResult();
    }
    
    
    private double ignoreNaN(double value)
    {
        if (Double.isNaN(value)) {
            value = 4.0;
        }
        return value;
    }
    
    
    private Set<PhysicalShape> getResult()
    {
        
        HashSet<PhysicalShape> shapeList = new HashSet<PhysicalShape>(10);
        
        FIS fis = getFIS();
        
        fis.evaluate();
        
        
        double shapeButton = fis.getVariable("fio_physical_shape_button").getValue();
        shapeButton = ignoreNaN(shapeButton);
        shapeList.add(new PhysicalShape(shapeButton, PhysicalShapeType.button));
        
        double shapeDisplay = fis.getVariable("fio_physical_shape_display").getValue();
        shapeDisplay = ignoreNaN(shapeDisplay);
        shapeList.add(new PhysicalShape(shapeDisplay, PhysicalShapeType.display));
        
        double shapeEmbedded = fis.getVariable("fio_physical_shape_embedded").getValue();
        shapeEmbedded = ignoreNaN(shapeEmbedded);
        shapeList.add(new PhysicalShape(shapeEmbedded, PhysicalShapeType.embedded));
        
        double shapeKeyboard = fis.getVariable("fio_physical_shape_keyboard").getValue();
        shapeKeyboard = ignoreNaN(shapeKeyboard);
        shapeList.add(new PhysicalShape(shapeKeyboard, PhysicalShapeType.keyboard));
        
        double shapeRemote = fis.getVariable("fio_physical_shape_remote").getValue();
        shapeRemote = ignoreNaN(shapeRemote);
        shapeList.add(new PhysicalShape(shapeRemote, PhysicalShapeType.remote));
        
        double shapeSurface = fis.getVariable("fio_physical_shape_surface").getValue();
        shapeSurface = ignoreNaN(shapeSurface);
        shapeList.add(new PhysicalShape(shapeSurface, PhysicalShapeType.surface));
        
        double shapeToy = fis.getVariable("fio_physical_shape_toy").getValue();
        shapeToy = ignoreNaN(shapeToy);
        shapeList.add(new PhysicalShape(shapeToy, PhysicalShapeType.toy));
        
        return shapeList;
        
    }
    

}
