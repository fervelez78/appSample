
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSSecurityQuestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSSecurityQuestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="AnswerType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Regex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValueList" type="{http://uy.com.dlya.bantotal/BTSOA/}ValueList"/>
 *         &lt;element name="QuestionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QuestionID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="QuestionDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSSecurityQuestion", propOrder = {

})
public class SBSSecurityQuestion {

    @XmlElement(name = "AnswerType", required = true)
    protected String answerType;
    @XmlElement(name = "Regex", required = true)
    protected String regex;
    @XmlElement(name = "ValueList", required = true)
    protected ValueList valueList;
    @XmlElement(name = "QuestionType", required = true)
    protected String questionType;
    @XmlElement(name = "QuestionID")
    protected short questionID;
    @XmlElement(name = "QuestionDesc", required = true)
    protected String questionDesc;

    /**
     * Gets the value of the answerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswerType() {
        return answerType;
    }

    /**
     * Sets the value of the answerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswerType(String value) {
        this.answerType = value;
    }

    /**
     * Gets the value of the regex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Sets the value of the regex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegex(String value) {
        this.regex = value;
    }

    /**
     * Gets the value of the valueList property.
     * 
     * @return
     *     possible object is
     *     {@link ValueList }
     *     
     */
    public ValueList getValueList() {
        return valueList;
    }

    /**
     * Sets the value of the valueList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueList }
     *     
     */
    public void setValueList(ValueList value) {
        this.valueList = value;
    }

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

    /**
     * Gets the value of the questionDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionDesc() {
        return questionDesc;
    }

    /**
     * Sets the value of the questionDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionDesc(String value) {
        this.questionDesc = value;
    }

}
