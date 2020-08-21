//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:30:49 PM IST 
//


package com.koopid.ccmm.wsdl.ci_customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIContactBlockReadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CIContactBlockReadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customerID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="contactsList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIContactReadType" minOccurs="0"/&gt;
 *         &lt;element name="contactsRemainingBefore" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="contactsRemainingAfter" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIContactBlockReadType", namespace = "http://datatypes.ci.ccmm.applications.nortel.com", propOrder = {
    "customerID",
    "contactsList",
    "contactsRemainingBefore",
    "contactsRemainingAfter"
})
public class CIContactBlockReadType {

    protected long customerID;
    protected ArrayOfCIContactReadType contactsList;
    protected long contactsRemainingBefore;
    protected long contactsRemainingAfter;

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
     * Gets the value of the contactsList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIContactReadType }
     *     
     */
    public ArrayOfCIContactReadType getContactsList() {
        return contactsList;
    }

    /**
     * Sets the value of the contactsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIContactReadType }
     *     
     */
    public void setContactsList(ArrayOfCIContactReadType value) {
        this.contactsList = value;
    }

    /**
     * Gets the value of the contactsRemainingBefore property.
     * 
     */
    public long getContactsRemainingBefore() {
        return contactsRemainingBefore;
    }

    /**
     * Sets the value of the contactsRemainingBefore property.
     * 
     */
    public void setContactsRemainingBefore(long value) {
        this.contactsRemainingBefore = value;
    }

    /**
     * Gets the value of the contactsRemainingAfter property.
     * 
     */
    public long getContactsRemainingAfter() {
        return contactsRemainingAfter;
    }

    /**
     * Sets the value of the contactsRemainingAfter property.
     * 
     */
    public void setContactsRemainingAfter(long value) {
        this.contactsRemainingAfter = value;
    }

}