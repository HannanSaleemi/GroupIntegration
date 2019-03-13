
import java.awt.Color;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class MAIN {
static int numObjEncountered = 0;
	
	protected static Finch myFinch = new Finch();
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Finch Connected");
		System.out.println();
		System.out.println();
		
		System.out.println("Welcome");
		System.out.println("Please make sure to place finch level on the floor");
		
		Boolean playGame = true;
		
		while (playGame == true)
		// allows the user to play the game again
		{
			
			while (myFinch.isBeakUp())
				//when the finch beak is up, this message will keep on printing
			{
				
				System.out.println("The game will not start until you place the finch level on the floor");
				Thread.sleep(2000);
				//waits for 2 seconds
			}
			
			int userChoice = 0;
			//by setting it initialy to 0
			//it will make sure to run the loop at least once
			
			while (!(userChoice == 1 || userChoice == 2))
				//will keep printing until the user chooses a valid option
			{
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				
				System.out.println("Type 1 and press enter for Curious Finch");
				System.out.println("Type 2 and press enter for Scaredy Finch");
				
				
				userChoice = 0;
				
				Scanner reader = new Scanner(System.in);
				try
				{
				 userChoice = reader.nextInt();
				// Scans the next token of the input as an int.
				}
				catch (InputMismatchException e)
				//if the user inputs something else other than an integer
				{
					System.out.println("INVALID INPUT");
					userChoice = 0;
					//allows the loop to run again
				}
				
				

			}
			
			
			String modeRan;
			
			long durationTimerStart = System.currentTimeMillis();
			//initialises and starts the timer
			//restarts it if the user chooses to play again
			
			if (userChoice == 1)
			{
				modeRan = "Curious Finch";
				numObjEncountered = 0;
				//resets the score when user decides to play the game again
				CuriousFinch CuriousFinch = new CuriousFinch();
				CuriousFinch.CuriousFinch();
				//calls CuriousFinch method in CuriousFinch class
			}
			else
			{
				modeRan = "Scaredy Finch";
				numObjEncountered = 0;
				//resets the score when user decides to play the game again
				ScaredyFinch ScaredyFinch = new ScaredyFinch();
				ScaredyFinch.ScaredyFinch();
				//calls ScaredyFinch method in CuriousFinch class
			}
			playGame = false;
			
			long durationTimerEnd = System.currentTimeMillis();
			//stops durationtimer
			
			long durationTimer = (durationTimerEnd - durationTimerStart);
			//calculates the duration of the game in milliseconds
			
			LOGBK(modeRan,durationTimer,numObjEncountered);
			//calls method LOGBK
			//asks user if he wants to see the LOG and displays it
			
			System.out.println();
			System.out.println();
			
			System.out.println("Would you like to play again");
			System.out.println("Enter 1 to play again.");
			System.out.println("Enter 2 to end.");
			int userplayGame = 0;
			while (!(userplayGame == 1 || userplayGame == 2))
				//while it userplayGame is NOT 1 or 2
			{
				Scanner reader2 = new Scanner(System.in);
				userplayGame = 0;
				try
				{
				userplayGame = reader2.nextInt();
				// Scans the next token of the input as an int.
				}
				catch (InputMismatchException e)
				//if the user inputs a character
				{
					System.out.println();
					System.out.println();
					
					System.out.println("INVALID INPUT");
					System.out.println("Enter 1 to play again.");
					System.out.println("Enter 2 to end.");
					userplayGame = 3;
					//allows the loop to run again
				}
				
				if (userplayGame == 1)
				{
					playGame = true;
				}
				else if (userplayGame == 2)
				{
					System.out.println("playGame set to false");
					playGame = false;
					//This will stop the playGame loop	
				}
				else if (userplayGame == 0)
				{
					System.out.println("Please enter a valid input");
					System.out.println("Choose between 1 or 2");
					System.out.println("1 to play again and 2 to quit");
					//userplayGame is still 0 and therefore loop will run again and 
					// get user input
				}
			
			
			
			
			}
			
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("GAME OVER");
		
		
	}
	
	public static void LOGBK(String modeRan, long durationTimer, int numObjEncountered)
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		myFinch.setLED(Color.BLUE);
		System.out.println("GAME OVER. Would you like to see the log?");
		String SeeLog = null;
		
		while (SeeLog == null || SeeLog.isEmpty())
		{
			System.out.println("Type 1 and enter to see the log");
			System.out.println("Type 2 and enter to end this game");
			
			int userInput = 0;
			Scanner reader2 = new Scanner(System.in);
			try 
			{
				userInput = reader2.nextInt();
				// Scans the next token of the input as an int.		
			}
			catch (InputMismatchException e)
			//if user inputs anything else apart from an integer
			{
				System.out.println();
				System.out.println();
				System.out.println("Invalid input");
				SeeLog = null;
				// allows the loop to run again
			}
					
		
			if (userInput == 1)
			{
				SeeLog = "y";
				//yes to see the log
			}
			else if (userInput == 2 )
			{
				SeeLog = "n";
				//no to see the log
			}
			else
			{
				System.out.println();
				System.out.println();
				System.out.println("enter a valid input");
				// loop runs again as SeeLog is still null
			}
			
			
		}
		
		
		if (SeeLog == "y")
		{
			System.out.println();
			System.out.println();
			
			
			System.out.println("LOG");
			System.out.println("Mode Ran: " + modeRan);
			
			
			
			long DTimer = durationTimer/1000;
			//converts milliseconds to seconds
			int i,dseconds, dminutes;
			i = (int)DTimer;
			//converts the long to integer
			dseconds = i % 60;
			//remainder will be number of seconds
			dminutes = i / 60;
			//number of minutes
			System.out.println("Duration: " + dminutes + " minutes" + "  "+ dseconds + " seconds");
			
			System.out.println("Number of objects encountered: " + numObjEncountered);
			
		}
			
			
				
				
	
	}
	
	public static boolean EncounteredObject()
	{
		if (myFinch.isObstacle() == true)
		{
			myFinch.setLED(Color.RED);
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	public static void Search()
	//used by CuriousFinch and ScaredyFinch when there are no objects in sight
	{
		while (EncounteredObject() == false)
		{
			long delayTimer = System.currentTimeMillis();
			//initialise delayTimer
			
			long delayTimerEnd = delayTimer + 5*1000;
			
			while (System.currentTimeMillis() < delayTimerEnd)
			//whiledelaytimer < 5 seconds
			{
				System.out.println("No objects in sight!");
				myFinch.setLED(Color.GREEN);
		        myFinch.setWheelVelocities(150, 150, 500);
		        if (EncounteredObject() == true)
		        {
		        	break;
		        }
			
		        if (myFinch.isBeakUp())
		        {
					break;
				}
			}
			//When there is no object found after 5seconds, loop ends 
			
			 if (myFinch.isBeakUp())
		        {
					break;
				}
			 
			 
			 if (EncounteredObject() == false)
				 // if there is no object found after 5 seconds
			 	 {
				 
				 
				 myFinch.setWheelVelocities(0, 0,1000);
				 //finch waits for 1 second
				 
				
				 Random randomTurn = new Random();      
			     int randTurn = randomTurn.nextInt(2);
			        //generates a random int 0 or 1
				 
			     
			     if (randTurn == 1 )
			     {
			    	 System.out.println();
					 System.out.println();
			    	 System.out.println("Turning right");
			    	 
			    	 myFinch.setWheelVelocities(200,-200,800);
					 //Turns the finch clockwise
			     }
			     
			     else 
			     {
			    	 System.out.println();
					 System.out.println();
			    	 System.out.println("Turning left");
			    	 myFinch.setWheelVelocities(-200, 200,800);
					 //Turns the finch counterclockwise
			     }
		}
		
	}
	
	}
}
