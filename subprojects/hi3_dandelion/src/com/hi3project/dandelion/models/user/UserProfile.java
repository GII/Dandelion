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
 
 package com.hi3project.dandelion.models.user;

/** Profile of a Dandelion user
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      27-may-2014
 */
public class UserProfile
{
    
    private final String name;
    
    private int age;
    
    private Sex sex;
    
    private Religion religion;
    
    private String nationality;    
    
    private String preferredLanguage;
    
    
    private final PhysicalProperties physical;
    private final VisionAbilities vision;
    private final HearingAbilities hearing;
    private final CognitiveAbilities cognitive;
    private final MotorAbilities motor;
    private final ICTAbilities ict;

    
    
    public UserProfile()
    {
        this.name = "default";
        this.age = 30;
        this.sex = Sex.MALE;
        this.religion = Religion.AGNOSTIC;
        this.nationality = "spain";
        this.preferredLanguage = "spanish";
        this.physical = new PhysicalProperties(185, 85);
        this.vision = new VisionAbilities(10, 10, ColourPerception.FULL_COLOR);
        this.hearing = new HearingAbilities(10);
        this.cognitive = new CognitiveAbilities(10, 10, 10, 10, 10, 10, 10);
        this.motor = new MotorAbilities(10, 10, 10, 10, 10, 10, 10, 10);
        this.ict = new ICTAbilities(10, 0);
    }

    
    
    
    
    public UserProfile(
            String name, int age, Sex sex, Religion religion,
            String nationality, String preferredLanguage, 
            PhysicalProperties physical, VisionAbilities vision, 
            HearingAbilities hearing, CognitiveAbilities cognitive, 
            MotorAbilities motor, ICTAbilities ict)
    {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.religion = religion;
        this.nationality = nationality;
        this.preferredLanguage = preferredLanguage;
        this.physical = physical;
        this.vision = vision;
        this.hearing = hearing;
        this.cognitive = cognitive;
        this.motor = motor;
        this.ict = ict;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public Sex getSex()
    {
        return sex;
    }

    public Religion getReligion()
    {
        return religion;
    }

    public String getNationality()
    {
        return nationality;
    }

    public String getPreferredLanguage()
    {
        return preferredLanguage;
    }

    public PhysicalProperties getPhysical()
    {
        return physical;
    }

    public VisionAbilities getVision()
    {
        return vision;
    }

    public HearingAbilities getHearing()
    {
        return hearing;
    }

    public CognitiveAbilities getCognitive()
    {
        return cognitive;
    }

    public MotorAbilities getMotor()
    {
        return motor;
    }

    public ICTAbilities getIct()
    {
        return ict;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public void setReligion(Religion religion)
    {
        this.religion = religion;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public void setPreferredLanguage(String preferredLanguage)
    {
        this.preferredLanguage = preferredLanguage;
    }
       

}
