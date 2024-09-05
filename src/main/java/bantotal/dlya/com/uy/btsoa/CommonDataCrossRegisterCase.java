
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
 *         &lt;element name="CustomerID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Amt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DueDtInst" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IssueDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DueDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
    "customerID",
    "amt",
    "term",
    "dueDtInst",
    "issueDt",
    "dueDt"
})
@XmlRootElement(name = "CommonDataCross.RegisterCase")
public class CommonDataCrossRegisterCase {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "CustomerID")
    protected long customerID;
    @XmlElement(name = "Amt")
    protected double amt;
    @XmlElement(name = "Term")
    protected int term;
    @XmlElement(name = "DueDtInst")
    protected short dueDtInst;
    @XmlElement(name = "IssueDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar issueDt;
    @XmlElement(name = "DueDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDt;

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
     * Gets the value of the amt property.
     * 
     */
    public double getAmt() {
        return amt;
    }

    /**
     * Sets the value of the amt property.
     * 
     */
    public void setAmt(double value) {
        this.amt = value;
    }

    /**
     * Gets the value of the term property.
     * 
     */
    public int getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     */
    public void setTerm(int value) {
        this.term = value;
    }

    /**
     * Gets the value of the dueDtInst property.
     * 
     */
    public short getDueDtInst() {
        return dueDtInst;
    }

    /**
     * Sets the value of the dueDtInst property.
     * 
     */
    public void setDueDtInst(short value) {
        this.dueDtInst = value;
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
     * Gets the value of the dueDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDt() {
        return dueDt;
    }

    /**
     * Sets the value of the dueDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDt(XMLGregorianCalendar value) {
        this.dueDt = value;
    }

}
