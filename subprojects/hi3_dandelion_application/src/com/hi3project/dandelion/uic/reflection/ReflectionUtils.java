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
 
 package com.hi3project.dandelion.uic.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/** Utility methods to execute get/set methods by reflection
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      08-may-2013 -- Initial version
 */
public final class ReflectionUtils 
{

    
    /** Executes a GET method for the specified property in the specified object
     * 
     * @param property the property to get
     * @param domainObject the object which property value is going to be gotten
     * @return the property value
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    public static Object executeGet(String property, Object domainObject)
        throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
    {
        Method method = domainObject.getClass().getMethod(getGetName(property), String.class);
        return method.invoke(domainObject);
    }
    

    /** Executes a SET method for the specified property in the specified object
     * 
     * @param property the property to set
     * @param value the new value for the property
     * @param domainObject the object to execute the set method
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    public static void executeSet(String property, String value, Object domainObject) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
    {
        Method method = domainObject.getClass().getMethod(getSetName(property), String.class);
        method.invoke(domainObject, value);
    }
    
    
    /** Executes a GET of a property in newDomainObject and then a set of the
     * same property in domainObject
     * 
     * @param m
     * @param domainObject
     * @param newDomainObject
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException 
     */
    public static void executeGetSet(Method m, Object domainObject, Object newDomainObject) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
    {
        
        Object getResult = m.invoke(newDomainObject);
        
        String setName = getSetName(getValueName(m.getName()));
        
        Method setMethod = domainObject.getClass().getMethod(setName);
        
        setMethod.invoke(domainObject, getResult);
        
    }
    
    
    private static String getValueName(String getName)
    {
        return getName.substring(3);
    }
    
    
    private static String getSetName(String valueName)
    {
        StringBuilder sb = new StringBuilder("set");
        sb.append(valueName);
        return sb.toString();
    }
    
    
    private static String getGetName(String valueName)
    {
        StringBuilder sb = new StringBuilder("get");
        sb.append(valueName);
        return sb.toString();
    }
    
    
}
