/**
 * 
 */
package com.company;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * FinchLogger.java
 * 
 * Logs messages to a file
 * 
 * @author Hannan Saleemi
 *
 */
public class FinchLogger {
	
	private Logger logger = Logger.getLogger("MyLog");
	private FileHandler fileHandler;
	private String filename;
	private File logFile;
	private SimpleFormatter formatter;
	
	/**
	 * Initialisation of the Logger (Constructor) With Parameter
	 *
	 * Some setup has to be done in order for the logger to start
	 * It concatenates the CWD with "logfile.txt" (THIS METHOD IS PLATFROM INDEPENDANT)
	 * It then sets the logger to output to that file
	 * It then sets the formatter to format the logs into human readable messages
	 * It then toggles a property of the logger - so that logs don't appear in the console
	 * The message parameter is then logged to file
	 *
	 * @param filename - Passed into constructor, to customise filename
	 */
	public FinchLogger(String filename) {
		
		// Get Timestamp to append to logfile name
		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
		
		// Set Filename
		this.filename = String.format("%s-%s.txt", filename, timeStamp);
		
		// Logger setup / Init
		try {
			this.logFile = new File(System.getProperty("user.dir"), this.filename);
			this.fileHandler = new FileHandler(this.logFile.getPath());
			this.logger.addHandler(this.fileHandler);
			this.formatter = new SimpleFormatter();
			this.fileHandler.setFormatter(this.formatter);
			this.logger.setUseParentHandlers(false);
			this.logger.info("[*] Log File Successfully Initialised");
			this.logger.removeHandler(this.fileHandler);
		}catch (Exception e) {
			System.out.println("[*] Unable to Initialise Logger");
			e.printStackTrace();
		}
	}
	
	/**
	 * Function that logs a message with normal severity to file
	 * 
	 * @param msg - The message to log to file
	 */
	public void logToFile(String msg) {
		this.logger.addHandler(fileHandler);
		this.logger.info(msg);
		this.closeLogFile();
	}
	
	/**
	 * Function that logs a message with high severity to file
	 * 
	 * @param severity - If severity provided, if none, or not valid then set to info else severe
	 * @param msg - The message to log to file
	 */
	public void logToFile(String severity, String msg) {
		this.logger.addHandler(fileHandler);
		if (severity.toLowerCase() == "severe") {
			this.logger.severe(msg);
		}else {
			this.logger.info(msg);
		}
		this.closeLogFile();
	}
	
	/**
	 * Function to close log file after writing to unlock file
	 */
	private void closeLogFile() {
		this.logger.removeHandler(this.fileHandler);
	}
	
	/**
	 * Function to close the file at end of file
	 */
	public void quitLogger() {
		try {
			this.closeLogFile();
			this.fileHandler.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
