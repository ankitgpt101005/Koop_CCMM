//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:30:49 PM IST 
//


package com.koopid.ccmm.wsdl.ci_customer;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIContactType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CIContactType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Fax"/&gt;
 *     &lt;enumeration value="SMS"/&gt;
 *     &lt;enumeration value="VoiceMail"/&gt;
 *     &lt;enumeration value="WhiteMail"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *     &lt;enumeration value="ScheduledCallback"/&gt;
 *     &lt;enumeration value="Voice"/&gt;
 *     &lt;enumeration value="Email"/&gt;
 *     &lt;enumeration value="Web_Communications"/&gt;
 *     &lt;enumeration value="Outbound"/&gt;
 *     &lt;enumeration value="Video"/&gt;
 *     &lt;enumeration value="Unspecified"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CIContactType", namespace = "http://datatypes.ci.ccmm.applications.nortel.com")
@XmlEnum
public enum CIContactType {

    @XmlEnumValue("Fax")
    FAX("Fax"),
    SMS("SMS"),
    @XmlEnumValue("VoiceMail")
    VOICE_MAIL("VoiceMail"),
    @XmlEnumValue("WhiteMail")
    WHITE_MAIL("WhiteMail"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("ScheduledCallback")
    SCHEDULED_CALLBACK("ScheduledCallback"),
    @XmlEnumValue("Voice")
    VOICE("Voice"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("Web_Communications")
    WEB_COMMUNICATIONS("Web_Communications"),
    @XmlEnumValue("Outbound")
    OUTBOUND("Outbound"),
    @XmlEnumValue("Video")
    VIDEO("Video"),
    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified");
    private final String value;

    CIContactType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CIContactType fromValue(String v) {
        for (CIContactType c: CIContactType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
