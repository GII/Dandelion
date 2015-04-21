//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.21 a las 06:25:49 PM CEST 
//


package org.usixml.model.aui.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbstractUIModel" type="{http://www.usixml.eu/aui}AbstractUIModel"/>
 *       &lt;/sequence>
 *       &lt;attribute name="useCase" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "abstractUIModel"
})
@XmlRootElement(name = "UserInterfaceModel")
public class UserInterfaceModel {

    @XmlElement(name = "AbstractUIModel", required = true)
    protected AbstractUIModel abstractUIModel;
    @XmlAttribute(name = "useCase")
    protected String useCase;

    /**
     * Obtiene el valor de la propiedad abstractUIModel.
     * 
     * @return
     *     possible object is
     *     {@link AbstractUIModel }
     *     
     */
    public AbstractUIModel getAbstractUIModel() {
        return abstractUIModel;
    }

    /**
     * Define el valor de la propiedad abstractUIModel.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractUIModel }
     *     
     */
    public void setAbstractUIModel(AbstractUIModel value) {
        this.abstractUIModel = value;
    }

    /**
     * Obtiene el valor de la propiedad useCase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Define el valor de la propiedad useCase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseCase(String value) {
        this.useCase = value;
    }

}
