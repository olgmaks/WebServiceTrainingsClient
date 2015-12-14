package com.epam.base;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

/**
 * Created by OLEG on 14.12.2015.
 */

public class TestBase {

    private static final Logger LOG = Logger.getLogger(TestBase.class);
    private static final String DIV = "-------------------------------------------------------";

    @BeforeTest
    public static void setUp(ITestContext testContext) {

        System.out.println(DIV);
        System.out.println("BEFORE SUITE : " + testContext.getName());
        System.out.println(DIV);
    }

    @AfterTest
    public static void tearDown() {

        System.out.println(DIV);
        System.out.println("AFTER SUITE");
        System.out.println(DIV);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {

        System.out.println(DIV);
        System.out.println("TEST : " + method.getName());
        System.out.println(DIV);
        LOG.info("started");
    }


    @AfterMethod
    public void afterMethod(ITestResult result, Method method) {

        if (result.getStatus() == ITestResult.FAILURE)
            LOG.error("failed " + method + "\n");
        else
            LOG.info("passed\n");

        System.out.println(DIV);
    }
}
