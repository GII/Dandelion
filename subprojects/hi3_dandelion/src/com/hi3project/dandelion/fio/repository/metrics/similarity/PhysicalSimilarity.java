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
 
 package com.hi3project.dandelion.fio.repository.metrics.similarity;


/** Distance between a physical specification of a FIO query and the
 * physical description of a FIO
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-sep-2014
 */
public class PhysicalSimilarity extends AbstractSimilarity
{

    private static final double ATTRIBUTE_SIM_WEIGHT = 0.65;
    private static final double SHAPE_SIM_WEIGHT = 0.35;
    
    private final double attributeSimilarity;
    private final double shapeSimilarity;

    
    
    public PhysicalSimilarity(double similarity)
    {
        super(similarity);
        this.attributeSimilarity = 0;
        this.shapeSimilarity = 0;
    }
    
    
    public PhysicalSimilarity(double attributeSimilarity, double shapeSimilarity)
    {
        super(attributeSimilarity*ATTRIBUTE_SIM_WEIGHT + shapeSimilarity*SHAPE_SIM_WEIGHT);
        this.attributeSimilarity = attributeSimilarity;
        this.shapeSimilarity = shapeSimilarity;
    }

    
    @Override
    public String toString()
    {
        return "PhysicalSim{" + "attrSim=" + attributeSimilarity + ", shpSim=" + shapeSimilarity + '}';
    }
    

}
