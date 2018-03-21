import java.io.*;
import javax.swing.*;
import java.util.*;
public class League_Manager 
{
    
	 private static int currentAdminNo;
	 final static String leagueFile="league.txt";
	 final static String adminFile="administrator.txt";
	 private static String item1;
     
	 public static void main(String[] args)throws IOException
	 {	
	   checkIfExists(adminFile);
	String username = JOptionPane.showInputDialog(null, "Enter username");
	String password = JOptionPane.showInputDialog(null, "Enter password"); 
	boolean isLoggedIn = loginMethod(username, password);
	if(isLoggedIn)
	{
		String [] initialOptions= { "Create League", "Edit/View League", "Remove League", "Exit Application" };
		String [] subOptions={"Fixture Generation", "View Table", "Input Results", "Main Menu" , "Exit Application"};
		String []subOptionsOfSubOptions={ "Input Results", "Edit Results","Main Menu" , "Exit Application" };
		boolean main = true;
		while(main)  // && not null 
		{	
		    int x=optionBoxs(initialOptions,"Choose an option");
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
					case 1: //displayTable();
					break;
					case 2: z=optionBoxs(subOptionsOfSubOptions,"Choose an option");
						switch (z)
						{
							case 0: //inputResults();
							break;
							case 1: //editResults();
							break;
							case 2: 
		                    break;
							case 3: main = false;
							break;
							
						}
					case 3: break;
					case 4: main = false;
					break;

				}

				break;
				case 2: removeLeague(Integer.toString(currentAdminNo));
				break;
				case 3: main = false; 
				break;

			}
			
		
		}
	        } 
		}

		 
		

      	public static boolean loginMethod1(String username,String password)
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
                outputBoxs(aFile.getName() + " does not exist.");
            else
			{	
			aFile.delete();
            }
    }

	 public static void removeLeague(String adminNumber)
	 {
         String [] temp=readFile("league.txt",adminNumber,0,1);
		 String leagueToRemove = dropDown(temp,"Choose a league to remove.");
		 ArrayList<String> leagues = new ArrayList<String>();
		 try{
		 Scanner s = new Scanner(new File("league.txt"));
		 String[] details;
		 while(s.hasNext())
		 {
			 String v = s.next();
			 details = v.split(",");
			 if (details[1].equals(leagueToRemove))
			 {
				 int idNumber = Integer.parseInt(details[2]);
			 }
			 else
			 {
				 leagues.add(v);
			 }
		 }
		 s.close();
		 }
		 catch(IOException e){}
		 try{
		 PrintWriter output = new PrintWriter("league.txt"); 
                 for (int i = 0; i<leagues.size();i++)
		 {
			 output.println(leagues.get(i));
		 }
                 output.close();
		 }
		 catch(IOException e)
		 {}
        //	 deleteFile(adminNumber+"_"+number+"_scoring.txt");
	//	 deleteFile(adminNumber+"_"+number+"_participants.txt");
	//	 deleteFile(adminNumber+"_"+number+"_fixtures.txt");
	//	 deleteFile(adminNumber+"_"+number+"_results.txt");
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
	public static int optionBoxs(String[] options,String whatYouWantItToSay)
	{
        int result = JOptionPane.showOptionDialog(null, whatYouWantItToSay, "League Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return result;
	}

	public static void checkIfExists(String fileName)throws IOException
	{
		File adminFile = new File(fileName);
		if (!(adminFile.exists()))
			adminFile.createNewFile();
	}

	public static void outputBoxs(int output)
	{
		JOptionPane.showMessageDialog(null,output);
	}
	 
	 public static String menuBox(String Options)
	 {
		 String input="";
		 input=JOptionPane.showInputDialog(null,Options);
		 if(input.equals(""))
		 {
			input=menuBox(Options);
		 }
		 return input;
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
     public static void writeFile(String output, String filename)   /// fileWriter   or   writeFile   -- what has sean already started with
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
	
	 
	
     	public static void addScoringScheme(int leagueNumber)
	{
		
		int win = menuBoxInt("Enter the number of points given for a win:");
		int draw = menuBoxInt("Enter the number of points given for a Draw:");
		int lose = menuBoxInt("Enter the number of points given for a Lose:");
		
		
		String fileName = (leagueNumber+"_scoring.txt");
		String output = (win+","+draw+","+lose);
		writeFile(fileName, output);	
	}
		
		
			public static boolean validateNumberInput(String text)
	{
		String result="";
		String pattern="[0-9]{1,2}";
		String message1 = "A number must be input.";
	    String message2 = "Format of user input is incorrect, a number between 1 and 99 is needed.";
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

	public static void fixtureGeneration()throws IOException
	{
	int numberOfTeams, totalNumberOfRounds, numberOfMatchesPerRound;
    int roundNumber;
	boolean added;
	int matchNumber=0;
	int homeTeamNumber, awayTeamNumber, even, odd;
    boolean additionalTeamIncluded = false;
	String [] selectionOfLeagues=readFile(leagueFile,"1",0,1);
	String whichLeaguer=dropDown(selectionOfLeagues,"Select a League");
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
		if (!(teamFile.exists()))
	{
		outputBoxs("There is no teams in this league.");
		added=addTeamsToLeague(whichLeague);
	}
	else
	{
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
	else
	{
	if (!(teamFile.exists()))
	{
		outputBoxs("There is no teams in this league.");
		added=addTeamsToLeague(whichLeague);
	}
	else
	{
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
	}
	
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
	
	}
	
