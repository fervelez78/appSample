
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSProfession complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSProfession">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ProfessionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProfessionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSProfession", propOrder = {

})
public class SBSProfession {

    @XmlElement(name = "ProfessionID")
    protected int professionID;
    @XmlElement(name = "ProfessionName", required = true)
    protected String professionName;

    /**
     * Gets the value of the professionID property.
     * 
     */
    public int getProfessionID() {
        return professionID;
    }

    /**
     * Sets the value of the professionID property.
     * 
     */
    public void setProfessionID(int value) {
        this.professionID = value;
    }

    /**
     * Gets the value of the professionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionName() {
        return professionName;
    }

    /**
     * Sets the value of the professionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionName(String value) {
        this.professionName = value;
    }

}
