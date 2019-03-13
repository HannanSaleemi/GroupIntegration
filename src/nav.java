package nav;

 import java.util.Scanner;
 import java.util.Stack;
 import edu.cmu.ri.createlab.terk.robot.finch.Finch;
 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Date;
  
  
 
 public class nav {                
                                  
	 // Initialise Finch
	 static Finch myFinch = new Finch();
	 
	 //Global variables
	 static boolean CheckInput;
	 static String CommandsStack;
	 static int size;
	 static String continueplaying;
	 static Stack<Stack<String>> commandsstack = new Stack<>();
	 static Stack<Stack<String>> Retrace = new Stack<>();
	 static Scanner user_input; // Global variable as it's repeatedly used throughout the code
         
	 public static void main(String args[]) throws IOException {
		 //Introduction to the beginning of my program
		 System.out.println("Welcome to navigating your Finch");
		 System.out.println();
  
		 // Instructions
		 System.out.println("Below are a list of the ONLY commands you can input in order to navigate your Finch program: ");
		 System.out.println("- 'F' / 'f' = Move Finch forward");
		 System.out.println("- 'B' / 'b' = Move Finch backward");
		 System.out.println("- 'R' / 'r' = Turn Finch right");
		 System.out.println("- 'L' / 'l' = Turn Finch left");
		 System.out.println("- 'T' / 't' = ReTrace over previous movements");
		 System.out.println("- 'W' / 'w' = Write current time and store all previous commmands in a text file");
		 System.out.println("- 'Q' / 'q' = Terminate program");
		 System.out.println();
                         
		 System.out.println("In order to move your Finch around, there also has to be some further input. This being 'Duration' and 'Speed'.");
		 System.out.println("- Your Duration integer cannot exceed 6 seconds. Any higher than this and an error message will appear.");
		 System.out.println("- Your Speed integer cannot exceed 200ms. Any higher than this and an error message will appear.");
		 System.out.println();
  
                                                 
		 
		 boolean Continue = true; // If 'Continue' is true the do while loop will continue to run until it turns false
		 while (Continue == true){
			 user_input = new Scanner(System.in);
			 String input;                       
		 do {
			 System.out.print("Please enter a command: ");
			 // Takes in the next String input the user inputs
			 input = user_input.next(); 
                                                   
		 } while (!CheckInput(input)); // Will execute if CheckInput is returned as 'false'
		 	Continue = RunInput(input);
        }
 	}
                                                 
	 public static boolean CheckInput(String input) throws IOException {
		 
		 // If the 'if' statements are true CheckInput will be returned back as true, thus moving onto the 'RunInput' method
		 if (input.equals("F") || input.equals("f") | input.equals("B") | input.equals("b")){
			 return true;
		 } else if(input.equals("R") | input.equals("r") | input.equals("L") | input.equals("l")) {
			 return true;
		 } else if(input.equals("T") | input.equals("t")) {
			 return true; // t
		 } else if(input.equals("W") | input.equals("w")) {
			 return true;
		 } else if(input.equals("Q") | input.equals("q")) {
			 return true;
		 } else {
			 myFinch.buzz(200,500); // If the input is incorrect the Finch will buzz to alert the user of this
			 System.out.println("This is an incorrect command. Please only enter F, B, R, L, T, W, or Q!"); // This error message will appear if user input is incorrect
			 return false; // CheckInput will be returned back as false if the user input is incorrect
     }                                           
  }
                                 
	 public static boolean RunInput(String input) throws IOException {
                                        
		 // This is the section where the command is directed to its suited method via calling 
		 if (input.equals("F") || input.equals("f") | input.equals("B") | input.equals("b")){
			 MoveFinch(input); // If the input is 'F', 'f', 'B', 'b' the MoveFinch method is called
		 } else if(input.equals("R") | input.equals("r") | input.equals("L") | input.equals("l")) {
			 TurnFinch(input); // If the input is 'R', 'r', 'L', 'l' the TurnFinch method is being called
		 } else if(input.equals("T") | input.equals("t")) {
			 ReTracePreviousMovements(); // If the input is 'T' or 't' the ReTracePreviousMovements method is being called
		 } else if(input.equals("W") | input.equals("w")) {
			 WriteLog(); // If the input is 'W' or 'w' the WriteLog method is being called
		 } else if(input.equals("Q") | input.equals("q")) {
			 TerminateProgram(); // If the input is 'Q' or 'q' the TerminateProgram method is being called
			 
     }
		 boolean Continue = ContinuePlaying(); 
		 return Continue;
     }
                                 
                                                                                 
	 public static int GetAndCheckDuration() throws IOException {
		 
		 // Scans user input for next int 
		 user_input = new Scanner(System.in);
		 int duration;
		 do { 
			 System.out.print("Please enter a duration <= 6 seconds: "); 
			 duration = user_input.nextInt(); 
			 if (duration > 6) {
				 myFinch.buzz(200, 500); // Finch will buzz to alert the user that the inputted duration was incorrect
				 System.out.println("This is an incorrect duration. Please only enter a duration <= 6"); // This error message will appear if duration > 6 seconds
			 } 
		 }
		 while(duration > 6); // Will loop round until the user enters a duration that is <= 6 seconds 
		 return duration;
	 }
                                 
                                                 
	 public static int GetAndCheckSpeed() throws IOException {
		 
		 // Scans user input for next int 
		 user_input = new Scanner(System.in);
		 int speed;
		 do {
			 System.out.print("Please enter a speed <= 200: ");
			 speed = user_input.nextInt(); 
			 if (speed > 200) {
				 myFinch.buzz(200, 500); // Finch will buzz to alert the user that the inputted speed was incorrect
				 System.out.println("This is an incorrect speed. Please enter a speed <= 200"); // This error message will appear if the speed > 200
			 } 
		 }
		 while(speed > 200); // Will loop around until the user enters a speed that is <= 200 
		 return speed;
	 }
                                 
	 public static boolean ContinuePlaying(){
		 
		 //User is asked whether they would like to continue
		 System.out.println("Would you like to enter another command? (Y/N) ");
		 continueplaying = user_input.next(); 
		 boolean Continue = false;
		 if (continueplaying.equals("Y") || continueplaying.equals("y")) { // If the user inputs "Y" 'Continue' will be true, and the user will be prompted for another command
			 Continue = true;   
		 }
		 else if(continueplaying.equals("N") | continueplaying.equals("n")){ // If the user inputs "N" 'Continue' will be false, and the program will stop
			 System.out.println("The program has been terminated.");
			 Continue = false;
		 } else {
     }
		 return Continue;
	 }
                                         
	 public static void MoveFinch(String input) throws IOException {
		 
		 System.out.println("You have chosen the 'Move Finch' command");
		 
		 String command;
		 if (input.equals("F")|| input.equals("f")){
		 
			 // If the user inputs "F" or "f" this section of the code will be executed
			 int duration = GetAndCheckDuration();
			 int speed = GetAndCheckSpeed();
			 CommandsStack(input, duration, speed);
			 duration = duration * 1000; // Multiplying the duration by 1000 to ensure that we are able to see the Finch moving
			 command = "Forward" + " " + duration + " " + speed; 
			 System.out.println(command); // Prints out the command for the user to see
			 
			 myFinch.setWheelVelocities(speed, speed, duration);
			 
		 } 
		 
		 else if(input.equals("B") || input.equals("b")) {
			 
			 // If the user inputs "B" or "b" this section of the code will be executed
			 int duration = GetAndCheckDuration();
			 int speed = GetAndCheckSpeed();
			 CommandsStack(input, duration, speed);
			 duration = duration * 1000; // Multiplying the duration by 1000 to ensure that we are able to see the Finch moving
			 command = "Backward" + " " + duration + " " + speed; 
			 System.out.println(command); // Prints out the command for the user to see
			 
			 myFinch.setWheelVelocities(-speed, -speed, duration); // Negative speed integers so that the Finch moves backwards
	  	  }
  }
                                                                 
	 public static void TurnFinch(String input) throws IOException {
		 
		 System.out.println("You have chosen the 'Turn Finch' command");
		 
		 String command;                                            
		 if (input.equals("R")|| input.equals("r")){
			 
			 // If the user inputs "R" or "r" this section of the code will be executed
			 int duration = GetAndCheckDuration();
			 int speed = GetAndCheckSpeed();
			 CommandsStack(input, duration, speed);
			 duration = duration * 1000; // Multiplying the duration by 1000 to ensure that we are able to see the Finch moving
			 command = "Right" + " " + duration + " " + speed;
			 System.out.println(command); // Prints out the command for the user to see
			 
			 myFinch.setWheelVelocities(100, 0, 2000); // Orthogonally turns the Finch 
			 myFinch.setWheelVelocities(speed, speed, duration);
		 }
		 
		 else if(input.equals("L") || input.equals("l")) {
			 
			// If the user inputs "L" or "l" this section of the code will be executed
			 int duration = GetAndCheckDuration();
			 int speed = GetAndCheckSpeed();
			 CommandsStack(input, duration, speed);
			 duration = duration * 1000; // Multiplying the duration by 1000 to ensure that we are able to see the Finch moving
			 command = "Left" + " " + duration + " " + speed;
			 System.out.println(command); // Prints out the command for the user to see
			 
			 myFinch.setWheelVelocities(0, 100, 2000);// Orthogonally turns the Finch 
			 myFinch.setWheelVelocities(speed, speed, duration);
	  	}
  }
                                                 
	 public static void ReTracePreviousMovements() throws IOException {
		 
		 System.out.println("You have chosen the 'ReTrace Previous Movements' command");
		 
		 // Initialising a new scanner
		 Scanner retrace = new Scanner(System.in);
		 int retraceinteger;
		 
		 // Initialising new variable that we will use to get the size of the stack
		 int sizeofstack = commandsstack.size(); 
		 System.out.println("You are able to retrace " + sizeofstack + " commands"); // Prints out the size of the stack for users to see how many commands they can retrace
		 
		 // Do while loop will continue to loop until retraceinteger <= sizeofstack
		 do {
			 System.out.print("Please enter a retrace integer: "); 
			 retraceinteger = retrace.nextInt(); 
             
			 // If the retraceinteger > sizeofstack an error message will be shown to the user
			 if (retraceinteger > sizeofstack) { 
				 System.out.println("Your retrace integer exceeds the amount of commands you've executed. Please try again.");
			 } 
                                           
			 else {
				 Stack<String> LastCommand; // Calls LastCommand stack where the last commands can be accessed
			  
			 for(int i = 0; i < retraceinteger; i++) 
			  {
				  
				  LastCommand = commandsstack.pop(); // Begin popping off the last commands of the stack
				  Stack<String> Command = new Stack<>();
				  // This means that both stacks held the same data so when one is affected, they're both affected. Therefore, a new stack had to be made. Making it a clone didn't get problem.
				  Command = (Stack<String>)LastCommand.clone();
 											 				  
				  Retrace.add(Command);
				  System.out.println(Retrace); // Prints out the commands that are being retraced
 
				  // Begin popping the commands off backwards due to stacks popping off the commands from the last to the first. We want input first, not speed. 
				  String getSpeed = LastCommand.pop(); 
				  int speed = Integer.parseInt(getSpeed);  // Have to cast the speed back from String to int to show the user the command they inputted
 
				  String getDuration = LastCommand.pop();
				  int duration = Integer.parseInt(getDuration); // Have to cast the duration back from String to int to show the user the command they inputted
				  duration = duration * 1000;
                                 					
				  String input = LastCommand.pop();
				  
                  // Have to re-tell the Finch what to do if it receives a particular input from the retrace, so that it knows how to retrace an input               					
				  if (input.equals("F") || (input.equals("f"))){
					  myFinch.setWheelVelocities(speed, speed, duration);    
				  } else if (input.equals("B") || (input.equals("b"))) {
					  myFinch.setWheelVelocities(-speed, -speed, duration);
				  } else if(input.equals("R") || (input.equals("r"))){
					  myFinch.setWheelVelocities(100, 0, 2000);
					  myFinch.setWheelVelocities(speed, speed, duration); 
				  } else if(input.equals("L") | (input.equals("l"))) {
					  myFinch.setWheelVelocities(0, 100, 2000);
					  myFinch.setWheelVelocities(speed, speed, duration);
				  }
			  }
		  }	
		} 	while (retraceinteger > sizeofstack); 
                                            
		 		int sizeofretrace = Retrace.size();
		 		for (int i = 0; i < sizeofretrace; i++) {
		 			Stack<String> Command = Retrace.pop();
		 			
		 			commandsstack.add(Command);
           }
    }  
                          
	 public static void WriteLog() throws IOException{
		 
		 System.out.println("You have chosen the 'Write Log' command");
		 
		 // Initialising 'date' variable which we will use to retrieve the current date
		 Date date = new Date();
		 // Gets the current time in HH:MM:SS format
		 DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss" + "  ");
  			
		 // Formats it in order for it to be added to the text file
		 String strTime = timeFormat.format(date);
		 System.out.println("Current time: " + strTime);
  			
		 // Initialising BufferedWriter as we will be using this to write our date and commands to a text file, in this case, 'commands.txt'.
		 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("H:commands.txt"));
		 bufferedWriter.write(strTime + commandsstack);
		 bufferedWriter.close();
		 
		 // Alerts the reader that their text file has been written
		 System.out.println("Your file has been written!"); 
  
      }
                                 
                                                    
  	 public static void CommandsStack(String input, int duration, int speed) {
  			
  		 // Initialising a new stack labelled 'CommandsStack'
  		 Stack<String> CommandsStack = new Stack<>();
  		 //The int variable 'speed' needs to be casted in order for it to be used in the stack due to the stack being a 'String'
  		 String getSpeed = String.valueOf(speed);
  			
  		 //The int variable 'duration' needs to be casted in order for it to be used in the stack due to the stack being a 'String'
  		 String getDuration = String.valueOf(duration);
            
  		 CommandsStack.add(input); // The 'input' will automatically be added to the stack here every time the user enters a new one
  		 CommandsStack.add(getDuration); // The 'duration' will automatically be added to the stack here every time the user enters a new one
  		 CommandsStack.add(getSpeed); // The 'speed' will automatically be added to the stack here every time the user enters a new one
                                     
  		 commandsstack.add(CommandsStack);
  	}                                 
                                 
  	 public static void TerminateProgram() {
  			
  		 // Finch will buzz to alert the user that they have chosen to terminate the program
  		 myFinch.buzz(5000, 10);
  		 // The Finch will then quit
  		 myFinch.quit();
  		 // This message will appear to show the user that the program has been terminated
  		 System.out.print("The program has been terminated. Thank you for playing!");
  		 // System will exit
  		 System.exit(0); 
  	 	}
    }

