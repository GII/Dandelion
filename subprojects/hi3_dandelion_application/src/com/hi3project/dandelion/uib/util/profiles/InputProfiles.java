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

/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-feb-2015
 */
public class InputProfiles 
{
    
    private String user_name;
    private int user_age;
    
    private double user_physical_height;
    private double user_physical_weight;

    private double user_vision_acuity;
    private double user_vision_field;
    
    private double user_hearing_hearing;
    
    private double user_cogn_langRecept;
    private double user_cogn_langProd;
    private double user_cogn_signs;
    private double user_cogn_attention;
    private double user_cogn_procSpeed;
    private double user_cogn_workMem;
    private double user_cogn_longMem;
    
    private double user_motor_speechArt;
    private double user_motor_finger;
    private double user_motor_hand;
    private double user_motor_arm;
    private double user_motor_contact;
    private double user_motor_pinch;
    private double user_motor_clench;
    private double user_motor_handEye;
    
    private double user_ict_literacy;
    private double user_ict_anxiety;
    
    
    
    
    private String env_name;
    private String env_location;
    
    private double env_clima_temp;
    private double env_clima_humidity;
    private double env_clima_wind;
    
    private double env_vision_visibility;
    private double env_vision_contrast;
    private double env_vision_lighting;
    
    private double env_noise_ambient;
    
    private double env_motion_motion;
    private double env_motion_vibra;
    
    private double env_space;
    
    private double env_type;
    private double env_situation;

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public int getUser_age()
    {
        return user_age;
    }

    public void setUser_age(int user_age)
    {
        this.user_age = user_age;
    }

    public double getUser_physical_height()
    {
        return user_physical_height;
    }

    public void setUser_physical_height(double user_physical_height)
    {
        this.user_physical_height = user_physical_height;
    }

    public double getUser_physical_weight()
    {
        return user_physical_weight;
    }

    public void setUser_physical_weight(double user_physical_weight)
    {
        this.user_physical_weight = user_physical_weight;
    }

    public double getUser_vision_acuity()
    {
        return user_vision_acuity;
    }

    public void setUser_vision_acuity(double user_vision_acuity)
    {
        this.user_vision_acuity = user_vision_acuity;
    }

    public double getUser_vision_field()
    {
        return user_vision_field;
    }

    public void setUser_vision_field(double user_vision_field)
    {
        this.user_vision_field = user_vision_field;
    }

    public double getUser_hearing_hearing()
    {
        return user_hearing_hearing;
    }

    public void setUser_hearing_hearing(double user_hearing_hearing)
    {
        this.user_hearing_hearing = user_hearing_hearing;
    }

    public double getUser_cogn_langRecept()
    {
        return user_cogn_langRecept;
    }

    public void setUser_cogn_langRecept(double user_cogn_langRecept)
    {
        this.user_cogn_langRecept = user_cogn_langRecept;
    }

    public double getUser_cogn_langProd()
    {
        return user_cogn_langProd;
    }

    public void setUser_cogn_langProd(double user_cogn_langProd)
    {
        this.user_cogn_langProd = user_cogn_langProd;
    }

    public double getUser_cogn_signs()
    {
        return user_cogn_signs;
    }

    public void setUser_cogn_signs(double user_cogn_signs)
    {
        this.user_cogn_signs = user_cogn_signs;
    }

    public double getUser_cogn_attention()
    {
        return user_cogn_attention;
    }

    public void setUser_cogn_attention(double user_cogn_attention)
    {
        this.user_cogn_attention = user_cogn_attention;
    }

    public double getUser_cogn_procSpeed()
    {
        return user_cogn_procSpeed;
    }

    public void setUser_cogn_procSpeed(double user_cogn_procSpeed)
    {
        this.user_cogn_procSpeed = user_cogn_procSpeed;
    }

    public double getUser_cogn_workMem()
    {
        return user_cogn_workMem;
    }

    public void setUser_cogn_workMem(double user_cogn_workMem)
    {
        this.user_cogn_workMem = user_cogn_workMem;
    }

    public double getUser_cogn_longMem()
    {
        return user_cogn_longMem;
    }

    public void setUser_cogn_longMem(double user_cogn_longMem)
    {
        this.user_cogn_longMem = user_cogn_longMem;
    }

    public double getUser_motor_speechArt()
    {
        return user_motor_speechArt;
    }

    public void setUser_motor_speechArt(double user_motor_speechArt)
    {
        this.user_motor_speechArt = user_motor_speechArt;
    }

    public double getUser_motor_finger()
    {
        return user_motor_finger;
    }

    public void setUser_motor_finger(double user_motor_finger)
    {
        this.user_motor_finger = user_motor_finger;
    }

    public double getUser_motor_hand()
    {
        return user_motor_hand;
    }

    public void setUser_motor_hand(double user_motor_hand)
    {
        this.user_motor_hand = user_motor_hand;
    }

    public double getUser_motor_arm()
    {
        return user_motor_arm;
    }

    public void setUser_motor_arm(double user_motor_arm)
    {
        this.user_motor_arm = user_motor_arm;
    }

    public double getUser_motor_contact()
    {
        return user_motor_contact;
    }

    public void setUser_motor_contact(double user_motor_contact)
    {
        this.user_motor_contact = user_motor_contact;
    }

    public double getUser_motor_pinch()
    {
        return user_motor_pinch;
    }

    public void setUser_motor_pinch(double user_motor_pinch)
    {
        this.user_motor_pinch = user_motor_pinch;
    }

    public double getUser_motor_clench()
    {
        return user_motor_clench;
    }

    public void setUser_motor_clench(double user_motor_clench)
    {
        this.user_motor_clench = user_motor_clench;
    }

    public double getUser_motor_handEye()
    {
        return user_motor_handEye;
    }

    public void setUser_motor_handEye(double user_motor_handEye)
    {
        this.user_motor_handEye = user_motor_handEye;
    }

    public double getUser_ict_literacy()
    {
        return user_ict_literacy;
    }

    public void setUser_ict_literacy(double user_ict_literacy)
    {
        this.user_ict_literacy = user_ict_literacy;
    }

    public double getUser_ict_anxiety()
    {
        return user_ict_anxiety;
    }

    public void setUser_ict_anxiety(double user_ict_anxiety)
    {
        this.user_ict_anxiety = user_ict_anxiety;
    }

    public String getEnv_name()
    {
        return env_name;
    }

    public void setEnv_name(String env_name)
    {
        this.env_name = env_name;
    }

    public String getEnv_location()
    {
        return env_location;
    }

    public void setEnv_location(String env_location)
    {
        this.env_location = env_location;
    }

    public double getEnv_clima_temp()
    {
        return env_clima_temp;
    }

    public void setEnv_clima_temp(double env_clima_temp)
    {
        this.env_clima_temp = env_clima_temp;
    }

    public double getEnv_clima_humidity()
    {
        return env_clima_humidity;
    }

    public void setEnv_clima_humidity(double env_clima_humidity)
    {
        this.env_clima_humidity = env_clima_humidity;
    }

    public double getEnv_clima_wind()
    {
        return env_clima_wind;
    }

    public void setEnv_clima_wind(double env_clima_wind)
    {
        this.env_clima_wind = env_clima_wind;
    }

    public double getEnv_vision_visibility()
    {
        return env_vision_visibility;
    }

    public void setEnv_vision_visibility(double env_vision_visibility)
    {
        this.env_vision_visibility = env_vision_visibility;
    }

    public double getEnv_vision_contrast()
    {
        return env_vision_contrast;
    }

    public void setEnv_vision_contrast(double env_vision_contrast)
    {
        this.env_vision_contrast = env_vision_contrast;
    }

    public double getEnv_vision_lighting()
    {
        return env_vision_lighting;
    }

    public void setEnv_vision_lighting(double env_vision_lighting)
    {
        this.env_vision_lighting = env_vision_lighting;
    }

    public double getEnv_noise_ambient()
    {
        return env_noise_ambient;
    }

    public void setEnv_noise_ambient(double env_noise_ambient)
    {
        this.env_noise_ambient = env_noise_ambient;
    }

    public double getEnv_motion_motion()
    {
        return env_motion_motion;
    }

    public void setEnv_motion_motion(double env_motion_motion)
    {
        this.env_motion_motion = env_motion_motion;
    }

    public double getEnv_motion_vibra()
    {
        return env_motion_vibra;
    }

    public void setEnv_motion_vibra(double env_motion_vibra)
    {
        this.env_motion_vibra = env_motion_vibra;
    }

    public double getEnv_space()
    {
        return env_space;
    }

    public void setEnv_space(double env_space)
    {
        this.env_space = env_space;
    }

    public double getEnv_type()
    {
        return env_type;
    }

    public void setEnv_type(double env_type)
    {
        this.env_type = env_type;
    }

    public double getEnv_situation()
    {
        return env_situation;
    }

    public void setEnv_situation(double env_situation)
    {
        this.env_situation = env_situation;
    }

    public String getScene_name()
    {
        return scene_name;
    }

    public void setScene_name(String scene_name)
    {
        this.scene_name = scene_name;
    }

    public double getScene_type()
    {
        return scene_type;
    }

    public void setScene_type(double scene_type)
    {
        this.scene_type = scene_type;
    }

    public double getScene_style()
    {
        return scene_style;
    }

    public void setScene_style(double scene_style)
    {
        this.scene_style = scene_style;
    }

    public double getScene_mode()
    {
        return scene_mode;
    }

    public void setScene_mode(double scene_mode)
    {
        this.scene_mode = scene_mode;
    }

    public double getScene_user_count()
    {
        return scene_user_count;
    }

    public void setScene_user_count(double scene_user_count)
    {
        this.scene_user_count = scene_user_count;
    }

    public double getScene_hands()
    {
        return scene_hands;
    }

    public void setScene_hands(double scene_hands)
    {
        this.scene_hands = scene_hands;
    }

    public double getScene_vision()
    {
        return scene_vision;
    }

    public void setScene_vision(double scene_vision)
    {
        this.scene_vision = scene_vision;
    }

    public double getScene_attention()
    {
        return scene_attention;
    }

    public void setScene_attention(double scene_attention)
    {
        this.scene_attention = scene_attention;
    }
    
    
    
    
    private String scene_name;
    
    private double scene_type;
    private double scene_style;
    private double scene_mode;
    private double scene_user_count;
    private double scene_hands;
    private double scene_vision;
    private double scene_attention;

    public InputProfiles(String user_name, int user_age, double user_physical_height, double user_physical_weight, double user_vision_acuity, double user_vision_field, double user_hearing_hearing, double user_cogn_langRecept, double user_cogn_langProd, double user_cogn_signs, double user_cogn_attention, double user_cogn_procSpeed, double user_cogn_workMem, double user_cogn_longMem, double user_motor_speechArt, double user_motor_finger, double user_motor_hand, double user_motor_arm, double user_motor_contact, double user_motor_pinch, double user_motor_clench, double user_motor_handEye, double user_ict_literacy, double user_ict_anxiety, String env_name, String env_location, double env_clima_temp, double env_clima_humidity, double env_clima_wind, double env_vision_visibility, double env_vision_contrast, double env_vision_lighting, double env_noise_ambient, double env_motion_motion, double env_motion_vibra, double env_space, double env_type, double env_situation, String scene_name, double scene_type, double scene_style, double scene_mode, double scene_user_count, double scene_hands, double scene_vision, double scene_attention)
    {
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_physical_height = user_physical_height;
        this.user_physical_weight = user_physical_weight;
        this.user_vision_acuity = user_vision_acuity;
        this.user_vision_field = user_vision_field;
        this.user_hearing_hearing = user_hearing_hearing;
        this.user_cogn_langRecept = user_cogn_langRecept;
        this.user_cogn_langProd = user_cogn_langProd;
        this.user_cogn_signs = user_cogn_signs;
        this.user_cogn_attention = user_cogn_attention;
        this.user_cogn_procSpeed = user_cogn_procSpeed;
        this.user_cogn_workMem = user_cogn_workMem;
        this.user_cogn_longMem = user_cogn_longMem;
        this.user_motor_speechArt = user_motor_speechArt;
        this.user_motor_finger = user_motor_finger;
        this.user_motor_hand = user_motor_hand;
        this.user_motor_arm = user_motor_arm;
        this.user_motor_contact = user_motor_contact;
        this.user_motor_pinch = user_motor_pinch;
        this.user_motor_clench = user_motor_clench;
        this.user_motor_handEye = user_motor_handEye;
        this.user_ict_literacy = user_ict_literacy;
        this.user_ict_anxiety = user_ict_anxiety;
        this.env_name = env_name;
        this.env_location = env_location;
        this.env_clima_temp = env_clima_temp;
        this.env_clima_humidity = env_clima_humidity;
        this.env_clima_wind = env_clima_wind;
        this.env_vision_visibility = env_vision_visibility;
        this.env_vision_contrast = env_vision_contrast;
        this.env_vision_lighting = env_vision_lighting;
        this.env_noise_ambient = env_noise_ambient;
        this.env_motion_motion = env_motion_motion;
        this.env_motion_vibra = env_motion_vibra;
        this.env_space = env_space;
        this.env_type = env_type;
        this.env_situation = env_situation;
        this.scene_name = scene_name;
        this.scene_type = scene_type;
        this.scene_style = scene_style;
        this.scene_mode = scene_mode;
        this.scene_user_count = scene_user_count;
        this.scene_hands = scene_hands;
        this.scene_vision = scene_vision;
        this.scene_attention = scene_attention;
    }
    
    

    
   
    
}