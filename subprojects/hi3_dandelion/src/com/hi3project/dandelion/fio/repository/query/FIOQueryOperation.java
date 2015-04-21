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
 
 package com.hi3project.dandelion.fio.repository.query;

/** A FIO query that respresents an binary operation between two
 * FIO queries.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      30-jul-2014
 */
public class FIOQueryOperation extends FIOQuery
{
    
    private final FIOQuery operand1, operand2;

    private final FIOQueryOperationType type;

    
    public FIOQueryOperation(
            FIOQuery operand1, FIOQuery operand2,
            FIOQueryOperationType type)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.type = type;
    }

    
    public FIOQuery getOperand1()
    {
        return operand1;
    }
    
    
    public FIOQuery getOperand2()
    {
        return operand2;
    }
    
    
    public FIOQueryOperationType getType()
    {
        return this.type;
    }
        

}
