package mvc;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogfileManager {

	private static Logger logger = Logger.getLogger("diktat.Log");
	private static Handler fileHandler;
	private static Formatter formatter;

	public static void init() {
        logger.setLevel(Level.INFO);
        
        try {
        	fileHandler = new FileHandler(Einstellungen.getPathToPropertyFile() + "/application_log.txt", 10000, 1, true);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);	
	}
	
	public static void logInfo(String message) {  
        logger.info(message);
	}
	
	public static void logWarn(String message) {  
        logger.warning(message);
	}
}
