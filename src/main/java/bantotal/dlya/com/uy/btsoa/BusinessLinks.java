
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessLinks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessLinks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSCompanyLink" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSCompanyLink" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessLinks", propOrder = {
    "sbsCompanyLink"
})
public class BusinessLinks {

    @XmlElement(name = "sBSCompanyLink")
    protected List<SBSCompanyLink> sbsCompanyLink;

    /**
     * Gets the value of the sbsCompanyLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsCompanyLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSCompanyLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSCompanyLink }
     * 
     * 
     */
    public List<SBSCompanyLink> getSBSCompanyLink() {
        if (sbsCompanyLink == null) {
            sbsCompanyLink = new ArrayList<SBSCompanyLink>();
        }
        return this.sbsCompanyLink;
    }

}
