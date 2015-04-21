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
 
 package com.hi3project.dandelion.uic.aop;

//package es.udc.gii.hi3.dandelion.uic.aop;
//
//
//import java.lang.reflect.Method;
//import org.springframework.aop.AfterReturningAdvice;
//
//
///** Interceps a call to a public method
// *
// * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
// * @version 1
// *
// * Changelog:
// *      19-jul-2012 -- Initial version
// */
//public class PublicSetMethodAdvice implements AfterReturningAdvice
//{
//
//    private DandelionApplicationController domainController;
//    
//    private Object domainObject;
//
//
//    public PublicSetMethodAdvice(
//            DandelionApplicationController domainController, Object domainObject)
//    {
//        this.domainController = domainController;
//        this.domainObject = domainObject;
//    }
//    
//    
//
//    @Override
//    public void afterReturning(
//            Object returnValue, Method method, Object[] args, Object target) throws Throwable
//    {
//        
//        this.domainController.dataObjectModified(getPropertyName(method.getName()), domainObject);
//        
//    }
//    
//    
//    private String getPropertyName(String getName)
//    {
//        return getName.substring(3);
//    }
//
//    
//}
