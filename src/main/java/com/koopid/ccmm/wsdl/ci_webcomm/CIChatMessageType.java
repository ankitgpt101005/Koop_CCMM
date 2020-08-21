//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:08:42 PM IST 
//


package com.koopid.ccmm.wsdl.ci_webcomm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIChatMessageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CIChatMessageType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Chat_Message_from_Customer"/&gt;
 *     &lt;enumeration value="Chat_Message_from_Agent"/&gt;
 *     &lt;enumeration value="Page_Pushed_by_Customer"/&gt;
 *     &lt;enumeration value="Page_Pushed_by_Agent"/&gt;
 *     &lt;enumeration value="Form_Shared_by_Customer"/&gt;
 *     &lt;enumeration value="Form_Shared_by_Agent"/&gt;
 *     &lt;enumeration value="CallMe_Request_from_Customer"/&gt;
 *     &lt;enumeration value="Page_Previewed_by_Customer"/&gt;
 *     &lt;enumeration value="Page_Previewed_by_Agent"/&gt;
 *     &lt;enumeration value="Session_Disconnected_by_Customer"/&gt;
 *     &lt;enumeration value="Session_Disconnected_by_Agent"/&gt;
 *     &lt;enumeration value="Private_Chat_Message_between_Agents"/&gt;
 *     &lt;enumeration value="Comfort_Message"/&gt;
 *     &lt;enumeration value="Chat_Message_from_Custom_Agent"/&gt;
 *     &lt;enumeration value="Page_Pushed_by_Custom_Agent"/&gt;
 *     &lt;enumeration value="Form_Shared_by_Custom_Agent"/&gt;
 *     &lt;enumeration value="Page_Previewed_by_Custom_Agent"/&gt;
 *     &lt;enumeration value="Session_Disconnected_by_Custom_Agent"/&gt;
 *     &lt;enumeration value="Private_Chat_Message_between_Custom_Agents"/&gt;
 *     &lt;enumeration value="Private_Chat_Message_between_Agents_and_Custom_Agents"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CIChatMessageType", namespace = "http://datatypes.ci.ccmm.applications.nortel.com")
@XmlEnum
public enum CIChatMessageType {

    @XmlEnumValue("Chat_Message_from_Customer")
    CHAT_MESSAGE_FROM_CUSTOMER("Chat_Message_from_Customer"),
    @XmlEnumValue("Chat_Message_from_Agent")
    CHAT_MESSAGE_FROM_AGENT("Chat_Message_from_Agent"),
    @XmlEnumValue("Page_Pushed_by_Customer")
    PAGE_PUSHED_BY_CUSTOMER("Page_Pushed_by_Customer"),
    @XmlEnumValue("Page_Pushed_by_Agent")
    PAGE_PUSHED_BY_AGENT("Page_Pushed_by_Agent"),
    @XmlEnumValue("Form_Shared_by_Customer")
    FORM_SHARED_BY_CUSTOMER("Form_Shared_by_Customer"),
    @XmlEnumValue("Form_Shared_by_Agent")
    FORM_SHARED_BY_AGENT("Form_Shared_by_Agent"),
    @XmlEnumValue("CallMe_Request_from_Customer")
    CALL_ME_REQUEST_FROM_CUSTOMER("CallMe_Request_from_Customer"),
    @XmlEnumValue("Page_Previewed_by_Customer")
    PAGE_PREVIEWED_BY_CUSTOMER("Page_Previewed_by_Customer"),
    @XmlEnumValue("Page_Previewed_by_Agent")
    PAGE_PREVIEWED_BY_AGENT("Page_Previewed_by_Agent"),
    @XmlEnumValue("Session_Disconnected_by_Customer")
    SESSION_DISCONNECTED_BY_CUSTOMER("Session_Disconnected_by_Customer"),
    @XmlEnumValue("Session_Disconnected_by_Agent")
    SESSION_DISCONNECTED_BY_AGENT("Session_Disconnected_by_Agent"),
    @XmlEnumValue("Private_Chat_Message_between_Agents")
    PRIVATE_CHAT_MESSAGE_BETWEEN_AGENTS("Private_Chat_Message_between_Agents"),
    @XmlEnumValue("Comfort_Message")
    COMFORT_MESSAGE("Comfort_Message"),
    @XmlEnumValue("Chat_Message_from_Custom_Agent")
    CHAT_MESSAGE_FROM_CUSTOM_AGENT("Chat_Message_from_Custom_Agent"),
    @XmlEnumValue("Page_Pushed_by_Custom_Agent")
    PAGE_PUSHED_BY_CUSTOM_AGENT("Page_Pushed_by_Custom_Agent"),
    @XmlEnumValue("Form_Shared_by_Custom_Agent")
    FORM_SHARED_BY_CUSTOM_AGENT("Form_Shared_by_Custom_Agent"),
    @XmlEnumValue("Page_Previewed_by_Custom_Agent")
    PAGE_PREVIEWED_BY_CUSTOM_AGENT("Page_Previewed_by_Custom_Agent"),
    @XmlEnumValue("Session_Disconnected_by_Custom_Agent")
    SESSION_DISCONNECTED_BY_CUSTOM_AGENT("Session_Disconnected_by_Custom_Agent"),
    @XmlEnumValue("Private_Chat_Message_between_Custom_Agents")
    PRIVATE_CHAT_MESSAGE_BETWEEN_CUSTOM_AGENTS("Private_Chat_Message_between_Custom_Agents"),
    @XmlEnumValue("Private_Chat_Message_between_Agents_and_Custom_Agents")
    PRIVATE_CHAT_MESSAGE_BETWEEN_AGENTS_AND_CUSTOM_AGENTS("Private_Chat_Message_between_Agents_and_Custom_Agents");
    private final String value;

    CIChatMessageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CIChatMessageType fromValue(String v) {
        for (CIChatMessageType c: CIChatMessageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
