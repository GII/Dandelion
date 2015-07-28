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
 
 package com.hi3project.dandelion.gip.codec.exception;

import com.mytechia.commons.framework.exception.InternalErrorException;
import com.hi3project.dandelion.gip.event.Event;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1

 Changelog:
      16-oct-2013 -- Initial version
 */
public class GIPEventCodeDecodeErrorException extends InternalErrorException
{
    
    private String data;
    private Event event;


    public GIPEventCodeDecodeErrorException(Exception exception)
    {
        super(exception);
    }
    
    
    public GIPEventCodeDecodeErrorException(Event event, String message)
    {
        super(message);
        this.event = event;
    }
    
    
    public GIPEventCodeDecodeErrorException(String data, String message)
    {
        super(message);
        this.data = data;
    }


    public String getData()
    {
        return data;
    }          
    
    
    public Event getEvent()
    {
        return event;
    }
    

}