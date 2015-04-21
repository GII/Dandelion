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
 
 package com.hi3project.dandelion.uib.util.profiles;

import com.hi3project.dandelion.models.environment.Climate;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.environment.EnvironmentSituation;
import com.hi3project.dandelion.models.environment.EnvironmentType;
import com.hi3project.dandelion.models.environment.Lighting;
import com.hi3project.dandelion.models.environment.LightingType;
import com.hi3project.dandelion.models.environment.Motion;
import com.hi3project.dandelion.models.environment.Noise;
import com.hi3project.dandelion.models.environment.Space;
import com.hi3project.dandelion.models.environment.Visibility;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.scene.activity.AbilityRequisites;
import com.hi3project.dandelion.models.scene.activity.Activity;
import com.hi3project.dandelion.models.scene.activity.ActivityMode;
import com.hi3project.dandelion.models.scene.activity.ActivityStyle;
import com.hi3project.dandelion.models.scene.activity.ActivityType;
import com.hi3project.dandelion.models.user.CognitiveAbilities;
import com.hi3project.dandelion.models.user.ColourPerception;
import com.hi3project.dandelion.models.user.HearingAbilities;
import com.hi3project.dandelion.models.user.ICTAbilities;
import com.hi3project.dandelion.models.user.MotorAbilities;
import com.hi3project.dandelion.models.user.PhysicalProperties;
import com.hi3project.dandelion.models.user.Religion;
import com.hi3project.dandelion.models.user.Sex;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.models.user.VisionAbilities;


/**
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog: 19-mar-2015
 */
public class ProfileUtils
{
    
    
    public static InputProfiles createInputProfiles(
            UserProfile user, EnvironmentProfile environment, SceneProfile scene)
    {
        
        InputProfiles profiles = new InputProfiles(
                user.getName(), user.getAge(), 
                user.getPhysical().getHeight(), user.getPhysical().getWeight(), 
                user.getVision().getVisualAcuity(), user.getVision().getVisionField(),
                user.getHearing().getHearing(),
                user.getCognitive().getLanguageReception(), user.getCognitive().getLanguageProduction(),
                user.getCognitive().getAbstractSigns(), user.getCognitive().getAttention(),
                user.getCognitive().getProcessingSpeed(), user.getCognitive().getWorkingMemory(), user.getCognitive().getLongtermMemory(), 
                user.getMotor().getSpeechArticulation(), user.getMotor().getFingerPrecision(),
                user.getMotor().getHandPrecision(), user.getMotor().getArmPrecision(), 
                user.getMotor().getContactGrip(), user.getMotor().getPinchGrip(),
                user.getMotor().getClenchGrip(), user.getMotor().getHandEyeCoordination(), 
                user.getIct().getLiteracy(), user.getIct().getAnxiety(),
                environment.getName(), environment.getLocation(), 
                environment.getClimate().getTemperature(), environment.getClimate().getHumidity(), environment.getClimate().getWind(),
                environment.getVisibility().getVisilibity(), environment.getVisibility().getContrast(),
                environment.getVisibility().getLighting().getIntensity(),
                environment.getNoise().getAmbient(),
                environment.getMotion().getMotion(), environment.getMotion().getVibration(),                
                environment.getSpace().getSpace(), createEvironmentType(environment.getType()), createEvironmentSituation(environment.getSituation()), 
                scene.getActivity().getName(), createActivityType(scene.getActivity().getActivityType()),
                createActivityStyle(scene.getActivity().getActivityStyle()), createActivityMode(scene.getActivity().getActivityMode()), scene.getUserCount(),
                scene.getActivity().getAbilityRequisites().getHands(),
                scene.getActivity().getAbilityRequisites().getVision(),
                scene.getActivity().getAbilityRequisites().getAttention());
        
        return profiles;
        
    }
    

    private static ColourPerception getColourPerception()
    {
        switch (0) {
            case 0:
                return ColourPerception.FULL_COLOR;
            case 1:
                return ColourPerception.COLOR_BLIND;
            case 2:
                return ColourPerception.NONE;
            default:
                return ColourPerception.FULL_COLOR;
        }
    }

    public static UserProfile createUserProfile(InputProfiles profiles)
    {

        PhysicalProperties physical = new PhysicalProperties(
                profiles.getUser_physical_height(),
                profiles.getUser_physical_weight());

        VisionAbilities vision = new VisionAbilities(
                profiles.getUser_vision_acuity(),
                profiles.getUser_vision_field(),
                getColourPerception());

        HearingAbilities hearing = new HearingAbilities(
                profiles.getUser_hearing_hearing());

        CognitiveAbilities cognitive = new CognitiveAbilities(
                profiles.getUser_cogn_langRecept(),
                profiles.getUser_cogn_langProd(),
                profiles.getUser_cogn_signs(),
                profiles.getUser_cogn_attention(),
                profiles.getUser_cogn_procSpeed(),
                profiles.getUser_cogn_workMem(),
                profiles.getUser_cogn_longMem());

        MotorAbilities motor = new MotorAbilities(
                profiles.getUser_motor_speechArt(),
                profiles.getUser_motor_finger(),
                profiles.getUser_motor_hand(),
                profiles.getUser_motor_arm(),
                profiles.getUser_motor_contact(),
                profiles.getUser_motor_pinch(),
                profiles.getUser_motor_clench(),
                profiles.getUser_motor_handEye());

        ICTAbilities ict = new ICTAbilities(
                profiles.getUser_ict_literacy(),
                profiles.getUser_ict_anxiety());

        return new UserProfile(
                profiles.getUser_name(),
                profiles.getUser_age(),
                Sex.MALE,
                Religion.CATHOLIC,
                "spanish",
                "es",
                physical,
                vision,
                hearing,
                cognitive,
                motor,
                ict);

    }

    private static Lighting getLighting(InputProfiles profiles)
    {

        LightingType ltype = LightingType.WARMLIGHT;
        switch (1) {
            case 0:
                ltype = LightingType.SUNLIGHT;
                break;
            case 1:
                ltype = LightingType.WARMLIGHT;
                break;
            case 2:
                ltype = LightingType.COLDLIGHT;
                break;
            case 3:
                ltype = LightingType.SHADOW;
                break;
            case 4:
                ltype = LightingType.NO_LIGHT;
                break;
        }

        return new Lighting(profiles.getEnv_vision_lighting(), ltype);

    }

    private static EnvironmentType createEnvironmentType(InputProfiles profiles)
    {

        if (profiles.getEnv_type() <= 5) {
            return EnvironmentType.mobile;
        }
        else {
            return EnvironmentType.stationary;
        }

    }
    
    private static double createEvironmentType(EnvironmentType type)
    {
        if (type == EnvironmentType.mobile) {
            return 2.;
        }
        else {
            return 8.0;
        }
    }
    

    private static EnvironmentSituation createEnvironmentSituation(InputProfiles profiles)
    {

        if (profiles.getEnv_situation() <= 5) {
            return EnvironmentSituation.indoor;
        }
        else {
            return EnvironmentSituation.outdoor;
        }

    }
    
    private static double createEvironmentSituation(EnvironmentSituation situation)
    {
        if (situation == EnvironmentSituation.indoor) {
            return 2.;
        }
        else {
            return 8.0;
        }
    }

    public static EnvironmentProfile createEnvironmentProfile(InputProfiles profiles)
    {

        Noise noise = new Noise(profiles.getEnv_noise_ambient());

        Climate climante = new Climate(
                profiles.getEnv_clima_temp(),
                profiles.getEnv_clima_humidity(),
                profiles.getEnv_clima_wind());

        Visibility visibility = new Visibility(
                getLighting(profiles),
                profiles.getEnv_vision_visibility(),
                profiles.getEnv_vision_contrast());

        Motion motion = new Motion(
                profiles.getEnv_motion_motion(),
                profiles.getEnv_motion_vibra());

        Space space = new Space(profiles.getEnv_space());

        return new EnvironmentProfile(
                profiles.getEnv_name(),
                profiles.getEnv_location(),
                noise,
                climante,
                visibility,
                motion,
                space,
                createEnvironmentType(profiles),
                createEnvironmentSituation(profiles));

    }

    private static ActivityType createActivityType(InputProfiles profiles)
    {
        if (profiles.getScene_type() <= 2.5) {
            return ActivityType.work;
        }
        else if (profiles.getScene_type() <= 5.0) {
            return ActivityType.daily;
        }
        else if (profiles.getScene_type() <= 7.5) {
            return ActivityType.fitness;
        }
        else if (profiles.getScene_type() <= 10.0) {
            return ActivityType.leisure;
        }
        else {
            return ActivityType.work;
        }
    }
    
    private static double createActivityType(ActivityType type)
    {
        if (type == ActivityType.work) {
            return 1.0;
        }
        else if (type == ActivityType.daily) {
            return 4.0;
        }
        else if (type == ActivityType.fitness) {
            return 6.0;
        }
        else if (type == ActivityType.leisure) {
            return 9.0;
        }
        else {
            return 1.0;
        }
    }

    private static ActivityStyle createActivityStyle(InputProfiles profiles)
    {
        if (profiles.getScene_style() <= 5) {
            return ActivityStyle.individual;
        }
        else {
            return ActivityStyle.social;
        }
    }
    
    private static double createActivityStyle(ActivityStyle style)
    {
        if (style == ActivityStyle.individual) {
            return 2.0;
        }
        else {
            return 7.5;
        }
    }

    private static ActivityMode createActivityMode(InputProfiles profiles)
    {
        if (profiles.getScene_mode() <= 5) {
            return ActivityMode.on_the_go;
        }
        else {
            return ActivityMode.stationary;
        }
    }
    
    private static double createActivityMode(ActivityMode mode)
    {
        if (mode == ActivityMode.on_the_go) {
            return 2.0;
        }
        else {
            return 7.5;
        }
    }

    public static SceneProfile createSceneProfile(InputProfiles profiles)
    {

        Activity activity = new Activity(
                profiles.getScene_name(),
                createActivityStyle(profiles),
                createActivityType(profiles),
                createActivityMode(profiles),
                new AbilityRequisites(profiles.getScene_hands(),
                        profiles.getScene_vision(),
                        profiles.getScene_attention()));

        return new SceneProfile(
                activity,
                (int) profiles.getScene_user_count());

    }

}
