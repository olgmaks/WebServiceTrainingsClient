
package com.epam.unit.modelweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for summaryFault complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="summaryFault">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.web.epam.com/}summary">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "summaryFault", propOrder = {
        "errorMessage"
})
public class SummaryFault
        extends Summary {

    protected String errorMessage;

    /**
     * Gets the value of the errorMessage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }


    @Override
    public String toString() {
        return "SummaryFault{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
