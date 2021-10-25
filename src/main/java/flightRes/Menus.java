package flightRes;
import java.util.Scanner;

public class Menus {
	public int initialScreen()
	{
		System.out.println("Welcome to Flight Reservation System");
		System.out.println("Enter 1 to search for flights .");
		System.out.println("Enter 2 for customer registration .");
		System.out.println("Enter 3 for customer login .");
		System.out.println("Enter 4 for admin login .");
		System.out.println("Enter 5 to exit .");
		int customer_Selection;
		
		Scanner input_Object = new Scanner(System.in);
		customer_Selection = input_Object.nextInt();
		return customer_Selection;
	}
}