package org.automationtest.CustomLogger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {

    private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
    private static final ConsoleHandler CONSOLE_HANDLER = new ConsoleHandler();

    static {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        CONSOLE_HANDLER.setLevel(Level.ALL);
        LOGGER.addHandler(CONSOLE_HANDLER);
    }

    public static void logInfo(String message) {
        LOGGER.log(Level.INFO, message);
    }
    public static void logWarning(String message)
    {
        LOGGER.log(Level.WARNING, message);
    }
}
