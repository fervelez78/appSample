
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Professions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Professions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSProfession" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSProfession" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Professions", propOrder = {
    "sbsProfession"
})
public class Professions {

    @XmlElement(name = "sBSProfession")
    protected List<SBSProfession> sbsProfession;

    /**
     * Gets the value of the sbsProfession property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsProfession property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSProfession().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSProfession }
     * 
     * 
     */
    public List<SBSProfession> getSBSProfession() {
        if (sbsProfession == null) {
            sbsProfession = new ArrayList<SBSProfession>();
        }
        return this.sbsProfession;
    }

}
