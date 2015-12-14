package com.epam.unit.repository;

import com.epam.unit.model.User;

/**
 * Created by OLEG on 14.12.2015.
 */
public class Users {

    private static User user;

    private static User emptyEmailUser;
    private static User emptyPasswordUser;
    private static User noExistUser;


    private Users() {
    }

    public static User getDefaultUser() {

        if (user == null) {
            user = new User();
            user.setEmail("dog4@gmail.com");
            user.setPassword("qwerty");
            user.setFistName("test4");
            user.setLastName("testLast4");
        }

        return user;
    }


    public static User getUniqueUser() {

        User user = new User();
        user.setEmail("email" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("qwerty");
        user.setFistName("test4");
        user.setLastName("testLast4");

        return user;
    }

    public static User getEmptyEmailUser() {

        if (emptyEmailUser == null) {
            emptyEmailUser = new User();
            emptyEmailUser.setEmail("");
            emptyEmailUser.setPassword("qwerty");
            emptyEmailUser.setFistName("test4");
            emptyEmailUser.setLastName("testLast4");
        }

        return emptyEmailUser;
    }


    public static User getEmptyPasswordUser() {

        if (emptyPasswordUser == null) {
            emptyPasswordUser = new User();
            emptyPasswordUser.setEmail("dog4@gmail.com");
            emptyPasswordUser.setPassword("");
            emptyPasswordUser.setFistName("test4");
            emptyPasswordUser.setLastName("testLast4");
        }

        return emptyPasswordUser;
    }

    public static User getNoExistUser() {

        if (noExistUser == null) {
            noExistUser = new User();
            noExistUser.setEmail("doasfasfg4@gmail.com");
            noExistUser.setPassword("asfsaf");
            noExistUser.setFistName("test4");
            noExistUser.setLastName("testLast4");
        }

        return noExistUser;
    }
}
