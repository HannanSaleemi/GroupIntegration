package com.company;

/*
 * BEGIN IMPORTS
 */
import java.awt.Color;
import java.io.File;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/*
 * END IMPORTS
 */

/**
 * TiltControl.java
 * Controlling the Finch bu Tilting the Finch
 * Assignment 2 for CS1701
 * Author:  Hannan Saleemi
 */

public class TiltControl {


	private ArrayList<Character> finchMovement;
	private int secondsToRecord;
	private Finch finchBot;
	private FinchLogger LOG;
	
	
	public TiltControl(){
		this.finchMovement = new ArrayList<Character>();
		this.finchBot = new Finch();
		this.LOG = new FinchLogger("logfile");		
	}


	/**
	 * Function to execute the main program
	 * Requires the use of setSecondsToRecord() in order to function correctly
	 *
	 */
	public void executeTiltControl() {
		this.secondsToRecord = this.getUserInput();
		this.recordFinchMovement();
		this.executeTiltCommands();
		this.LOG.quitLogger();
	}
	
	
	/**
	 * Function that gets User's Input and validates it
	 * 
	 * Gets the user input
	 * If user's input is not an integer - ask for input again
	 * If user's input is not in valid range (1-20) - ask for input again
	 * Validation is done by the validateUserInput() function
	 * 
	 * NOTE: DELETE note once done: input.close() -> Complier warning for resource leak - adding this got rid of it (Affected Line was 74 ish)
	 * 
	 * @return userInput -> The Integer between 1 and 20, which represents the number of seconds to record for
	 */
	public int getUserInput() {
		this.finchBot.setLED(Color.ORANGE);
		Scanner input = new Scanner(System.in);
		int userInput = -1;
		boolean inputValid = false;
		
		do {
			System.out.println("[*] Input the number of seconds to record for (Between 1 and 20 seconds):");
			
			try {
				userInput = Integer.parseInt(input.nextLine());
				inputValid = this.validateUserInput(userInput);
				
				if (!inputValid) {
					this.LOG.logToFile(String.format("[*] Invalid User Input: %s", userInput));
					System.out.println("[*] INVALID INPUT: Please eneter an integer between 1 and 20:");
					continue;
				}

			} catch (Exception e) {
				this.LOG.logToFile("severe", String.format("[*] NaN detected in User Input. Exception: %s Input: %d", e, userInput));
				System.out.println("[*] INVALID INPUT: The input was not an integer. Please try again:");
				userInput = -1;
			}
		} while (!inputValid);
		
		input.close();
		return userInput;
	}

	
	/**
	 * Validation function for user input
	 * 
	 * Only gets to this function if input is an integer
	 * Checks if number is between 1 and 20
	 * 
	 * @param numberToValidate -> Number to validate
	 * @return true/false -> Based on whether number is valid or not
	 */
	public boolean validateUserInput(int numberToValidate) {
		return (numberToValidate >= 1 && numberToValidate <= 20) ? true : false;
	}
	
	
	/**
	 * Function to record Finch movement every 500ms in finchMovement arraylist
	 * 
	 * Set LED to red to show beginning
	 * Depending on the beak position, the appropriate command is added to arraylist
	 * Flash Red and White to show recording finished
	 */
	public void recordFinchMovement() {
		
		this.finchBot.setLED(Color.RED);
		
		System.out.println("[*] Finch recording begins in 2 seconds...");
		this.finchBot.sleep(2000);
		System.out.println("[*] Recording...");
		
		int doubleSeconds = this.secondsToRecord * 2;
		while (doubleSeconds != 0) {
			
			this.LOG.logToFile(String.format("[*] 500ms Intervals Left: %s", doubleSeconds));
			
			if (finchBot.isBeakUp()) {
				this.appendRecordedDataToArray('F', "[*] Forward Detected");
			}else if (finchBot.isBeakDown()) {
				this.appendRecordedDataToArray('B', "[*] Backwards Detected");
			}else if (finchBot.isLeftWingDown()) {
				this.appendRecordedDataToArray('L', "[*] Left Detected");
			}else if (finchBot.isRightWingDown()) {
				this.appendRecordedDataToArray('R', "[*] Right Detected");
			}else if (finchBot.isFinchLevel() || finchBot.isFinchUpsideDown()) {
				this.appendRecordedDataToArray('S', "[*] STOP Detected");
			}else {
				this.appendRecordedDataToArray('S', "[*] No Sensors Resulting to true, Defaulting to Stop.");
			}
			
			this.finchBot.sleep(500);
			doubleSeconds -= 1;
			
		}
		
		this.finchBot.buzz(1000, 1000);
		FinchHelper.flashLEDsForTwoSeconds(finchBot, 255, 0, 0);
//		this.finchBot.setLED(Color.WHITE);
//		this.finchBot.sleep(500);
//		this.finchBot.setLED(255, 0, 0, 500);
		
		this.LOG.logToFile("[*] Recording Complete");
		System.out.println("[*] Recording Complete");
		
	}
	
	
	/**
	 * Function to append commands to end of arraylist
	 * 
	 * Log message and output registered command to user
	 * @param data -> The command to add to the arraylist
	 * @param logMessage -> The Message to save to logfile and output to screen
	 */
	public void appendRecordedDataToArray(Character data, String logMessage) {
		this.LOG.logToFile(logMessage);
		System.out.println(logMessage);
		this.finchMovement.add(data);
	}

	/**
	 * Function that executes the previously recorded commands with random wheel speeds
	 */
	public void executeTiltCommands() {
		
		System.out.println("[*] Finch will start moving in 2 seconds...");
		FinchHelper.flashLEDsForTwoSeconds(finchBot,255, 165, 0);
//		this.finchBot.setLED(255, 165, 0, 500);
//		this.finchBot.setLED(255, 255, 255, 500);
//		this.finchBot.setLED(255, 165, 0, 500);
//		this.finchBot.setLED(255, 255, 255, 500);
		this.LOG.logToFile("[*] Beginning Playback");
		
		this.finchBot.setLED(Color.GREEN);
		
		int wheelSpeed = 0;
		int lowerWheelSpeed = 0;
		
		for (char command: finchMovement) {
			
			wheelSpeed = this.generateRandomWheelSpeed(255);

			switch(command) {
				case 'S':
					this.LOG.logToFile(String.format("[*] Command: %s, Stopping Finch", command));
					break;
				case 'F':
					this.LOG.logToFile(String.format("[*] Command: %s, Moving Finch Forward, Wheel Speeds: %d", command, wheelSpeed));
					this.finchBot.setWheelVelocities(wheelSpeed, wheelSpeed, 500);
					break;
				case 'B':
					this.LOG.logToFile(String.format("[*] Command: %s, Moving Finch Backwards, Wheel Speeds: %d", command, wheelSpeed));
					this.finchBot.setWheelVelocities(wheelSpeed*-1, wheelSpeed*-1, 500);
					break;
				case 'L':
					lowerWheelSpeed = wheelSpeed*-1;
					this.LOG.logToFile(String.format("[*] Command: %s, Moving Finch Left, Wheel Speeds: %d %d", command, wheelSpeed, lowerWheelSpeed));
					this.finchBot.setWheelVelocities(lowerWheelSpeed, wheelSpeed, 500);
					break;
				case 'R':
					lowerWheelSpeed = wheelSpeed*-1;
					this.LOG.logToFile(String.format("[*] Command: %s, Moving Finch Right, Wheel Speeds: %d %d", command, lowerWheelSpeed, wheelSpeed));
					this.finchBot.setWheelVelocities(wheelSpeed, lowerWheelSpeed, 500);
					break;
				default:
					this.LOG.logToFile(String.format("[*] Command: %s, Command doesn't match SWITCH cases", command));
			}
			
			finchBot.buzz(1000, 200);
			finchBot.sleep(500);
		}
		System.out.println("Playback Complete!");
		
	}

	/**
	 * Generates a random number between 40 (fixed lower limit) and the given limit
	 *
	 * @param limit -> The upper limit of number generation
	 * @return int -> Contains the randomly generated number
	 */
	public static int generateRandomWheelSpeed(int limit) {
		Random rand = new Random();
		int lowLimit = 40;
		int highLimit = 256;
		return rand.nextInt(highLimit - lowLimit) + lowLimit;
	}

	/**
	 * Flashes colour provided for 2 seconds on given Finch
	 *
	 * @param r -> The Red Value of the colour
	 * @param g -> The Green value of the colour
	 * @param b -> The Blue value of the colour
	 */
	public void flashLEDsForTwoSeconds(int r, int g, int b) {
		this.finchBot.setLED(r, g, b, 500);
		this.finchBot.setLED(255, 255, 255, 500);
		this.finchBot.setLED(r, g, b, 500);
		this.finchBot.setLED(255, 255, 255, 500);
	}
	
}