
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSAcctEntryHead complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSAcctEntryHead">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="AcctEntryHead" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSAcctEntryHead", propOrder = {

})
public class SBSAcctEntryHead {

    @XmlElement(name = "AcctEntryHead", required = true)
    protected String acctEntryHead;

    /**
     * Gets the value of the acctEntryHead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctEntryHead() {
        return acctEntryHead;
    }

    /**
     * Sets the value of the acctEntryHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctEntryHead(String value) {
        this.acctEntryHead = value;
    }

}
