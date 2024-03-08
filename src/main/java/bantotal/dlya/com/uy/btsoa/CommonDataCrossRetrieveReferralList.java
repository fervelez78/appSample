
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
 *         &lt;element name="CustomedID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FromDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ToDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FromPaginationKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MovementQty" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "fromDt",
    "toDt",
    "fromPaginationKey",
    "order",
    "movementQty"
})
@XmlRootElement(name = "CommonDataCross.RetrieveReferralList")
public class CommonDataCrossRetrieveReferralList {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "CustomedID")
    protected long customedID;
    @XmlElement(name = "FromDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fromDt;
    @XmlElement(name = "ToDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar toDt;
    @XmlElement(name = "FromPaginationKey")
    protected long fromPaginationKey;
    @XmlElement(name = "Order", required = true)
    protected String order;
    @XmlElement(name = "MovementQty")
    protected int movementQty;

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
     * Gets the value of the fromDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFromDt() {
        return fromDt;
    }

    /**
     * Sets the value of the fromDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDt(XMLGregorianCalendar value) {
        this.fromDt = value;
    }

    /**
     * Gets the value of the toDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getToDt() {
        return toDt;
    }

    /**
     * Sets the value of the toDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setToDt(XMLGregorianCalendar value) {
        this.toDt = value;
    }

    /**
     * Gets the value of the fromPaginationKey property.
     * 
     */
    public long getFromPaginationKey() {
        return fromPaginationKey;
    }

    /**
     * Sets the value of the fromPaginationKey property.
     * 
     */
    public void setFromPaginationKey(long value) {
        this.fromPaginationKey = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrder(String value) {
        this.order = value;
    }

    /**
     * Gets the value of the movementQty property.
     * 
     */
    public int getMovementQty() {
        return movementQty;
    }

    /**
     * Sets the value of the movementQty property.
     * 
     */
    public void setMovementQty(int value) {
        this.movementQty = value;
    }

}
