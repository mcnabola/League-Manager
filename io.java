import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class io
{
	
	public static void main(String[] args)
	{
		// need to get some code to write and read from a csv file 
		
        FileWriter fileWriter = null;
        

	        try {
	            fileWriter = new FileWriter("C:\Users\Mark\Documents\ALeageManagement\League-Manager\usersadmin.csv");
	            //Write the CSV file header

	            fileWriter.append("Header");

	            //Add a new line separator after the header

	            fileWriter.append("\n");

	            //Write a new student object list to the CSV file
                /*
	            for (Student student : students) {

	                fileWriter.append(String.valueOf(student.getId()));

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(student.getFirstName());

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(student.getLastName());

	                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(student.getGender());

                fileWriter.append(COMMA_DELIMITER);

	                fileWriter.append(g);

	                fileWriter.append(NEW_LINE_SEPARATOR);

	            }
                 */
	 

	            System.out.println("CSV file was created successfully !!!");

             }   
		
		     catch (Exception e) 
			 {
	            System.out.println("Error in CsvFileWriter !!!");
	            e.printStackTrace();
	         } 
			finally 
			{

	            try 
				{
	                fileWriter.flush();
	                fileWriter.close();
	            } 
				catch (IOException e) 
				{

			
	                System.out.println("Error while flushing/closing fileWriter !!!");

	                e.printStackTrace();

	            }

	             

	        }
	}
}


