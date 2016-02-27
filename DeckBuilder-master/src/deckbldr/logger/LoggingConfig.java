package deckbldr.logger;

import java.util.logging.*;

/**
 * @author Shimingyan Yu
 */
public class LoggingConfig {

    /**
     * The pattern for naming the output file.
     */
    private static final String LOG_FILE_PATTERN = "%t/DeckBuilder.log";

    /**
     * The maximum number of bytes to write to any one file.
     */
    private static final int BYTES_LIMIT = 10000000;

    /**
     * The number of files to use.
     */
    private static final int FILES_COUNT = 10;

    private static final Logger LOGGER = Logger.getLogger(LoggingConfig.class.getName());

    public LoggingConfig() {
        try {
            // Programmatic configuration
            Formatter formatter = new SimpleFormatter();

            final Handler fileHandler = new FileHandler(LOG_FILE_PATTERN, BYTES_LIMIT, FILES_COUNT);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(formatter);

            final Logger app = Logger.getLogger("");// Root logger
            if (app.getHandlers().length == 1) {
                Handler handler = app.getHandlers()[0];// Console Handler
                handler.setLevel(Level.ALL);
                handler.setFormatter(formatter);
                // Add FileHandler
                app.addHandler(fileHandler);
                app.setLevel(Level.ALL);

                LOGGER.info("Logging Handlers configured");
            }
        } catch (Exception e) {
            // The runtime won't show stack traces if the exception is thrown
            e.printStackTrace();
        }
    }

}