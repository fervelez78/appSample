
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBTErrorNegocio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBTErrorNegocio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Codigo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Severidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBTErrorNegocio", propOrder = {

})
public class SBTErrorNegocio {

    @XmlElement(name = "Codigo")
    protected long codigo;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "Severidad", required = true)
    protected String severidad;

    /**
     * Gets the value of the codigo property.
     * 
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     */
    public void setCodigo(long value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the severidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeveridad() {
        return severidad;
    }

    /**
     * Sets the value of the severidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeveridad(String value) {
        this.severidad = value;
    }

}
