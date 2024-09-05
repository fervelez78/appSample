
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Referral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Referral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sReferral" type="{http://uy.com.dlya.bantotal/BTSOA/}sReferral" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Referral", propOrder = {
    "sReferral"
})
public class Referral {

    protected List<SReferral> sReferral;

    /**
     * Gets the value of the sReferral property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sReferral property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSReferral().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SReferral }
     * 
     * 
     */
    public List<SReferral> getSReferral() {
        if (sReferral == null) {
            sReferral = new ArrayList<SReferral>();
        }
        return this.sReferral;
    }

}
