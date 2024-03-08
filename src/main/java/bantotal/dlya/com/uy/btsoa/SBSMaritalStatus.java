
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSMaritalStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSMaritalStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="MaritalStatusID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaritalStatusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSMaritalStatus", propOrder = {

})
public class SBSMaritalStatus {

    @XmlElement(name = "MaritalStatusID", required = true)
    protected String maritalStatusID;
    @XmlElement(name = "MaritalStatusName", required = true)
    protected String maritalStatusName;

    /**
     * Gets the value of the maritalStatusID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatusID() {
        return maritalStatusID;
    }

    /**
     * Sets the value of the maritalStatusID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatusID(String value) {
        this.maritalStatusID = value;
    }

    /**
     * Gets the value of the maritalStatusName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    /**
     * Sets the value of the maritalStatusName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatusName(String value) {
        this.maritalStatusName = value;
    }

}
