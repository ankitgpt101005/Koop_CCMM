//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.01 at 04:50:33 PM IST 
//


package com.koopid.ccmm.wsdl.ci_contact;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIContactPriority.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CIContactPriority"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Priority_1_Highest"/&gt;
 *     &lt;enumeration value="Priority_2_High"/&gt;
 *     &lt;enumeration value="Priority_3_Medium_High"/&gt;
 *     &lt;enumeration value="Priority_4"/&gt;
 *     &lt;enumeration value="Priority_5"/&gt;
 *     &lt;enumeration value="Priority_6"/&gt;
 *     &lt;enumeration value="Priority_7"/&gt;
 *     &lt;enumeration value="Priority_8_Medium_Low"/&gt;
 *     &lt;enumeration value="Priority_9_Low"/&gt;
 *     &lt;enumeration value="Priority_10_Lowest"/&gt;
 *     &lt;enumeration value="Unspecified"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CIContactPriority5")
@XmlEnum
public enum CIContactPriority {

    @XmlEnumValue("Priority_1_Highest")
    PRIORITY_1_HIGHEST("Priority_1_Highest"),
    @XmlEnumValue("Priority_2_High")
    PRIORITY_2_HIGH("Priority_2_High"),
    @XmlEnumValue("Priority_3_Medium_High")
    PRIORITY_3_MEDIUM_HIGH("Priority_3_Medium_High"),
    @XmlEnumValue("Priority_4")
    PRIORITY_4("Priority_4"),
    @XmlEnumValue("Priority_5")
    PRIORITY_5("Priority_5"),
    @XmlEnumValue("Priority_6")
    PRIORITY_6("Priority_6"),
    @XmlEnumValue("Priority_7")
    PRIORITY_7("Priority_7"),
    @XmlEnumValue("Priority_8_Medium_Low")
    PRIORITY_8_MEDIUM_LOW("Priority_8_Medium_Low"),
    @XmlEnumValue("Priority_9_Low")
    PRIORITY_9_LOW("Priority_9_Low"),
    @XmlEnumValue("Priority_10_Lowest")
    PRIORITY_10_LOWEST("Priority_10_Lowest"),
    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified");
    private final String value;

    CIContactPriority(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CIContactPriority fromValue(String v) {
        for (CIContactPriority c: CIContactPriority.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}