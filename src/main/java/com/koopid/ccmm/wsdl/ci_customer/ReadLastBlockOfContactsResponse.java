//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:30:49 PM IST 
//


package com.koopid.ccmm.wsdl.ci_customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ReadLastBlockOfContactsResult" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactBlockReadType" minOccurs="0"/&gt;
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
    "readLastBlockOfContactsResult"
})
@XmlRootElement(name = "ReadLastBlockOfContactsResponse")
public class ReadLastBlockOfContactsResponse {

    @XmlElement(name = "ReadLastBlockOfContactsResult")
    protected CIContactBlockReadType readLastBlockOfContactsResult;

    /**
     * Gets the value of the readLastBlockOfContactsResult property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactBlockReadType }
     *     
     */
    public CIContactBlockReadType getReadLastBlockOfContactsResult() {
        return readLastBlockOfContactsResult;
    }

    /**
     * Sets the value of the readLastBlockOfContactsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactBlockReadType }
     *     
     */
    public void setReadLastBlockOfContactsResult(CIContactBlockReadType value) {
        this.readLastBlockOfContactsResult = value;
    }

}
