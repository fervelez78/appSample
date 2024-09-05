
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentTypes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSDocumentType" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentTypes", propOrder = {
    "sbsDocumentType"
})
public class DocumentTypes {

    @XmlElement(name = "sBSDocumentType")
    protected List<SBSDocumentType> sbsDocumentType;

    /**
     * Gets the value of the sbsDocumentType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsDocumentType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSDocumentType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSDocumentType }
     * 
     * 
     */
    public List<SBSDocumentType> getSBSDocumentType() {
        if (sbsDocumentType == null) {
            sbsDocumentType = new ArrayList<SBSDocumentType>();
        }
        return this.sbsDocumentType;
    }

}
