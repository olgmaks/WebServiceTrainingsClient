package com.epam.unit.businesslogic;

import com.epam.unit.model.Role;
import com.epam.unit.model.User;
import com.epam.unit.modelweb.Response;
import com.epam.unit.modelweb.SummaryFault;
import com.epam.unit.modelweb.SummarySuccess;
import com.epam.unit.service.UserService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLEG on 14.12.2015.
 */
public class UserServiceBO {

    private static final Logger LOG = Logger.getLogger(UserServiceBO.class);

    private UserService userService;

    private Response response;

    public UserServiceBO(UserService userService) {
        this.userService = userService;
    }


    public void login(User user) {
        LOG.info("login user : " + user);
        response = userService.login(user.getEmail(), user.getPassword());
    }

    public void register(User user) {
        LOG.info("register user : " + user);
        response = userService.register(user.getEmail(), user.getPassword(), user.getFistName(), user.getLastName());
    }

    public void delete(long id) {
        LOG.info("delete user with id : " + id);
        String idString = String.valueOf(id);
        response = userService.delete(idString);
    }

    public void authorize(User user) {
        LOG.info("authorize user : " + user);
        response = userService.authorize(user.getEmail(), user.getPassword());
    }

    public void getUsersByRoleName(String role) {
        LOG.info("getUsersByRoleName role : " + role);
        response = userService.getByRoleName(role);
    }

    public void getAllUsers() {
        LOG.info("getAllUsers");
        response = userService.getAll();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return (List) response.getResults();
    }

    public User getUser() {
        return (User) response.getResults().get(0);
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles() {
        List<Role> roles = (List) response.getResults();
        List<String> strings = new ArrayList<>();

        for (Role role : roles) {
            strings.add(role.getName());
        }

        return strings;
    }


    public boolean isResponseSuccess(String expectedMessage) {

        if (response == null) {
            LOG.info("response == null");
            return false;
        }

        SummarySuccess summarySuccess = response.getSummarySuccess();

        if (summarySuccess == null) {
            LOG.info("summarySuccess == null");
            return false;
        }

        if (summarySuccess.getSuccessMessage() == null) {
            LOG.info("successMessage == null");
            return false;
        }

        LOG.info("message : \"" + summarySuccess.getSuccessMessage() + "\"");

        boolean result = summarySuccess.getSuccessMessage().equals(expectedMessage);

        if (!result) {
            LOG.info("EXPECTED : " + expectedMessage);
            LOG.info("ACTUAL : " + summarySuccess.getSuccessMessage());
        }


        return result;

    }

    public boolean isResponseFault(String expectedMessage) {

        if (response == null) {
            LOG.info("response == null");
            return false;
        }

        SummaryFault summaryFault = response.getSummaryFault();

        if (summaryFault == null) {
            LOG.info("summaryFault == null");
            return false;
        }

        if (summaryFault.getErrorMessage() == null) {
            LOG.info("errorMessage == null");
            return false;
        }

        LOG.info("message : \"" + summaryFault.getErrorMessage() + "\"");

        boolean result = summaryFault.getErrorMessage().equals(expectedMessage);

        if (!result) {
            LOG.info("EXPECTED : " + expectedMessage);
            LOG.info("ACTUAL : " + summaryFault.getErrorMessage());
        }


        return result;

    }


}
