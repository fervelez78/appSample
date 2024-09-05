
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
 *         &lt;element name="ReferenceSD" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSRetrieveRefSD"/>
 *         &lt;element name="ReferenceTD" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSRetrieveRefTD"/>
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
    "referenceSD",
    "referenceTD",
    "erroresnegocio",
    "btoutreq"
})
@XmlRootElement(name = "CommonDataCross.RetrieveReferenceTypesResponse")
public class CommonDataCrossRetrieveReferenceTypesResponse {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "ReferenceSD", required = true)
    protected SBSRetrieveRefSD referenceSD;
    @XmlElement(name = "ReferenceTD", required = true)
    protected SBSRetrieveRefTD referenceTD;
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
     * Gets the value of the referenceSD property.
     * 
     * @return
     *     possible object is
     *     {@link SBSRetrieveRefSD }
     *     
     */
    public SBSRetrieveRefSD getReferenceSD() {
        return referenceSD;
    }

    /**
     * Sets the value of the referenceSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBSRetrieveRefSD }
     *     
     */
    public void setReferenceSD(SBSRetrieveRefSD value) {
        this.referenceSD = value;
    }

    /**
     * Gets the value of the referenceTD property.
     * 
     * @return
     *     possible object is
     *     {@link SBSRetrieveRefTD }
     *     
     */
    public SBSRetrieveRefTD getReferenceTD() {
        return referenceTD;
    }

    /**
     * Sets the value of the referenceTD property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBSRetrieveRefTD }
     *     
     */
    public void setReferenceTD(SBSRetrieveRefTD value) {
        this.referenceTD = value;
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
