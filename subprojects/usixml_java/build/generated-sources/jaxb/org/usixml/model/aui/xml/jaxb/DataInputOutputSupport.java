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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DataInputOutputSupport complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DataInputOutputSupport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.usixml.eu/aui}PresentationSupport">
 *       &lt;sequence>
 *         &lt;element name="dataType" type="{http://www.usixml.eu/aui}EnumDataType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="minCardinality" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="maxCardinality" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dataLength" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="dataFormat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="range" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ioFacet" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isDynamic" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="isRelevant" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="patternMatching" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="inputSupport" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="outputSupport" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataInputOutputSupport", propOrder = {
    "dataType"
})
public class DataInputOutputSupport
    extends PresentationSupport
{

    @XmlElement(required = true)
    protected EnumDataType dataType;
    @XmlAttribute(name = "minCardinality")
    protected BigInteger minCardinality;
    @XmlAttribute(name = "maxCardinality")
    protected BigInteger maxCardinality;
    @XmlAttribute(name = "defaultValue")
    protected String defaultValue;
    @XmlAttribute(name = "dataLength")
    protected BigInteger dataLength;
    @XmlAttribute(name = "dataFormat")
    protected String dataFormat;
    @XmlAttribute(name = "range")
    protected String range;
    @XmlAttribute(name = "ioFacet")
    protected String ioFacet;
    @XmlAttribute(name = "isDynamic")
    protected Boolean isDynamic;
    @XmlAttribute(name = "isRelevant")
    protected Boolean isRelevant;
    @XmlAttribute(name = "patternMatching")
    protected String patternMatching;
    @XmlAttribute(name = "inputSupport")
    protected Boolean inputSupport;
    @XmlAttribute(name = "outputSupport")
    protected Boolean outputSupport;

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
     * Obtiene el valor de la propiedad minCardinality.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinCardinality() {
        return minCardinality;
    }

    /**
     * Define el valor de la propiedad minCardinality.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinCardinality(BigInteger value) {
        this.minCardinality = value;
    }

    /**
     * Obtiene el valor de la propiedad maxCardinality.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxCardinality() {
        return maxCardinality;
    }

    /**
     * Define el valor de la propiedad maxCardinality.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxCardinality(BigInteger value) {
        this.maxCardinality = value;
    }

    /**
     * Obtiene el valor de la propiedad defaultValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Define el valor de la propiedad defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Obtiene el valor de la propiedad dataLength.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataLength() {
        return dataLength;
    }

    /**
     * Define el valor de la propiedad dataLength.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataLength(BigInteger value) {
        this.dataLength = value;
    }

    /**
     * Obtiene el valor de la propiedad dataFormat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFormat() {
        return dataFormat;
    }

    /**
     * Define el valor de la propiedad dataFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFormat(String value) {
        this.dataFormat = value;
    }

    /**
     * Obtiene el valor de la propiedad range.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRange() {
        return range;
    }

    /**
     * Define el valor de la propiedad range.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRange(String value) {
        this.range = value;
    }

    /**
     * Obtiene el valor de la propiedad ioFacet.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIoFacet() {
        return ioFacet;
    }

    /**
     * Define el valor de la propiedad ioFacet.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIoFacet(String value) {
        this.ioFacet = value;
    }

    /**
     * Obtiene el valor de la propiedad isDynamic.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDynamic() {
        return isDynamic;
    }

    /**
     * Define el valor de la propiedad isDynamic.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDynamic(Boolean value) {
        this.isDynamic = value;
    }

    /**
     * Obtiene el valor de la propiedad isRelevant.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRelevant() {
        return isRelevant;
    }

    /**
     * Define el valor de la propiedad isRelevant.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRelevant(Boolean value) {
        this.isRelevant = value;
    }

    /**
     * Obtiene el valor de la propiedad patternMatching.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatternMatching() {
        return patternMatching;
    }

    /**
     * Define el valor de la propiedad patternMatching.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatternMatching(String value) {
        this.patternMatching = value;
    }

    /**
     * Obtiene el valor de la propiedad inputSupport.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInputSupport() {
        return inputSupport;
    }

    /**
     * Define el valor de la propiedad inputSupport.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInputSupport(Boolean value) {
        this.inputSupport = value;
    }

    /**
     * Obtiene el valor de la propiedad outputSupport.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOutputSupport() {
        return outputSupport;
    }

    /**
     * Define el valor de la propiedad outputSupport.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOutputSupport(Boolean value) {
        this.outputSupport = value;
    }

}
