//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.07 at 03:30:49 PM IST 
//


package com.koopid.ccmm.wsdl.ci_customer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCIContactReadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCIContactReadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CIContactReadType" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactReadType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCIContactReadType", namespace = "http://datatypes.ci.ccmm.applications.nortel.com", propOrder = {
    "ciContactReadType"
})
public class ArrayOfCIContactReadType {

    @XmlElement(name = "CIContactReadType", nillable = true)
    protected List<CIContactReadType> ciContactReadType;

    /**
     * Gets the value of the ciContactReadType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ciContactReadType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCIContactReadType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIContactReadType }
     * 
     * 
     */
    public List<CIContactReadType> getCIContactReadType() {
        if (ciContactReadType == null) {
            ciContactReadType = new ArrayList<CIContactReadType>();
        }
        return this.ciContactReadType;
    }

}
