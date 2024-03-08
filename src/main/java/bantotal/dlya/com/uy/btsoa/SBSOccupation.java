
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSOccupation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSOccupation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="OccupationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OccupationID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSOccupation", propOrder = {

})
public class SBSOccupation {

    @XmlElement(name = "OccupationName", required = true)
    protected String occupationName;
    @XmlElement(name = "OccupationID")
    protected int occupationID;

    /**
     * Gets the value of the occupationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupationName() {
        return occupationName;
    }

    /**
     * Sets the value of the occupationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupationName(String value) {
        this.occupationName = value;
    }

    /**
     * Gets the value of the occupationID property.
     * 
     */
    public int getOccupationID() {
        return occupationID;
    }

    /**
     * Sets the value of the occupationID property.
     * 
     */
    public void setOccupationID(int value) {
        this.occupationID = value;
    }

}
