
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Referral" type="{http://uy.com.dlya.bantotal/BTSOA/}Referral"/>
 *         &lt;element name="TotalSendWhatsApp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastSendDtWhatsApp" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PaginationKey" type="{http://uy.com.dlya.bantotal/BTSOA/}PaginationKey"/>
 *         &lt;element name="Erroresnegocio" type="{http://uy.com.dlya.bantotal/BTSOA/}sBTErroresNegocio"/>
 *         &lt;element name="Btoutreq" type="{http://uy.com.dlya.bantotal/BTSOA/}sBTOutReq"/>
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
    "referral",
    "totalSendWhatsApp",
    "lastSendDtWhatsApp",
    "paginationKey",
    "erroresnegocio",
    "btoutreq"
})
@XmlRootElement(name = "CommonDataCross.RetrieveReferralListResponse")
public class CommonDataCrossRetrieveReferralListResponse {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "Referral", required = true)
    protected Referral referral;
    @XmlElement(name = "TotalSendWhatsApp")
    protected int totalSendWhatsApp;
    @XmlElement(name = "LastSendDtWhatsApp", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastSendDtWhatsApp;
    @XmlElement(name = "PaginationKey", required = true)
    protected PaginationKey paginationKey;
    @XmlElement(name = "Erroresnegocio", required = true)
    protected SBTErroresNegocio erroresnegocio;
    @XmlElement(name = "Btoutreq", required = true)
    protected SBTOutReq btoutreq;

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
     * Gets the value of the referral property.
     * 
     * @return
     *     possible object is
     *     {@link Referral }
     *     
     */
    public Referral getReferral() {
        return referral;
    }

    /**
     * Sets the value of the referral property.
     * 
     * @param value
     *     allowed object is
     *     {@link Referral }
     *     
     */
    public void setReferral(Referral value) {
        this.referral = value;
    }

    /**
     * Gets the value of the totalSendWhatsApp property.
     * 
     */
    public int getTotalSendWhatsApp() {
        return totalSendWhatsApp;
    }

    /**
     * Sets the value of the totalSendWhatsApp property.
     * 
     */
    public void setTotalSendWhatsApp(int value) {
        this.totalSendWhatsApp = value;
    }

    /**
     * Gets the value of the lastSendDtWhatsApp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSendDtWhatsApp() {
        return lastSendDtWhatsApp;
    }

    /**
     * Sets the value of the lastSendDtWhatsApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSendDtWhatsApp(XMLGregorianCalendar value) {
        this.lastSendDtWhatsApp = value;
    }

    /**
     * Gets the value of the paginationKey property.
     * 
     * @return
     *     possible object is
     *     {@link PaginationKey }
     *     
     */
    public PaginationKey getPaginationKey() {
        return paginationKey;
    }

    /**
     * Sets the value of the paginationKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginationKey }
     *     
     */
    public void setPaginationKey(PaginationKey value) {
        this.paginationKey = value;
    }

    /**
     * Gets the value of the erroresnegocio property.
     * 
     * @return
     *     possible object is
     *     {@link SBTErroresNegocio }
     *     
     */
    public SBTErroresNegocio getErroresnegocio() {
        return erroresnegocio;
    }

    /**
     * Sets the value of the erroresnegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBTErroresNegocio }
     *     
     */
    public void setErroresnegocio(SBTErroresNegocio value) {
        this.erroresnegocio = value;
    }

    /**
     * Gets the value of the btoutreq property.
     * 
     * @return
     *     possible object is
     *     {@link SBTOutReq }
     *     
     */
    public SBTOutReq getBtoutreq() {
        return btoutreq;
    }

    /**
     * Sets the value of the btoutreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBTOutReq }
     *     
     */
    public void setBtoutreq(SBTOutReq value) {
        this.btoutreq = value;
    }

}
