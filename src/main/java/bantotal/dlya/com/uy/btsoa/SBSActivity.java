
package bantotal.dlya.com.uy.btsoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sBSActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sBSActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ActivityTypeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ActivityID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ActivityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sBSActivity", propOrder = {

})
public class SBSActivity {

    @XmlElement(name = "ActivityTypeID")
    protected long activityTypeID;
    @XmlElement(name = "ActivityID")
    protected int activityID;
    @XmlElement(name = "ActivityName", required = true)
    protected String activityName;

    /**
     * Gets the value of the activityTypeID property.
     * 
     */
    public long getActivityTypeID() {
        return activityTypeID;
    }

    /**
     * Sets the value of the activityTypeID property.
     * 
     */
    public void setActivityTypeID(long value) {
        this.activityTypeID = value;
    }

    /**
     * Gets the value of the activityID property.
     * 
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * Sets the value of the activityID property.
     * 
     */
    public void setActivityID(int value) {
        this.activityID = value;
    }

    /**
     * Gets the value of the activityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Sets the value of the activityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityName(String value) {
        this.activityName = value;
    }

}
