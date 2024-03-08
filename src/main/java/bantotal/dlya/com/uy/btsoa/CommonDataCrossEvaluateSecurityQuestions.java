
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Btinreq" type="{http://uy.com.dlya.bantotal/BTSOA/}sBTInReq"/>
 *         &lt;element name="FirmID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Answers" type="{http://uy.com.dlya.bantotal/BTSOA/}Answers"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "btinreq",
    "firmID",
    "channel",
    "language",
    "answers"
})
@XmlRootElement(name = "CommonDataCross.EvaluateSecurityQuestions")
public class CommonDataCrossEvaluateSecurityQuestions {

    @XmlElement(name = "Btinreq", required = true)
    protected SBTInReq btinreq;
    @XmlElement(name = "FirmID")
    protected short firmID;
    @XmlElement(name = "Channel", required = true)
    protected String channel;
    @XmlElement(name = "Language", required = true)
    protected String language;
    @XmlElement(name = "Answers", required = true)
    protected Answers answers;

    /**
     * Gets the value of the btinreq property.
     * 
     * @return
     *     possible object is
     *     {@link SBTInReq }
     *     
     */
    public SBTInReq getBtinreq() {
        return btinreq;
    }

    /**
     * Sets the value of the btinreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBTInReq }
     *     
     */
    public void setBtinreq(SBTInReq value) {
        this.btinreq = value;
    }

    /**
     * Gets the value of the firmID property.
     * 
     */
    public short getFirmID() {
        return firmID;
    }

    /**
     * Sets the value of the firmID property.
     * 
     */
    public void setFirmID(short value) {
        this.firmID = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the answers property.
     * 
     * @return
     *     possible object is
     *     {@link Answers }
     *     
     */
    public Answers getAnswers() {
        return answers;
    }

    /**
     * Sets the value of the answers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Answers }
     *     
     */
    public void setAnswers(Answers value) {
        this.answers = value;
    }

}
