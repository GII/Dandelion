//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.21 a las 06:25:49 PM CEST 
//


package org.usixml.model.aui.xml.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AbstractInteractionUnit complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AbstractInteractionUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Composition" type="{http://www.usixml.eu/aui}Composition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataInputOutputSupport" type="{http://www.usixml.eu/aui}DataInputOutputSupport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataSelectionSupport" type="{http://www.usixml.eu/aui}DataSelectionSupport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TriggerSupport" type="{http://www.usixml.eu/aui}TriggerSupport" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="role" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="importanceLevel" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="securityLevel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isCompound" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="frequency" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="repetitionFactor" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="domainReference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isLocalisable" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractInteractionUnit", propOrder = {
    "composition",
    "dataInputOutputSupport",
    "dataSelectionSupport",
    "triggerSupport"
})
public class AbstractInteractionUnit {

    @XmlElement(name = "Composition")
    protected List<Composition> composition;
    @XmlElement(name = "DataInputOutputSupport")
    protected List<DataInputOutputSupport> dataInputOutputSupport;
    @XmlElement(name = "DataSelectionSupport")
    protected List<DataSelectionSupport> dataSelectionSupport;
    @XmlElement(name = "TriggerSupport")
    protected List<TriggerSupport> triggerSupport;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "role")
    protected String role;
    @XmlAttribute(name = "importanceLevel")
    protected BigInteger importanceLevel;
    @XmlAttribute(name = "securityLevel")
    protected String securityLevel;
    @XmlAttribute(name = "isCompound")
    protected Boolean isCompound;
    @XmlAttribute(name = "state")
    protected String state;
    @XmlAttribute(name = "frequency")
    protected Float frequency;
    @XmlAttribute(name = "repetitionFactor")
    protected BigInteger repetitionFactor;
    @XmlAttribute(name = "domainReference")
    protected String domainReference;
    @XmlAttribute(name = "isLocalisable")
    protected String isLocalisable;

    /**
     * Gets the value of the composition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the composition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComposition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Composition }
     * 
     * 
     */
    public List<Composition> getComposition() {
        if (composition == null) {
            composition = new ArrayList<Composition>();
        }
        return this.composition;
    }

    /**
     * Gets the value of the dataInputOutputSupport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataInputOutputSupport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataInputOutputSupport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataInputOutputSupport }
     * 
     * 
     */
    public List<DataInputOutputSupport> getDataInputOutputSupport() {
        if (dataInputOutputSupport == null) {
            dataInputOutputSupport = new ArrayList<DataInputOutputSupport>();
        }
        return this.dataInputOutputSupport;
    }

    /**
     * Gets the value of the dataSelectionSupport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSelectionSupport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSelectionSupport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSelectionSupport }
     * 
     * 
     */
    public List<DataSelectionSupport> getDataSelectionSupport() {
        if (dataSelectionSupport == null) {
            dataSelectionSupport = new ArrayList<DataSelectionSupport>();
        }
        return this.dataSelectionSupport;
    }

    /**
     * Gets the value of the triggerSupport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the triggerSupport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTriggerSupport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TriggerSupport }
     * 
     * 
     */
    public List<TriggerSupport> getTriggerSupport() {
        if (triggerSupport == null) {
            triggerSupport = new ArrayList<TriggerSupport>();
        }
        return this.triggerSupport;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad role.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Define el valor de la propiedad role.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obtiene el valor de la propiedad importanceLevel.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getImportanceLevel() {
        return importanceLevel;
    }

    /**
     * Define el valor de la propiedad importanceLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setImportanceLevel(BigInteger value) {
        this.importanceLevel = value;
    }

    /**
     * Obtiene el valor de la propiedad securityLevel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Define el valor de la propiedad securityLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityLevel(String value) {
        this.securityLevel = value;
    }

    /**
     * Obtiene el valor de la propiedad isCompound.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCompound() {
        return isCompound;
    }

    /**
     * Define el valor de la propiedad isCompound.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCompound(Boolean value) {
        this.isCompound = value;
    }

    /**
     * Obtiene el valor de la propiedad state.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Define el valor de la propiedad state.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Obtiene el valor de la propiedad frequency.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getFrequency() {
        return frequency;
    }

    /**
     * Define el valor de la propiedad frequency.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setFrequency(Float value) {
        this.frequency = value;
    }

    /**
     * Obtiene el valor de la propiedad repetitionFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRepetitionFactor() {
        return repetitionFactor;
    }

    /**
     * Define el valor de la propiedad repetitionFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRepetitionFactor(BigInteger value) {
        this.repetitionFactor = value;
    }

    /**
     * Obtiene el valor de la propiedad domainReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainReference() {
        return domainReference;
    }

    /**
     * Define el valor de la propiedad domainReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainReference(String value) {
        this.domainReference = value;
    }

    /**
     * Obtiene el valor de la propiedad isLocalisable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLocalisable() {
        return isLocalisable;
    }

    /**
     * Define el valor de la propiedad isLocalisable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLocalisable(String value) {
        this.isLocalisable = value;
    }

}
