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
 
 package com.hi3project.dandelion.util.jfl;

import com.hi3project.dandelion.util.jfl.exception.ErrorLoadingFISException;
import java.io.InputStream;
import net.sourceforge.jFuzzyLogic.FIS;


/** Represents a generic JFuzzyLogic Fuzzy Infence System (FIS)
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      29-jul-2014
 */
public class AbstractJFuzzyLogicFIS 
{
    
    
    private FIS fis;
    
    
    
    protected FIS getFIS()
    {
        return this.fis;
    }
    
    public void loadFCL(InputStream fcl) throws ErrorLoadingFISException
    {
        this.fis = FIS.load(fcl, false);
        if (this.fis == null) {
            throw new ErrorLoadingFISException(fcl.toString());
        }
    }
    

}
