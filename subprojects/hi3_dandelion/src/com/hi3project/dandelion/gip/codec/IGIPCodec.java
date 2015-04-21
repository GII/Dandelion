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
 
 package com.hi3project.dandelion.gip.codec;

import com.hi3project.dandelion.gip.codec.exception.GIPEventCodeDecodeErrorException;
import com.hi3project.dandelion.gip.event.Event;


/** Implementations of this interface are in charge of providing concrete
 * code/decoding techniques for transfering GIP Events over a network
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1

 Changelog:
      16-oct-2013 -- Initial version
 */
public interface IGIPCodec 
{
    
    
    /** Codes a GIP event into a string for network interchange.
     * 
     * @param event the event to code
     * @return a string representation of the event
     * @throws GIPEventCodeDecodeErrorException if there is a problem while coding the event
     */
    public String codeGIPEvent(Event event) throws GIPEventCodeDecodeErrorException;
    
    
    
    /** Decodes a GIP event from a its represention as a string
     * 
     * @param data the string representation of the event
     * @return the event decoded from the string representation
     * @throws GIPEventCodeDecodeErrorException if there was a problem while decoding the event
     */
    public Event decodeGIPEvent(String data) throws GIPEventCodeDecodeErrorException;
    
    

}
