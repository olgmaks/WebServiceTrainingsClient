package com.epam.unit;

import com.epam.base.TestBase;
import com.epam.unit.factory.Services;
import com.epam.unit.model.User;
import com.epam.unit.modelweb.Response;
import com.epam.unit.reporter.Log;
import com.epam.unit.service.UserService;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

/**
 * Unit test for simple App.
 */
public class UserServiceTest extends TestBase {

    private static final Log LOG = Log.getLoggerForClass(UserServiceTest.class);

    public static final String USER_EMAIL = "dog4@gmail.com";
    public static final String USER_PASSWORD = "qwerty";
    private static UserService userService;


    @Parameters({"serviceType"})
    @BeforeClass
    public static void setUp(@Optional("SOAP") String serviceType) {
        LOG.info("setting up remote web service");
        userService = Services.getUserService(serviceType);
        LOG.info("Web service type : " + serviceType);
        LOG.info("connection to remote web service successful\n");

    }



    @Test
    public void testLoginUser() {


        LOG.info(String.format("params : email = %s, password = %s", USER_EMAIL, USER_PASSWORD));



        Response response = userService.login(USER_EMAIL, USER_PASSWORD);

        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "Login successful");

        List<Object> results = response.getResults();

        assertNotNull(results);

        Object obj = results.get(0);

        assertNotNull(obj);
        assertTrue(obj instanceof User);

        User user = (User) obj;

        assertEquals(user.getEmail(), USER_EMAIL);
        assertEquals(user.getPassword(), USER_PASSWORD);


    }


    @Test
    public void testRegisterUser() {

        String email = "jik" + System.currentTimeMillis() + "@yandex.ru";
        String password = "qwerty";
        String firstName = "testFirstName";
        String lastName = "testLastName";

        LOG.info(String.format("params : email=%s, password=%s, firstName=%s, lastName=%s", email, password, firstName, lastName));

        Response response = userService.register(email, password, firstName, lastName);

        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "Registration successful");
        assertNotNull(response.getResults());
        assertFalse(response.getResults().isEmpty());

        User user = (User) response.getResults().get(0);

        assertNotNull(user);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getFistName(), firstName);
        assertEquals(user.getLastName(), lastName);
        assertEquals(user.getPassword(), password);

    }

    @Test
    public void testDeleteUser() {

        User user = (User) userService.register("email" + System.currentTimeMillis() + "@gmail.com", USER_PASSWORD, "someFirstName", "someLastName").getResults().get(0);

        String id = String.valueOf(user.getId());

        LOG.info("params : id=" + id);

        if (id == null) return;

        Response response = userService.delete(id);

        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertNotNull(response.getSummarySuccess());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "User has been deleted successfully, User_ID = " + id);

    }

    private String getIdUserToBeDeleted() {

        String id;

        try {

            List<Object> users = userService.getAll().getResults();

            if (users == null || users.isEmpty())
                return null;

            User user = (User) users.get(new Random().nextInt(users.size() - 1));

            id = String.valueOf(user.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    @Test
    public void testGetAllUser() {

        Response response = userService.getAll();

        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertNotNull(response.getSummarySuccess());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "Get all users successful");
        assertNotNull(response.getResults());

    }

    @Test
    public void testGetUsersByRoleName() {

        String role = "admin";
        LOG.info("params : role=" + role);

        Response response = userService.getByRoleName(role);
        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertNotNull(response.getSummarySuccess());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "Users for role " + role + " successfully found");
        assertNotNull(response.getResults());

    }


    @Test
    public void testAuthorizeUser() {

        Response response = userService.authorize(USER_EMAIL, USER_PASSWORD);
        LOG.info(String.format("params : email = %s, password = %s", USER_EMAIL, USER_PASSWORD));

        assertNotNull(response);
        assertNull(response.getSummaryFault());
        assertNotNull(response.getSummarySuccess());
        assertEquals(response.getSummarySuccess().getSuccessMessage(), "User has been authorised successful");

    }


}
