//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.01 at 04:50:33 PM IST 
//


package com.koopid.ccmm.wsdl.ci_contact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIMultipleClosedRCReadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CIMultipleClosedRCReadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listOfClosedReasonCodes" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIClosedRCReadType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIMultipleClosedRCReadType", propOrder = {
    "listOfClosedReasonCodes"
})
public class CIMultipleClosedRCReadType {

    protected ArrayOfCIClosedRCReadType listOfClosedReasonCodes;

    /**
     * Gets the value of the listOfClosedReasonCodes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIClosedRCReadType }
     *     
     */
    public ArrayOfCIClosedRCReadType getListOfClosedReasonCodes() {
        return listOfClosedReasonCodes;
    }

    /**
     * Sets the value of the listOfClosedReasonCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIClosedRCReadType }
     *     
     */
    public void setListOfClosedReasonCodes(ArrayOfCIClosedRCReadType value) {
        this.listOfClosedReasonCodes = value;
    }

}
