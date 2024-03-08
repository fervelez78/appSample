
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSSecurityAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSSecurityAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="QuestionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnswerText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QuestionID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSSecurityAnswer", propOrder = {

})
public class SBSSecurityAnswer {

    @XmlElement(name = "QuestionType", required = true)
    protected String questionType;
    @XmlElement(name = "AnswerText", required = true)
    protected String answerText;
    @XmlElement(name = "QuestionID")
    protected short questionID;

    /**
     * Gets the value of the questionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * Sets the value of the questionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionType(String value) {
        this.questionType = value;
    }

    /**
     * Gets the value of the answerText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Sets the value of the answerText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswerText(String value) {
        this.answerText = value;
    }

    /**
     * Gets the value of the questionID property.
     * 
     */
    public short getQuestionID() {
        return questionID;
    }

    /**
     * Sets the value of the questionID property.
     * 
     */
    public void setQuestionID(short value) {
        this.questionID = value;
    }

}
