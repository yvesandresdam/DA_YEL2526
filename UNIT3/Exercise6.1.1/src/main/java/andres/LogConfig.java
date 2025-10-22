package andres;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogConfig {
    Logger logger;
    FileHandler fileHandler;

    public LogConfig(String filePath){
        // create a new logger
        createLogger();

        // create a new filehandler
        try {
            createFileHandler(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Optional simple logger
        fileHandler.setFormatter(new SimpleFormatter());

        // add the handler to the logger
        logger.addHandler(fileHandler);
    }

    // Creates the logger with a description name
    public void createLogger(){
        logger = Logger.getLogger("LoggerInfo");
        logger.setLevel(Level.INFO);
    }

    // Creates the file that handles the logger, with relative path
    public void createFileHandler(String filePath) throws IOException {
        try {
            fileHandler = new FileHandler(filePath, true);
        } catch (IOException e) {
            throw new IOException("La ruta es incorrecta", e);
        }
    }

    // Functions that creates logs with diferent level
    public void addLogInfo(String message) {
        logger.info(message);
    }

    public void addLogSevere(String message) {
        logger.severe(message);
    }

    public void addLogWarning(String message) {
        logger.warning(message);
    }
}
