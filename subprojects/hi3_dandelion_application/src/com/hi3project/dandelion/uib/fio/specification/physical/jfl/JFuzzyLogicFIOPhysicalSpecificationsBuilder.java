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
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalSpecificationBuilder;
import com.hi3project.dandelion.fio.specification.PhysicalSpecification;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.uib.fio.specification.physical.IFIOPhysicalShapeSelector;
import com.hi3project.dandelion.util.jfl.AbstractJFuzzyLogicFIS;
import com.hi3project.dandelion.util.jfl.JFuzzyLogicFISLoader;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.util.Collection;
import net.sourceforge.jFuzzyLogic.FIS;
import org.usixml.model.aui.AbstractInteractionUnit;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.ActivityFuzzifiers.fuzzyActivityMode;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.ActivityFuzzifiers.fuzzyActivityStyle;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.ActivityFuzzifiers.fuzzyActivityType;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.EnvironmentFuzzifiers.fuzzyEnvironmentSituation;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.EnvironmentFuzzifiers.fuzzyEnvironmentType;
import java.util.Set;


/** A FIO Physical Specification Builder using fuzzy logic.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class JFuzzyLogicFIOPhysicalSpecificationsBuilder extends AbstractJFuzzyLogicFIS implements IFIOPhysicalSpecificationBuilder
{
    
    
    private IFIOPhysicalShapeSelector shapeSelector;
    
    private AbstractInteractionUnit aiu;
    private SceneProfile scene;
    private UserProfile user;
    private EnvironmentProfile environment;
    private Collection<Modality> modalitiesGranularity;
    
    
    
    private static final String CLASS_NAME="com.hi3project.dandelion.uib.fio.specification.physical.jfl.JFuzzyLogicFIOPhysicalSpecificationsBuilder";
    private static final String FCL_PATH="/com/hi3project/dandelion/uib/fio/specification/physical/jfl/fio_physical_specs_builder.fcl";
    
    
    
    public static JFuzzyLogicFIOPhysicalSpecificationsBuilder instantiate(
        IFIOPhysicalShapeSelector shapeSelector) throws ErrorLoadingFISException
    {
        JFuzzyLogicFISLoader fisLoader = new JFuzzyLogicFISLoader("");
        try {
            
            JFuzzyLogicFIOPhysicalSpecificationsBuilder specBuilder =
                    (JFuzzyLogicFIOPhysicalSpecificationsBuilder) fisLoader.loadFIS(CLASS_NAME, FCL_PATH);
            
            specBuilder.setShapeSelector(shapeSelector);
            
            return specBuilder;
            
        } catch (ClassCastException ex) {
            throw new ErrorLoadingFISException(ex, FCL_PATH);
        }
    }
    
    
    public static JFuzzyLogicFIOPhysicalSpecificationsBuilder instantiate() throws ErrorLoadingFISException
    {
        return instantiate(JFuzzyLogicFIOPhysicalShapeSelector.instantiate());
    }
    
    
    
    private void setShapeSelector(IFIOPhysicalShapeSelector shapeSelector)
    {
        this.shapeSelector = shapeSelector;
    }
    
    protected void setAiu(AbstractInteractionUnit aiu)
    {
        this.aiu = aiu;
    }

    protected void setScene(SceneProfile scene)
    {
        this.scene = scene;
        double activityMode = 0, activityStyle = 0, activityType = 0, userCount = 1;
        if (scene != null) {
            activityMode = fuzzyActivityMode(scene.getActivity().getActivityMode());
            activityStyle = fuzzyActivityStyle(scene.getActivity().getActivityStyle());
            activityType = fuzzyActivityType(scene.getActivity().getActivityType());
            userCount = scene.getUserCount();
        }
        getFIS().setVariable("scene_activity_mode", activityMode);
        getFIS().setVariable("scene_activity_type", activityType);
        getFIS().setVariable("scene_activity_style", activityStyle);
        getFIS().setVariable("scene_user_count", userCount);
    }
    
    protected void setUser(UserProfile user)
    {
        this.user = user;
    }
    
    protected void setEnvironment(EnvironmentProfile env)
    {
        this.environment = env;
        double motion = 0, situation = 0, type = 0, space = 0;
        if (env != null) {
            motion = env.getMotion().getMotion();
            space = env.getSpace().getSpace();
            situation = fuzzyEnvironmentSituation(env.getSituation());
            type = fuzzyEnvironmentType(env.getType());
        }
        getFIS().setVariable("env_motion", motion);
        getFIS().setVariable("env_situation", situation);
        getFIS().setVariable("env_type", type);
        getFIS().setVariable("env_space", space);
    }
    
    
    @Override
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, Collection<Modality> modalitiesGranularity)
    {
        
        setAiu(aiu);
        setScene(scene);
        setUser(null);
        setEnvironment(null);
        this.modalitiesGranularity = modalitiesGranularity;
        
        return getResult();
        
    }
    

    @Override
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, Collection<Modality> modalitiesGranularity)
    {
        
        setAiu(aiu);
        setScene(scene);
        setUser(user);
        setEnvironment(null);
        this.modalitiesGranularity = modalitiesGranularity;
        
        return getResult();
        
    }

    
    
    @Override
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, EnvironmentProfile env, Collection<Modality> modalitiesGranularity)
    {
        
        setAiu(aiu);
        setScene(scene);
        setEnvironment(env);
        setUser(null);
        this.modalitiesGranularity = modalitiesGranularity;
        
        return getResult();
        
    }

    
    
    @Override
    public PhysicalSpecification buildPhysicalSpecification(
            AbstractInteractionUnit aiu, SceneProfile scene, UserProfile user, EnvironmentProfile env, Collection<Modality> modalitiesGranularity)
    {
        
        setAiu(aiu);
        setScene(scene);
        setUser(user);
        setEnvironment(env);
        this.modalitiesGranularity = modalitiesGranularity;
        
        return getResult();
        
    }
    
    
    
    private PhysicalSpecification getResult()
    {
        
        FIS fis = getFIS();
        
        fis.evaluate();
        
        double size = fis.getVariable("fio_physical_size").getValue();
        double status = fis.getVariable("fio_physical_status").getValue();
        double distance = fis.getVariable("fio_physical_distance").getValue();

        
        Set<PhysicalShape> physicalShapes = 
                shapeSelector.selectPhysicalShape(
                        aiu, modalitiesGranularity, user, environment,
                        size, status, distance);
        
        return new PhysicalSpecification(size, status, distance, physicalShapes);
        
    }
    

}
