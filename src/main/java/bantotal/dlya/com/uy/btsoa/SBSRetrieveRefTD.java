
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSRetrieveRefTD complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSRetrieveRefTD">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="NominalGAT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="BSTerm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BSNominalGAT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="BSCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BSAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSRetrieveRefTD", propOrder = {

})
public class SBSRetrieveRefTD {

    @XmlElement(name = "NominalGAT")
    protected double nominalGAT;
    @XmlElement(name = "BSTerm")
    protected int bsTerm;
    @XmlElement(name = "BSNominalGAT")
    protected double bsNominalGAT;
    @XmlElement(name = "BSCurrency", required = true)
    protected String bsCurrency;
    @XmlElement(name = "BSAmt")
    protected double bsAmt;

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
     * Gets the value of the bsTerm property.
     * 
     */
    public int getBSTerm() {
        return bsTerm;
    }

    /**
     * Sets the value of the bsTerm property.
     * 
     */
    public void setBSTerm(int value) {
        this.bsTerm = value;
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

    /**
     * Gets the value of the bsCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBSCurrency() {
        return bsCurrency;
    }

    /**
     * Sets the value of the bsCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBSCurrency(String value) {
        this.bsCurrency = value;
    }

    /**
     * Gets the value of the bsAmt property.
     * 
     */
    public double getBSAmt() {
        return bsAmt;
    }

    /**
     * Sets the value of the bsAmt property.
     * 
     */
    public void setBSAmt(double value) {
        this.bsAmt = value;
    }

}
