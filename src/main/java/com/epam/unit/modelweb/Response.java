
package com.epam.unit.modelweb;

import com.epam.unit.model.Role;
import com.epam.unit.model.User;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * <p>Java class for response complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://service.web.epam.com/}summaryFault"/>
 *           &lt;element ref="{http://service.web.epam.com/}summarySuccess"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element ref="{http://service.web.epam.com/}role"/>
 *           &lt;element ref="{http://service.web.epam.com/}user"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://service.web.epam.com/}role"/>
 *           &lt;element ref="{http://service.web.epam.com/}user"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
        "summaryFault",
        "summarySuccess",
        "roleOrUser"
})
public class Response {

    @XmlElement(namespace = "http://service.web.epam.com/")
    protected SummaryFault summaryFault;

    @XmlElement(namespace = "http://service.web.epam.com/")
    protected SummarySuccess summarySuccess;


    @XmlElements({
            @XmlElement(name = "role", namespace = "http://service.web.epam.com/", type = Role.class),
            @XmlElement(name = "user", namespace = "http://service.web.epam.com/", type = User.class)
    })
    protected List<Object> roleOrUser;


    public Response () {}

    /**
     * Gets the value of the summaryFault property.
     *
     * @return possible object is
     * {@link SummaryFault }
     */
    public SummaryFault getSummaryFault() {
        return summaryFault;
    }

    /**
     * Sets the value of the summaryFault property.
     *
     * @param value allowed object is
     *              {@link SummaryFault }
     */
    public void setSummaryFault(SummaryFault value) {
        this.summaryFault = value;
    }

    /**
     * Gets the value of the summarySuccess property.
     *
     * @return possible object is
     * {@link SummarySuccess }
     */
    public SummarySuccess getSummarySuccess() {
        return summarySuccess;
    }

    /**
     * Sets the value of the summarySuccess property.
     *
     * @param value allowed object is
     *              {@link SummarySuccess }
     */
    public void setSummarySuccess(SummarySuccess value) {
        this.summarySuccess = value;
    }


    public List<Object> getResults() {
//        if (roleOrUser == null) {
//            roleOrUser = new ArrayList<>();
//        }
        return this.roleOrUser;
    }

    public void setRoleOrUser(List<Object> roleOrUser) {
        this.roleOrUser = roleOrUser;
    }

    @Override
    public String toString() {
        return "Response{" +
                "summaryFault=" + summaryFault +
                ", summarySuccess=" + summarySuccess +
                ", roleOrUser=" + roleOrUser +
                '}';
    }
}
