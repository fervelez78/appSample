
package bantotal.dlya.com.uy.btsoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Answers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Answers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sBSSecurityAnswer" type="{http://uy.com.dlya.bantotal/BTSOA/}sBSSecurityAnswer" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Answers", propOrder = {
    "sbsSecurityAnswer"
})
public class Answers {

	/**
	 * Lista de respuestas de seguridad.
	 */
    @XmlElement(name = "sBSSecurityAnswer")
    protected List<SBSSecurityAnswer> sbsSecurityAnswer;

    /**
     * Gets the value of the sbsSecurityAnswer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sbsSecurityAnswer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSBSSecurityAnswer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBSSecurityAnswer }
     * 
     * 
     */
    public List<SBSSecurityAnswer> getSBSSecurityAnswer() {
        if (sbsSecurityAnswer == null) {
            sbsSecurityAnswer = new ArrayList<SBSSecurityAnswer>();
        }
        return this.sbsSecurityAnswer;
    }

}
