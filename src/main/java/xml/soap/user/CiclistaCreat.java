//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.07.24 at 02:30:55 PM WAT 
//


package xml.soap.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userCreat" type="{http://user.soap.xml}UserCreat"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userCreat"
})
@XmlRootElement(name = "CiclistaCreat")
public class CiclistaCreat {

    @XmlElement(required = true)
    protected UserCreat userCreat;

    /**
     * Gets the value of the userCreat property.
     * 
     * @return
     *     possible object is
     *     {@link UserCreat }
     *     
     */
    public UserCreat getUserCreat() {
        return userCreat;
    }

    /**
     * Sets the value of the userCreat property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserCreat }
     *     
     */
    public void setUserCreat(UserCreat value) {
        this.userCreat = value;
    }

}
