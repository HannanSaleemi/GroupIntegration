
import java.awt.Color;
import java.util.Random;

public class ScaredyFinch extends MAIN{

	public static void ScaredyFinch()
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("You chose Scaredy Finch");
		
		while (!(myFinch.isBeakUp()))
		{
			if(EncounteredObject() == true )
			{
				System.out.println();
				System.out.println();
				System.out.println("OH NO. THERE IS AN OBJECT");
				
				myFinch.setLED(Color.RED);
				 // Make Finch Buzz at Frequency 4000 for 500ms
	            myFinch.buzz(4000, 500);
	            // the sound of the finch moving is louder than the sound
	            RunAway();
	            
	            numObjEncountered = numObjEncountered + 1;
			}
			else 
			{
				Search();
			}
		}
		

	}
	
	public static void RunAway()
	{
		myFinch.setWheelVelocities(-255,-255,500);
		//finch moves backwards for 0.5 seconds 
		
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
		
        
        
        Random randVel = new Random();
        

     
        int randLeftWheel = randVel.nextInt(250);
        //generates a random number
        int randRightWheel = randVel.nextInt(250);
        
        //less than 100 velocity is too slow
       if (randLeftWheel < 100)
       {
    	   randLeftWheel += 100;
       }
        
       if (randRightWheel < 100)
       {
    	   randRightWheel += 100;
       }
        
        
        System.out.println("This is the random left wheel velocity " + randLeftWheel);
        System.out.println("This is the random right wheel velocity " + randRightWheel);
        myFinch.setWheelVelocities(randLeftWheel, randRightWheel, 2000);
        //sets the velocity of left and right wheel to the randomly generated numbers
        // moves away from the object randomly for 2 seconds
        
        System.out.println();
        System.out.println();
        
	}
}
