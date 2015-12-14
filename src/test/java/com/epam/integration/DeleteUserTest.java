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
public class DeleteUserTest extends TestBase {

    private static final Logger LOG = Logger.getLogger(DeleteUserTest.class);

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
    public void testDeleteUserPositive() {


        LOG.info("PARAMS : No params");
        userServiceBO.register(Users.getUniqueUser());
        User user = userServiceBO.getUser();

        userServiceBO.delete(user.getId());

        assertTrue(userServiceBO.isResponseSuccess(String.format(Messages.USER_DELETED_SUCCESS_MESSAGE, user.getId())));

    }

    @Test
    public void testDeleteInvalidIdNegative() {
        long id = -1;
        LOG.info("PARAMS : id=" + id);
        userServiceBO.delete(id);

        assertTrue(userServiceBO.isResponseFault(Messages.INVALID_INPUT_DATA_TYPE_FOR_ID + id));

    }


    @Test
    public void testDeleteNoExistUserNegative() {
        long id = 15684;
        LOG.info("PARAMS : id=" + id);
        userServiceBO.delete(id);

        assertTrue(userServiceBO.isResponseFault(Messages.USER_NOT_DELETED_FAULT_MESSAGE));

    }


}
