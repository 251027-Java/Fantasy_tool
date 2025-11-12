package com.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLogger {
    private static GlobalLogger instance;
    private Logger actualLogger; // e.g., an instance of java.util.logging.Logger or org.slf4j.Logger

    private GlobalLogger() {
        // Initialize your actual logging framework here
        // e.g., actualLogger = Logger.getLogger(GlobalLogger.class.getName());
        actualLogger = LoggerFactory.getLogger(GlobalLogger.class);

    }

    public static GlobalLogger getLog() {
        if (instance == null) {
            instance = new GlobalLogger();
        }
        return instance;
    }

    // Methods to delegate to the actual logger
    public void info(String message) {
        actualLogger.info(message);
    }

    public void error(String message) {
        actualLogger.error(message); 
    }

    public void error(String message, Throwable t) {
        actualLogger.error(message, t); 
    }

    public void debug(String message) {
        actualLogger.debug(message);
    }
    // ... other logging methods
}