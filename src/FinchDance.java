//Finch Dance Code:

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;


/*
 * For the movement i am going to put the binary into a string reverse
 *  it and then put it in an array and let the finch
 *  read one index position at a time
 */

public class FinchDance {
	public void TaskFinchDance throws IOException{  
		
		Finch myFinch = new Finch();
		
		int counter=0, green;
		boolean restart=true, check=true;
		boolean question=true, answer=true;
		
	
	
	
	
	while(restart)
	{
		if(counter<1 && check==true)
		{
		System.out.println("Give a hexadecimal number of a length of 2:");
		}else if(check==false)
		{
			System.out.println("You have entered a non hexadecimal number of 2 digits, please enter a hexadecimal number:");	
			//System.out.println("Give another hexadecimal number of 2 digits:");
		}else
		{
			System.out.println("Give another hexadecimal number of 2 digits:");
		}
		

		
		Scanner scanner = new Scanner(System.in); //reading from system.in
		//System.out.println("Before reading n");
		String dec;
		//System.out.println("before closure");
		dec=scanner.nextLine(); //User inputs the hexadecimal number
		
		if(dec.length()<=2 && dec.toUpperCase().matches("[0123456789ABCDEF]+"))
		{
	int hex2dec=getDecimal(dec);
	String octalString=getOctal(hex2dec);
	int Octal = Integer.parseInt(octalString);//transform the string octal to an int octal
		
	int blue;
	
		//converting the binary from an output to a string
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		getBinary(hex2dec);
		System.out.flush();
	    System.setOut(old);
	    String binary = baos.toString();
	    //System.out.println(binary);
	    
	    reverseBinaryInput(binary);
	    setSpeed(Octal);//set the speed of the finch using the setSpeed Method
	    green= hex2dec%80+60;
	    blue=(hex2dec+green)/2;
	    myFinch.setLED(hex2dec, green, blue); //set the colours of the LED
	    /* Move coding */
		String zeroesAndOnes = addCommasToNumericString(reverseBinaryInput(binary));
        
		//System.out.println(zeroesAndOnes);
			       
		String[] zeroAndOnesarray =  (zeroesAndOnes).split(",");
			        
		//System.out.println(Arrays.toString(zeroAndOnesarray));
		        
		int array []= new int[reverseBinaryInput(binary).length()];
					
		for(int i=0; i<zeroAndOnesarray.length; i++)
		   {
				array[i]=Integer.parseInt(zeroAndOnesarray[i]);
		   }
		//System.out.println(Arrays.toString(array));

		printing(hex2dec,dec,binary,blue,Octal, green);
		
		for(int i=0; i<reverseBinaryInput(binary).length(); i++)
		{
			if(array[i]==1)
			{
				myFinch.setWheelVelocities(setSpeed(Octal), setSpeed(Octal), 1000);		
				//System.out.println("Finch going forward");
			}
			else
			{
				myFinch.setWheelVelocities(-setSpeed(Octal), -setSpeed(Octal), 1000);
				//System.out.println("Finch going backwards");
			}
		}
		counter++;
		check=true;
	}
		else
		{
			check=false;
		}
		
		if(check==true)
		{
		while(question)
		{
			if(!answer)
			{
				System.out.println("Give a correct answer, yes or no:");
				answer=true;
			}else
			{
				System.out.println("Would you like to enter another hexadecimal number?, enter yes no");
			}
			
		String ans;
		
		ans=scanner.nextLine();
		
		if(ans.toUpperCase().contains("YES"))
		{
			restart=true;
			question=false;
		//System.out.println(ans);
		}
		else if(ans.toUpperCase().contains("NO"))
		{
		restart=false;
		question=false;
		//System.out.println(ans);
		} 
		if(!ans.toUpperCase().contains("YES") && !ans.toUpperCase().contains("YES"))
		{
			answer=false;
		}
		
		counter++;
		}
		question=true;
		}
	}
	}
	
	//Print the values Method
	public static void printing(int hex2dec, String dec, String binary, int blue, int speed, int green) throws IOException
	{
		PrintWriter writer = new PrintWriter(new FileWriter("Dance Outputs", true));
		writer.println("The hexadecimal is:  " + dec);
		writer.println("The Decimal of " + dec + " is: " + hex2dec);
		writer.println("The Binary of " + dec + " is: " + binary);
		writer.println("The Octal of " + dec + " is: " + getOctal(hex2dec));
		writer.println("The Red Value is: " + hex2dec);
		writer.println("The Green Value is: " + green);
		writer.println("The Blue Value is: " + blue);
		writer.println("The speed of the finch is: " + setSpeed(speed));
		writer.println("");
		System.out.println("The hexadecimal is:  " + dec);
		System.out.println("The Decimal of " + dec + " is: " + hex2dec);//print the decimal
		System.out.println("The Binary of " + dec + " is: " + binary);//print the binary
		System.out.println("The Octal of " + dec + " is: " + getOctal(hex2dec));//print the octal
		System.out.println("The Red Value is: " + hex2dec);
		System.out.println("The Green Value is: " + green);
		System.out.println("The Blue Value is: " + blue);
		System.out.println("The speed of the finch is: " + setSpeed(speed));
		writer.close();
		
	}
	
	//Set the moves
	public static String reverseBinaryInput(String binaryInput)
	{
		StringBuilder reverseBinary = new StringBuilder(); 
		reverseBinary.append(binaryInput);
		reverseBinary = reverseBinary.reverse();
		//System.out.println(reverseBinary);
		
		int stringLength = reverseBinary.length();
		//System.out.println(stringLength);
		
		return binaryInput;
		
	}
	
	public static String addCommasToNumericString(String string){
        String result = "";
        for (int i=0; i < string.length(); ++i) {
            char ch = string.charAt(i);
            result = ch + result;
            if(i<string.length()-1)
            {
            	result = "," + result;
            }
        }
        return result;
    }
	
	//Speed setter
	public static int setSpeed(int octal)
	{
		int speed = 0;
		
		if(octal<60)
		{
			//set speed=octal+30
			speed=octal+30;
			//System.out.println("The speed of the finch is: " + speed);
		}
		if(octal>225)
		{
			//if octal is above speed limit speed=limit
			speed=225;
			//System.out.println("Speed has been set to the limit");
		}
		
		if(octal>60 && octal<225)
		{
			speed=octal;
		}
		
		return speed;
	}
	
	//Octal Converter
	public static String getOctal(int decimal){    
	    int rem; //declaring variable to store remainder  
	    String octal=""; //declareing variable to store octal  
	    //declaring array of octal numbers  
	    char octalchars[]={'0','1','2','3','4','5','6','7'};  
	    //writing logic of decimal to octal conversion   
	    while(decimal>0)  
	    {  
	       rem=decimal%8;   
	       octal=octalchars[rem]+octal;   
	       decimal=decimal/8;  
	    }  
	    return octal;  
	}    
	
	//Binary Converter
	public static void getBinary(int number) 
	{
        int remainder;

        if (number <= 1) 
        {
            System.out.print(number);
            //System.out.print("inside");
            return; // KICK OUT OF THE RECURSION
        }

        remainder = number % 2;
       // System.out.print("outside");
        getBinary(number/2);
        System.out.print(remainder);
    }
	
	//Hexadecimal to Decimal converter
	public static int getDecimal(String hex){
	    String digits = "0123456789ABCDEF";
	             hex = hex.toUpperCase();
	             int val = 0;
	             for (int i = 0; i < hex.length(); i++)
	             {
	                 char c = hex.charAt(i);
	                 int d = digits.indexOf(c);
	                 val = 16*val + d;
	             }
	             return val;
	}
	
}