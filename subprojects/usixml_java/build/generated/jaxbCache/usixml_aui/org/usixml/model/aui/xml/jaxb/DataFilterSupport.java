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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DataFilterSupport complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DataFilterSupport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.usixml.eu/aui}DataSelectionSupport">
 *       &lt;attribute name="filterType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="filterCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="filterProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataFilterSupport")
public class DataFilterSupport
    extends DataSelectionSupport
{

    @XmlAttribute(name = "filterType")
    protected String filterType;
    @XmlAttribute(name = "filterCondition")
    protected String filterCondition;
    @XmlAttribute(name = "filterProperty")
    protected String filterProperty;

    /**
     * Obtiene el valor de la propiedad filterType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterType() {
        return filterType;
    }

    /**
     * Define el valor de la propiedad filterType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterType(String value) {
        this.filterType = value;
    }

    /**
     * Obtiene el valor de la propiedad filterCondition.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterCondition() {
        return filterCondition;
    }

    /**
     * Define el valor de la propiedad filterCondition.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterCondition(String value) {
        this.filterCondition = value;
    }

    /**
     * Obtiene el valor de la propiedad filterProperty.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterProperty() {
        return filterProperty;
    }

    /**
     * Define el valor de la propiedad filterProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterProperty(String value) {
        this.filterProperty = value;
    }

}
