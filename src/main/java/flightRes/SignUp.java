package flightRes;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp {
	Customer customer_Account = new Customer();
	Login customer_Credntials = new Login();
	
	public void createAccount()
	{
		Scanner input_Object = new Scanner(System.in);
		
		System.out.print("Enter your first Name : ");
		customer_Account.first_Name = input_Object.nextLine();
		System.out.print("Enter your last Name : ");
		customer_Account.last_Name = input_Object.nextLine();
		System.out.print("Enter your gender : ");
		customer_Account.gender = input_Object.nextLine();
		System.out.print("Enter your age : ");
		customer_Account.age = input_Object.nextLine();
		
		boolean passport_Checking = true;
		while(passport_Checking != false)
		{
			try {
				
					System.out.print("Enter your passport No. : ");
					customer_Account.Passport_No = input_Object.nextLine();	
					passport_Checking = checkPassportValidity(customer_Account.Passport_No);
				} catch (ExceptionRepition error)
					{ System.out.println(error);}
		}

		
		
		boolean email_Checking = true;
		while(passport_Checking != false)
		{
			try {
		
					System.out.print("Enter your email : ");
					customer_Credntials.email = input_Object.nextLine();	
					email_Checking = checkEmailValidity(customer_Credntials.email);
				} catch (ExceptionRepition error)
					{ System.out.println(error);}
			}
		
		System.out.print("Enter your password : ");
		customer_Credntials.password = input_Object.nextLine();
		
		// Adding data to file
		try {
			String index;
			FileWriter write_Data = new FileWriter("customerInfo",true);
			index = getIndex();
			String writing_Data;
			writing_Data = index+","+customer_Account.first_Name+","+customer_Account.last_Name+","+customer_Credntials.email+",";
			writing_Data += customer_Credntials.password+","+customer_Account.Passport_No+","+customer_Account.gender+","+customer_Account.age+"\n";
			write_Data.write(writing_Data);
			write_Data.close();
		} catch (IOException error) {
			System.out.println("File Writting error");
		}
	}
	public boolean checkPassportValidity(String passport_Number)
	{
		// Create Exception here if file doesn't exist
		// Create Exception if file is empty
		try {
			File read_File = new File("customerInfo");
			Scanner read_Input = new Scanner(read_File);
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (passport_Number.equals(data_Arr[5]) == true)
		        {
		        	throw new ExceptionRepition("Passport number already exits");
		        }
		      }
			return false;
		} catch (FileNotFoundException error) {
			return false;
		}
	}
	public boolean checkEmailValidity(String email)
	{
		try {
			File read_File = new File("customerInfo");
			Scanner read_Input = new Scanner(read_File);
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (email.equals(data_Arr[3]) == true)
		        {
		        	throw new ExceptionRepition("Email already exits");
		        }
		      }
			return false;
		} catch (FileNotFoundException error) {
			return false;
		}
	}
	public String getIndex()
	{
		try {
			File read_File = new File("customerInfo");
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
}