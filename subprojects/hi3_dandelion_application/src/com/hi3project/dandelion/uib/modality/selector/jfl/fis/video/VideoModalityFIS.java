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
 
 package com.hi3project.dandelion.uib.modality.selector.jfl.fis.video;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.modality.selector.jfl.AbstractModalitySelectionFIS;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.ActivityFuzzifiers.fuzzyActivityMode;
import static com.hi3project.dandelion.util.jfl.fuzzifiers.ActivityFuzzifiers.fuzzyActivityType;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.usixml.model.aui.AbstractInteractionUnit;


/** A FIS to evaluate the granularity of a SPEECH recognition or synthesis modality for a specific
 * user and environment set.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-jul-2014
 */
public class VideoModalityFIS extends AbstractModalitySelectionFIS
{

    @Override
    protected void setInputVariable(UserProfile user)
    {
        double visualAcuity=0;
        if (user != null) {
            visualAcuity = user.getVision().getVisualAcuity();
        }
        
        getFIS().setVariable("user_visual_acuity", visualAcuity);
    }

    @Override
    protected void setInputVariable(EnvironmentProfile environmnet)
    {
        double visibility = 0, contrast = 0, motion = 0;
        if (environmnet != null) {
            visibility = environmnet.getVisibility().getVisilibity();
            contrast = environmnet.getVisibility().getContrast();
            motion = environmnet.getMotion().getMotion();
        }
        
        getFIS().setVariable("env_visibility", visibility);
        getFIS().setVariable("env_contrast", contrast);
        getFIS().setVariable("env_motion", motion);
    }

    @Override
    protected void setInputVariable(AbstractInteractionUnit aiu)
    {
        
    }
    
    @Override
    protected void setInputVariable(SceneProfile scene)
    {        
        double vision=0, type=0, mode=0;
        if (scene != null) {
            vision = scene.getActivity().getAbilityRequisites().getVision();
            type = fuzzyActivityType(scene.getActivity().getActivityType());
            mode = fuzzyActivityMode(scene.getActivity().getActivityMode());
        }
        getFIS().setVariable("scene_vision_occupied", vision);
        getFIS().setVariable("scene_activity_type", type);
        getFIS().setVariable("scene_activity_mode", mode);
    }

    @Override
    public Modality getResult()
    {
        Variable gestureGranularity = getFIS().getVariable("modality_video");
        Modality gestureModality = new Modality(
                ModalityType.video, gestureGranularity.getValue());
        
//        for( Rule r : getFIS().getFunctionBlock("modality_video").getFuzzyRuleBlock("No1").getRules() )
//            System.out.println(r);
        
        return gestureModality;
    }

}
