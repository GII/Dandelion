/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hi3project.dandelion.uic.exception;

import com.hi3project.dandelion.fio.FIOMetadata;
import com.hi3project.dandelion.gip.exception.CommunicationErrorException;
import java.util.ArrayList;
import java.util.Collection;


/** 
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-mar-2014
 */
public class CommunicationErrorWithFIOsException extends CommunicationErrorException 
{
    
    private Collection<FIOMetadata> fioCollection;
    

    public CommunicationErrorWithFIOsException(Collection<FIOMetadata> fioCollection)
    {
        super("Communication error");
        this.fioCollection = new ArrayList<FIOMetadata>(fioCollection);
    }

    public Collection<FIOMetadata> getFioCollection()
    {
        return fioCollection;
    }
    
    
}
