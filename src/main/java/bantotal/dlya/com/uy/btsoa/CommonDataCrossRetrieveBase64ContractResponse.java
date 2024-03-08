
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
 *         &lt;element name="Base64Contract" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentFutureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "base64Contract",
    "documentFutureName",
    "erroresnegocio",
    "btoutreq"
})
@XmlRootElement(name = "CommonDataCross.RetrieveBase64ContractResponse")
public class CommonDataCrossRetrieveBase64ContractResponse {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "Base64Contract", required = true)
    protected String base64Contract;
    @XmlElement(name = "DocumentFutureName", required = true)
    protected String documentFutureName;
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
     * Gets the value of the base64Contract property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase64Contract() {
        return base64Contract;
    }

    /**
     * Sets the value of the base64Contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase64Contract(String value) {
        this.base64Contract = value;
    }

    /**
     * Gets the value of the documentFutureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentFutureName() {
        return documentFutureName;
    }

    /**
     * Sets the value of the documentFutureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentFutureName(String value) {
        this.documentFutureName = value;
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
