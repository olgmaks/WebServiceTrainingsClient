package com.epam.integration;

import com.epam.base.TestBase;
import com.epam.unit.businesslogic.UserServiceBO;
import com.epam.unit.factory.Services;
import com.epam.unit.repository.Messages;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by OLEG on 14.12.2015.
 */
public class GetUsersByRoleNameTest extends TestBase {

    private static final Logger LOG = Logger.getLogger(GetUsersByRoleNameTest.class);

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
    public void testGetUsersByRoleNamePositive() {

        String role = "user";
        userServiceBO.getUsersByRoleName(role);

        assertTrue(userServiceBO.isResponseSuccess(String.format(Messages.GET_USERS_BY_ROLE_MESSAGE, role)));

        assertNotNull(userServiceBO.getUsers());

        assertTrue(userServiceBO.getUsers().size() > 0);

    }

    @Test
    public void testGetUserByRoleNameNegative() {

        String role = "userss";
        userServiceBO.getUsersByRoleName(role);

        assertTrue(userServiceBO.isResponseFault(String.format(Messages.INVALID_ROLE_NAME_FAULT_MESSAGE, role)));

    }


}
