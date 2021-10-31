package emailapp;

import java.util.*;

public class emailApp {

	public static void main(String[] args) {

		//getting user info
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your First Name: ");
		String f_name = scan.next();
		System.out.println("Enter your Last Name: ");
		String l_name = scan.next();
		
		//instantiating Email class
		Email em1 = new Email(f_name, l_name);
		int choice = -1;
		do {
			System.out.println("\n****** \nEnter your choice \n1. Show Info \n2. Change Password \n3. Change mailbox Capacity \n4. Set alternate mail \n5. Store data in file \n6. Display data from file \n7. Exit");
			choice = scan.nextInt();
			switch(choice)
			{
			case 1: 
				em1.getInfo();
				break;
			case 2:
				em1.set_password();
				break;
			case 3:
				em1.set_mailCap();
				break;
			case 4:
				em1.set_alternateEmail();
				break;
			case 5:
				em1.storeInfo();
				break;
			case 6: 
				em1.readFile();
				break;
			case 7:
				System.out.println("Thank you for using this application.");
				break;
			default: 
				System.out.println("Invalid choice! \nEnter proper choice again.");
			}
		}while(choice!=7);
		
		scan.close();
	}

}
