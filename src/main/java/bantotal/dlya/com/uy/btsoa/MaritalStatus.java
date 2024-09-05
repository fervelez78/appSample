
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MaritalStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaritalStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSMaritalStatus" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSMaritalStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaritalStatus", propOrder = {
    "sbsMaritalStatus"
})
public class MaritalStatus {

    @XmlElement(name = "sBSMaritalStatus")
    protected List<SBSMaritalStatus> sbsMaritalStatus;

    /**
     * Gets the value of the sbsMaritalStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsMaritalStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSMaritalStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSMaritalStatus }
     * 
     * 
     */
    public List<SBSMaritalStatus> getSBSMaritalStatus() {
        if (sbsMaritalStatus == null) {
            sbsMaritalStatus = new ArrayList<SBSMaritalStatus>();
        }
        return this.sbsMaritalStatus;
    }

}
