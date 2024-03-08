
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValueList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSValueList" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSValueList" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueList", propOrder = {
    "sbsValueList"
})
public class ValueList {

    @XmlElement(name = "sBSValueList")
    protected List<SBSValueList> sbsValueList;

    /**
     * Gets the value of the sbsValueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsValueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSValueList }
     * 
     * 
     */
    public List<SBSValueList> getSBSValueList() {
        if (sbsValueList == null) {
            sbsValueList = new ArrayList<SBSValueList>();
        }
        return this.sbsValueList;
    }

}
