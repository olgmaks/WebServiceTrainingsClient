package com.epam.unit.reporter;

import org.apache.log4j.Logger;
import org.testng.Reporter;

/**
 * Created by Oleh_Maksymuk on 12/14/2015.
 */
public class Log extends Reporter{

    private Logger logger;
    private Class clazz;

    private Log () {}

    public static Log getLoggerForClass (Class clazz) {

        Log log = new Log();
        log.setClazz(clazz);
        log.setLogger(Logger.getLogger(clazz));

        return log;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void info (String message) {
        Reporter.log(message);
        logger.info(message);
    }
}
