import java.util.*;

import javax.swing.JOptionPane;

/**
 * Implementation Class
 *
 * @author Gene Lee
 * @author Yamil Castro
 * @version (a version number or a date)
 */
public class implentationp
{
    // instance variables - replace the example below with your own
 public static void main(String[] args){
	 
	 //map with login as key, and arraylist of customer and accounts as value
	 HashMap<Login, Vector<Account> > logins = new HashMap<Login, Vector<Account>>();
	 
		String path = "./src/ImportCustomers.txt";
		
		//call method to read files from path
	    ImportFiles.readFromFile(path, logins);
	 
	 //System.out.println(logins);
	 //System.out.println(logins.containsKey(L));

	 //System.out.println(logins.entrySet().toString());
	 boolean validLogin = false;

	 
	 //Customer c = (Customer) logins.get(new Login("customer3", "125")).elements();
	 //System.out.println(logins.get(new Login("customer3", "125")).elementAt(0));
	 

	 

	 //print everything in map
	 System.out.println("\n\n-----Logins In HashMap---------\n");
 	Iterator it = logins.entrySet().iterator();
	
 	//while there is a next pair
 	while(it.hasNext())
 	{
 		Map.Entry<Login, Vector<Account>> entry = (Map.Entry<Login, Vector<Account>>)it.next();
 		
 		System.out.println(entry.getKey().getUserName()+ "  " + entry.getKey().getPassword() );
 	
 	}
	 
	 
 	//initialize temporary Login object
 	Login x = null;
 	boolean isValid = false;
	 
	 try {
		 x = CreateLogin();
		 
		 //will return illegal argument exception if login is invalid
		 isValid = Login.validate(x, logins);
		 
		 //call menu method with customer associated with the login
		 
		 //get value of the specific login
		 
		 if(isValid)
		 {
			 //Customer c = (Customer) logins.get(new Login("customer3", "125")).elements();
			//Customer c = (Customer) logins.get(x).firstElement();
			 
			 //get customer
				if (logins.containsKey(x))
				{
					System.out.println("GET CUSTOMER    : " + logins.get(x).firstElement().getCustomer());
				}
				else {
					System.out.println("\nKEY DOESNT MATCH");
				}
			 
			// System.out.println(c.getFirstName() + " " + c.getLastName());
		 }

		 //Menu(d);


	 }
	 catch(IllegalArgumentException e)
	 {
		 JOptionPane.showMessageDialog(null, e.getMessage());
	 }
	 
	 
	 
	 //login must be valid in order to access the program
	 /*if(validLogin)
	 {
		 Menu(customer1);
	 }*/
	 
     //if true
     //customer1, john, etc,
     //tokens
     //Customer c = new Customer (//get data from file);
     //menu
    // Account s = new Checkings(///);
     // for the other ones
    // print(linkedlist);
     
    }

 public static void Menu(Customer customer, HashMap<Login, Vector<Account> > logins) {
		 
		 ArrayList<String> optionList = new ArrayList<String>();
		
		 optionList.add("1");
		 optionList.add("2");
		 optionList.add("3");

		 Object[] options = optionList.toArray();
		 int value = JOptionPane.showOptionDialog(
		                 null,
		                 "Please select an option:\n 1. Create Account \n 2. View Accounts \n3. Exit",
		                 "Pick",
		                 JOptionPane.YES_NO_OPTION,
		                 JOptionPane.QUESTION_MESSAGE,
		                 null,
		                 options,
		                 optionList.get(0));

		 String opt = optionList.get(value);
		 
		 
		 switch(Integer.parseInt(opt))
		 {
		 case 1: //If the user selects 1 -- create an account
			 CreateBankAccount(customer, logins);
			 break;
		 case 2: //If the user selects 2 -- view accounts
			 ViewAccounts(customer, logins);
			 break;
		 case 3: //If the user selects 3 -- Exit, exit the program
			 System.exit(0);
			 break;
		 default:
			 break;
		 }
		 
		 

 }

/*
 * Static method that handles logins
 * */
public static Login CreateLogin()
{
	 String username = JOptionPane.showInputDialog("Enter Username: ");
	 String password = JOptionPane.showInputDialog("Enter password: ");
	 
	 Login log = new Login(username, password);
	 
	 return log;
}


/*
 * This method creates a new Customer, and allows the customer to create a checking, savings, and money market account
 * */
public static Vector<Account> createCustomerAccount(HashMap<Login, Vector<Account> > logins) {
	 Vector<Account> objVector = new Vector<Account>();
	int counter = 1;
	Customer c;
	Account a = null;
	 do {
		 
	//create login
	String user = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your username? ");	 
	String password = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your password? ");	 
		 
	Login newLogin = new Login(user, password);	 
		 
	 // creates a customer
	c = new Customer(JOptionPane.showInputDialog("First Name"),JOptionPane.showInputDialog("Last Name"), 
			 					JOptionPane.showInputDialog("Address"), JOptionPane.showInputDialog("Phone"), 
			 							JOptionPane.showInputDialog("email"));
	
	
	//initialize Key Value set in logins hashmap, we will add this customer to the hashmap
	logins.put(newLogin, new Vector<Account>() {{

	}});
	
	//Menu for the user to create a new account
	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 Object[] options = optionList.toArray();
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "Please select an option:\n 1. Create Checking Account \n 2. Create Savings Account \n3. Create Money Market Account \n4. Exit",
	                 "Pick",
	                 JOptionPane.YES_NO_OPTION,
	                 JOptionPane.QUESTION_MESSAGE,
	                 null,
	                 options,
	                 optionList.get(0));

	 String opt = optionList.get(value);
	 
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1: //If the user selects 1 -- 
			//creates a checking account
			a = new Checking(c,JOptionPane.showInputDialog("Transaction Date"),
					 	new Transaction(JOptionPane.showInputDialog("transaction Date"),
						Double.parseDouble(JOptionPane.showInputDialog("amount"))));
			
			//add account to hashmap
			if (logins.containsKey(newLogin))
			{
				logins.get(newLogin).add(a);
			}
			
			break;
	 case 2:
			//creates a MarketAccount account
			a = new MarketAccount(c,JOptionPane.showInputDialog("Transaction Date"));
			
			//add account to hashmap
			if (logins.containsKey(newLogin))
			{
				logins.get(newLogin).add(a);
			}
			
			break;
	 case 3: //If the user selects 3 
			//creates a savings account
		    a = new Savings(c,JOptionPane.showInputDialog("Transaction Date"),
					new Transaction(JOptionPane.showInputDialog("transaction Date"),
					Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		    
			//add account to hashmap
			if (logins.containsKey(newLogin))
			{
				logins.get(newLogin).add(a);
			}
			
			break;
	 case 4: //Exit, exit the program
	 System.exit(0);
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to add more accounts ?", "Please select",JOptionPane.YES_NO_OPTION)==1
			 && counter <= Account.MAX_NUM_ACC );

	return objVector;
}

/*
 * The customer can create additional accounts if they dont exist yet.
 * */
public static void CreateBankAccount(Customer customer, HashMap<Login, Vector<Account> > logins)
{
	 //find and return existing accounts for the customer
	 
	 //give option to create accounts that dont exist
	
}


/*
 * The program should display the customer account number, first name, last name, total account balance before interest,
 *  total interest, and grand total balance for each account for each type of account.
 * 
 * */
public static void ViewAccounts(Customer customer, HashMap<Login, Vector<Account> > logins)
{
	 do {

	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 Object[] options = optionList.toArray();
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "View an Account:\n 1. Checking Account \n 2. Savings Account \n3. Money Market Account \n4. Exit",
	                 "Pick",
	                 JOptionPane.YES_NO_OPTION,
	                 JOptionPane.QUESTION_MESSAGE,
	                 null,
	                 options,
	                 optionList.get(0));

	 String opt = optionList.get(value);
	 
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1: //If the user selects 1 -- 
			//display checking account toString
		 
		 
		 //Find customer account
		 
		 	JOptionPane.showMessageDialog(null, "");
			break;
	 case 2:
			//display MarketAccount account
			break;
	 case 3: //If the user selects 3 
			//display savings account
		 break;
	 case 4: //Exit, exit the program
	 System.exit(0);
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to view another account ?", "Please select",JOptionPane.YES_NO_OPTION)==1);
}

 
}
