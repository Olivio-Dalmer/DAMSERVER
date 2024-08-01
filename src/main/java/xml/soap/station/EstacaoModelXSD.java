//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.07.24 at 02:30:55 PM WAT 
//


package xml.soap.station;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstacaoModelXSD complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstacaoModelXSD"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="premio" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="activeState" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="qtdDocasTotais" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="qtdDocasLivres" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstacaoModelXSD", propOrder = {
    "id",
    "nome",
    "premio",
    "latitude",
    "longitude",
    "activeState",
    "qtdDocasTotais",
    "qtdDocasLivres",
    "dateCreated",
    "dateUpdated"
})
public class EstacaoModelXSD {

    protected long id;
    @XmlElement(required = true)
    protected String nome;
    protected double premio;
    protected double latitude;
    protected double longitude;
    protected boolean activeState;
    protected int qtdDocasTotais;
    protected int qtdDocasLivres;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateUpdated;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the premio property.
     * 
     */
    public double getPremio() {
        return premio;
    }

    /**
     * Sets the value of the premio property.
     * 
     */
    public void setPremio(double value) {
        this.premio = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the activeState property.
     * 
     */
    public boolean isActiveState() {
        return activeState;
    }

    /**
     * Sets the value of the activeState property.
     * 
     */
    public void setActiveState(boolean value) {
        this.activeState = value;
    }

    /**
     * Gets the value of the qtdDocasTotais property.
     * 
     */
    public int getQtdDocasTotais() {
        return qtdDocasTotais;
    }

    /**
     * Sets the value of the qtdDocasTotais property.
     * 
     */
    public void setQtdDocasTotais(int value) {
        this.qtdDocasTotais = value;
    }

    /**
     * Gets the value of the qtdDocasLivres property.
     * 
     */
    public int getQtdDocasLivres() {
        return qtdDocasLivres;
    }

    /**
     * Sets the value of the qtdDocasLivres property.
     * 
     */
    public void setQtdDocasLivres(int value) {
        this.qtdDocasLivres = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the value of the dateUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateUpdated(XMLGregorianCalendar value) {
        this.dateUpdated = value;
    }

}
