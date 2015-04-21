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
 
 package com.hi3project.dandelion.domain.controller.test;

/** description
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      19-jul-2012 -- Initial version
 */
public class CustomerService {
	private String name;
	private String url;
 
	public void setName(String name) {
		this.name = name;
	}
 
	public void setUrl(String url) {
		this.url = url;
	}
 
	public void printName() {
		System.out.println("Customer name : " + this.name);
	}
 
	public void printURL() {
		System.out.println("Customer website : " + this.url);
	}
 
	public void printThrowException() {
		throw new IllegalArgumentException();
	}



    public void method1()
    {
        System.out.println("Probando 123");
    }
 
        
    }



