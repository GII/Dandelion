//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.21 a las 06:25:55 PM CEST 
//


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
 
 package com.hi3project.dandelion.uib.xml.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AIU2FIOMapping complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AIU2FIOMapping">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbstractInteractionUnit-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InteractionSupportElement-ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AssociatedFIO" type="{http://www.hi3project.com/dandelion/uib}AssociatedFIO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AIU2FIOMapping", propOrder = {
    "abstractInteractionUnitID",
    "interactionSupportElementID",
    "associatedFIO"
})
public class AIU2FIOMapping {

    @XmlElement(name = "AbstractInteractionUnit-ID", required = true)
    protected String abstractInteractionUnitID;
    @XmlElement(name = "InteractionSupportElement-ID", required = true)
    protected String interactionSupportElementID;
    @XmlElement(name = "AssociatedFIO", required = true)
    protected List<AssociatedFIO> associatedFIO;

    /**
     * Obtiene el valor de la propiedad abstractInteractionUnitID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstractInteractionUnitID() {
        return abstractInteractionUnitID;
    }

    /**
     * Define el valor de la propiedad abstractInteractionUnitID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstractInteractionUnitID(String value) {
        this.abstractInteractionUnitID = value;
    }

    /**
     * Obtiene el valor de la propiedad interactionSupportElementID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInteractionSupportElementID() {
        return interactionSupportElementID;
    }

    /**
     * Define el valor de la propiedad interactionSupportElementID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInteractionSupportElementID(String value) {
        this.interactionSupportElementID = value;
    }

    /**
     * Gets the value of the associatedFIO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedFIO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedFIO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociatedFIO }
     * 
     * 
     */
    public List<AssociatedFIO> getAssociatedFIO() {
        if (associatedFIO == null) {
            associatedFIO = new ArrayList<AssociatedFIO>();
        }
        return this.associatedFIO;
    }

}
