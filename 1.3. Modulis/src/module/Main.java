package module;

import java.io.IOException;
import java.util.Scanner;

import edu.ernestas.ConcreteValidators.EmailValidator;
import edu.ernestas.ConcreteValidators.PasswordChecker;
import edu.ernestas.ConcreteValidators.PhoneValidator;
import edu.ernestas.Helpers.ValidationResult;



public class Main {
	public static String path = "D:\\Users\\User\\Desktop\\Users.txt";
	public static void main(String[] args) 
	{
		try 
		{
			mainMenu();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static User getNewUserInfo() throws Exception
	{
		ValidationResult r;
		Scanner s = new Scanner( System.in );

		System.out.println("Name:");
		String name = s.nextLine();

		System.out.println("Surname:");
		String surname = s.nextLine();

		System.out.println("Phone number:");
		String number = s.nextLine();
		r = validatePhone(number);
		if(r.getStatus() == false )
		{
			System.out.println(r.getMessage());
			throw new Exception();
		}
		
		System.out.println("Email:");
		String email = s.nextLine();
		r = validateEmail(email);
		if(r.getStatus() == false )
		{
			System.out.println(r.getMessage());
			throw new Exception();
		}

		System.out.println("Address:");
		String address = s.nextLine();

		System.out.println("Password:");
		String password = s.nextLine();
		r = validatePassword(password);
		if(r.getStatus() == false )
		{
			System.out.println(r.getMessage());
			throw new Exception();
		}
		
		User u = new User("", name, surname, email, number, address, password);
		return u;
	}
	
	public static ValidationResult validateEmail (String email)
	{ 
		EmailValidator ev = new EmailValidator();
		return ev.validate(email);
	}
	
	public static ValidationResult validatePhone (String phoneNumber)
	{
		PhoneValidator pv = new PhoneValidator();
		return pv.validate(phoneNumber);
	}
	
	public static ValidationResult validatePassword (String password)
	{
		PasswordChecker ph = new PasswordChecker ();
		return ph.validate(password);		
	}
	
	public static int addUser(User u, String path)
	{
		int result = 1;
		Actions a = new Actions();
		User newUser = a.save(u, path);
		if(newUser.id == "")
			result = -1;

		return result;
	}
	
	public static int updateUser(User u, String path)
	{
		int result = 1;
		Actions a = new Actions();
		result = a.update(u, path);
		return result;
	}
	
	public static int deleteUser(String id, String path)
	{
		int result = 1;
		
		Actions a = new Actions();
		result = a.delete(id, path);

		return result;
	}
	
	public static void mainMenu() throws IOException
	{
		showOptions();
		Scanner s = new Scanner( System.in );
		int option = s.nextInt();
		int result = 1;
		
		switch(option)
		{
		case 1:
			try 
			{
				User u = getNewUserInfo();
				result = addUser(u, path);
			}
			catch (Exception e)
			{
				result = -1;
			}
			if(result != 1)
				System.out.println("Failed to save new user!");
			else System.out.println("Done!");
			mainMenu();
			break;
		case 2:
			try 
			{
				Scanner sc = new Scanner( System.in );
				System.out.println("Old Id:");
				String id = sc.nextLine();
				
				User u = getNewUserInfo();
				u.id = id;
				result = updateUser(u, path);
			}
			catch (Exception e)
			{
				result = -1;
			}
			if(result != 1)
				System.out.println("Failed to update user!");
			else System.out.println("Done!");
			mainMenu();
			break;
		case 3:
			try 
			{
				Scanner sc = new Scanner( System.in );
				System.out.println("User Id:");
				String id = sc.nextLine();
				
				result = deleteUser(id, path);
			}
			catch (Exception e)
			{
				result = -1;
			}
			if(result != 1)
				System.out.println("Failed to update user!");
			else System.out.println("Done!");
			mainMenu();
			break;
		}
	}
	
	public static void showOptions()
	{
		System.out.println("Options:");
		System.out.println("0: exit");
		System.out.println("1: add user");
		System.out.println("2: update user");
		System.out.println("3: delete user");
	}
}
