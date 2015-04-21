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
 
 package com.hi3project.dandelion.uib.modality.selector.jfl.fis.touch;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.modality.selector.jfl.AbstractModalitySelectionFIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.usixml.model.aui.AbstractInteractionUnit;


/** A FIS to evaluate the granularity of a KEYBOARD modality for a specific
 * user and environment set.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-jul-2014
 */
public class TouchModalityFIS extends AbstractModalitySelectionFIS
{

    @Override
    protected void setInputVariable(UserProfile user)
    {
        double hand=0, arm=0, finger=0, handeye=0;
        if (user != null) {
            hand =user.getMotor().getHandPrecision();
            arm =user.getMotor().getArmPrecision();
            finger =user.getMotor().getFingerPrecision();
            handeye =user.getMotor().getHandEyeCoordination();
        }
        
        getFIS().setVariable("user_motor_hand_precision", hand);
        getFIS().setVariable("user_motor_arm_precision", arm);
        getFIS().setVariable("user_motor_finger_precision", finger);
        getFIS().setVariable("user_motor_handeye_coordination", handeye);
    }

    @Override
    protected void setInputVariable(EnvironmentProfile environmnet)
    {
        double motion=0, vibration=0;
        if (environmnet != null) {
            motion = environmnet.getMotion().getMotion();
            vibration = environmnet.getMotion().getVibration();
        }
        getFIS().setVariable("env_motion", motion);
        getFIS().setVariable("env_vibration", vibration);
    }
    
    @Override
    protected void setInputVariable(SceneProfile scene)
    {
        double hands=0;
        if (scene != null) {
            hands = scene.getActivity().getAbilityRequisites().getHands();
        }
        getFIS().setVariable("scene_hands_occupied", hands);
    }

    @Override
    protected void setInputVariable(AbstractInteractionUnit aiu)
    {
        
    }

    @Override
    public Modality getResult()
    {
        Variable gestureGranularity = getFIS().getVariable("modality_touch");
        Modality gestureModality = new Modality(
                ModalityType.touch, gestureGranularity.getValue());
        
        return gestureModality;
    }

}
