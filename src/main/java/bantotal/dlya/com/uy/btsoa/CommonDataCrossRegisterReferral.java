
package bantotal.dlya.com.uy.btsoa;

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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Btinreq" type="{http://uy.com.dlya.bantotal/BTSOA/}sBTInReq"/>
 *         &lt;element name="CustomedID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ReferralRecieverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SendType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralRecieverEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralRecieverPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferralEmailSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "btinreq",
    "customedID",
    "referralRecieverName",
    "sendType",
    "referralRecieverEmail",
    "referralRecieverPhone",
    "referralDesc",
    "referralEmailSubject"
})
@XmlRootElement(name = "CommonDataCross.RegisterReferral")
public class CommonDataCrossRegisterReferral {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "CustomedID")
    protected long customedID;
    @XmlElement(name = "ReferralRecieverName", required = true)
    protected String referralRecieverName;
    @XmlElement(name = "SendType", required = true)
    protected String sendType;
    @XmlElement(name = "ReferralRecieverEmail", required = true)
    protected String referralRecieverEmail;
    @XmlElement(name = "ReferralRecieverPhone", required = true)
    protected String referralRecieverPhone;
    @XmlElement(name = "ReferralDesc", required = true)
    protected String referralDesc;
    @XmlElement(name = "ReferralEmailSubject", required = true)
    protected String referralEmailSubject;

    /**
     * Gets the value of the btinreq property.
     * 
     * @return
     *     possible object is
     *     {@link SBTInReq }
     *     
     */
    public SBTInReq getBtinreq() {
        return btinreq;
    }

    /**
     * Sets the value of the btinreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBTInReq }
     *     
     */
    public void setBtinreq(SBTInReq value) {
        this.btinreq = value;
    }

    /**
     * Gets the value of the customedID property.
     * 
     */
    public long getCustomedID() {
        return customedID;
    }

    /**
     * Sets the value of the customedID property.
     * 
     */
    public void setCustomedID(long value) {
        this.customedID = value;
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
     * Gets the value of the referralDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralDesc() {
        return referralDesc;
    }

    /**
     * Sets the value of the referralDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralDesc(String value) {
        this.referralDesc = value;
    }

    /**
     * Gets the value of the referralEmailSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferralEmailSubject() {
        return referralEmailSubject;
    }

    /**
     * Sets the value of the referralEmailSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferralEmailSubject(String value) {
        this.referralEmailSubject = value;
    }

}
