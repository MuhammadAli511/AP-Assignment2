package flightRes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	String email;
	String password;
	
	public boolean loginCustomer()
	{
		Scanner input_Object1 = new Scanner(System.in);


		boolean checkMail = false;
		while (checkMail != true)
		{
			try {
				System.out.print("Enter email : ");
				email = input_Object1.nextLine();
				checkMail = checkEmail(email,"customerInfo");
			} catch (ExceptionNotAvailable error)
			{ System.out.println(error);}
		}
		
		boolean check_Password = false;
		while (check_Password != true)
		{
			try {
			System.out.print("Enter password : ");
			password = input_Object1.nextLine();
			check_Password = checkPassword(password,"customerInfo");
			} catch (IncorrectData error)
			{ System.out.println(error);}
		}
		return true;
	}
	public boolean loginAdmin()
	{
		Scanner input_Object1 = new Scanner(System.in);

		boolean checkMail = false;
		while (checkMail != true)
		{
			try
			{
				System.out.print("Enter email : ");
				email = input_Object1.nextLine();
				checkMail = checkEmail(email,"adminInfo");
			} catch (ExceptionNotAvailable error)
			{ System.out.println(error);}
			
		}
		
		boolean check_Password = false;
		while (check_Password != true)
		{
			try {
			System.out.print("Enter password : ");
			password = input_Object1.nextLine();
			check_Password = checkPassword(password,"adminInfo");
			} catch (IncorrectData error)
			{ System.out.println(error);}
		}
		return true;
	}
	
	public boolean checkEmail(String emailArg,String fileName)
	{
		try {
			File read_File = new File(fileName);
			Scanner read_Input = new Scanner(read_File);
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (emailArg.equals(data_Arr[3]) == true)
		        {
		        	return true;
		        }
		      }
			throw new ExceptionNotAvailable("Email does not exist");
		} catch (FileNotFoundException error) {
			return false;
		}
	}
	public boolean checkPassword(String passArg,String fileName)
	{
		try {
			File read_File = new File(fileName);
			Scanner read_Input = new Scanner(read_File);
			while (read_Input.hasNextLine()) {
		        String read_Line = read_Input.nextLine();
		        String[] data_Arr = read_Line.split(",");
		        if (passArg.equals(data_Arr[4]) == true)
		        {
		        	return true;
		        }
		      }
			throw new IncorrectData("Password is incorrect. Try Again");
		} catch (FileNotFoundException error) {
			return false;
		}
	}
}