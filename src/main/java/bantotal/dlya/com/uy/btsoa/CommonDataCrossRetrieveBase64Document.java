
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
 *         &lt;element name="DocumentID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AccessType" type="{http://www.w3.org/2001/XMLSchema}short"/>
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
    "documentID",
    "version",
    "accessType"
})
@XmlRootElement(name = "CommonDataCross.RetrieveBase64Document")
public class CommonDataCrossRetrieveBase64Document {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "DocumentID")
    protected long documentID;
    @XmlElement(name = "Version")
    protected int version;
    @XmlElement(name = "AccessType")
    protected short accessType;

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
     * Gets the value of the documentID property.
     * 
     */
    public long getDocumentID() {
        return documentID;
    }

    /**
     * Sets the value of the documentID property.
     * 
     */
    public void setDocumentID(long value) {
        this.documentID = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Gets the value of the accessType property.
     * 
     */
    public short getAccessType() {
        return accessType;
    }

    /**
     * Sets the value of the accessType property.
     * 
     */
    public void setAccessType(short value) {
        this.accessType = value;
    }

}
