# League-Manager
College project to create a modular League Manager that is versatile enough to work with any sport.

Written in Java







import java.io.*;
import javax.swing.*;
import java.util.*;
public class admin
{
	private static int currentAdminNo;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	

	
	public static void main(String [] args)throws IOException
	{
	checkIfExists(adminFile);
	
	
	String username = JOptionPane.showInputDialog(null, "Enter username"); // menubox or other?
	String password = JOptionPane.showInputDialog(null, "Enter password"); 
	
	
	boolean isLoggedIn = loginMethod(username, password);
	if(isLoggedIn)
	{
		System.out.print("Logged In" + currentAdminNo);
	}
	}
	
	//already in main
	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}
	
	
	
	public static boolean loginMethod(String username,String password)
	{
	
	     boolean loggedInStatus = false;
		 int maxLoginAttempts = 3;
		 ArrayList<String> adminFile = new ArrayList<String>();
		 String[] details;
		 
         // Load the admin file into a arraylist! Load it once to avoid costly read/write operations.		 
		 try
		 {
		     Scanner s = new Scanner(new File(adminFile));
		     while(s.hasNext())
		     {
			     //add to arraylist
				 String v = s.next();
				 adminFile.add(v);
		     }
			 s.close();
		 catch(IOException e)
		 {}
		   	
	       
		 //for loop to check for the users password 3 times  
		 for (int i = maxLoginAttempts; i > 0;i--)
			 
		         String username = menuBox("Enter username");
		         String username = menuBox("Enter Password");
		         
				 
				 /*
		         details = v.split(","); 
		   	     if (details[1].equals(username) && details[2].equals(password))
                 { 
             		  loggedInStatus = true;
                      currentAdminNo = Integer.parseInt(details[0]);					   
              	 }
                 */
				 JOptionPane.showMessageDialog(null, "Successfully logged in as " + username);

             return loggedInStatus;				 
			 } 
         
	
	
     public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	 /*
	 public static boolean loginMethod(String username, String password)
	{
		int maxLoginAttempts = 3;
		String loginMethod = "";
		boolean loggedInStatus = false;
		boolean foundUserDetails = false;
		
		for (int i=maxLoginAttempts;i>0;i--)
		{
			foundUserDetails = readFile(adminFile, username, password, 1, 2);
			if (foundUserDetails == true)
			{
				currentAdminNo = Integer.parseInt(item1);
				loggedInStatus = true;
				JOptionPane.showMessageDialog(null, "Successfully logged in as " + username);
				break;
			}
			else
			{
				if (maxLoginAttempts == 1)
				{
					JOptionPane.showMessageDialog(null, "Incorrect login details\nNo attempt remaining");
					break;
				}	
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect login details\n" + (maxLoginAttempts-1) + " attempt(s) remaining");
					maxLoginAttempts--;
					username = JOptionPane.showInputDialog(null, "Enter username");
					password = JOptionPane.showInputDialog(null, "Enter password");
					foundUserDetails = readFile(adminFile, username, password, 1, 2);
				}
			}
		}
		return loggedInStatus;
	}	
	
	 */

	

}
