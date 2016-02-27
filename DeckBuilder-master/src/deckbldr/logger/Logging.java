package deckbldr.logger;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Shimingyan Yu
 */
public abstract class Logging {

    private static final String CONFIG_CLASS_PROPERTY = "java.util.logging.config.class";

    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());

    public static void init() {
        System.setProperty(CONFIG_CLASS_PROPERTY, LoggingConfig.class.getCanonicalName());
        LOGGER.info("Loading logger configuration class: " + System.getProperty(CONFIG_CLASS_PROPERTY));
        try {
            LogManager.getLogManager().readConfiguration();
            LOGGER.info("Logger configuration loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}