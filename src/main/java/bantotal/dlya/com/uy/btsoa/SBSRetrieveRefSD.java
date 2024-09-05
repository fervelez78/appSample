
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSRetrieveRefSD complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSRetrieveRefSD">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="NominalGAT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="BSNominalGAT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSRetrieveRefSD", propOrder = {

})
public class SBSRetrieveRefSD {

    @XmlElement(name = "NominalGAT")
    protected double nominalGAT;
    @XmlElement(name = "BSNominalGAT")
    protected double bsNominalGAT;

    /**
     * Gets the value of the nominalGAT property.
     * 
     */
    public double getNominalGAT() {
        return nominalGAT;
    }

    /**
     * Sets the value of the nominalGAT property.
     * 
     */
    public void setNominalGAT(double value) {
        this.nominalGAT = value;
    }

    /**
     * Gets the value of the bsNominalGAT property.
     * 
     */
    public double getBSNominalGAT() {
        return bsNominalGAT;
    }

    /**
     * Sets the value of the bsNominalGAT property.
     * 
     */
    public void setBSNominalGAT(double value) {
        this.bsNominalGAT = value;
    }

}
