//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:30:49 PM IST 
//


package com.koopid.ccmm.wsdl.ci_customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="custID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="customFieldID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "custID",
    "customFieldID",
    "sessionKey"
})
@XmlRootElement(name = "RemoveCustomField")
public class RemoveCustomField {

    protected long custID;
    protected long customFieldID;
    protected String sessionKey;

    /**
     * Gets the value of the custID property.
     * 
     */
    public long getCustID() {
        return custID;
    }

    /**
     * Sets the value of the custID property.
     * 
     */
    public void setCustID(long value) {
        this.custID = value;
    }

    /**
     * Gets the value of the customFieldID property.
     * 
     */
    public long getCustomFieldID() {
        return customFieldID;
    }

    /**
     * Sets the value of the customFieldID property.
     * 
     */
    public void setCustomFieldID(long value) {
        this.customFieldID = value;
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
