
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
 *         &lt;element name="CaseID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CaseDueDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CaseDueTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "caseID",
    "caseDueDt",
    "caseDueTime",
    "erroresnegocio",
    "btoutreq"
})
@XmlRootElement(name = "CommonDataCross.RegisterCaseResponse")
public class CommonDataCrossRegisterCaseResponse {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "CaseID")
    protected long caseID;
    @XmlElement(name = "CaseDueDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar caseDueDt;
    @XmlElement(name = "CaseDueTime", required = true)
    protected String caseDueTime;
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
     * Gets the value of the caseID property.
     * 
     */
    public long getCaseID() {
        return caseID;
    }

    /**
     * Sets the value of the caseID property.
     * 
     */
    public void setCaseID(long value) {
        this.caseID = value;
    }

    /**
     * Gets the value of the caseDueDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCaseDueDt() {
        return caseDueDt;
    }

    /**
     * Sets the value of the caseDueDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCaseDueDt(XMLGregorianCalendar value) {
        this.caseDueDt = value;
    }

    /**
     * Gets the value of the caseDueTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseDueTime() {
        return caseDueTime;
    }

    /**
     * Sets the value of the caseDueTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseDueTime(String value) {
        this.caseDueTime = value;
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
