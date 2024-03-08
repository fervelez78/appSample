
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Activities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Activities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSActivity" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSActivity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Activities", propOrder = {
    "sbsActivity"
})
public class Activities {

    @XmlElement(name = "sBSActivity")
    protected List<SBSActivity> sbsActivity;

    /**
     * Gets the value of the sbsActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSActivity }
     * 
     * 
     */
    public List<SBSActivity> getSBSActivity() {
        if (sbsActivity == null) {
            sbsActivity = new ArrayList<SBSActivity>();
        }
        return this.sbsActivity;
    }

}
