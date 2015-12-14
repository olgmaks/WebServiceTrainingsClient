package com.epam.unit.factory;

import com.epam.unit.servicersimpl.UserServiceRS;
import com.epam.unit.service.UserService;
import com.epam.unit.servicewsimpl.UserServiceImplService;

/**
 * Created by OLEG on 09.12.2015.
 */
public class Services {

    public static final String REST = "REST";
    public static final String SOAP = "SOAP";


    private static UserServiceRS userServiceRS;

    private static UserService userService;

    private Services() {
    }

    public static UserService getUserService(String param) {

        if (param.equals(REST)) {

            if (userServiceRS == null) {
                userServiceRS = new UserServiceRS();
            }

            return userServiceRS;


        } else if (param.equals(SOAP)) {

            if (userService == null) {
                userService = new UserServiceImplService().getUserServiceImplPort();
            }

            return userService;

        }

        throw new RuntimeException("Factory exception. No such method type");
    }
}
