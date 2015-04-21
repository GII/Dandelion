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

import com.hi3project.fuzzylogic.FuzzyVariable;


/**
 * Description of the motor and mobility abilities of a user
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog: 27-may-2014
 */
public class MotorAbilities
{

    private static final String NAME_SPEECH_ARTICULATION = "motor_speech_articulation";
    private static final String NAME_FINGER_PRECISION = "motor_finger_precision";
    private static final String NAME_HAND_PRECISION = "motor_abstract_signs";
    private static final String NAME_ARM_PRECISION = "motor_arm_precision";
    private static final String NAME_CONTACT_GRIP = "motor_contact_grip";
    private static final String NAME_PINCH_GRIP = "motor_pinch_grip";
    private static final String NAME_CLENCH_GRIP = "motor_clench_grip";
    private static final String NAME_HANDEYE_COORDINATION = "motor_handeye_coordination";

    private final FuzzyVariable speechArticulation;
    private final FuzzyVariable fingerPrecision;
    private final FuzzyVariable handPrecision;
    private final FuzzyVariable armPrecision;
    private final FuzzyVariable contactGrip;
    private final FuzzyVariable pinchGrip;
    private final FuzzyVariable clenchGrip;
    private final FuzzyVariable handEyeCoordination;

    public MotorAbilities(
            double speechArticulation,
            double fingerPrecision,
            double handPrecision,
            double armPrecision,
            double contactGrip,
            double pinchGrip,
            double clenchGrip,
            double handEyeCoordination)
    {

        this.speechArticulation = new FuzzyVariable(NAME_SPEECH_ARTICULATION);
        this.speechArticulation.setValue(speechArticulation);

        this.fingerPrecision = new FuzzyVariable(NAME_FINGER_PRECISION);
        this.fingerPrecision.setValue(fingerPrecision);

        this.handPrecision = new FuzzyVariable(NAME_HAND_PRECISION);
        this.handPrecision.setValue(handPrecision);

        this.armPrecision = new FuzzyVariable(NAME_ARM_PRECISION);
        this.armPrecision.setValue(armPrecision);

        this.contactGrip = new FuzzyVariable(NAME_CONTACT_GRIP);
        this.contactGrip.setValue(contactGrip);

        this.pinchGrip = new FuzzyVariable(NAME_PINCH_GRIP);
        this.pinchGrip.setValue(pinchGrip);

        this.clenchGrip = new FuzzyVariable(NAME_CLENCH_GRIP);
        this.clenchGrip.setValue(clenchGrip);

        this.handEyeCoordination = new FuzzyVariable(NAME_HANDEYE_COORDINATION);
        this.handEyeCoordination.setValue(handEyeCoordination);

    }

    public void setSpeechArticulation(double speechArticulation)
    {
        this.speechArticulation.setValue(speechArticulation);
    }

    public double getSpeechArticulation()
    {
        return this.speechArticulation.getValue();
    }

    public void setFingerPrecision(double fingerPrecision)
    {
        this.fingerPrecision.setValue(fingerPrecision);
    }

    public double getFingerPrecision()
    {
        return this.fingerPrecision.getValue();
    }

    public void setHandPrecision(double handPrecision)
    {
        this.handPrecision.setValue(handPrecision);
    }

    public double getHandPrecision()
    {
        return this.handPrecision.getValue();
    }

    public void setArmPrecision(double armPrecision)
    {
        this.armPrecision.setValue(armPrecision);
    }

    public double getArmPrecision()
    {
        return this.armPrecision.getValue();
    }

    public void setContactGrip(double contactGrip)
    {
        this.contactGrip.setValue(contactGrip);
    }

    public double getContactGrip()
    {
        return this.contactGrip.getValue();
    }

    public void setPinchGrip(double pinchGrip)
    {
        this.pinchGrip.setValue(pinchGrip);
    }

    public double getPinchGrip()
    {
        return this.pinchGrip.getValue();
    }

    public void setClenchGrip(double clenchGrip)
    {
        this.clenchGrip.setValue(clenchGrip);
    }

    public double getClenchGrip()
    {
        return this.clenchGrip.getValue();
    }
        
    public void setHandEyeCoordination(double handEyeCoordination)
    {
        this.handEyeCoordination.setValue(handEyeCoordination);
    }

    public double getHandEyeCoordination()
    {
        return this.handEyeCoordination.getValue();
    }

}
