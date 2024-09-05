
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for sReferral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sReferral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="AcceptDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TotalSend" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastSendDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IssueDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SendReferralID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralRecieverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralRecieverPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SendType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralRecieverEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sReferral", propOrder = {

})
public class SReferral {

    @XmlElement(name = "AcceptDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar acceptDt;
    @XmlElement(name = "TotalSend")
    protected int totalSend;
    @XmlElement(name = "LastSendDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastSendDt;
    @XmlElement(name = "IssueDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar issueDt;
    @XmlElement(name = "SendReferralID")
    protected long sendReferralID;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "ReferralRecieverName", required = true)
    protected String referralRecieverName;
    @XmlElement(name = "ReferralRecieverPhone", required = true)
    protected String referralRecieverPhone;
    @XmlElement(name = "SendType", required = true)
    protected String sendType;
    @XmlElement(name = "ReferralRecieverEmail", required = true)
    protected String referralRecieverEmail;

    /**
     * Gets the value of the acceptDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptDt() {
        return acceptDt;
    }

    /**
     * Sets the value of the acceptDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptDt(XMLGregorianCalendar value) {
        this.acceptDt = value;
    }

    /**
     * Gets the value of the totalSend property.
     * 
     */
    public int getTotalSend() {
        return totalSend;
    }

    /**
     * Sets the value of the totalSend property.
     * 
     */
    public void setTotalSend(int value) {
        this.totalSend = value;
    }

    /**
     * Gets the value of the lastSendDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSendDt() {
        return lastSendDt;
    }

    /**
     * Sets the value of the lastSendDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSendDt(XMLGregorianCalendar value) {
        this.lastSendDt = value;
    }

    /**
     * Gets the value of the issueDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIssueDt() {
        return issueDt;
    }

    /**
     * Sets the value of the issueDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIssueDt(XMLGregorianCalendar value) {
        this.issueDt = value;
    }

    /**
     * Gets the value of the sendReferralID property.
     * 
     */
    public long getSendReferralID() {
        return sendReferralID;
    }

    /**
     * Sets the value of the sendReferralID property.
     * 
     */
    public void setSendReferralID(long value) {
        this.sendReferralID = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the referralRecieverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralRecieverName() {
        return referralRecieverName;
    }

    /**
     * Sets the value of the referralRecieverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralRecieverName(String value) {
        this.referralRecieverName = value;
    }

    /**
     * Gets the value of the referralRecieverPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralRecieverPhone() {
        return referralRecieverPhone;
    }

    /**
     * Sets the value of the referralRecieverPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralRecieverPhone(String value) {
        this.referralRecieverPhone = value;
    }

    /**
     * Gets the value of the sendType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * Sets the value of the sendType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendType(String value) {
        this.sendType = value;
    }

    /**
     * Gets the value of the referralRecieverEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralRecieverEmail() {
        return referralRecieverEmail;
    }

    /**
     * Sets the value of the referralRecieverEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralRecieverEmail(String value) {
        this.referralRecieverEmail = value;
    }

}
