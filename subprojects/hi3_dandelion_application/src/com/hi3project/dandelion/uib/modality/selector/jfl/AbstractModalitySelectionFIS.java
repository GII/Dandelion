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

import com.hi3project.dandelion.fio.description.modality.Modality;
import com.hi3project.dandelion.models.environment.EnvironmentProfile;
import com.hi3project.dandelion.models.scene.SceneProfile;
import com.hi3project.dandelion.models.user.UserProfile;
import com.hi3project.dandelion.util.jfl.AbstractJFuzzyLogicFIS;
import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.io.InputStream;
import net.sourceforge.jFuzzyLogic.FIS;
import org.usixml.model.aui.AbstractInteractionUnit;


/** A general implementation of a FIS using the JFuzzyLogic library.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-jul-2014
 */
public abstract class AbstractModalitySelectionFIS extends AbstractJFuzzyLogicFIS
{
    
    
    public void evaluate(AbstractInteractionUnit aiu, UserProfile user)
    {
        
        evaluate(null, user);
        
    }
    
    public void evaluate(AbstractInteractionUnit aiu, EnvironmentProfile environment)
    {
        
        evaluate(aiu, null, environment, null);
        
    }
    
    public void evaluate(AbstractInteractionUnit aiu, UserProfile user, EnvironmentProfile environment, SceneProfile scene)
    {
        
        setInputVariable(aiu);
        setInputVariable(user);
        setInputVariable(environment);
        setInputVariable(scene);
        
        this.getFIS().evaluate();        
        
    }
    
    
    protected void setInputVariable(UserProfile user) {}
    
    protected void setInputVariable(EnvironmentProfile environmnet) {}
    
    protected void setInputVariable(SceneProfile scene) {}
    
    protected void setInputVariable(AbstractInteractionUnit aiu) {}
    
    public abstract Modality getResult();
    

}
