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
 * <p>Java class for CICallbackStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CICallbackStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unknown"/&gt;
 *     &lt;enumeration value="No_Callback"/&gt;
 *     &lt;enumeration value="Call_Completed"/&gt;
 *     &lt;enumeration value="Call_Not_Answered"/&gt;
 *     &lt;enumeration value="Wrong_Telephone_Number"/&gt;
 *     &lt;enumeration value="No_Number_Selected"/&gt;
 *     &lt;enumeration value="Busy"/&gt;
 *     &lt;enumeration value="Other"/&gt;
 *     &lt;enumeration value="EMail_In_Queue"/&gt;
 *     &lt;enumeration value="EMail_Sent"/&gt;
 *     &lt;enumeration value="EMail_Undeliverable"/&gt;
 *     &lt;enumeration value="EMail_Not_Sent"/&gt;
 *     &lt;enumeration value="EMail_Address_Not_Provided"/&gt;
 *     &lt;enumeration value="EMail_Received"/&gt;
 *     &lt;enumeration value="EMail_Send_In_Progress"/&gt;
 *     &lt;enumeration value="Fax_In_Queue"/&gt;
 *     &lt;enumeration value="Fax_Sent"/&gt;
 *     &lt;enumeration value="Wrong_Fax_Number"/&gt;
 *     &lt;enumeration value="Fax_Not_Sent"/&gt;
 *     &lt;enumeration value="Mail_Posted"/&gt;
 *     &lt;enumeration value="Response_Cancelled"/&gt;
 *     &lt;enumeration value="Web_reply"/&gt;
 *     &lt;enumeration value="Contact_Closed"/&gt;
 *     &lt;enumeration value="Contact_ReOpened"/&gt;
 *     &lt;enumeration value="Contact_Transferred"/&gt;
 *     &lt;enumeration value="Contact_Barred"/&gt;
 *     &lt;enumeration value="Agent_Note"/&gt;
 *     &lt;enumeration value="Chat_Response"/&gt;
 *     &lt;enumeration value="Done"/&gt;
 *     &lt;enumeration value="Created"/&gt;
 *     &lt;enumeration value="Received"/&gt;
 *     &lt;enumeration value="Unspecified"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CICallbackStatus", namespace = "http://datatypes.ci.ccmm.applications.nortel.com")
@XmlEnum
public enum CICallbackStatus {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("No_Callback")
    NO_CALLBACK("No_Callback"),
    @XmlEnumValue("Call_Completed")
    CALL_COMPLETED("Call_Completed"),
    @XmlEnumValue("Call_Not_Answered")
    CALL_NOT_ANSWERED("Call_Not_Answered"),
    @XmlEnumValue("Wrong_Telephone_Number")
    WRONG_TELEPHONE_NUMBER("Wrong_Telephone_Number"),
    @XmlEnumValue("No_Number_Selected")
    NO_NUMBER_SELECTED("No_Number_Selected"),
    @XmlEnumValue("Busy")
    BUSY("Busy"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("EMail_In_Queue")
    E_MAIL_IN_QUEUE("EMail_In_Queue"),
    @XmlEnumValue("EMail_Sent")
    E_MAIL_SENT("EMail_Sent"),
    @XmlEnumValue("EMail_Undeliverable")
    E_MAIL_UNDELIVERABLE("EMail_Undeliverable"),
    @XmlEnumValue("EMail_Not_Sent")
    E_MAIL_NOT_SENT("EMail_Not_Sent"),
    @XmlEnumValue("EMail_Address_Not_Provided")
    E_MAIL_ADDRESS_NOT_PROVIDED("EMail_Address_Not_Provided"),
    @XmlEnumValue("EMail_Received")
    E_MAIL_RECEIVED("EMail_Received"),
    @XmlEnumValue("EMail_Send_In_Progress")
    E_MAIL_SEND_IN_PROGRESS("EMail_Send_In_Progress"),
    @XmlEnumValue("Fax_In_Queue")
    FAX_IN_QUEUE("Fax_In_Queue"),
    @XmlEnumValue("Fax_Sent")
    FAX_SENT("Fax_Sent"),
    @XmlEnumValue("Wrong_Fax_Number")
    WRONG_FAX_NUMBER("Wrong_Fax_Number"),
    @XmlEnumValue("Fax_Not_Sent")
    FAX_NOT_SENT("Fax_Not_Sent"),
    @XmlEnumValue("Mail_Posted")
    MAIL_POSTED("Mail_Posted"),
    @XmlEnumValue("Response_Cancelled")
    RESPONSE_CANCELLED("Response_Cancelled"),
    @XmlEnumValue("Web_reply")
    WEB_REPLY("Web_reply"),
    @XmlEnumValue("Contact_Closed")
    CONTACT_CLOSED("Contact_Closed"),
    @XmlEnumValue("Contact_ReOpened")
    CONTACT_RE_OPENED("Contact_ReOpened"),
    @XmlEnumValue("Contact_Transferred")
    CONTACT_TRANSFERRED("Contact_Transferred"),
    @XmlEnumValue("Contact_Barred")
    CONTACT_BARRED("Contact_Barred"),
    @XmlEnumValue("Agent_Note")
    AGENT_NOTE("Agent_Note"),
    @XmlEnumValue("Chat_Response")
    CHAT_RESPONSE("Chat_Response"),
    @XmlEnumValue("Done")
    DONE("Done"),
    @XmlEnumValue("Created")
    CREATED("Created"),
    @XmlEnumValue("Received")
    RECEIVED("Received"),
    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified");
    private final String value;

    CICallbackStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CICallbackStatus fromValue(String v) {
        for (CICallbackStatus c: CICallbackStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
