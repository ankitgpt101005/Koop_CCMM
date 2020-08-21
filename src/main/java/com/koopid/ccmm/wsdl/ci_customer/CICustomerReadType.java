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
 * <p>Java class for CICustomerReadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CICustomerReadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="registerDate" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIDateTime" minOccurs="0"/&gt;
 *         &lt;element name="addressList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIAddressReadType" minOccurs="0"/&gt;
 *         &lt;element name="phoneNumberList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIPhoneNumberReadType" minOccurs="0"/&gt;
 *         &lt;element name="emailAddressList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIEmailAddressReadType" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCICustomFieldReadType" minOccurs="0"/&gt;
 *         &lt;element name="defaultAddress" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIAddressReadType" minOccurs="0"/&gt;
 *         &lt;element name="defaultPhoneNumber" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIPhoneNumberReadType" minOccurs="0"/&gt;
 *         &lt;element name="defaultEmailAddress" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIEmailAddressReadType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CICustomerReadType2", namespace = "http://datatypes.ci.ccmm.applications.nortel.com", propOrder = {
    "id",
    "title",
    "firstName",
    "lastName",
    "username",
    "registerDate",
    "addressList",
    "phoneNumberList",
    "emailAddressList",
    "customFieldList",
    "defaultAddress",
    "defaultPhoneNumber",
    "defaultEmailAddress"
})
public class CICustomerReadType {

    protected long id;
    protected String title;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected CIDateTime registerDate;
    protected ArrayOfCIAddressReadType addressList;
    protected ArrayOfCIPhoneNumberReadType phoneNumberList;
    protected ArrayOfCIEmailAddressReadType emailAddressList;
    protected ArrayOfCICustomFieldReadType customFieldList;
    protected CIAddressReadType defaultAddress;
    protected CIPhoneNumberReadType defaultPhoneNumber;
    protected CIEmailAddressReadType defaultEmailAddress;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the registerDate property.
     * 
     * @return
     *     possible object is
     *     {@link CIDateTime }
     *     
     */
    public CIDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets the value of the registerDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIDateTime }
     *     
     */
    public void setRegisterDate(CIDateTime value) {
        this.registerDate = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIAddressReadType }
     *     
     */
    public ArrayOfCIAddressReadType getAddressList() {
        return addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIAddressReadType }
     *     
     */
    public void setAddressList(ArrayOfCIAddressReadType value) {
        this.addressList = value;
    }

    /**
     * Gets the value of the phoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIPhoneNumberReadType }
     *     
     */
    public ArrayOfCIPhoneNumberReadType getPhoneNumberList() {
        return phoneNumberList;
    }

    /**
     * Sets the value of the phoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIPhoneNumberReadType }
     *     
     */
    public void setPhoneNumberList(ArrayOfCIPhoneNumberReadType value) {
        this.phoneNumberList = value;
    }

    /**
     * Gets the value of the emailAddressList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIEmailAddressReadType }
     *     
     */
    public ArrayOfCIEmailAddressReadType getEmailAddressList() {
        return emailAddressList;
    }

    /**
     * Sets the value of the emailAddressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIEmailAddressReadType }
     *     
     */
    public void setEmailAddressList(ArrayOfCIEmailAddressReadType value) {
        this.emailAddressList = value;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCICustomFieldReadType }
     *     
     */
    public ArrayOfCICustomFieldReadType getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCICustomFieldReadType }
     *     
     */
    public void setCustomFieldList(ArrayOfCICustomFieldReadType value) {
        this.customFieldList = value;
    }

    /**
     * Gets the value of the defaultAddress property.
     * 
     * @return
     *     possible object is
     *     {@link CIAddressReadType }
     *     
     */
    public CIAddressReadType getDefaultAddress() {
        return defaultAddress;
    }

    /**
     * Sets the value of the defaultAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIAddressReadType }
     *     
     */
    public void setDefaultAddress(CIAddressReadType value) {
        this.defaultAddress = value;
    }

    /**
     * Gets the value of the defaultPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link CIPhoneNumberReadType }
     *     
     */
    public CIPhoneNumberReadType getDefaultPhoneNumber() {
        return defaultPhoneNumber;
    }

    /**
     * Sets the value of the defaultPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIPhoneNumberReadType }
     *     
     */
    public void setDefaultPhoneNumber(CIPhoneNumberReadType value) {
        this.defaultPhoneNumber = value;
    }

    /**
     * Gets the value of the defaultEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link CIEmailAddressReadType }
     *     
     */
    public CIEmailAddressReadType getDefaultEmailAddress() {
        return defaultEmailAddress;
    }

    /**
     * Sets the value of the defaultEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIEmailAddressReadType }
     *     
     */
    public void setDefaultEmailAddress(CIEmailAddressReadType value) {
        this.defaultEmailAddress = value;
    }

}