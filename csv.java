import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class csv
{
     public static void main(String[] args)
	 {
	     Scanner Key = new Scanner (System.in);
		 System.out.println("Do you want to Sign in (1) or Create an Account (2) : ");
		 int h = Integer.parseInt(key.nextLine());
		 if (h == 1)
	     {
			 System.out.println("Enter your username: ");
			 String username = key.nextLine();
			 
			 System.out.println("Enter your password: ");
			 
			 String password = key.nextLine();
			 
			 // search the csv for this name - check the corresponding password - then int return if 0 then account dont exist if >=1 then this is the accounts number
			 
			 
		 }
		 else if (h == 2)
		 {
			 // create a account
			 //String patternPass = "[a-zA-Z0-9]{7,}";   // can have fancy regex or funct to remove letter num and check for special symbols
			 //String patternUsername = "[a-zA-Z0-9{3,8}]";
			 
			 
		 }
		 else
		 {
			 System.out.println("invalid request ");
		 }
		 
	
	 }
	 public static boolean passwordStrength(String password)
	 {
		 // check is there minimum 8 chars
		 // 1 uppercase
		 // 1 num
		 // 1 symbol
		 
		 
		 // annette had a solution written on one of the sheets this is just one version - probably less efficient
		 boolean verified = false;
		 int pLength = password.length();
		 int numbers = 0;
		 int capitalLetters = 0;
		 int symbol = 0;
		 for (int i =0 ; i< pLength;i++)
		 {
			 char x = password.charAt(i);
			 
			 // ascii 48 57 is numbers
			 // 65 90 cap
			 // 97 121 lowercase
			 // else is a ascii other symbol
			 
			 if ( x >= 48 && x <= 57)
			 {
				 numbers++;
			 }
			 else if (x >= 65 && x <= 90)
			 {
				 capitalLetters++;
			 }
			 else if (x >= 97 && x <= 121)
			 {
				 // normol lowercase letter
			 }
			 else
			 {
				 symbol++;
			 }
			 
			 if (numbers >= 1 && pLength >=8 && symbol >= 1 && capitalLetters >= 1 )
			 {
				 // password is valid
				 verified = true;
				 break;
			 }
		 }
		 return verified;
	 }
	
	 /*
	 *  -- explanation of inputs
	 *  -- output
	 *  -- author
	 */
     public class boolean adminFilesFound(String filename, int adminNumber, String splitCondition ) // pass in string and another arguement 1 or 0 that allows u to convert the Stirng to a int in the function for checking of things
     {
	     // check does the filename exist
		 BufferedReader br = null;
         boolean found = false;
		 
         File filename = new File(filename);
		 br = new BufferedReader(new, FileReader(filename));
			 
		 while (line = br.readLine() != null) // add the conditional line number not found - then check for the number
	     {
		     String[] SPLIT = line.split(splitCondition);
			 int num = Integer.parseInt(SPLIT[0]);
			 if (adminNumber == num)  
             {
				 found = true;
			 } 					 
		 }
 		 
         return found;		 
	 }	
	 
	 public static boolean accountChecker(String filename, String username, String password);  // could this function be made more anonymous
	 {
		 BufferedReader br = null;
         boolean found = false;
		 br = new BufferedReader(new, FileReader("admin.txt"));
		 int counter = 0;
		 while (line = br.readLine()!= null)
		 {
			 String[] split = line.split(",");
			 counter++;
			 if (split[1].equals(username) && split[2].equals(password))
				 found = true;
			     // get the current iteration - this is the admins identifier code.
				 break;
			 
			 
		 }
		 
		 return true;
	 }

}