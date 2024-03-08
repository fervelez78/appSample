
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for sBSDigitalFile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSDigitalFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Base64File" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreationDt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FileID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSDigitalFile", propOrder = {

})
public class SBSDigitalFile {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "ContentType", required = true)
    protected String contentType;
    @XmlElement(name = "Base64File", required = true)
    protected String base64File;
    @XmlElement(name = "CreationDt", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDt;
    @XmlElement(name = "Version")
    protected int version;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "FileID")
    protected long fileID;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the base64File property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase64File() {
        return base64File;
    }

    /**
     * Sets the value of the base64File property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase64File(String value) {
        this.base64File = value;
    }

    /**
     * Gets the value of the creationDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDt() {
        return creationDt;
    }

    /**
     * Sets the value of the creationDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDt(XMLGregorianCalendar value) {
        this.creationDt = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the fileID property.
     * 
     */
    public long getFileID() {
        return fileID;
    }

    /**
     * Sets the value of the fileID property.
     * 
     */
    public void setFileID(long value) {
        this.fileID = value;
    }

}
