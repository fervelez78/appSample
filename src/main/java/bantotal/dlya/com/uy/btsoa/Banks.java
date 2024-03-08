
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Banks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Banks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSBank" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSBank" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Banks", propOrder = {
    "sbsBank"
})
public class Banks {

    @XmlElement(name = "sBSBank")
    protected List<SBSBank> sbsBank;

    /**
     * Gets the value of the sbsBank property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsBank property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSBank().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSBank }
     * 
     * 
     */
    public List<SBSBank> getSBSBank() {
        if (sbsBank == null) {
            sbsBank = new ArrayList<SBSBank>();
        }
        return this.sbsBank;
    }

}
