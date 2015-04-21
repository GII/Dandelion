//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.21 a las 06:25:49 PM CEST 
//


package org.usixml.model.aui.xml.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnumTriggerType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTriggerType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="navigation"/>
 *     &lt;enumeration value="operation"/>
 *     &lt;enumeration value="operation_navigation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTriggerType")
@XmlEnum
public enum EnumTriggerType {

    @XmlEnumValue("navigation")
    NAVIGATION("navigation"),
    @XmlEnumValue("operation")
    OPERATION("operation"),
    @XmlEnumValue("operation_navigation")
    OPERATION_NAVIGATION("operation_navigation");
    private final String value;

    EnumTriggerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTriggerType fromValue(String v) {
        for (EnumTriggerType c: EnumTriggerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
