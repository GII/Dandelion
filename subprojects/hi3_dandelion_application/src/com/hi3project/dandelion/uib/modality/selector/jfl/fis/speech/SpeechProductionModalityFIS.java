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
 
 package com.hi3project.dandelion.uib.modality.selector.jfl.fis.speech;

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.fio.description.modality.ModalityType;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.uib.modality.selector.jfl.AbstractModalitySelectionFIS;
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
public class SpeechProductionModalityFIS extends AbstractModalitySelectionFIS
{

    @Override
    protected void setInputVariable(UserProfile user)
    {
        double hearing = 0, langReception=0, langProduction=0;
        if (user != null) {
            hearing = user.getHearing().getHearing();
            langReception = user.getCognitive().getLanguageReception();
            langProduction = user.getCognitive().getLanguageProduction();
        }
        
        getFIS().setVariable("user_hearing", hearing);
        getFIS().setVariable("user_cognitive_lang_reception", langReception);
        getFIS().setVariable("user_cognitive_lang_production", langProduction);        
    }

    @Override
    protected void setInputVariable(EnvironmentProfile environmnet)
    {
        double noise = 0;
        if (environmnet != null) {
            noise = environmnet.getNoise().getAmbient();
        }
        
        getFIS().setVariable("env_ambient_noise", noise);
    }

    @Override
    protected void setInputVariable(AbstractInteractionUnit aiu)
    {
        
    }

    @Override
    public Modality getResult()
    {
        Variable gestureGranularity = getFIS().getVariable("modality_speech_production");
        Modality gestureModality = new Modality(
                ModalityType.speech_production, gestureGranularity.getValue());
        
        return gestureModality;
    }

}
