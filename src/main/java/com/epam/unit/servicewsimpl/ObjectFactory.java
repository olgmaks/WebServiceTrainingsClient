
package com.epam.unit.servicewsimpl;

import com.epam.unit.model.Role;
import com.epam.unit.model.User;
import com.epam.unit.modelweb.Response;
import com.epam.unit.modelweb.Summary;
import com.epam.unit.modelweb.SummaryFault;
import com.epam.unit.modelweb.SummarySuccess;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.web.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://service.web.epam.com/", "user");
    private final static QName _Role_QNAME = new QName("http://service.web.epam.com/", "role");
    private final static QName _SummarySuccess_QNAME = new QName("http://service.web.epam.com/", "summarySuccess");
    private final static QName _SummaryFault_QNAME = new QName("http://service.web.epam.com/", "summaryFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.web.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SummaryFault }
     * 
     */
    public SummaryFault createSummaryFault() {
        return new SummaryFault();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link SummarySuccess }
     * 
     */
    public SummarySuccess createSummarySuccess() {
        return new SummarySuccess();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Summary }
     * 
     */
    public Summary createSummary() {
        return new Summary();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.epam.com/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Role }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.epam.com/", name = "role")
    public JAXBElement<Role> createRole(Role value) {
        return new JAXBElement<Role>(_Role_QNAME, Role.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummarySuccess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.epam.com/", name = "summarySuccess")
    public JAXBElement<SummarySuccess> createSummarySuccess(SummarySuccess value) {
        return new JAXBElement<SummarySuccess>(_SummarySuccess_QNAME, SummarySuccess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.web.epam.com/", name = "summaryFault")
    public JAXBElement<SummaryFault> createSummaryFault(SummaryFault value) {
        return new JAXBElement<SummaryFault>(_SummaryFault_QNAME, SummaryFault.class, null, value);
    }

}
