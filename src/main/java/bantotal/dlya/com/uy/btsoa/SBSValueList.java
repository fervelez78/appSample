
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSValueList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSValueList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="IDList" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ValueDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSValueList", propOrder = {

})
public class SBSValueList {

    @XmlElement(name = "IDList")
    protected long idList;
    @XmlElement(name = "ValueDesc", required = true)
    protected String valueDesc;

    /**
     * Gets the value of the idList property.
     * 
     */
    public long getIDList() {
        return idList;
    }

    /**
     * Sets the value of the idList property.
     * 
     */
    public void setIDList(long value) {
        this.idList = value;
    }

    /**
     * Gets the value of the valueDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueDesc() {
        return valueDesc;
    }

    /**
     * Sets the value of the valueDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueDesc(String value) {
        this.valueDesc = value;
    }

}
