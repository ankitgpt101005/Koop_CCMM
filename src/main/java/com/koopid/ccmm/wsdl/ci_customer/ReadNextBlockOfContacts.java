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
 *         &lt;element name="customerID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="numOfContacts" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="startContactID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "customerID",
    "numOfContacts",
    "startContactID",
    "sessionKey"
})
@XmlRootElement(name = "ReadNextBlockOfContacts")
public class ReadNextBlockOfContacts {

    protected long customerID;
    protected long numOfContacts;
    protected long startContactID;
    protected String sessionKey;

    /**
     * Gets the value of the customerID property.
     * 
     */
    public long getCustomerID() {
        return customerID;
    }

    /**
     * Sets the value of the customerID property.
     * 
     */
    public void setCustomerID(long value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the numOfContacts property.
     * 
     */
    public long getNumOfContacts() {
        return numOfContacts;
    }

    /**
     * Sets the value of the numOfContacts property.
     * 
     */
    public void setNumOfContacts(long value) {
        this.numOfContacts = value;
    }

    /**
     * Gets the value of the startContactID property.
     * 
     */
    public long getStartContactID() {
        return startContactID;
    }

    /**
     * Sets the value of the startContactID property.
     * 
     */
    public void setStartContactID(long value) {
        this.startContactID = value;
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