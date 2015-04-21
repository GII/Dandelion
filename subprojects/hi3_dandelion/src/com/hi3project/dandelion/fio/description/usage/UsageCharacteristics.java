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
 
 package com.hi3project.dandelion.fio.description.usage;

/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      31-jul-2014
 */
public class UsageCharacteristics 
{
    
    
    private final double recommendedUserAge;       
    
    private final double ictDifficulty;
    
    //private final double ictPrivacy;

    
    
    
    public UsageCharacteristics()
    {
        this.recommendedUserAge = 0;
        this.ictDifficulty = 0;
        //this.ictPrivacy = 0;
    }

    
    public UsageCharacteristics(
            double recommendedUserAge, 
            double ictDifficulty)
    {
        this.recommendedUserAge = recommendedUserAge;
        this.ictDifficulty = ictDifficulty;
        //this.ictPrivacy = ictPrivacy;
    }

    
    public double getRecommendedUserAge()
    {
        return recommendedUserAge;
    }

    public double getIctDifficulty()
    {
        return ictDifficulty;
    }

//    public double getIctPrivacy()
//    {
//        return ictPrivacy;
//    }

    @Override
    public String toString()
    {
        return "age=" + recommendedUserAge + ",\n"
                + "ict=" + ictDifficulty;
    }
    
    
    
    

}
