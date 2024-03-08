
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBTErroresNegocio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBTErroresNegocio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BTErrorNegocio" type="{http://uy.com.dlya.bantotal/BTSOA/}sBTErrorNegocio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBTErroresNegocio", propOrder = {
    "btErrorNegocio"
})
public class SBTErroresNegocio {

    @XmlElement(name = "BTErrorNegocio")
    protected List<SBTErrorNegocio> btErrorNegocio;

    /**
     * Gets the value of the btErrorNegocio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the btErrorNegocio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBTErrorNegocio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBTErrorNegocio }
     * 
     * 
     */
    public List<SBTErrorNegocio> getBTErrorNegocio() {
        if (btErrorNegocio == null) {
            btErrorNegocio = new ArrayList<SBTErrorNegocio>();
        }
        return this.btErrorNegocio;
    }

}
