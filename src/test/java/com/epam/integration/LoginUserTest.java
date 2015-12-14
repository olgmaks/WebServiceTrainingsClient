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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by OLEG on 14.12.2015.
 */
public class LoginUserTest extends TestBase {

    private static final Logger LOG = Logger.getLogger(LoginUserTest.class);

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
    public void testLoginPositive() {

        User user = Users.getDefaultUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.login(user);

        assertTrue(userServiceBO.isResponseSuccess(Messages.LOGIN_SUCCESS_MESSAGE));

        User userResult = userServiceBO.getUser();

        assertEquals(userResult.getEmail(), user.getEmail());
        assertEquals(userResult.getPassword(), user.getPassword());
        assertEquals(userResult.getFistName(), user.getFistName());
        assertEquals(userResult.getLastName(), user.getLastName());

    }

    @Test
    public void testLoginEmptyEmailNegative() {

        User user = Users.getEmptyEmailUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.login(user);
        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_EMAIL_FAULT_MESSAGE));
    }


    @Test
    public void testLoginEmptyPasswordNegative() {

        User user = Users.getEmptyPasswordUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.login(user);
        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_PASSWORD_FAULT_MESSAGE));
    }


    @Test
    public void testLoginNoExistUser() {

        User user = Users.getNoExistUser();

        LOG.info("PARAMS : " + user);
        userServiceBO.login(user);

        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_CREDENTIALS_FAULT_MESSAGE));

    }


}
