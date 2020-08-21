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
 * <p>Java class for CIContactSource.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CIContactSource"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NotAvailable"/&gt;
 *     &lt;enumeration value="EMail"/&gt;
 *     &lt;enumeration value="AgentCreated"/&gt;
 *     &lt;enumeration value="Web"/&gt;
 *     &lt;enumeration value="Unspecified"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CIContactSource5")
@XmlEnum
public enum CIContactSource {

    @XmlEnumValue("NotAvailable")
    NOT_AVAILABLE("NotAvailable"),
    @XmlEnumValue("EMail")
    E_MAIL("EMail"),
    @XmlEnumValue("AgentCreated")
    AGENT_CREATED("AgentCreated"),
    @XmlEnumValue("Web")
    WEB("Web"),
    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified");
    private final String value;

    CIContactSource(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CIContactSource fromValue(String v) {
        for (CIContactSource c: CIContactSource.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}