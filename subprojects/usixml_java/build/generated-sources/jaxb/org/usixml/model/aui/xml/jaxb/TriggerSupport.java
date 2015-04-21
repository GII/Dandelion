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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TriggerSupport complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TriggerSupport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.usixml.eu/aui}EventSupport">
 *       &lt;sequence>
 *         &lt;element name="triggerType" type="{http://www.usixml.eu/aui}EnumTriggerType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="triggerExpression" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="requiresConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TriggerSupport", propOrder = {
    "triggerType"
})
public class TriggerSupport
    extends EventSupport
{

    @XmlElement(required = true)
    protected EnumTriggerType triggerType;
    @XmlAttribute(name = "triggerExpression")
    protected String triggerExpression;
    @XmlAttribute(name = "requiresConfirmation")
    protected Boolean requiresConfirmation;

    /**
     * Obtiene el valor de la propiedad triggerType.
     * 
     * @return
     *     possible object is
     *     {@link EnumTriggerType }
     *     
     */
    public EnumTriggerType getTriggerType() {
        return triggerType;
    }

    /**
     * Define el valor de la propiedad triggerType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTriggerType }
     *     
     */
    public void setTriggerType(EnumTriggerType value) {
        this.triggerType = value;
    }

    /**
     * Obtiene el valor de la propiedad triggerExpression.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTriggerExpression() {
        return triggerExpression;
    }

    /**
     * Define el valor de la propiedad triggerExpression.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTriggerExpression(String value) {
        this.triggerExpression = value;
    }

    /**
     * Obtiene el valor de la propiedad requiresConfirmation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiresConfirmation() {
        return requiresConfirmation;
    }

    /**
     * Define el valor de la propiedad requiresConfirmation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiresConfirmation(Boolean value) {
        this.requiresConfirmation = value;
    }

}
