package com.epam.unit.servicersimpl;

import com.epam.unit.modelweb.Response;
import com.epam.unit.service.UserService;

/**
 * Created by OLEG on 07.12.2015.
 */
public class UserServiceRS implements UserService {

    private UserServiceRSHTTPClient restClient;

    public  UserServiceRS () {

        restClient = new UserServiceRSHTTPClient();
    }

    public Response register(String email,
                             String password,
                             String firstName,
                             String lastName) {

        return restClient.register(email, password, firstName, lastName);
    }


    public Response delete(String id) {

        return restClient.deleteUserById(id);
    }


    public Response getByRoleName(String role) {

        return restClient.getUsersByRoleName(role);
    }


    public Response authorize(String email,
                              String password) {

        return restClient.authorize(email, password);
    }


    public Response getAll() {

        return restClient.getAllUsers();
    }


    public Response login(String email,
                          String password) {

        return restClient.login(email, password);
    }
}
