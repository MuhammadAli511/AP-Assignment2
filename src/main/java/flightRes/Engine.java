package flightRes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Engine {
	static SignUp signupObj = new SignUp();
	static Menus menuObj = new Menus();
	static Login loginObj = new Login();
	static Flight flightObj = new Flight();
	static Customer customerObj = new Customer();
	
	
	 public static void bookFlight(String flight, String customer,String classC)
	 {
		 String total_SeatsS = "";
		 String economy_SeatsS = "";
		 String business_SeatsS = "";
		 String first_SeatsS = "";
		 try {
				File read_File = new File("flightInfo");
				Scanner read_Input = new Scanner(read_File);
				while (read_Input.hasNextLine()) {
					String read_Line = read_Input.nextLine();
			        String[] data_Arr = read_Line.split(",");
			        if (flight.equals(data_Arr[0]) == true)
			        {
			        	total_SeatsS = data_Arr[4];
			        	economy_SeatsS = data_Arr[8];
			        	business_SeatsS = data_Arr[10];
			        	first_SeatsS = data_Arr[12];
			        }
			     }
			} catch (FileNotFoundException error) {}
	 
		 int total_SeatsI = Integer.valueOf(total_SeatsS);
		 int economy_SeatsI = Integer.valueOf(economy_SeatsS);
		 int business_SeatsI = Integer.valueOf(business_SeatsS);
		 int first_SeatsI = Integer.valueOf(first_SeatsS);
		 
		 int econo_Start = 0;
		 int econo_End = 0;
		 int busin_Start = 0;
		 int busin_End = 0;
		 int first_Start = 0;
		 int first_End = 0;
		 
		 
		 boolean first = false;
		 for (int i = 1 ; i <= economy_SeatsI ; i++)
		 {
			 if (first == false)
			 {
				 econo_Start = i;
				 first = true;
			 }
			 econo_End = i;
		 }
		 first = false;
		 int total1 = business_SeatsI+economy_SeatsI;
		 for (int i = (econo_End+1) ; i <= total1 ; i++)
		 {
			 if (first == false)
			 {
				 first = true;
				 busin_Start = i;
			 }
			 busin_End = i;
		 }
		 int total2 = business_SeatsI+economy_SeatsI+first_SeatsI;
		 first = false;
		 for (int i = (busin_End+1) ; i <= total2 ; i++)
		 {
			 if (first == false)
			 {
				 first = true;
				 first_Start = i;
			 }
			 first_End = i;
		 }
	 
		 
		 int start=0;
		 int end=0;
		 if (classC.equals("E") == true)
		 {
			 start = econo_Start;
			 end = econo_End;
		 }
		 else if (classC.equals("B") == true)
		 {
			 start = busin_Start;
			 end = busin_End;
		 }
		 else if (classC.equals("F") == true)
		 {
			 start = first_Start;
			 end = first_End;
		 }
		 
		 
		 int counting = 0;
		 for (int j = start ; j <= end ; j++)
		 {
			 counting++;
		 }
		 int map[] = new int[counting];
		 
		 int k = 0;
		 for (int j = start ; j <= end ; j++)
		 {
			 map[k] = j;
			 k++;
		 }
		 
		 
		 for (int j = start ; j <= end ; j++)
		 {
			 try {
					File read_File = new File("registrationInfo");
					Scanner read_Input = new Scanner(read_File);
					while (read_Input.hasNextLine()) {
						String read_Line = read_Input.nextLine();
				        String[] data_Arr = read_Line.split(",");
				        if (data_Arr[4].equals(classC) == true && data_Arr[2].equals(flight) == true)
				        {
				        	int l = 0;
				        	int seat_Value = Integer.valueOf(data_Arr[3]);
				        	for (int m = start ; m <= end ; m++)
				   		 	{
				        		if (seat_Value == map[l])
				        		{
				        			map[l] = -1;
				        		}
				        		l++;
				   		 	}
				        }
				     }
				} catch (FileNotFoundException error) {}
		 }
		 
		 int final_Value = 0;
		 int u = 0;
		 for (int z = start ; z <= end ; z++)
		 {
			 if (map[u] != -1)
			 {
				 final_Value = map[u];
				 break;
			 }
		 }
		 String final_Seat = Integer.toString(final_Value);
		 
		 try {
			 String index;
			 FileWriter write_Data2 = new FileWriter("reservationInfo",true);
			 index = getIndex();
			 String writing_Data2;
			 writing_Data2 = index+","+customer+","+flight+","+final_Seat+","+classC + "\n";
			 write_Data2.write(writing_Data2);
			 write_Data2.close();
		 } catch (IOException error)
		 {
			 System.out.println("File Writting error");}
	 
	 }
	 
	 
	 public static String getIndex()
		{
			try {
				File read_File = new File("reservationInfo");
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

	
	 public static void main(String[] args)
	 {
		 //Customer customerObj = new Customer();
		 int choice = menuObj.initialScreen();
		 while (true)
		 {
			 if (choice == 1)
			 {
				 String flights = flightObj.SearchFlight();
				 String[] data_Arr = flights.split(",");
				 if (data_Arr[0] == "false")
				 {
					 Scanner input_Object = new Scanner(System.in);
					 System.out.println("Enter 1 to search again");
					 System.out.println("Enter 0 to exit");
					 int choice1 = input_Object.nextInt();
					 if (choice1 == 0)
					 {
						 System.out.println("Program Ended");
						 System.exit(0);
					 }
					 else if (choice1 == 1)
					 {
						 choice = 1;
					 }
				 }
				 if (data_Arr[0] == "true")
				 {
					 Scanner input_Object = new Scanner(System.in);
					 Scanner input_ObjectI = new Scanner(System.in);
					 System.out.println("Enter 1 to search again");
					 System.out.println("Enter 2 to continue with provided flights");
					 int choice1 = input_ObjectI.nextInt();
					 if (choice1 == 1)
					 {
						 choice = 1;
					 }
					 else if (choice1 == 2)
					 {
						 System.out.println("Enter index number of flight : ");
						 String flight_Index = input_Object.nextLine();
						 String all_Data = flightObj.SearchOneFlight(flight_Index);
						 
						 System.out.println("Enter your passport number : ");
						 String passport_Value = input_Object.nextLine();
						 String customer_Index="";
						 try {
								File read_File = new File("customerInfo");
								Scanner read_Input = new Scanner(read_File);
								while (read_Input.hasNextLine()) {
									String read_Line = read_Input.nextLine();
							        String[] data_Arr2 = read_Line.split(",");
							        if (passport_Value.equals(data_Arr2[5]) == true)
							        {
							        	customer_Index = data_Arr2[0];
							        }
							     }
							} catch (FileNotFoundException error) {}
						 
						 String[] all_DataArr = all_Data.split(",");
						 System.out.println("Enter class for traveling according to available seats: ");
						 if (all_DataArr[0].equals("0") == false)
						 {
							 System.out.println("Enter 1 for economy class");
						 }
						 if (all_DataArr[1].equals("0") == false)
						 {
							 System.out.println("Enter 2 for business class");
						 }
						 if (all_DataArr[2].equals("0") == false)
						 {
							 System.out.println("Enter 3 for first class");
						 }
						 Scanner input_Object5 = new Scanner(System.in);
						 int customer_Class = input_Object5.nextInt();
						 String customer_ClassS = "";
						 if (customer_Class == 1)
						 {
							 customer_ClassS = "E";
						 }
						 else if (customer_Class == 2)
						 {
							 customer_ClassS = "B";
						 }
						 else if (customer_Class == 3)
						 {
							 customer_ClassS = "F";
						 }
						bookFlight(flight_Index,customer_Index,customer_ClassS);
					 }
				 }
				 Scanner input_Object10 = new Scanner(System.in);
				 String card_no;
				 System.out.println("Enter your credit card number : ");
				 card_no = input_Object10.nextLine();
				 
				 String pin;
				 System.out.println("Enter your pinr : ");
				 pin = input_Object10.nextLine();
				 
				 System.out.println("Payment Successfull");
				 System.out.println("Your ticket has been purchased .");
				 System.exit(0);
			 }
			 else if (choice == 2)
			 {
				 signupObj.createAccount();
				 System.out.println("Created Account-");
				 choice = 3;
			 }
			 else if (choice == 3)
			 {
				 boolean loginPass = loginObj.loginCustomer();
				 if (loginPass == false)
				 {
					 System.out.println("Failed to Log in"); 
				 }
				 else if (loginPass == true)
				 {
					 System.out.println("Logged in");
					 Scanner input_Object = new Scanner(System.in);
					 System.out.println("Enter 1 to search flights .");
					 System.out.println("Enter 0 to exit .");
					 
					 int choice1 = input_Object.nextInt();
					 if (choice1 == 0)
					 {
						 System.out.println("Program Ended");
						 System.exit(0);
					 }
					 else if (choice1 == 1)
					 {
						 choice = 1;
					 }
				 }
			 }
			 else if (choice == 4)
			 {
				 boolean loginPass = loginObj.loginAdmin();
				 if (loginPass == false)
				 {
					 System.out.println("Failed to Log in"); 
				 }
				 else if (loginPass == true)
				 {
					 System.out.println("Logged in");
					 Scanner input_Object = new Scanner(System.in);
					 System.out.println("Enter 1 to add flight .");
					 System.out.println("Enter 0 to exit .");
					 int choice1 = input_Object.nextInt();
					 if (choice1 == 0)
					 {
						 System.out.println("Program Ended");
						 System.exit(0);
					 }
					 else if (choice1 == 1)
					 {
						 flightObj.createFlight();
						 System.out.println("New Flight Added"); 
					 }
				 }
			 }
			 else if (choice == 5)
			 {
				 System.out.println("Program Ended"); 
				 System.exit(0);
			 }
		 }
	 }
}