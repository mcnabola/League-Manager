import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
public class FinalLeague
{
	public static ArrayList<ArrayList<String>>  fixtureDetails;
	public static ArrayList<ArrayList<String>>  teamDetails;	
	private static int currentAdminNo;
	final static String leagueFile="league.txt";
	final static String adminFile="administrator.txt";
	private static String item1;
	public static ArrayList<ArrayList<String>>  teams;
        public static ArrayList<ArrayList<Integer>> fixtures;	
        public static ArrayList<ArrayList<Integer>> results;
        public static int [][] leaderBoard;
	 /**
	   *Gui for user to navigate through commands
	   *Input -user first logs in and then has numerous options through switch case
	   *Output-User input is fed into specific methods to return desired result
	   *
	   **/	
	public static void main(String [] args)throws IOException
	{
	checkIfExists(adminFile);
	String username = JOptionPane.showInputDialog(null, "Enter username");
	String password = JOptionPane.showInputDialog(null, "Enter password"); 
	boolean isLoggedIn = loginMethod(username, password);
	if(isLoggedIn)
	{
	String [] initialOptions= { "Create league", "Edit/view League", "Remove League" };
		String [] subOptions={" Fixture Generation", "View Table", "Input results", "Add teams","Back to Main Menu"};
	        boolean main = true;
		int x=0;
		while(main&&x==0||x==1||x==2||x==3)  // && not null 
		{	
		    x=optionBoxs(initialOptions,"Choose an option");
		    int y=0;
		    int z=0;
		
<<<<<<< HEAD
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
	     out.print(output+"\n"); 
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
=======
		    switch (x)
>>>>>>> 62fdb52b007807757c791c257f9e67d65d319aee
		    {
			    case 0: createNewLeague();
		            break;
			    case 1: y=optionBoxs(subOptions,"Choose an option");
			
			    switch (y)
			    {
					case 0: fixtureGeneration();
					break;
					case 1:String [] selectionOfLeaguers=readFile(leagueFile,(Integer.toString(currentAdminNo)),0,1);
							String whichLeaguers=dropDown(selectionOfLeaguers,"Select a League");
							if(whichLeaguers.equals(""))
								outputBoxs("you have not selected a league or no leagues exist.");									
							else
							{
								String [] whichLeagues=readFile(leagueFile,whichLeaguers,1,2);
								int whichLeague=Integer.parseInt(whichLeagues[0]);
								generateTable(whichLeague);
							}    
					
					break;
					case 2:String [] selectionOfLeagues=readFile(leagueFile,(Integer.toString(currentAdminNo)),0,1);
							String whichLeaguer=dropDown(selectionOfLeagues,"Select a League");						
							 if(!(whichLeaguer!=null))
							{
								break;
							}
							else
							{
								String [] whichLeagues=readFile(leagueFile,whichLeaguer,1,2);
								int whichLeague=Integer.parseInt(whichLeagues[0]);
								editResults(whichLeague);
							}   
				        break;
					case 3: String [] selectionsOfLeagues=readFile(leagueFile,(Integer.toString(currentAdminNo)),0,1);
							String whichLeaguerx=dropDown(selectionsOfLeagues,"Select a League");						
							if(!(whichLeaguerx!=null))
							{
							break;
							}
							else
							{
								String [] whichLeaguesx=readFile(leagueFile,whichLeaguerx,1,2);
								int whichLeaguex=Integer.parseInt(whichLeaguesx[0]);
								addTeamsToLeague(whichLeaguex);
							}   
					break;
					case 4: break;
				}
                                break;
			
				case 2: removeLeague(Integer.toString(currentAdminNo));//removeLeague();
				break;
				}
			}
		}
		
	}
	
	 /**
	   *Writes the input given to the file specified
	   *input to method:passed a string of what the user wants to qrite to file and a filename of where the input is printed to.
	   *the input is is wrote to file specified
	   *
	   **/		 
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
	 /**
	   *a menu box which allows the user to enter a word or numbers and is returned as a string.
	   *input:input is a string in which is displayed in the menubox
	   *output:what the user enters is returned as a string.
	   *includes checks to see if the user enters nothing and uses recursion
	   **/		 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		if(!(input != null))
		 {
			outputBoxs("You must enter a word/number");
			input=menuBox(Options);
		 }
		else if(input.equals(""))
		 {
			outputBoxs("You must enter a word/number");
			input=menuBox(Options);
		 }
		 else if(input.contains(","))
		 {
			 outputBoxs("You have entered a invalid character");
			input=menuBox(Options);
		 }
		 return input;
	 }

	 /**
	   *creates a new league and the info is wrote to file
	   *input: the user has to input the name of the league
	   *output:league info is wrote to the league file
	   *also calls addTeamsToLeague which allows the user to add teams to the league made.
	   **/	
	public static void createNewLeague()throws IOException
	{
		String leagueName=""; 
		boolean added;
		String leagueFileInput="";
		File leagueFilers = new File(leagueFile);
		if (!(leagueFilers.exists()))
		{
			leagueFilers.createNewFile();
			leagueName=menuBox("Enter your league name:");
			if (!leagueName.equals(""))
			{
			added=addTeamsToLeague(1);
			if(added)
			{
			leagueFileInput=currentAdminNo+","+leagueName+",1";
			writeFile(leagueFileInput,leagueFile);
			outputBoxs(leagueName+" has been created.");
			}
			}
			else
			{
				outputBoxs("You must enter a team name.");
				deleteFile(leagueFile);
			}
		}
		else
		{
			leagueName=menuBox("Enter your league name:");
			if (!leagueName.equals(""))
			{
			added=addTeamsToLeague(getNumberOfLeaguesMade()+1);
			if(added)
			{
			leagueFileInput=currentAdminNo+","+leagueName+","+(getNumberOfLeaguesMade()+1);
			writeFile(leagueFileInput,leagueFile);
			outputBoxs(leagueName+" has been created.");
			}
			}
			else
			{
			outputBoxs("you must enter a team name.");
			}
		}
	}
	 /**
	   *gets the number of leagues made so far by each administrator
	   *input:passed the current administrator number
	   *output:returns an integer of the amount of leagues made by that specific admin.
	   *
	   **/	
	public static int getNumberOfLeaguesMade()
	{
		int numberOfLeagues=0;
		String adminNumberAsString="";
		adminNumberAsString=adminNumberAsString+currentAdminNo;
		String [] temp=readFile(leagueFile,adminNumberAsString,0,2);
		if(temp[temp.length-1].equals(""))
		{
			numberOfLeagues=0;
		}
		else
		{
			numberOfLeagues=Integer.parseInt(temp[temp.length-1]);
		}
		return numberOfLeagues;
	}
	 /**
	   *reads the file and adds it all to a string.
	   *input: the file in which the user wants to read.
	   *output:returns a string of everything in the file
	   *
	   **/	
	public static String readFile(String textFile)
	{
		String fileText="";
		String x="";
		try
		{
		FileReader reader=new FileReader(textFile);
		Scanner in=new Scanner(reader);
		while(in.hasNext())
		{
			x=in.nextLine();
			fileText=fileText+x+",";
		}
		in.close();
		reader.close();
		}
		catch(Exception e)
		{}
		return fileText;
	}
	 /**
	   *outputs a string
	   *input:a string of what you want to diplay
	   *
	   *
	   **/	
	public static void outputBoxs(String output)
	{
	     JOptionPane.showMessageDialog(null, output);	
	}
	 /**
	   *asks the user how many teams they want to add to the league and writes it to the file.
	   *input:the leagueNumber in which they want to add teams to.
	   *output:the teams are wrote to the specific league participant file.
	   *
	   **/	
	public static boolean addTeamsToLeague(int whichLeagues)throws IOException
	{
		boolean added=false;
		String info=""; 
		String teamName=""; 
		String teamFileInfo=""; 
		String teamFileName="";
		int whichLeague=whichLeagues;
		teamFileName=currentAdminNo+"_"+whichLeague+"_participants.txt";
		File teamFile = new File(teamFileName);
		if (!(teamFile.exists()))
		{
			String numberOfTeams=menuBox("Enter the amount of teams/players:");
			boolean isRightFormat=validateNumberInput(numberOfTeams);
			if(isRightFormat)
			{
				int numberOfTeams2=Integer.parseInt(numberOfTeams);
				if(numberOfTeams2>1)
				{
				for(int i=0;i<numberOfTeams2;i++)
				{
				info="enter team/player number:"+(i+1);
				teamName=menuBox(info);
				teamFileInfo=(i+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
				}
				added=true;
				}
				else
				{
				    outputBoxs("number must be between 2 and 99");
				}
		    }
		}
		else
		{
		outputBoxs("Warning this will regenerate fixtures if you have already done so.");
		String numberOfTeams=menuBox("Enter the amount of teams/players:");
		boolean isRightFormat=validateNumberInput(numberOfTeams);
		if(isRightFormat)
		{
		int numberOfTeams2=Integer.parseInt(numberOfTeams);
		int counter=getNumberOfTeams(teamFileName);
		if(counter+numberOfTeams2>99)
		{
			outputBoxs("cannot have more than 99 teams");
		}
		else
		{
			for(int i=0;i<numberOfTeams2;i++)
			{
				info="enter team/player number:"+(counter+1);
				teamName=menuBox(info);
				teamFileInfo=(counter+1)+","+teamName;
				writeFile(teamFileInfo,teamFileName);
				counter++;
				
			}
			added=true;
			outputBoxs("please reselect your league");
			fixtureGeneration();
		}
		}
		}
		return added;
	}
	 /**
	   *generates a fixture list for a given league in which the user selects.
	   *input:the user has to select a league in which they want to generate a fixture list for.
	   *output:the fixture list is output to a specific fixture generation file of the specified league.
	   *
	   **/	
	public static void fixtureGeneration()throws IOException
	{
	int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
        int roundNumber;
	boolean added;
	String currentAdminNoAsString="";
	currentAdminNoAsString=currentAdminNoAsString+currentAdminNo;
	int matchNumber=0;
	int homeTeamNumber, awayTeamNumber, even, odd;
        boolean additionalTeamIncluded = false;
	String [] selectionOfLeagues=readFile(leagueFile,currentAdminNoAsString,0,1);
	String whichLeaguer=dropDown(selectionOfLeagues,"Select a League");
	if(whichLeaguer.equals(""))
	{
		outputBoxs("you have not selected a league or no leagues exist.");
	}
	else
	{
	String [] whichLeagues=readFile(leagueFile,whichLeaguer,1,2);
	int whichLeague=Integer.parseInt(whichLeagues[0]);
	String teamFileName=currentAdminNo+"_"+whichLeague+"_participants.txt";
	String fixtureGenerationFileName=currentAdminNo+"_"+whichLeague+"_fixtures.txt";
	File teamFile = new File(teamFileName);
	File fixtureFile = new File(fixtureGenerationFileName);
	String [][] fixtures;
        String [][] revisedFixtures;
        String []   elementsOfFixture;
        String fixtureAsText;
	String info="";
	if(fixtureFile.exists())
	{
		outputBoxs("Fixtures alredy exist for this league, it will now be regenerated");
		deleteFile(fixtureGenerationFileName);
	}
	else if (!(teamFile.exists()))
	{
		outputBoxs("There is no teams in this league.");
		added=addTeamsToLeague(whichLeague);
	}
	int selection=getNumberOfTeams(teamFileName);	
        if (selection != 0)
        {
           numberOfTeams = selection; 
           if (numberOfTeams % 2 == 1)
           {
	       numberOfTeams++;
	       additionalTeamIncluded = true;
           }
	   totalNumberOfRounds     = numberOfTeams - 1;
           numberOfMatchesPerRound = numberOfTeams / 2;
           fixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];  
        
           for (roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++) 
           {
               for (matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++) 
	       {
                   homeTeamNumber = (roundNumber + matchNumber) % (numberOfTeams - 1);
		   awayTeamNumber = (numberOfTeams - 1 - matchNumber + roundNumber) % (numberOfTeams - 1);
                   if (matchNumber == 0) 
                   awayTeamNumber = numberOfTeams - 1;
		   fixtures[roundNumber][matchNumber] = (homeTeamNumber + 1) + "," + (awayTeamNumber + 1);
               }
           } 
	   revisedFixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];
           even = 0;
           odd = numberOfTeams / 2;
           for (int i = 0; i < fixtures.length; i++) 
           {
               if (i % 2 == 0) 	
               revisedFixtures[i] = fixtures[even++];
               else 				
                   revisedFixtures[i] = fixtures[odd++];
           }
       fixtures = revisedFixtures;
       int matchCounter=1;
       for (roundNumber = 0; roundNumber < fixtures.length; roundNumber++) 
       {
         if (roundNumber % 2 == 1) 
	     {
	       fixtureAsText = fixtures[roundNumber][0];
	       elementsOfFixture = fixtureAsText.split(",");
           fixtures[roundNumber][0] = elementsOfFixture[1] + "," + elementsOfFixture[0];
	     }
       }
		for (roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++) 
       {
		   for (matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++) 
		    {
					info=matchCounter+",";
					info=info+fixtures[roundNumber][matchNumber];
					writeFile(info,fixtureGenerationFileName);
					matchCounter++;	   
			}	
	   }
	}
	}
}
	 /**
	   *gets the number of teams in a league.
	   *input:the user inputs the name of the file they want to check.
	   *output: returns a integer of the amount of teams.
	   *
	   **/	
	public static int getNumberOfTeams(String teamFileName)
	{
		int numberOfTeams=0;
		String [] arrayOfDetails=readFile(teamFileName).split(",");
		numberOfTeams=Integer.parseInt(arrayOfDetails[arrayOfDetails.length - 2]);
		return numberOfTeams;
	}
	 /**
	   *returns a teamname after been given the team number and the filename
	   *input: passed the teamNumber of which the user wants to get the name of and the filename they want to search.
	   *output:returns a teamname in the form of a string.
	   *
	   **/	
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
	 /**
	   * Compares username input and password input with values stored in a file
	   * Gives the user three attempts to enter correct details
	   * Input: User inputs a username string and a password string
	   * Output: If inputted details are found in admin file, the method outputs a true boolean
	   *
	   **/	
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
	 /**
	   * Overloaded readFile method to check if two strings are found in the same line in a file
	   * Input: Takes a filename, two strings, and their positions in an array 0-2
	   * Output: Returns a true Boolean if both strings are found in the fileElements array
	   **/
        public static Boolean readFile(String fileName, String str1, String str2, int pos1, int pos2)
   	{
		String[] fileElements;	
		boolean found = false;
		Scanner in;
		FileReader read;
		try
		{
	        read = new FileReader(fileName);
			in = new Scanner(read);
			while(in.hasNext())
			{    
		        fileElements= (in.nextLine()).split(",");
				
				if (fileElements[pos1].equals(str1) && fileElements[pos2].equals(str2))
				{
					found = true;
					item1 = fileElements[0]; // Admin#, League#, fixture#.
					break;
				}
			}
			in.close();
			read.close();	
		 }
		 catch (Exception e)
		 {}
		
		return found;
	}
	 /**
	   *check if a file exists and if it doesnt then it creates the file
	   *input:passed the filename in which it is checked.
	   *output:creates a file if it doesnt exist.
	   *
	   **/	
	
	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}
	 /**
	   *Check if a file exists and returns a boolean value
	   *Input -the filename in question
	   *output-boolean dependant on if the file exists
	   *
	   **/
	public static boolean checkIfItExists(String fileName)throws IOException
	{
		File file = new File(fileName);
		boolean found =false;
		if ((file.exists()))
		{
			return true;
		}
			
			else return false;
			
	}
	
	 /**
	   * Function that allows the user to select a box option of their choice. 
	   * Input - A string array of options for the user to choose, and a string for displaying a comment to the user. 
	   * Output - returns the integer position of the choice that the user made.
	   *
	   **/	
	public static int optionBoxs(String[] options,String whatYouWantItToSay)
	{
        int result = JOptionPane.showOptionDialog(null, whatYouWantItToSay, "League Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}
	 /**
	   *A method to validate the users input when a number in form of string is required
	   *input-the string the user has entered
	   *output-a boolean dependant on whether the input was valid
	   *
	   **/	
	public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[0-9]{0,10}";
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
	        	{
					if (!text.matches(pattern)) 
					{
					result = message2;
					outputBoxs(message2);
					}
				
					else
					{
					verified=true;
					}
				}
			}
		}
		return verified ;
	}
	 /**
	   * Searching function that is versatile to be used in comma delimited files.
	   * Input - textFile is the file you want to search through, searchedItem is a string of what you want to search for in the file, itemPositionNo is the position of the "searchedItem" in 
	   *         the comma delimited file, returnedItemNo is used once the "searchedItem" is found in the "itemPositionNo" -- it returns all items as an array where this conditional is held. 
	   * output - A String array of all items in returnedItemNo, where "searchedItem" is found in index "itemPositionNo" of the CSV file.
	   **/
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
	   *Generates leaderboard of specified league by calling other methods
	   *input-League number the user wants to generate the leaderboard for
	   *output-this has no output but calls all the necessary methods to display leaderboards=
	   *
	   **/	
	public static void generateTable(int leagueNumber)throws IOException
	{
		boolean readFile; 
		readFile = readFilesIntoArrayLists(leagueNumber);
		if (!readFile)
		System.out.println("One or more files do not exist.");
			else
			{
			createEmptyLeaderBoard();
			processResults();
			orderLeaderBoard();
			displayLeaderboard();
			}
	}
	 /**
	   *Method to check if all relevant files exist to genrate table,If so it reads these files into an ArrayList	
	   *input-The league Number the user wishes to generate the table for
	   *output-This creates arraylists with the files data in them and also returns a boolean on whether they existed
	   *
	   **/	
	 public static boolean readFilesIntoArrayLists( int leagueNumber) throws IOException
  {
    String filename1 = currentAdminNo+"_"+leagueNumber+"_participants.txt";
    String filename2 = currentAdminNo+"_"+leagueNumber+"_fixtures.txt";
    String filename3 = currentAdminNo+"_"+leagueNumber+"_Results.txt";
    
    String fileElements[];
	File inputFile1 = new File(filename1);
	File inputFile2 = new File(filename2);
	File inputFile3 = new File(filename3);
	
	teams = new ArrayList<ArrayList<String>>();
	teams.add(new ArrayList<String>());
        teams.add(new ArrayList<String>());
  
        fixtures = new ArrayList<ArrayList<Integer>>();
	fixtures.add(new ArrayList<Integer>());
        fixtures.add(new ArrayList<Integer>());
        fixtures.add(new ArrayList<Integer>());
    
        results = new ArrayList<ArrayList<Integer>>();
	results.add(new ArrayList<Integer>());
        results.add(new ArrayList<Integer>());
        results.add(new ArrayList<Integer>());
    
	if (inputFile1.exists() && inputFile2.exists() && inputFile3.exists())
	{
	  Scanner in;
	  in = new Scanner(inputFile1);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    teams.get(0).add(fileElements[0]);  
	    teams.get(1).add(fileElements[1]);  
	  } 
	  in.close();
	  in = new Scanner(inputFile2);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    fixtures.get(0).add(Integer.parseInt(fileElements[0]));  
	    fixtures.get(1).add(Integer.parseInt(fileElements[1]));  
	    fixtures.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  in = new Scanner(inputFile3);
	  while(in.hasNext())
	  {
	    fileElements = (in.nextLine()).split(",");
	    results.get(0).add(Integer.parseInt(fileElements[0]));  
	    results.get(1).add(Integer.parseInt(fileElements[1]));  
	    results.get(2).add(Integer.parseInt(fileElements[2]));  
	  } 
	  in.close();
	  return true;
    }
    else
    return false;
  }
	 /**
	   *Creates a generic empty LeaderBoard based on amount of teams
	   *Input-no input needed as it is called from another method
	   *output-Creates a leaderboard of the correct size
	   *
	   **/  
          public static void createEmptyLeaderBoard()
          {
	  // find out the number of teams/players which will determine 
	  // the number of rows  
          int rows = teams.get(0).size();
	  int columns = 14;  
	  leaderBoard = new int[rows][columns];
	  // place team numbers in column 0 of leader board
	  for (int i = 0; i < leaderBoard.length; i++)
              leaderBoard[i][0] = Integer.parseInt(teams.get(0).get(i));
          }	  
	 /**
	   *Uses the arraylists to process results and determine the points to award
	   *input-no input needed
	   *output-This method only calculates which method to call next based on the score of a game
	   *
	   **/  
  public static void processResults()
  {
	 int win = menuBoxInt("Enter Points for a win");
	 int loss = menuBoxInt("Enter Points for a loss");
	 int draw = menuBoxInt("Enter Points for a draw");

	int fixtureNumber, homeTeamScore, awayTeamScore, homeTeamNumber, awayTeamNumber;
	int position;
	for (int i = 0; i < results.get(0).size(); i++)  
    {
	  fixtureNumber  = results.get(0).get(i);
	  homeTeamScore  = results.get(1).get(i);
	  awayTeamScore  = results.get(2).get(i);
	  position       = fixtures.get(0).indexOf(fixtureNumber);
	  homeTeamNumber = fixtures.get(1).get(position);
	  awayTeamNumber = fixtures.get(2).get(position);
	  if (homeTeamScore == awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,1,0,homeTeamScore,awayTeamScore,draw);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,1,0,homeTeamScore,awayTeamScore,draw);
	  }  
	  else if (homeTeamScore > awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,1,0,0,homeTeamScore,awayTeamScore,win);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,0,1,homeTeamScore,awayTeamScore,loss);  
	  }  
	  else
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,0,1,homeTeamScore,awayTeamScore,loss);
		recordFixtureResultForAwayTeam(awayTeamNumber,1,0,0,homeTeamScore,awayTeamScore,win);  
	  }    
    }
  }	 
    /**
     * Method that takes an int input in a gui box , if the result is wrong it asks the user again until it's correct.
     * input - the desired message to prompt users to enter a int
     * output - the validated int result
     **/
    public static int menuBoxInt(String whatYouWantItToSay)
    {
		int g;
		String h = JOptionPane.showInputDialog(null,whatYouWantItToSay);
		boolean check = validateNumberInput(h);
		if (check ==  true)
		{
			g = Integer.parseInt(h);
			
		}
		else
		{
		    g =	menuBoxInt("Please re-enter a valid number.");
		
		}
		return g;
    }
	
          /**
	   *This method fills the leaderboard with input based on the home team's perspective
	   *input-HomeTeamNumber,Points for win,loss and draw,Home team score ,away team score and points
	   *output-This fills the leaderboard partially
	   *
	   **/      
          public static void recordFixtureResultForHomeTeam(int hTN, int w, int d, int l, 
 	                                                int hTS, int aTS, int p)
          {
	  leaderBoard[hTN-1][1]++;        			// gamesPlayed
	  leaderBoard[hTN-1][2]+= w;      			// homeWin
	  leaderBoard[hTN-1][3]+= d;      			// homeDraw
	  leaderBoard[hTN-1][4]+= l;      			// homeLoss
	  leaderBoard[hTN-1][5]+= hTS;    			// homeTeamScore
	  leaderBoard[hTN-1][6]+= aTS;    			// awayTeamScore
	  leaderBoard[hTN-1][12] += (hTS - aTS);    	// goalDifference
	  leaderBoard[hTN-1][13] += p;    			// points
          }
 	 /**
	   *This method fills the leaderboard with input based on the Away team's perspective
	   *input-AwayTeamNumber,Points for win,draw and loss,Home team score ,away team score and points
	   *output-This fills the leaderboard partially
	   *
	   **/
  public static void recordFixtureResultForAwayTeam(int aTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderBoard[aTN-1][1]++;        			// gamesPlayed
	leaderBoard[aTN-1][7]+= w;      			// awayWin
	leaderBoard[aTN-1][8]+= d;      			// awayDraw
	leaderBoard[aTN-1][9]+= l;      			// awayLoss
	leaderBoard[aTN-1][10]+= aTS;    			// awayTeamScore
	leaderBoard[aTN-1][11]+= hTS;    			// homeTeamScore
	leaderBoard[aTN-1][12] += (aTS - hTS);    	// goalDifference
	leaderBoard[aTN-1][13] += p;    			// points  
  }	
	 /**
	   *Orders the leaderboard based on points using nested for loops
	   *input-No input neccessary
	   *output-Reorders the leaderboard
	   *
	   **/  
  public static void orderLeaderBoard()
  {
	int [][] temp = new int[leaderBoard.length][leaderBoard[0].length];
    boolean finished = false;
    while (!finished) 
    {
      finished = true;
      for (int i = 0; i < leaderBoard.length - 1; i++) 
      {
        if (leaderBoard[i][13] < leaderBoard[i + 1][13])
        {
          for (int j = 0; j < leaderBoard[i].length; j++) 
          {
            temp[i][j]            = leaderBoard[i][j];
            leaderBoard[i][j]     = leaderBoard[i + 1][j];
            leaderBoard[i + 1][j] = temp[i][j];
          }
          finished = false;
        }
      }
    }
  }	  
	 /**
	   *Displays the finalised formatted leaderboard 
	   *input-No input needed	
	   *output-Prints the leaderboard on the cmd
	   *
	   **/	  
  public static void displayLeaderboard()
  {
	int aTeamNumber;
	String aTeamName, formatStringTeamName;
	String longestTeamName       = teams.get(1).get(0);
        int    longestTeamNameLength = longestTeamName.length();
    
    for (int i = 1; i < teams.get(1).size(); i++)
    {
	  longestTeamName = teams.get(1).get(i);  
          if (longestTeamNameLength < longestTeamName.length())
          longestTeamNameLength = longestTeamName.length();
    }
	
	//String b[][] = new String[teams.get(0).size()][14];
	String b[][] = new String[leaderBoard.length][14];
	 
	 
	  
	 /// For the JTable Output -- loading the team names into the first column of a 2d Array
	 for (int i = 0; i < leaderBoard.length; i++)
         {

		  aTeamNumber       = leaderBoard[i][0];
                  aTeamName = teams.get(1).get(aTeamNumber - 1); 
		  b[i][0] =aTeamName;
	 }
	 
	   
	 /// Have to write the leaderBoard 2d int array into the rest of the b string array that will be used by the jtable
	 for (int i=0; i<leaderBoard.length; i++)  
	 {
		 for (int y=1; y<leaderBoard[i].length; y++)//columns need the [] called too and use the outside loops i for the call value
		 {
			 String x = Integer.toString(leaderBoard[i][y]);
			 b[i][y] = x;
		 }
		 
	 }
	 
	 
	 JFrame f = new JFrame("LeaderBoard"); // (league_Name+" LeaderBoard")
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Container content = f.getContentPane();
         Object columns[] = {"Teams","GP","HW","HD","HL","GF","GA","AW","AD","AL","GF","GA","GD","TP"};
         JTable table = new JTable(b, columns);
	 JOptionPane.showMessageDialog(null, new JScrollPane(table));
     	  
	
    
  }
	

	  /**
	   * Displays a list of fixtures in the chosen league and allows the user to enter a home score and away
	   * score for the fixture. Does not display the fixtures containing byes
	   * Input: Takes in the chosen league number
	   * Output: Edited results are written to a results file including the fixture number, home score and away score
	   **/
	public static void editResults(int leagueNumber) throws IOException 
	{
		boolean resultExists = false;
		String [] fixtureDisplay 		  = readFixtures(Integer.toString(leagueNumber));
		String [] fixtureDisplayNoNumbers = new String[fixtureDisplay.length];

		String fixture					  = currentAdminNo + "_" + leagueNumber+"_fixtures.txt";
		String resultsFileName            = currentAdminNo + "_" + leagueNumber + "_results.txt";

		String fixtureNumber = "";
		String pattern 		 = "[0-9]{1,}";
		int fixtureChoice = 0, choice = 0;
		int homeScore  = 0, awayScore = 0;
		int indexStr = 0;
		String test  = "";
		
		boolean checker=checkIfItExists(fixture);
		if (checker==true)
		{
			for (int i = 0;i< fixtureDisplayNoNumbers.length;i++)
			{
				indexStr 				   = 	fixtureDisplay[i].indexOf(".") + 1;
				fixtureDisplayNoNumbers[i] = 	fixtureDisplay[i].substring(indexStr);
			}
			
			String temp = dropDown(fixtureDisplayNoNumbers, "Select a fixture");
			if(!(temp!=null))//Allows user to cancel
				return;
			for (int j = 0;j< fixtureDisplay.length;j++)
			{
				if (fixtureDisplay[j].contains(temp))
				{
					test = 		fixtureDisplay[j];
					test = 		test.substring(0, (test.indexOf(".")));
				}
			}
			
			fixtureNumber= test;
			resultExists = readFile(resultsFileName, fixtureNumber, 0);
			
			if (resultExists == true)
			{		
				choice = JOptionPane.showConfirmDialog(null, "Already entered result for this fixture, Do you want to edit the result?", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);	
				if (choice == JOptionPane.YES_OPTION) //If yes
				{
				removeLineFromFile(resultsFileName, fixtureNumber, 0);
							
				
				homeScore = 	menuBoxInt("Enter home score:");	
			
				awayScore = 	menuBoxInt("Enter away score:");		
				
				String output = 	fixtureNumber + "," + homeScore + "," + awayScore;
				writeFile(output,resultsFileName);
				}
				
				editResults(leagueNumber);
			}	
			else 
			{
				
			
				homeScore = 	menuBoxInt("Enter home score:");	
				
			
				awayScore = 	menuBoxInt("Enter away score:");
			
				String output = 	fixtureNumber + "," + homeScore + "," + awayScore;
				writeFile(output,resultsFileName);

				editResults(leagueNumber);
			}		
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Generate Fixtures First");
		}
	}
	
	  /**
	   * reads details of fixtures from file into a multidimensional arraylist 
	   * fixtureDetails takes fixture number, home team number and away team number
	   * teamDetails takes fixture number and home team name and away team name
	   * Input: takes in league number to determine which files to search
	   * Output: returns an array containing fixture number + home team V away Team
	   **/
	public static String[] readFixtures(String leagueChoice) //NEW READFIXTURES METHOD
	{
		fixtureDetails = new ArrayList<ArrayList<String>>(); //MAKE GLOBAL
		fixtureDetails.add(new ArrayList<String>());
		fixtureDetails.add(new ArrayList<String>());		
		fixtureDetails.add(new ArrayList<String>());
		
		teamDetails =   new ArrayList<ArrayList<String>>(); //MAKE GLOBAL
		teamDetails.add(new ArrayList<String>());
		teamDetails.add(new ArrayList<String>());		
		teamDetails.add(new ArrayList<String>());
		
		String homeTeam = "", awayTeam = "", fixtureNumber = "";
		String temp1 	= "", temp2 = "";
		String fileElements[] = {""};
		String fixtureDisplay [] = {""};
		String currentLeagueParticipants = currentAdminNo + "_" + leagueChoice + "_participants.txt";
		int teamCount = getNumberOfTeams(currentLeagueParticipants);
		try
		{
			File aFile = new File(currentAdminNo + "_" + leagueChoice +  "_fixtures.txt");
			if (aFile.exists())
			{
				Scanner in = new Scanner(aFile);
			
				while (in.hasNext())
				{
					fileElements = in.nextLine().split(",");

					if (Integer.parseInt(fileElements[1]) > teamCount || Integer.parseInt(fileElements[2]) > teamCount)
					{
					}
					else
					{
						fixtureDetails.get(0).add(fileElements[0]);  
						fixtureDetails.get(1).add(fileElements[1]);
						fixtureDetails.get(2).add(fileElements[2]); 
						
						temp1 = getTeamName(Integer.parseInt(fileElements[1]), currentLeagueParticipants);
						temp2 = getTeamName(Integer.parseInt(fileElements[2]), currentLeagueParticipants);
						
						teamDetails.get(0).add(fileElements[0]);
						teamDetails.get(1).add(temp1);
						teamDetails.get(1).add(temp2);
			
					}
				}
				in.close();				
				fixtureDisplay = new String[fixtureDetails.get(0).size()];
				for (int count = 0;count < fixtureDetails.get(0).size();count++)
				{	
					fixtureNumber = fixtureDetails.get(0).get(count);
					homeTeam 	  = 	getTeamName(Integer.parseInt(fixtureDetails.get(1).get(count)), currentLeagueParticipants);
					awayTeam      =		getTeamName(Integer.parseInt(fixtureDetails.get(2).get(count)), currentLeagueParticipants);
					
				
					fixtureDisplay[count] = (fixtureNumber + "." + homeTeam + " V " + awayTeam);
				}
			}
		}
		catch(Exception e)
		{}
		return fixtureDisplay;
	}
	 /**
	   * Searches a file for a given string and recreates the file without the chosen file included
	   * Input: takes the file name to search, the string to delete and the position in the array it is located in
	   * Creates the new file with the same name as the old file without the deleted line
	   **/
	public static void removeLineFromFile(String fileName, String toDel, int pos) //Params file name, String to delete, position of string
	{
		try {
		String[] fileElements;
		String line = "";
        File inFile = new File(fileName);
          if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return;
          }
          File tempFile = new File("temp.txt");
          BufferedReader br = new BufferedReader(new FileReader(inFile));
          PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
          while ((line = br.readLine()) != null) 
		  {
			fileElements = line.split(","); 
            if (!fileElements[pos].equals(toDel)) {
              pw.println(line);
              pw.flush();
            }
          }
          pw.close();
          br.close();
          //Delete the original file
          if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
          } 
          //Rename the new file to the filename the original file had.
          if (!tempFile.renameTo(inFile)) {
            System.out.println("Could not rename file");
          }
        } catch (FileNotFoundException ex) {
          ex.printStackTrace();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
	}	
		
	 
		
	 /**
	   * Search function of a comma delimited text file that returns a boolean based on the search parameters. 
	   * input - textFile, desired search file , searchedItem is a string of a item you want to find, itemPositionNo is the position in the comma delimited sequence to be searched in 
	   * Output - returns a boolean whether or not the "searchedItem" is found in "itemPositionNo"
	   *
	   **/
	public static Boolean readFile(String textFile, String searchedItem, int itemPositionNo)
    	{
		boolean found = false;
		try
		 {
			item1 = "";
	        FileReader reader=new FileReader(textFile);
			Scanner in=new Scanner(reader);
			while(in.hasNext())
			{    
		        String fileText=in.nextLine();
		        String[] split = fileText.split(",");
				if (split[itemPositionNo].equals(searchedItem))
				{
					found = true;
					item1 = split[0]; // Admin#, League#, fixture#.
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
	 /**
	   * Removes a League from a user selected dropDown 
	   * input - the adminNumber, this is the admin who wants the option of removing a league
	   * output - there is no output - the method removes the league and its assosiating files from the system 
	   *
	   **/
public static void removeLeague(String adminNumber)
	 {
		 // load all the leagues that a admin has access to into a array
                 String [] temp=readFile("league.txt",adminNumber,0,1);		// adminNumber 
		 // pass array with all of adminNumber's Leagues into dropdown
		 String leagueToRemove = dropDown(temp,"Choose a league to remove.");
		
		 // load league names into arraylist except the one league you wish to remove. (!leagueToRemove)
		 ArrayList<String> leagues = new ArrayList<String>();
		 int idNumber=0;
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
				 idNumber = Integer.parseInt(details[2]);
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
		 
		 // deleting the assosiated files with each league
		 deleteFile(adminNumber+"_"+idNumber+"_participants.txt");
		 deleteFile(adminNumber+"_"+idNumber+"_fixtures.txt");
		 deleteFile(adminNumber+"_"+idNumber+"_results.txt");
		 
     }
	 /**
	   * Creates a dropdown menu with a list of options to choose, returns the string of the value the user choose
	   * String[] options is the list of things you want the user to choose from
	   * String dialogText is the message you want displayed to guide the user on the dropdown box
	   **/
	 public static String dropDown(String[] options, String dialogText)
	 {
		 String selected="";
		 if(options.length==0)
		 {
			 
		 }
		 else
		 {
		 selected = (String) JOptionPane.showInputDialog(null, dialogText,"Input",1,null,options,options[0]);  
		 }
		 return selected;
	 }
	 /**
	   * Delete File removes files from the users computer.
	   * Input - String of the file name that you want removed.
	   * Output - Void no output to the program but removes file from the system computer.
	   *
	   **/
	 public static void deleteFile(String deleteFile)
	{
	    String filename = deleteFile;  
        File aFile = new File(filename);
            if (!(aFile.exists()))
                outputBoxs(aFile.getName() + " does not exist.");
            else if(aFile.delete())
                outputBoxs("Operation to Remove: Successful");
            else
                outputBoxs("Operation to delete file failed.");
    }
	 
		
}
