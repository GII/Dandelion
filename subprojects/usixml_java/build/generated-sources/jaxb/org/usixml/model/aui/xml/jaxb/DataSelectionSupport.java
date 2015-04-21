//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.21 a las 06:25:49 PM CEST 
//


package org.usixml.model.aui.xml.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DataSelectionSupport complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DataSelectionSupport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.usixml.eu/aui}PresentationSupport">
 *       &lt;sequence>
 *         &lt;element name="dataType" type="{http://www.usixml.eu/aui}EnumDataType"/>
 *         &lt;element name="selectionType" type="{http://www.usixml.eu/aui}EnumSelectionType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="end" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="step" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="isExpandable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isContinuous" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isRating" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isSecret" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSelectionSupport", propOrder = {
    "dataType",
    "selectionType"
})
@XmlSeeAlso({
    DataFilterSupport.class
})
public class DataSelectionSupport
    extends PresentationSupport
{

    @XmlElement(required = true)
    protected EnumDataType dataType;
    @XmlElement(required = true)
    protected EnumSelectionType selectionType;
    @XmlAttribute(name = "start")
    protected BigInteger start;
    @XmlAttribute(name = "end")
    protected BigInteger end;
    @XmlAttribute(name = "step")
    protected BigInteger step;
    @XmlAttribute(name = "isExpandable")
    protected Boolean isExpandable;
    @XmlAttribute(name = "isContinuous")
    protected Boolean isContinuous;
    @XmlAttribute(name = "isRating")
    protected Boolean isRating;
    @XmlAttribute(name = "isSecret")
    protected Boolean isSecret;

    /**
     * Obtiene el valor de la propiedad dataType.
     * 
     * @return
     *     possible object is
     *     {@link EnumDataType }
     *     
     */
    public EnumDataType getDataType() {
        return dataType;
    }

    /**
     * Define el valor de la propiedad dataType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumDataType }
     *     
     */
    public void setDataType(EnumDataType value) {
        this.dataType = value;
    }

    /**
     * Obtiene el valor de la propiedad selectionType.
     * 
     * @return
     *     possible object is
     *     {@link EnumSelectionType }
     *     
     */
    public EnumSelectionType getSelectionType() {
        return selectionType;
    }

    /**
     * Define el valor de la propiedad selectionType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumSelectionType }
     *     
     */
    public void setSelectionType(EnumSelectionType value) {
        this.selectionType = value;
    }

    /**
     * Obtiene el valor de la propiedad start.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStart() {
        return start;
    }

    /**
     * Define el valor de la propiedad start.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStart(BigInteger value) {
        this.start = value;
    }

    /**
     * Obtiene el valor de la propiedad end.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEnd() {
        return end;
    }

    /**
     * Define el valor de la propiedad end.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEnd(BigInteger value) {
        this.end = value;
    }

    /**
     * Obtiene el valor de la propiedad step.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStep() {
        return step;
    }

    /**
     * Define el valor de la propiedad step.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStep(BigInteger value) {
        this.step = value;
    }

    /**
     * Obtiene el valor de la propiedad isExpandable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsExpandable() {
        return isExpandable;
    }

    /**
     * Define el valor de la propiedad isExpandable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsExpandable(Boolean value) {
        this.isExpandable = value;
    }

    /**
     * Obtiene el valor de la propiedad isContinuous.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsContinuous() {
        return isContinuous;
    }

    /**
     * Define el valor de la propiedad isContinuous.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsContinuous(Boolean value) {
        this.isContinuous = value;
    }

    /**
     * Obtiene el valor de la propiedad isRating.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRating() {
        return isRating;
    }

    /**
     * Define el valor de la propiedad isRating.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRating(Boolean value) {
        this.isRating = value;
    }

    /**
     * Obtiene el valor de la propiedad isSecret.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSecret() {
        return isSecret;
    }

    /**
     * Define el valor de la propiedad isSecret.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSecret(Boolean value) {
        this.isSecret = value;
    }

}
