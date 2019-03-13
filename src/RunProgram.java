package com.company;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class RunProgram {

	public static void main(String args[]) {
		TiltControl program = new TiltControl();
		program.executeTiltControl();


		// Change into do while Loop
		// Random speed will now be between 40 and 255 - since 40 is where wheels start to move,
	}
}












/**
 *
 * From the TiltControl - Will be needed for group intgeration
 * Still needs work
 *
 **/

//	/**
//	 * Function to set the value of the secondsToRecord variable
//	 *
//	 * @param secondsToRecord -> The amount of time Finch should record movement for
//	 */
//	public void setSecondsToRecord(int secondsToRecord) {
//		try {
//			this.secondsToRecord = secondsToRecord;
//			this.validateUserInput(secondsToRecord);
//		} catch	(Exception e) {
//			System.out.println("[*] INVALID INPUT: Pleas make sure input is an integer and between 1 and 20");
//		}
//	}
//
//	/**
//	 * Function to execute the main program
//	 * Requires the use of setSecondsToRecord() in order to function correctly
//	 *
//	 */
//	public void executeTiltControl() {
//		this.recordFinchMovement();
//		this.executeTiltCommands();
//		this.LOG.quitLogger();
//	}