import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.Arrays;
public class League_Manager
{
    public static void main(String[] args)
    {
	    // first thing as the program starts is to log in, ask user do they have an account or do they want to sign in?
		
		
		// have to check is the username valid
		// is the password valid
		// does it already exist

    }
	 /**   Function that checks the strength of the users password that they enter and returns a boolean based on whether it is strong enough to be used.
	  *   Inputs - Password user entered as a String.
	  *   Output - Boolean True/False based on the passwords strength.
	  */
	 public static boolean passwordStrength(String password)
	 {
		 // check is there minimum 8 chars, 1 Uppercase, 1 Number, 1 Symbol

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
	 
	/**
	 * GUI Method User enters a string with user suggested values that the user chooses what option they desire by entering the number they desire 
	 * Input - String options = "1) Option1 \n2) Option2  \n3) Option3"; in that format
	 * Returns a int output
	 */
	public static int menuBox(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		// parse to int and return the users choice
		int x = Integer.parseInt(text);
		return x;
	}

	/**
	 * GUI Method that returns the position of the option that the user choose from an array of inputs. 
	 * Input - Array String of options that the user wants displayed on the buttons, e.g input: String[] opt = {"opt1", "opt2", "opt3"};
	 * Output - Integer output of the corresponding position in the array that the user made. 
	 */	
	public static int optionBoxs(String[] options)
	{
		int result = JOptionPane.showOptionDialog(null, "Returns pos of choice in array", "Click button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	    return result;
	}
	
	/**
	 * GUI Method for displaying a message output
	 * Input - String input of 
	 * Output - No output from the method - The String is converted to a GUI output box.
	 */
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	/**
	 * Input - filename to push text to and the actual text you want put in the file.
	 * Output - 
 	 */   // concern >> ? Does this overwrite data in the txtfile or append it to the end of the file
	 public static void writeFile(String input, String fileName) 
	 {
		 try (Writer writer = new BufferedWriter(new OutputStreamWriter(
         new FileOutputStream(filename), "utf-8"))) 
		 {
             writer.write(input);
         }
		 catch(Exception e)
         {} 
         
	 }
	 
	 /*
	  * Input - filename in string format
	  * Output - ?? Arraylist or a array of the string 
	  */
	 public static String readFile(String filename)  
	 {
		 String line = "";
		 
		 try
		 {
			
	        BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null)
			{    
		        String[] split = line.split(",");
				System.out.println(Arrays.toString(split));
			}
			/*
			 One method to find things as described below
			
			 One method to return the whole text file as a string or an array
			*/
			
			
			/*
			//  Enter filename to search  admin.txt scores.txt
			
			    Enter thing to find - string == yes
				                      int    == 1  
									  
				Enter position in the text file to search for said item
				   Format:   1,admin,password
							 want to look for admin - enter 1
							 want to look for the unique identifier no. enter 0
			*/
		 }
		 catch (Exception e)
		 {}
		 
		 return line;
	 } 

}

