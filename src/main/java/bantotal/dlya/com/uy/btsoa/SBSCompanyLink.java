
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSCompanyLink complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSCompanyLink">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="CompanyLinkID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Member" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompanyLinkName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSCompanyLink", propOrder = {

})
public class SBSCompanyLink {

    @XmlElement(name = "CompanyLinkID")
    protected short companyLinkID;
    @XmlElement(name = "Member", required = true)
    protected String member;
    @XmlElement(name = "CompanyLinkName", required = true)
    protected String companyLinkName;

    /**
     * Gets the value of the companyLinkID property.
     * 
     */
    public short getCompanyLinkID() {
        return companyLinkID;
    }

    /**
     * Sets the value of the companyLinkID property.
     * 
     */
    public void setCompanyLinkID(short value) {
        this.companyLinkID = value;
    }

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMember(String value) {
        this.member = value;
    }

    /**
     * Gets the value of the companyLinkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyLinkName() {
        return companyLinkName;
    }

    /**
     * Sets the value of the companyLinkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyLinkName(String value) {
        this.companyLinkName = value;
    }

}
