
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="DocumentTypeID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="DocumentTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSDocumentType", propOrder = {

})
public class SBSDocumentType {

    @XmlElement(name = "DocumentTypeID")
    protected short documentTypeID;
    @XmlElement(name = "DocumentTypeName", required = true)
    protected String documentTypeName;

    /**
     * Gets the value of the documentTypeID property.
     * 
     */
    public short getDocumentTypeID() {
        return documentTypeID;
    }

    /**
     * Sets the value of the documentTypeID property.
     * 
     */
    public void setDocumentTypeID(short value) {
        this.documentTypeID = value;
    }

    /**
     * Gets the value of the documentTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentTypeName() {
        return documentTypeName;
    }

    /**
     * Sets the value of the documentTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentTypeName(String value) {
        this.documentTypeName = value;
    }

}
