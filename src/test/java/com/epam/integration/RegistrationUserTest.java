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
public class RegistrationUserTest extends TestBase{

    private static final Logger LOG = Logger.getLogger(RegistrationUserTest.class);

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
    public void testRegisterPositive() {

        User user = Users.getUniqueUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.register(user);

        assertTrue(userServiceBO.isResponseSuccess(Messages.REGISTRATION_SUCCESS_MESSAGE));

        User userResult = userServiceBO.getUser();

        assertEquals(userResult.getEmail(), user.getEmail());
        assertEquals(userResult.getPassword(), user.getPassword());
        assertEquals(userResult.getFistName(), user.getFistName());
        assertEquals(userResult.getLastName(), user.getLastName());

        userServiceBO.login(userResult);

        assertTrue(userServiceBO.isResponseSuccess(Messages.LOGIN_SUCCESS_MESSAGE));
    }

    @Test
    public void testRegisterExistingUserNegative () {

        User user = Users.getDefaultUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.register(user);

        assertTrue(userServiceBO.isResponseFault(Messages.SUCH_USER_EXISTS_FAULT_MESSAGE));
    }

    @Test
    public void testRegisterEmptyEmailNegative () {

        User user = Users.getEmptyEmailUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.register(user);

        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_EMAIL_FAULT_MESSAGE));
    }

    @Test
    public void testRegisterEmptyPasswordNegative () {

        User user = Users.getEmptyPasswordUser();
        LOG.info("PARAMS : " + user);
        userServiceBO.register(user);

        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_PASSWORD_FAULT_MESSAGE));
    }


}
