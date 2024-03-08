
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Occupations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Occupations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSOccupation" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSOccupation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Occupations", propOrder = {
    "sbsOccupation"
})
public class Occupations {

    @XmlElement(name = "sBSOccupation")
    protected List<SBSOccupation> sbsOccupation;

    /**
     * Gets the value of the sbsOccupation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsOccupation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSOccupation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSOccupation }
     * 
     * 
     */
    public List<SBSOccupation> getSBSOccupation() {
        if (sbsOccupation == null) {
            sbsOccupation = new ArrayList<SBSOccupation>();
        }
        return this.sbsOccupation;
    }

}
