
import java.awt.Color;

public class CuriousFinch extends MAIN {

	
	public static void CuriousFinch()
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("You chose Curious Finch");
		while (!(myFinch.isBeakUp()))
		{
			myFinch.setLED(Color.RED);
			if (EncounteredObject() == true)
			{
				while (myFinch.isObstacle() == true)
				{
					
					//if object is moving then 
					myFinch.setLED(Color.GREEN);
					
					 
		             myFinch.buzz(1000, 5000);
		          // Make Finch Buzz at Frequency 3000 for 1s
		             //not too loud sound
		             FollowMe();
				
				}
				
				numObjEncountered = numObjEncountered + 1;
				//increases num of object detected by 1
			}
			else
			{
				myFinch.buzz(0, 1000);
				//stops the buzzing sound
				Search();
				
			}
		}
		
	}
	public static void FollowMe()
	{
		while (EncounteredObject() == true)
		{
			
			
			
			while(myFinch.isObstacleLeftSide() && !myFinch.isObstacleRightSide())
				//if object is present left side only
			{
				System.out.println("The object is on the left side");
				myFinch.setLED(Color.GREEN);
				myFinch.setWheelVelocities(100, 200, 400);
				//finch rotates counterclockwise until object can be seen by both left and right sensors
			}
			
			while(!myFinch.isObstacleLeftSide() && myFinch.isObstacleRightSide())
				//if object is present right side only
			{
				System.out.println("The object is on the right side");
				myFinch.setLED(Color.GREEN);
				myFinch.setWheelVelocities(200, 100, 400);
				//finch rotates clockwise until object can be seen by both left and right sensors
			}
			
			
			
			while (myFinch.isObstacleLeftSide() && myFinch.isObstacleRightSide())
				//if object is straight ahead
			{
				System.out.println("The object has stopped moving!");
				myFinch.setLED(Color.RED);
				myFinch.buzz(0, 1000);
				//stops the buzzing sound
				myFinch.setWheelVelocities(0, 0, 400);
				//finch stays until the object starts moving again (moves left or right)
				
			}

			
			
			if (myFinch.isBeakUp())
	        {
				break;
				//if finch obstacle sensors are covered while finch beak is up, loop is escaped
			}
			
		}
	}
	
}
	