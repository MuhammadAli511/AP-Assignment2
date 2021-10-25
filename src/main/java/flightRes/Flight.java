package flightRes;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Flight {
	String flight_Code;
	String origin;
	String destination;
	String seats;
	String flight_Date;
	String flight_Time;
	String economy_Price;
	String economy_Seats;
	String business_Price;
	String business_Seats;
	String first_Price;
	String first_Seats;
	
	public void createFlight()
	{
		Scanner input_Object = new Scanner(System.in);
		Scanner input_Object1 = new Scanner(System.in);
		
		System.out.print("Enter flight code : ");
		flight_Code = input_Object.nextLine();
		System.out.print("Enter departure city : ");
		origin = input_Object.nextLine();
		System.out.print("Enter your arrival city : ");
		destination = input_Object.nextLine();
		
		
		int temp_DateTime;
		System.out.print("Enter departure day (1-31) : ");
		temp_DateTime = input_Object1.nextInt();
		flight_Date = Integer.toString(temp_DateTime);
		flight_Date += "/";
		System.out.print("Enter departure month (1-12) : ");
		temp_DateTime = input_Object1.nextInt();
		flight_Date += Integer.toString(temp_DateTime);
		flight_Date += "/";
		System.out.print("Enter departure year : ");
		temp_DateTime = input_Object1.nextInt();
		flight_Date += Integer.toString(temp_DateTime);
		

		System.out.print("Enter departure hour (0-23) : ");
		temp_DateTime = input_Object1.nextInt();
		flight_Time = Integer.toString(temp_DateTime);
		flight_Time += ":";
		System.out.print("Enter departure minute (0-59) : ");
		temp_DateTime = input_Object1.nextInt();
		flight_Time += Integer.toString(temp_DateTime);
		
		int temp_Price;
		System.out.print("Enter economy class seat price : ");
		temp_Price = input_Object1.nextInt();
		economy_Price = Integer.toString(temp_Price);
		
		System.out.print("Enter business class seat price : ");
		temp_Price = input_Object1.nextInt();
		business_Price = Integer.toString(temp_Price);
		
		System.out.print("Enter first class seat price : ");
		temp_Price = input_Object1.nextInt();
		first_Price = Integer.toString(temp_Price);
		
		
		int temp_Ind = 0;
		int temp_Seats = 0;
		System.out.print("Enter number of economy class seats : ");
		temp_Ind = input_Object1.nextInt();
		temp_Seats += temp_Ind;
		economy_Seats = Integer.toString(temp_Ind);
		
		System.out.print("Enter number of business class seats : ");
		temp_Ind = input_Object1.nextInt();
		temp_Seats += temp_Ind;
		business_Seats = Integer.toString(temp_Ind);
		
		System.out.print("Enter number of first class seats : ");
		temp_Ind = input_Object1.nextInt();
		temp_Seats += temp_Ind;
		first_Seats = Integer.toString(temp_Ind);
		
		seats = Integer.toString(temp_Seats);
		
		try {
			String index;
			FileWriter write_Data = new FileWriter("flightInfo",true);
			index = getIndex();
			String writing_Data;
			writing_Data = index+","+flight_Code+","+origin+","+destination+","+seats+","+flight_Date+","+flight_Time+",";
			writing_Data += economy_Price+","+economy_Seats+","+business_Price+","+business_Seats+","+first_Price+",";
			writing_Data += first_Seats + "\n";
			write_Data.write(writing_Data);
			write_Data.close();
		} catch (IOException error) {
			System.out.println("File Writting error");
		}

	}
	
	public String SearchFlight()
	{
		Scanner input_Object = new Scanner(System.in);
		System.out.print("Enter your departure city : ");
		origin = input_Object.nextLine();
		
		System.out.print("Enter your arrival city : ");
		destination = input_Object.nextLine();
		
		String day,month,year;
		
		System.out.print("Enter day of departure : ");
		day = input_Object.nextLine();
		
		System.out.print("Enter month of departure : ");
		month = input_Object.nextLine();
		
		System.out.print("Enter year of departure : ");
		year = input_Object.nextLine();
		
		flight_Date = day + "/" + month + "/" + year;
		try {
			File read_File = new File("flightInfo");
			Scanner read_Input = new Scanner(read_File);
			boolean found = false;
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (origin.equals(data_Arr[2]) == true && destination.equals(data_Arr[3]) == true && flight_Date.equals(data_Arr[5]) == true)
		        {
		        	int economy_SeatsI;
		    		int business_SeatsI;
		    		int first_SeatsI;
		        	System.out.println("Flight Index : " + data_Arr[0]);
			        System.out.println("Flight Code : " + data_Arr[1]);
			        System.out.print(data_Arr[2]);
			        System.out.println("  --------->  " + data_Arr[3]);
			        System.out.println("Date : " + data_Arr[5]);
			        System.out.println("Time : " + data_Arr[6]);
			        System.out.println("Economy class Price : " + data_Arr[7]);
			        System.out.println("Businees class Price : " + data_Arr[9]);
			        System.out.println("First class Price : " + data_Arr[11]);
			        
			        economy_SeatsI = Integer.valueOf(data_Arr[8]);
			        business_SeatsI = Integer.valueOf(data_Arr[10]);
			        first_SeatsI = Integer.valueOf(data_Arr[12]);
			        
			        try {
			        	File depth_read_File = new File("reservationInfo");
			        	Scanner depth_read_Input = new Scanner(depth_read_File);
			        	while (depth_read_Input.hasNextLine())
			        	{
			        		String depth_read_Line = depth_read_Input.nextLine();
					        String[] depth_data_Arr = depth_read_Line.split(",");
					        if (data_Arr[0].equals(depth_data_Arr[2]) == true)
					        {
					        	if (depth_data_Arr[4].equals("E") == true)
					        	{
					        		economy_SeatsI--;
					        	}
					        	else if (depth_data_Arr[4].equals("B") == true)
					        	{
					        		business_SeatsI--;
					        	}
					        	else if (depth_data_Arr[4].equals("F") == true)
					        	{
					        		first_SeatsI--;
					        	}
					        }
			        	}
			        } catch (FileNotFoundException error) {
			        	System.out.print("File reading issue .");
			        }
			        

			        System.out.println("Economy class Seats : " + economy_SeatsI);
			        System.out.println("Businees class Seats : " + business_SeatsI);
			        System.out.println("First class Seats : " + first_SeatsI);
			        
			        
			        System.out.println("-----------------------------------------------------------------\n\n");
			        found = true; // Flight Found
			        if (economy_SeatsI == 0 && business_SeatsI == 0 && first_SeatsI == 0)
			        {
			        	System.out.println("No available seats . ");
			        	return "false";
			        }
			    
			        if (found == true)
					{
						String returner = "true";
						return returner;
					}
			        
			        
		        }
		    
		    }
			
			if (found == false)
			{
				System.out.println("No flights were found");
				return "false";
			}
		} catch (FileNotFoundException error) {
			System.out.print("File was not read .");
			return "false";
		}
		return "false";
		
		
	}
	
	public String getIndex()
	{
		try {
			File read_File = new File("flightInfo");
			Scanner read_Input = new Scanner(read_File);
			int index = 0;
			while (read_Input.hasNextLine()) {
				String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        index = Integer.valueOf(data_Arr[0]);
		     }
			index++;
			String indexS = Integer.toString(index);
			return indexS;
		} catch (FileNotFoundException error) {
			return "1";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String SearchOneFlight(String searchIndex)
	{
		try {
			File read_File = new File("flightInfo");
			Scanner read_Input = new Scanner(read_File);
			boolean found = false;
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (searchIndex.equals(data_Arr[0]) == true)
		        {
		        	int economy_SeatsI;
		    		int business_SeatsI;
		    		int first_SeatsI;
		        	
			        economy_SeatsI = Integer.valueOf(data_Arr[8]);
			        business_SeatsI = Integer.valueOf(data_Arr[10]);
			        first_SeatsI = Integer.valueOf(data_Arr[12]);
			        
			        try {
			        	File depth_read_File = new File("reservationInfo");
			        	Scanner depth_read_Input = new Scanner(depth_read_File);
			        	while (depth_read_Input.hasNextLine())
			        	{
			        		String depth_read_Line = depth_read_Input.nextLine();
					        String[] depth_data_Arr = depth_read_Line.split(",");
					        if (data_Arr[0].equals(depth_data_Arr[2]) == true)
					        {
					        	if (depth_data_Arr[4].equals("E") == true)
					        	{
					        		economy_SeatsI--;
					        	}
					        	else if (depth_data_Arr[4].equals("B") == true)
					        	{
					        		business_SeatsI--;
					        	}
					        	else if (depth_data_Arr[4].equals("F") == true)
					        	{
					        		first_SeatsI--;
					        	}
					        }
			        	}
			        } catch (FileNotFoundException error) {
			        	System.out.print("File reading issue .");
			        }
			        
			        
			        System.out.println("-----------------------------------------------------------------\n\n");
			        found = true; // Flight Found
			        
			        if (found == true)
					{
			        	String econo = Integer.toString(economy_SeatsI);
			        	String busin = Integer.toString(business_SeatsI);
			        	String first = Integer.toString(first_SeatsI);
						String returner = econo+","+busin+"," +first;
						return returner;
					}
			        
			        
		        }
		    
		    }
			
			if (found == false)
			{
				System.out.println("No flights were found");
				return "false";
			}
		} catch (FileNotFoundException error) {
			System.out.print("File was not read .");
			return "false";
		}
		return "false";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}