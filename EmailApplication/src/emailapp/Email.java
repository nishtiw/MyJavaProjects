package emailapp;

import java.util.*;
import java.io.*;

public class Email {

	public Scanner scan = new Scanner(System.in);
	
	private String fname;
	private String lname;
	private String dept;
	private String email;
	private String password; 
	private int mailCapacity = 500;
	private String alter_email;
	
	public Email(String fname,  String lname)
	{
		this.fname = fname;
		this.lname = lname; 
		System.out.println("New employee: "+ this.fname+" "+ this.lname);
		
		//calling methods
		this.dept = this.setDept();
		this.password = this.generate_password(8); 
		this.email = this.generate_email(); 
	}
	
	//create generate_email method. It is private to attain abstraction
	private String generate_email()
	{
		return this.fname.toLowerCase()+"."+this.lname.toLowerCase()+"@"+this.dept.toLowerCase()+".company.com";
	}
	
	private String setDept()
	{
		System.out.println("Department codes \n1 for Sales \n2 for Development \n3 for Accounting "); 
		boolean flag = false; 
		do {
			System.out.print("Enter Department Code: ");
			int choice = scan.nextInt();
			switch(choice) //chose switch instead of if-else to save time
			{
			case 1:
				return "Sales"; //no break statement because we have a return statement
			case 2:
				return "Development";
			case 3:
				return "Accounting";
			//case 0: 
				//return "null";
			default:
				System.out.println("Invalid choice. Please choose it again.");	
			}
		}while(!flag); //when flag = true, exit this loop
		return null;
	}
	
	//create Random password method
	private String generate_password(int length)
	{
		Random r = new Random();
		String Capital_chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%&?";
		String values = Capital_chars+Small_chars+numbers+symbols; //syntax of our system default password
		String password = "";
		for (int i =0; i<length; i++)
		{
			password = password + values.charAt(r.nextInt(values.length())); //r.nextInt because nextInt(int n) is used to get a random number between 0(inclusive) and the number passed in this argument(n or values.length), exclusive.
		}
		return password;
	}
	
	//change password method is public because user will change it. It is void because it will only print and return nothing.
	public void set_password()
	{
		boolean flag = false;
		do {
			System.out.println("Do you want to change your password? (Y/N)");
			char choice = scan.next().charAt(0); //why charAt(0)? because char choice cannot take string value. As we want only one character we use charAt(0).
			if(choice=='Y' || choice=='y')
			{
				flag=true;
				System.out.print("Enter current password: ");
				String temp = scan.next();
				if(temp.equals(this.password))
				{
					System.out.print("Enter new password: ");
					this.password = scan.next();
					System.out.println("Password changed successfully!");
				}
				else
				{
					System.out.println("Incorrect password entered!");
				}
			}
			else if(choice=='N' || choice=='n')
			{
				flag=true;
				System.out.println("Password change option cancelled!");
			}
			else
			{
				System.out.println("Enter a valid choice");
			}
		}while(!flag); 
	}
	
	//set methods
	//set mailbox capacity
	public void set_mailCap()
	{
		System.out.println("Current capacity = "+this.mailCapacity+"mb");
		System.out.print("Enter new mailbox capacity: ");
		this.mailCapacity = scan.nextInt(); 
		System.out.println("\n Mailbox capacity updated successfully.");
	}
	
	//set alternate mail
	public void set_alternateEmail()
	{
		System.out.println("Enter alternate email id: ");
		this.alter_email = scan.next();
		System.out.println("Alternate email has been set successfully.");
	}
	
	//display user info 
	public void getInfo()
	{
		System.out.println("New: "+this.fname+" "+this.lname);
		System.out.println("Department: "+this.dept);
		System.out.println("Emaild id: "+this.email);
		System.out.println("Password: "+this.password); //for testing only and not to display it to user
		System.out.println("Mailbox capacity: "+this.mailCapacity+"mb");
		System.out.println("Alternate email id: "+this.alter_email);
	}
	
	//store info in file
	public void storeInfo()
	{
		try
		{
			FileWriter in = new FileWriter("C:\\Users\\DELL\\Documents\\info.txt");
			in.write("First Name: "+this.fname);
			in.append("\nLast Name: "+this.lname);
			in.append("\nEmail: "+this.email);
			in.append("\nPassword: "+this.password);
			in.append("\nMail Capacity: "+this.mailCapacity);
			in.append("\nAlternate mail: "+this.alter_email);
			in.close();
			System.out.println("Data stored in file.. ");
			
		}catch(Exception e) {System.out.println(e);}
	}
	
	//reading file
	public void readFile()
	{
		try
		{
			FileReader f1 = new FileReader("C:\\Users\\DELL\\Documents\\info.txt");
			int i;
			while((i=f1.read())!= -1)
			{
				System.out.print((char)i);
			}
			System.out.println();
			f1.close();
			
		}catch(Exception e) {System.out.println(e);}
	}
}
