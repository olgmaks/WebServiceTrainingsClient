package com.epam.integration;

import com.epam.base.TestBase;
import com.epam.unit.businesslogic.UserServiceBO;
import com.epam.unit.factory.Services;
import com.epam.unit.model.User;
import com.epam.unit.repository.Messages;
import com.epam.unit.repository.Users;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by OLEG on 14.12.2015.
 */
public class AuthorizeUserTest extends TestBase {

    private static final Logger LOG = Logger.getLogger(AuthorizeUserTest.class);

    private static UserServiceBO userServiceBO;


    @Parameters({"serviceType"})
    @BeforeClass
    public static void setUp(String serviceType) {
        LOG.info("setting up remote web service");
        userServiceBO = new UserServiceBO(Services.getUserService(serviceType));
        LOG.info("Web service type : " + serviceType);
        LOG.info("connection to remote web service successful\n");

    }

    @Test
    public void testAuthorizePositive() {

        User user = Users.getDefaultUser();
        LOG.info("PARAMS : " + user);

        userServiceBO.authorize(user);
        assertTrue(userServiceBO.isResponseSuccess(Messages.AUTHORISATION_SUCCESS_MESSAGE));

        assertTrue(userServiceBO.getRoles().contains("user"));
    }


    @Test
    public void testAuthorizeEmptyEmailNegative() {

        User user = Users.getEmptyEmailUser();
        LOG.info("PARAMS : " + user);

        userServiceBO.authorize(user);
        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_EMAIL_FAULT_MESSAGE));
    }

    @Test
    public void testAuthorizeEmptyPasswordNegative() {

        User user = Users.getEmptyPasswordUser();
        LOG.info("PARAMS : " + user);

        userServiceBO.authorize(user);
        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_PASSWORD_FAULT_MESSAGE));
    }

    @Test
    public void testAuthorizeNoExistNegative() {

        User user = Users.getNoExistUser();
        LOG.info("PARAMS : " + user);

        userServiceBO.authorize(user);
        assertTrue(userServiceBO.isResponseSuccess(Messages.AUTHORISATION_GUEST_MESSAGE));
    }

}
