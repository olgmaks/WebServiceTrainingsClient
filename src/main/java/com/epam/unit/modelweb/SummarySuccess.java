
package com.epam.unit.modelweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for summarySuccess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="summarySuccess">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.web.epam.com/}summary">
 *       &lt;sequence>
 *         &lt;element name="successMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "summarySuccess", propOrder = {
    "successMessage"
})
public class SummarySuccess extends Summary {

    protected String successMessage;


    public SummarySuccess () {}

    public SummarySuccess(String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * Gets the value of the successMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the value of the successMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessMessage(String value) {
        this.successMessage = value;
    }


    @Override
    public String toString() {
        return "SummarySuccess{" +
                "successMessage='" + successMessage + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SummarySuccess that = (SummarySuccess) o;

        return !(successMessage != null ? !successMessage.equals(that.successMessage) : that.successMessage != null);

    }

    @Override
    public int hashCode() {
        return successMessage != null ? successMessage.hashCode() : 0;
    }
}
