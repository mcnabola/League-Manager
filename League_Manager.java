import java.io.*;
import javax.swing.*;
import java.util.*;
public class League_Manager 
{
    
	 private static int currentAdminNo;
	 final static String leagueFile="league.txt";
	 final static String adminFile="administrator.txt";
     
	 public static void main(String[] args)
	 {	
	     
                String [] initialOptions= { "1-create league", "2-Edit/view League", "3-Remove League", "4-Exit Application" };
		 String [] subOptions={" 1-Fixture Generation", "2-View Table", "3-Input results", "4-Add teams","5-Back to Main Menu","6-Exit Application"};
	        boolean main = true;
		int x=0;
		while(main&&x==0||x==1||x==2||x==3)  // && not null 
		{	
		    x=optionBoxs(initialOptions,"Choose an option");
		    int y=0;
		    int z=0;
		
		    switch (x)
		    {
			    case 0: createNewLeague();
		        break;
			    case 1: y=optionBoxs(subOptions,"Choose an option");
			
			    switch (y)
			    {
					case 0: fixtureGeneration();
					break;
					case 1: generateTable();//displayTable();
					break;
					case 2: editResults();  
				    break;
					case 3: addTeamsToLeague();
					break;
					case 4: break;
					case 5: main = false;
					break;

				}
                                break;
			
				case 2: removeLeague(Integer.toString(currentAdminNo));//removeLeague();
				break;
				case 3: main = false; 
				break;

				}
			}
		}
		
}
		 
		

      	public static boolean loginMethod(String username,String password)
	{
	
	    boolean loggedInStatus = false;
		 try
		 {
		     Scanner s = new Scanner(new File(adminFile));
		     String[] details;
		     //ArrayList<String> leagues = new ArrayList<String>();
		     while(s.hasNext())
		     {
			     // check for admin pass as its read in
			     String v = s.next();
                     details = v.split(",");
	             if (details[1].equals(username) && details[2].equals(password))
                     { 
             		  loggedInStatus = true;
                          currentAdminNo = Integer.parseInt(details[0]);					   
              	     }			 
	           } 
		   s.close();
		   }
	        catch(IOException e)
	        {}
                return loggedInStatus;	
	}  


	public static void deleteFile(String deleteFile)
	{
	    String filename = deleteFile;  
        File aFile = new File(filename);
            if (!(aFile.exists()))
                System.out.println(aFile.getName() + " does not exist.");
            else if(aFile.delete())
                System.out.println(aFile.getName() + " is now deleted.");
            else    // replace sys.out with optionboxs
                System.out.println("Operation to delete file failed.");
    }
	 
	 
	 // string or int adminNumber in the main file ?????? --- ADD ARGUMENT adminNumber
	 public static void removeLeague(String adminNumber)
	 {
		 // load all the leagues that a admin has access to into a array
                 String [] temp=readFile("league.txt",adminNumber,0,1);		// adminNumber 
		 // pass array with all of adminNumber's Leagues into dropdown
		 String leagueToRemove = dropDown(temp,"Choose a league to remove.");
		 
		 // load league names into arraylist except the one league you wish to remove. (!leagueToRemove)
		 ArrayList<String> leagues = new ArrayList<String>();
		 int idNumber = 0;
		 try{
		 Scanner s = new Scanner(new File("league.txt"));
		 String[] details;
		 
		 while(s.hasNext())
		 {
			 // check for leagueToRemove if not in the current line add to arraylist
			 String v = s.next();
			 details = v.split(",");
			 if (details[1].equals(leagueToRemove))
			 {
				 //get the league's ID number: details[2] : needed for removing 3_scoring, 3_participants etc
				 idNumber = details[2];
			 }
			 else
			 {
				 leagues.add(v);
			 }
		 }
		 s.close();
		 }
		 catch(IOException e){}
		 
		 // Rewriting the leagues file with the arraylist with the desired league removed
		 try{
		 PrintWriter output = new PrintWriter("league.txt"); 
                 for (int i = 0; i<leagues.size();i++)
		 {
			 output.println(leagues.get(i));
			 //System.out.println(leagues.get(i));
		 }
                 output.close();
		 }
		 catch(IOException e)
		 {}
		 
		 
		 deleteFile(adminNumber+"_"+number+"_scoring.txt");
		 deleteFile(adminNumber+"_"+number+"_participants.txt");
		 deleteFile(adminNumber+"_"+number+"_fixtures.txt");
		 deleteFile(adminNumber+"_"+number+"_results.txt");
		 
	         ///CALL DELETEFILE METHOD FOR THE ABOVE
		 ///  deleteFile(idNumber+"_results.txt");
                 ///  Awaiting guidance on the new file structure before adding it in.		 
		 /// reading the file and if the line is not something you want to remove then add to arraylist - else dont add to arraylist
     }
	 /**
	   * Creates a dropdown menu with a list of options to choose, returns the string of the value the user choose
	   * String[] options is the list of things you want the user to choose from
	   * String dialogText is the message you want displayed to guide the user on the dropdown box
	   **/
	 public static String dropDown(String[] options, String dialogText)
	 {
		 String selected = (String) JOptionPane.showInputDialog(null, dialogText,"Input",1,null,options,options[0]);  
	     return selected;
	 }

	 
     
	 
	 // START OF GUI METHODS
	 
    /**
	 * GUI Method for displaying a message output
	 * Input - Any string input 
	 * Output - No output from the method - The String is converted to a GUI output box.
	 */
	public static void outputBoxs(String output) // be aware many other methods are calling outputBoxs - want just "outputBox"
	{
	     JOptionPane.showMessageDialog(null, output);	
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
	 

	public static String menuBox(String options)  //== method name
	{
		String text = JOptionPane.showInputDialog(null, options);
		return text;
	}

    /**
	 * GUI Method User enters a string with user suggested values that the user chooses what option they desire by entering the number they desire 
	 * Input - String options = "1) Option1 \n2) Option2  \n3) Option3"; in that format
	 * Returns a int output
	 */	
	public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
		
		/// include pauls validation
	}
	
	// READING AND WRITING METHODS
		/**
	 * Input - filename to push text to and the actual text you want put in the file.
	 * Output - 
 	 */     // this method both appends to already created files and if a file doesnt exist it creates it and adds to it
     public static void writeFile(String filename, String output)   /// fileWriter   or   writeFile   -- what has sean already started with
	 {
     try
     {
	     FileWriter aFileWriter = new FileWriter(filename,true);
         PrintWriter out = new PrintWriter(aFileWriter);			 
	     out.print("\n" + output); // or output + "\n"
	     out.close();
		 aFileWriter.close();
     }
	 catch(Exception e)
	 {}
	 }
	 
	 // sean's readfile  - was readFile -- changed to readFile1 in the method commented below too
	 	public static String readFile(String textFile)
	{
		String fileText="";
		try
		{
		    FileReader reader=new FileReader(textFile);
		    Scanner in=new Scanner(reader);
		    while(in.hasNext())
		    {
			    fileText=in.nextLine();
			    fileText=fileText+",";
		    }
		    in.close();
		    reader.close();
		}
		catch(Exception E)
		{}
	    return fileText;
	}
	
		 /*
	  * Input - String textFile -- name of the textFile to search
                String searchedItem -- what string value are we looking for in the text file
                int itemPositionNo -- what position in the line format is this "searchedItem" located
                int returnedItemNo -- if the "searchedItem" is found in the "itemPositionNo" in the file format return a string array of everything in the location "returnedItemNo"				
	  
	  File Format  -- leagueID,LeagueName,adminID
	  1,Premiership,1     
	  2,SnookerLeage,1
	  
	  // if you wanted to check for all adminID value 1 in the position 3 -- then if true return all the leagues name in position 1 
	  
	  * Output -    // also anyone wanting to use this ask me to explain it
	  */
	 public static String[] readFile(String textFile, String searchedItem, int itemPositionNo, int returnedItemNo)  
	 {
		 String x="";
		 try
		 {
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText= in.nextLine();
		        String[] split = fileText.split(","); 
				if (split[itemPositionNo].equals(searchedItem))
				{
					// if true store the item in position returnedItemNo
					x += split[returnedItemNo]+",";
					
				}
				
			}
			in.close();
			reader.close();		
            
		 }
		 catch (Exception e)
		 {}
		 String[] returned = x.split(",");
		 return returned;
    } 
	
	
		/**
     * Input -- textFile -- name of the file to search 
	            searchedItem -- what is the string you want to search for e.g look for a username enter "john.brown" 
                itemPositionNo -- what position is the "searchedItem" stored in				
	 *
	 */
	public static Boolean readFile(String textFile, String searchedItem, int itemPositionNo)
    {
		boolean found = false;
		try
		 {
			
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText=in.nextLine();
		        String[] split = fileText.split(",");
				if (split[itemPositionNo].equals(searchedItem))
				{
					found = true;
					break;
				}
				
			}
			in.close();
			reader.close();	
		 }
		 catch (Exception e)
		 {}
		
		return found;
	}	
	
	
	
	
    
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName=""; //=
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:"); 
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague) //=
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName); //=
			}
		}
	
	}
	
	
    
     	public static void createNewLeague()  
	{
		String leagueName=""; String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
	    leagueNo++;  
	}
    	
	
	
	

	
	public static int getNumberOfLeaguesMade()  //=
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0; 
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		return numberOfLeagues;
	} */
	
	 
	 
	 
	 /*
	 
	 	 //=
	public static void addTeamsToLeague()
	{
		String info=""; String teamName=""; String teamFileInfo=""; String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:"); //=
		teamFileName=whichLeague+"_"+"participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague) //=
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
		}
     }
	 
	 */
		
	/**
	 *
	 *
	 */
     	public static void addScoringScheme(int leagueNumber)
	{
		
		int win = menuBoxInt("Enter the number of points given for a win:");
		int draw = menuBoxInt("Enter the number of points given for a Draw:");
		int lose = menuBoxInt("Enter the number of points given for a Lose:");
		
		
		String fileName = (leagueNumber+"_scoring.txt");
		String output = (win+","+draw+","+lose);
		writeFile(fileName, output);	
	}
		
		
		
	     /// includde this in all the gui methods
		 /// is there a else in reply to the first if
	 	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[1-4]{1}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number  is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
	
	// include in gui method
	public static boolean validateInput(String text)
	{
		String result="";
		String pattern="[A-Za-z||0-9]{1,}";
		String message1 = "A Name must be input.";
	    String message2 = "Format of user input is incorrect, a Name is required.";
		boolean verified=false;
		if      (text != null)        
		{ 
			if (text.equals(""))   
			{
				result = message1;
				outputBoxs(message1);
			}
			else
			{				
		
				if (text.indexOf(" ") != -1) text = text.replaceAll("\\s+","");
	        
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					verified=true;
					}
			}
		}
		return verified ;
	}
/*	
import java.io.*;
import javax.swing.*;
import java.util.*;
public class League
{
	private static int currentAdminNo=1;
	private static int leagueNo=1;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	
	public static void main(String [] args)
	{
	createNewLeague();
    addTeamsToLeague();
	fixtureGeneration();
	}
	
		 
		 public static void writeFile(String input, String fileName)
		 {
		 try
		 {
		     FileWriter aFileWriter = new FileWriter(fileName,true);
             PrintWriter out = new PrintWriter(aFileWriter);
			 out.print(input);
			 out.println();
			 out.close();
			 aFileWriter.close();
        			
		 }
		 catch(Exception e)
		 {}
		 }
		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 return input;
	 }
	 
	 public static int menuBoxInt(String options)
	{
		String text = JOptionPane.showInputDialog(null, options);
		int x = Integer.parseInt(text);
		return x;
	}
	
	public static void createNewLeague()
	{
		String leagueName=""; 
		String leagueFileInput="";
		leagueName=menuBox("Enter your league name:");
		leagueFileInput=currentAdminNo+","+leagueName+","+leagueNo;
		writeFile(leagueFileInput,leagueFile);
	    leagueNo++;
	}
	
	public static int getNumberOfLeaguesMade()
	{
		boolean sameAdmin=true;
		boolean found=false;
		int currentAdminPostion=0;
		int temp=0;
		int numberOfLeagues=0;
		String [] arrayOfDetails=readFile(leagueFile).split(",");
		for (int i=0;i<arrayOfDetails.length&&found==false;i=i+3)
		{
			temp=Integer.parseInt(arrayOfDetails[i]);
			if(temp==currentAdminNo)
			{
				found=true;
				currentAdminPostion=i;
			}
		}
		for(int i=currentAdminPostion;i<arrayOfDetails.length && sameAdmin==true;i=i+3)
		{
			if(currentAdminNo!=Integer.parseInt(arrayOfDetails[i]))
			{
				sameAdmin=false;
			}
			else
			{
				numberOfLeagues=Integer.parseInt(arrayOfDetails[i+2]);
			}
		}
		return numberOfLeagues;
	}
	
	public static String readFile(String textFile)
	{
		String fileText="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			fileText=in.nextLine();
			fileText=fileText+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception E)
		{}
		return fileText;
	}
	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	
	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	
	public static void addTeamsToLeague()
	{
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=menuBoxInt("Enter which league number to add teams/players to:");
		teamFileName=whichLeague+"_participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("This league does not exist.");
		}
		else
		{
			int numberOfTeams=menuBoxInt("Enter the amount of teams/players:");
			for(int i=0;i<numberOfTeams;i++)
			{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
			}
		}
	}
	
	public static void fixtureGeneration()
	{
		String info=""; 
		String fixtureFileInfo=""; 
		String fixtureGenerationFileName="";
		int numberOfTeams=0;
		String teamFileName="";
		int totalNumberOfRounds=0;
		int numberOfMatchesPerRound=0;
		int roundNumber=0;
		int matchNumber=0;
		int homeTeamNumber=0;
		int awayTeamNumber=0;
		int even=0;
		int odd=0;
		boolean additionalTeamIncluded=false;
		String [][] fixtures;
		String [][]revisedFixtures;
		String [] elementOfFixture;
		String fixtureAsText="";
		int whichLeague=menuBoxInt("Enter which league number to generate fixtures for:");
		fixtureGenerationFileName=whichLeague+"_"+"fixtures.txt";
		teamFileName=whichLeague+"_participants.txt";
		if(getNumberOfLeaguesMade()<whichLeague)
		{
			outputBoxs("You did not create this league, access denied.");
		}
		else
		{
			numberOfTeams=getNumberOfTeams(teamFileName);
			if (numberOfTeams%2==1)
			{
				numberOfTeams++;
				additionalTeamIncluded=true;
			}
			totalNumberOfRounds=numberOfTeams-1;
			numberOfMatchesPerRound=numberOfTeams/2;
			fixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			for(roundNumber=0; roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					homeTeamNumber=(roundNumber+matchNumber)%(numberOfTeams-1);
					awayTeamNumber=(numberOfTeams-1-matchNumber+roundNumber)%(numberOfTeams-1);
					if(matchNumber==0)
						awayTeamNumber=numberOfTeams-1;
					fixtures[roundNumber][matchNumber]=(homeTeamNumber+1)+"v"+(awayTeamNumber+1);
				}
			}
			revisedFixtures=new String[totalNumberOfRounds][numberOfMatchesPerRound];
			even=0;
			odd=numberOfTeams/2;
			for(int i=0;i<fixtures.length;i++)
			{
				if(i%2==0)
					revisedFixtures[i]=fixtures[even++];
				else
			revisedFixtures[i]=fixtures[odd++];
			}
			fixtures=revisedFixtures;
			for(roundNumber=0;roundNumber<fixtures.length;roundNumber++)
			{
				if(roundNumber%2==1)
				{
					fixtureAsText=fixtures[roundNumber][0];
					elementOfFixture=fixtureAsText.split("v");
					fixtures[roundNumber][0]=elementOfFixture[1]+"v"+elementOfFixture[0];
				}
			}
			int matchCounter=1;
			for(roundNumber=0;roundNumber<totalNumberOfRounds;roundNumber++)
			{
				for(matchNumber=0;matchNumber<numberOfMatchesPerRound;matchNumber++)
				{
					info=matchCounter+",";
					info=info+fixtures[roundNumber][matchNumber].substring(0,1)+","+fixtures[roundNumber][matchNumber].substring(2,3);
					writeFile(info,fixtureGenerationFileName);
					matchCounter++;
				}
			}
		}
	}
/*	
	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	
	public static String getTeamName(int teamNumber, String teamFileName)
	{
		String teamName="";
		int arrayNum=0;
		int positionInArray=1;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		for(int i=0;i<arrayOfDetails.length;i++)
		{
			if(i%2==0)
			{
				arrayNum=Integer.parseInt(arrayOfDetails[i]);
				if(teamNumber==arrayNum)
					positionInArray=i+1;
			}	
		}	
		teamName=arrayOfDetails[positionInArray];
		return teamName;
	}

}
*/
	
	
	
	
	 
	
	
	

	

}



