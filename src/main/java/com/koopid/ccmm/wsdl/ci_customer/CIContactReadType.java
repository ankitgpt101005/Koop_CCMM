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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIContactReadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CIContactReadType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="customerID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="originalSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="source" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactSource"/&gt;
 *         &lt;element name="status" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactStatus"/&gt;
 *         &lt;element name="skillset" type="{http://datatypes.ci.ccmm.applications.nortel.com}CISkillsetReadType" minOccurs="0"/&gt;
 *         &lt;element name="priority" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactPriority"/&gt;
 *         &lt;element name="timezone" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="webOnHoldTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arrivalTime" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIDateTime" minOccurs="0"/&gt;
 *         &lt;element name="closedTime" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIDateTime" minOccurs="0"/&gt;
 *         &lt;element name="openTime" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIDateTime" minOccurs="0"/&gt;
 *         &lt;element name="openDuration" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="MailTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailCC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contactType" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIContactType"/&gt;
 *         &lt;element name="agent" type="{http://datatypes.ci.ccmm.applications.nortel.com}CIAgentReadType" minOccurs="0"/&gt;
 *         &lt;element name="actionList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCIActionReadType" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{http://datatypes.ci.ccmm.applications.nortel.com}ArrayOfCICustomFieldReadType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIContactReadType", namespace = "http://datatypes.ci.ccmm.applications.nortel.com", propOrder = {
    "id",
    "customerID",
    "originalSubject",
    "source",
    "status",
    "skillset",
    "priority",
    "timezone",
    "webOnHoldTag",
    "arrivalTime",
    "closedTime",
    "openTime",
    "openDuration",
    "mailTo",
    "mailFrom",
    "mailCC",
    "contactType",
    "agent",
    "actionList",
    "customFieldList"
})
public class CIContactReadType {

    protected long id;
    protected long customerID;
    protected String originalSubject;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CIContactSource source;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CIContactStatus status;
    protected CISkillsetReadType skillset;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CIContactPriority priority;
    protected long timezone;
    protected String webOnHoldTag;
    protected CIDateTime arrivalTime;
    protected CIDateTime closedTime;
    protected CIDateTime openTime;
    protected long openDuration;
    @XmlElement(name = "MailTo")
    protected String mailTo;
    @XmlElement(name = "MailFrom")
    protected String mailFrom;
    @XmlElement(name = "MailCC")
    protected String mailCC;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CIContactType contactType;
    protected CIAgentReadType agent;
    protected ArrayOfCIActionReadType actionList;
    protected ArrayOfCICustomFieldReadType customFieldList;

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
     * Gets the value of the originalSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalSubject() {
        return originalSubject;
    }

    /**
     * Sets the value of the originalSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalSubject(String value) {
        this.originalSubject = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactSource }
     *     
     */
    public CIContactSource getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactSource }
     *     
     */
    public void setSource(CIContactSource value) {
        this.source = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactStatus }
     *     
     */
    public CIContactStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactStatus }
     *     
     */
    public void setStatus(CIContactStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the skillset property.
     * 
     * @return
     *     possible object is
     *     {@link CISkillsetReadType }
     *     
     */
    public CISkillsetReadType getSkillset() {
        return skillset;
    }

    /**
     * Sets the value of the skillset property.
     * 
     * @param value
     *     allowed object is
     *     {@link CISkillsetReadType }
     *     
     */
    public void setSkillset(CISkillsetReadType value) {
        this.skillset = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactPriority }
     *     
     */
    public CIContactPriority getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactPriority }
     *     
     */
    public void setPriority(CIContactPriority value) {
        this.priority = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     */
    public long getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     */
    public void setTimezone(long value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the webOnHoldTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebOnHoldTag() {
        return webOnHoldTag;
    }

    /**
     * Sets the value of the webOnHoldTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebOnHoldTag(String value) {
        this.webOnHoldTag = value;
    }

    /**
     * Gets the value of the arrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link CIDateTime }
     *     
     */
    public CIDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the value of the arrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIDateTime }
     *     
     */
    public void setArrivalTime(CIDateTime value) {
        this.arrivalTime = value;
    }

    /**
     * Gets the value of the closedTime property.
     * 
     * @return
     *     possible object is
     *     {@link CIDateTime }
     *     
     */
    public CIDateTime getClosedTime() {
        return closedTime;
    }

    /**
     * Sets the value of the closedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIDateTime }
     *     
     */
    public void setClosedTime(CIDateTime value) {
        this.closedTime = value;
    }

    /**
     * Gets the value of the openTime property.
     * 
     * @return
     *     possible object is
     *     {@link CIDateTime }
     *     
     */
    public CIDateTime getOpenTime() {
        return openTime;
    }

    /**
     * Sets the value of the openTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIDateTime }
     *     
     */
    public void setOpenTime(CIDateTime value) {
        this.openTime = value;
    }

    /**
     * Gets the value of the openDuration property.
     * 
     */
    public long getOpenDuration() {
        return openDuration;
    }

    /**
     * Sets the value of the openDuration property.
     * 
     */
    public void setOpenDuration(long value) {
        this.openDuration = value;
    }

    /**
     * Gets the value of the mailTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * Sets the value of the mailTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailTo(String value) {
        this.mailTo = value;
    }

    /**
     * Gets the value of the mailFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * Sets the value of the mailFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailFrom(String value) {
        this.mailFrom = value;
    }

    /**
     * Gets the value of the mailCC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailCC() {
        return mailCC;
    }

    /**
     * Sets the value of the mailCC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailCC(String value) {
        this.mailCC = value;
    }

    /**
     * Gets the value of the contactType property.
     * 
     * @return
     *     possible object is
     *     {@link CIContactType }
     *     
     */
    public CIContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the value of the contactType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIContactType }
     *     
     */
    public void setContactType(CIContactType value) {
        this.contactType = value;
    }

    /**
     * Gets the value of the agent property.
     * 
     * @return
     *     possible object is
     *     {@link CIAgentReadType }
     *     
     */
    public CIAgentReadType getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     * 
     * @param value
     *     allowed object is
     *     {@link CIAgentReadType }
     *     
     */
    public void setAgent(CIAgentReadType value) {
        this.agent = value;
    }

    /**
     * Gets the value of the actionList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCIActionReadType }
     *     
     */
    public ArrayOfCIActionReadType getActionList() {
        return actionList;
    }

    /**
     * Sets the value of the actionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCIActionReadType }
     *     
     */
    public void setActionList(ArrayOfCIActionReadType value) {
        this.actionList = value;
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

}
