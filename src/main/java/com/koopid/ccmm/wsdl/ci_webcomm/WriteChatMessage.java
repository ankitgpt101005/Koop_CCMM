//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:08:42 PM IST 
//


package com.koopid.ccmm.wsdl.ci_webcomm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contactID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="hiddenMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="chatMessageType" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIChatMessageType"/&gt;
 *         &lt;element name="sessionKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contactID",
    "message",
    "hiddenMessage",
    "chatMessageType",
    "sessionKey"
})
@XmlRootElement(name = "WriteChatMessage")
public class WriteChatMessage {

    protected long contactID;
    protected String message;
    protected String hiddenMessage;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CIChatMessageType chatMessageType;
    protected String sessionKey;

    /**
     * Gets the value of the contactID property.
     * 
     */
    public long getContactID() {
        return contactID;
    }

    /**
     * Sets the value of the contactID property.
     * 
     */
    public void setContactID(long value) {
        this.contactID = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the hiddenMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHiddenMessage() {
        return hiddenMessage;
    }

    /**
     * Sets the value of the hiddenMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHiddenMessage(String value) {
        this.hiddenMessage = value;
    }

    /**
     * Gets the value of the chatMessageType property.
     * 
     * @return
     *     possible object is
     *     {@link CIChatMessageType }
     *     
     */
    public CIChatMessageType getChatMessageType() {
        return chatMessageType;
    }

    /**
     * Sets the value of the chatMessageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIChatMessageType }
     *     
     */
    public void setChatMessageType(CIChatMessageType value) {
        this.chatMessageType = value;
    }

    /**
     * Gets the value of the sessionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * Sets the value of the sessionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionKey(String value) {
        this.sessionKey = value;
    }

}