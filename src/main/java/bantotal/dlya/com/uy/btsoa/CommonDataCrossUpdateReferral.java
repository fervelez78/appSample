
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
 *         &lt;element name="SendReferralID" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "sendReferralID"
})
@XmlRootElement(name = "CommonDataCross.UpdateReferral")
public class CommonDataCrossUpdateReferral {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "CustomedID")
    protected long customedID;
    @XmlElement(name = "SendReferralID")
    protected long sendReferralID;

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

}
