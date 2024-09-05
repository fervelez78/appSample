
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSSendMail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSSendMail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MessageText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EventCode" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Attached" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSSendMail", propOrder = {

})
public class SBSSendMail {

    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "CC", required = true)
    protected String cc;
    @XmlElement(name = "MessageText", required = true)
    protected String messageText;
    @XmlElement(name = "To", required = true)
    protected String to;
    @XmlElement(name = "EventCode")
    protected short eventCode;
    @XmlElement(name = "Attached", required = true)
    protected String attached;

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCC() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCC(String value) {
        this.cc = value;
    }

    /**
     * Gets the value of the messageText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the value of the messageText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageText(String value) {
        this.messageText = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the eventCode property.
     * 
     */
    public short getEventCode() {
        return eventCode;
    }

    /**
     * Sets the value of the eventCode property.
     * 
     */
    public void setEventCode(short value) {
        this.eventCode = value;
    }

    /**
     * Gets the value of the attached property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttached() {
        return attached;
    }

    /**
     * Sets the value of the attached property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttached(String value) {
        this.attached = value;
    }

}
