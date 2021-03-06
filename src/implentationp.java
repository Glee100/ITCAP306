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
	 
	//path of text file that will be updated and read from
	String pathEx = "./src/ExportCustomers.txt";
	
	//call method to read files from path
    ImportFiles.readFromFile(pathEx, logins);
 
    boolean validLogin = false;


	 //print everything in map
	 System.out.println("\n\n-----Logins In HashMap---------\n");
 	 Iterator it = logins.entrySet().iterator();
	
 	//while there is a next pair
 	while(it.hasNext())
 	{
 		Map.Entry<Login, Vector<Account>> entry = (Map.Entry<Login, Vector<Account>>)it.next();
 		
 		System.out.println(entry.getKey().getUserName()+ "  " + entry.getKey().getPassword() );
 	
 	}
	
 	
 	//Login or create an account
	 ArrayList<String> optionList = new ArrayList<String>();
	 
	 Login customerLogin = null;
	 
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");

	 Object[] options = optionList.toArray();
	 
	 while(customerLogin == null)
	 {
		 int value = JOptionPane.showOptionDialog(
                 null,
                 "Please select an option:\n 1. Log in \n 2. Create New Account \n3. Exit",
                 "Pick",
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 options,
                 optionList.get(0));

		 String opt = optionList.get(value);
		 
		 
		 switch(Integer.parseInt(opt))
		 {
		 case 1: //If the user selects 1 -- attempt to log in
			 
			 //assign customerLogin to a login value or null value that is returned by AttemptLogin
			 customerLogin = AttemptLogin(logins);
			 System.out.println("LOGIN WORKS : " + customerLogin);
			 
			 //if customer login is not null, give user access to his/her account
			 if(customerLogin != null)
			 {
				 Menu(pathEx, customerLogin, logins);
			 }
			 
			 break;
		 case 2: //If the user selects 2 -- create a new user account in the system
			 
			 //return the new customer login if it is valid. An invalid account will return null
			 //customer information is saved to the text file
			 Login newCustomerLogin = CreateCustomerAccount(pathEx, logins);
			 
			 if(newCustomerLogin != null)
			 {
				 Menu(pathEx, newCustomerLogin, logins);
			 }

			 break;
		 case 3: //If the user selects 3 -- Exit, exit the program
			 System.exit(0);
			 break;
		 default:
			 break;
		 }
	 }


    }

 /*
  * 
  * Menu displayed to the customer
  * @param Login customer Login, HashMap<Login, Vector<Account>> logins
  * @return none
  * */
 public static void Menu(String pathEx, Login customerLogin, HashMap<Login, Vector<Account> > logins) {
		 
	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 optionList.add("5");

	 Object[] options = optionList.toArray();
	 
	 boolean menuClose = false;
	 
	 do {
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "Please select an option:\n 1. Create Account \n 2. View Accounts \n3. Make Deposit \n4. Make Withdrawal \n5. Exit",
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
		 CreateBankAccount(pathEx, customerLogin, logins);
		 break;
	 case 2: //If the user selects 2 -- view accounts
		 ViewAccounts(customerLogin, logins);
		 break;
	 case 3: //If the user selects 2 -- view accounts
		 MakeDeposit(pathEx, customerLogin, logins);
		 break;
	 case 4: //If the user selects 2 -- view accounts
		 MakeWithdrawal(pathEx, customerLogin, logins);
		 break;
	 case 5: //If the user selects 3 -- Exit, exit the program
		 menuClose = true;
		 System.exit(0);
		 break;
	 default:
		 break;
	 }
	 }while(!menuClose);

 }

/*
 * Static method that handles logins
 * @return Login login
 * */
public static Login CreateLogin()
{
	 String username = JOptionPane.showInputDialog("Enter Username: ");
	 String password = JOptionPane.showInputDialog("Enter password: ");
	 
	 Login log = new Login(username, password);
	 
	 return log;
}

/*
 * Validate login, and return the matching login from the hashmap if found
 * @param HashMap<Login, Vector<Account>> logins
 * @return Login login
 * 
 * */
public static Login AttemptLogin(HashMap<Login, Vector<Account> > logins)
{
	
	//initialize temporary Login object
	 	Login x = null;
	 	boolean isValid = false;
	 	
		 //will return illegal argument exception if login is invalid
		 try {
			 x = CreateLogin();

			 Login matchedLogin = Login.validate(x, logins);
			 
			 //get value of the specific login

			 if(matchedLogin != null)
			 {
				 
				 //get customer
					if (logins.containsKey(matchedLogin))
					{
						//System.out.println("GET CUSTOMER    : " + logins.get(matchedLogin).firstElement().getCustomer());
						//System.out.println("GET CUSTOMER NAME   : " + logins.get(matchedLogin).firstElement().getCustomer().getFirstName());

						return matchedLogin;
					}
					else {
						System.out.println("\nKEY DOESNT MATCH");
						return null;
					}
			 }

		 }
		 catch(IllegalArgumentException e)
		 {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		 }
		 return null;

}


/*
 * This method creates a new Customer, and allows the customer to create a checking, savings, and money market account
 * @param Hashmap<Login, Vector<Account>> logins
 * @return Login 
 * */
public static Login CreateCustomerAccount(String pathEx, HashMap<Login, Vector<Account> > logins) {
	Customer c = null;
	Account a = null;
	int totalAccounts = 0;
	boolean newAccountCreated = false;
	
	 //prompt user for username and password
	String user = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your username? ");	 
	String password = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your password? ");	 
	
	Login newLogin = new Login(user, password);	 
		 
	boolean validatedInfo = false;
	
	 // creates a customer
	do {
		try {
			c = new Customer();
			c.setFirstName(JOptionPane.showInputDialog("First Name"));
			c.setLastName(JOptionPane.showInputDialog("Last Name"));
			c.setAddress(JOptionPane.showInputDialog("Address \n Example : 123 fake st"));
			c.setPhone(JOptionPane.showInputDialog("Phone\n Example : xxx-xxx-xxxx"));
			c.setEmail(JOptionPane.showInputDialog("Email \n Example : myfake@email.com"));

			validatedInfo = true;
		}
		catch(IllegalArgumentException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}while(!validatedInfo);
	

	//initialize Key Value set in logins hashmap, we will add this customer to the hashmap
	logins.put(newLogin, new Vector<Account>() {{ }});

		 	
	do {
		
		//count how many accounts customer has
		final int NUM_ACCOUNTS = logins.get(newLogin).size();
		
		boolean hasCheckingAccount = false;
		boolean hasSavingsAccount = false;
		boolean hasMarketAccount = false;
		
		//identify what type of accounts already exist for the customer
		for(int i = 0; i<NUM_ACCOUNTS; i++)
		{
			//if the account in the vector is a checking account, set boolean hasCheckingAccount to true
			if(logins.get(newLogin).get(i) instanceof Checking)
			{
				hasCheckingAccount = true;
			}
			else if(logins.get(newLogin).get(i) instanceof Savings)
			{
				hasSavingsAccount = true;
			}
			else if(logins.get(newLogin).get(i) instanceof MarketAccount)
			{
				hasMarketAccount = true;
			}
		}
	
	int currentSize = logins.get(newLogin).size();
	try { // if client creates more than one account
	//Menu for the user to create a new account
	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 Object[] options = optionList.toArray();
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "Please select an option:\n 1. Create Checking Account \n 2. Create Money Market Account \n3. Create Savings Account \n4. Exit",
	                 "Pick",
	                 JOptionPane.YES_NO_OPTION,
	                 JOptionPane.QUESTION_MESSAGE,
	                 null,
	                 options,
	                 optionList.get(0));

	 String opt = optionList.get(value);
	 
	 String date;
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1:
		 	//if user does not have existing checking account, create a checking account
		 	if(!hasCheckingAccount && c!=null)
		 	{
		 
		 		date = JOptionPane.showInputDialog("Transaction Date");
		 
		 		a = new Checking(c,date,
					 	new Transaction(date,
						Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		 		
		 		//add account to hashmap
		 		//verify if value has checking
		 		System.out.println("****Account to be Added ****\n " + a.toString());
		 		if (logins.containsKey(newLogin) && currentSize > 0)
		 		{
		 			for(int i = 0; i < currentSize; i++) {
		 				// compares objects and if finds a duplicate it throws an exception
			 			a.compareTo(logins.get(newLogin).get(i));
		 				logins.get(newLogin).add(a);
		 			}
		 		}else{
		 			logins.get(newLogin).add(a);
		 		}
		 		//Customer account has successfully been created
		 		newAccountCreated = true;
		 		
			}
		 	else {
		 		JOptionPane.showMessageDialog(null, "Checking account already exists");
		 	}
			
			break;
	 case 2:
		 	//if user does not have existing market account, create a market account
		 	if(!hasMarketAccount && c!=null)
		 	{
		 
			//creates a MarketAccount account
				a = new MarketAccount(c,JOptionPane.showInputDialog("Transaction Date"));
		 		
		 		
		 		date = JOptionPane.showInputDialog("Transaction Date");
		 		
				/*a = new MarketAccount(c,date,
					new Transaction(date,
					Double.parseDouble(JOptionPane.showInputDialog("amount"))));*/

			//add account to hashmap
		 		System.out.println("****Account to be Added**** \n" + a.toString());
		 		if (logins.containsKey(newLogin) && currentSize > 0)
		 		{
		 			for(int i = 0; i < currentSize; i++) {
			 			a.compareTo(logins.get(newLogin).get(i));
		 				logins.get(newLogin).add(a);
		 			}
		 		}else{
		 			logins.get(newLogin).add(a);
		 		}
		 		//Customer account has successfully been created
		 		newAccountCreated = true;
		 	}
		 	else {
		 		JOptionPane.showMessageDialog(null, "Market account already exists");
		 	}
	 		
			break;
	 case 3: //If the user selects 3 
		 	//if user does not have existing savings account, create a savings account
		 	if(!hasSavingsAccount && c!=null)
		 	{
		 		date = JOptionPane.showInputDialog("Transaction Date");
		 		
				a = new Savings(c,date,
					new Transaction(date,
					Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		   
				//add account to hashmap
		 		System.out.println("****Account to be Added**** \n " + a.toString());
		 		if (logins.containsKey(newLogin) && currentSize > 0)
		 		{
		 			for(int i = 0; i < currentSize; i++) {
			 			a.compareTo(logins.get(newLogin).get(i));
		 				logins.get(newLogin).add(a);
		 			}
		 		}else{
		 			logins.get(newLogin).add(a);
		 		}
		 		
		 		//Customer account has successfully been created
		 		newAccountCreated = true;
		 	}
		 	else {
		 		JOptionPane.showMessageDialog(null, "Savings account already exists");
		 	}
			break;
	 case 4: //Exit, exit the program
	 System.exit(0);
	 default:
		 break;
	 }
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		 totalAccounts = logins.get(newLogin).size();
		}while(JOptionPane.showConfirmDialog(null,"Do you want to add more accounts ?", "Please select",JOptionPane.YES_NO_OPTION)==0 && logins.get(newLogin).size() < Account.MAX_NUM_ACC );

	 // needs to a try catch result inside of the while
	 //}while(totalAccounts<= Account.MAX_NUM_ACC ); 
	 if(totalAccounts >= Account.MAX_NUM_ACC ) {
		 JOptionPane.showMessageDialog(null, "You reached the maximum number of bank accounts");
	 }
	 
	 //if new customer account is successfully created, return the login
	 //save customer account to the text file
	 if(newAccountCreated)
	 {
		 ImportFiles.writeToFile(pathEx, logins);
		 return newLogin;
	 }
	 else {return null;}

	
}

/*
 * The customer can create additional accounts if they dont exist yet.
 * find and return existing accounts for the customer
 * give option to create accounts that dont exist
 * 
 * @param Login customerLogin, HashMap<Login, Vector<Account>> logins
 * @return none
 * 
 * */
public static void CreateBankAccount(String pathEx, Login customerLogin, HashMap<Login, Vector<Account> > logins)
{
	
		String customerFirstName = logins.get(customerLogin).firstElement().getCustomer().getFirstName();

		Account a = null;
		
		Customer cust = logins.get(customerLogin).firstElement().getCustomer();

		do {
			
			//count how many accounts customer has
			final int NUM_ACCOUNTS = logins.get(customerLogin).size();
			
			boolean hasCheckingAccount = false;
			boolean hasSavingsAccount = false;
			boolean hasMarketAccount = false;
			
			//identify what type of accounts already exist for the customer
			for(int i = 0; i<NUM_ACCOUNTS; i++)
			{
				//if the account in the vector is a checking account, set boolean hasCheckingAccount to true
				if(logins.get(customerLogin).get(i) instanceof Checking)
				{
					hasCheckingAccount = true;
				}
				else if(logins.get(customerLogin).get(i) instanceof Savings)
				{
					hasSavingsAccount = true;
				}
				else if(logins.get(customerLogin).get(i) instanceof MarketAccount)
				{
					hasMarketAccount = true;
				}
			}
			
			
			//Menu for the user to create a new account
			 ArrayList<String> optionList = new ArrayList<String>();
				
			 optionList.add("Checking Account");
			 optionList.add("Money Market Account");
			 optionList.add("Savings Account");
			 optionList.add("Exit");
			 Object[] options = optionList.toArray();
			 int value = JOptionPane.showOptionDialog(
			                 null,
			                 "What kind of account do you want to create?",
			                 "Pick",
			                 JOptionPane.YES_NO_OPTION,
			                 JOptionPane.QUESTION_MESSAGE,
			                 null,
			                 options,
			                 optionList.get(0));

			 String opt = optionList.get(value);
			 
			 
			 switch(opt)
			 {
			 case "Checking Account": //If the user selects 1 -- 
					//creates a checking account
				 	if(!hasCheckingAccount)
				 	{
				 		String date = JOptionPane.showInputDialog("Transaction Date: ");
				 		
						a = new Checking(cust,date,
							 	new Transaction(date,
								Double.parseDouble(JOptionPane.showInputDialog("amount"))));
					
						//add account to hashmap
						if (logins.containsKey(customerLogin))
						{
							logins.get(customerLogin).add(a);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						}
				 	}
				 	else
				 	{
				 		JOptionPane.showMessageDialog(null, "Checking account already exists");
				 	}
				 	

					break;
			 case "Money Market Account":
					//creates a MarketAccount account
				 	if(!hasMarketAccount)
				 	{
						a = new MarketAccount(cust,JOptionPane.showInputDialog("Transaction Date"));
				 		
				 		String date = JOptionPane.showInputDialog("Transaction Date: ");
				 		
					   /* a = new MarketAccount(cust,date,
								new Transaction(date,
								Double.parseDouble(JOptionPane.showInputDialog("amount"))));*/
						
						//add account to hashmap
						if (logins.containsKey(customerLogin))
						{
							logins.get(customerLogin).add(a);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						}
				 	}
				 	else
				 	{
				 		JOptionPane.showMessageDialog(null, "Market account already exists");
				 	}

					
					break;
			 case "Savings Account": //If the user selects 3 
					//creates a savings account
				 	if(!hasSavingsAccount)
				 	{
				 		String date = JOptionPane.showInputDialog("Transaction Date: ");
				 		
					    a = new Savings(cust,date,
								new Transaction(date,
								Double.parseDouble(JOptionPane.showInputDialog("amount"))));
					    
						//add account to hashmap
						if (logins.containsKey(customerLogin))
						{
							logins.get(customerLogin).add(a);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						}
						
				 	}
				 	else
				 	{
				 		JOptionPane.showMessageDialog(null, "Savings account already exists");
				 	}

					
					break;
			 case "Exit": //Exit, exit the program
			 System.exit(0);
			 default:
				 break;
			 }
			
			 }while(JOptionPane.showConfirmDialog(null,"Do you want to add more accounts ?", "Please select",JOptionPane.YES_NO_OPTION)==0);
		
}


/*
 * The program should display the customer account number, first name, last name, total account balance before interest,
 *  total interest, and grand total balance for each account for each type of account.
 *  
 *  @param Login customerLogin, HashMap<Login, Vector<Account>> logins
 *  @return none
 * 
 * */
public static void ViewAccounts(Login customerLogin, HashMap<Login, Vector<Account>> logins)
{
	//count how many accounts customer has
	final int NUM_ACCOUNTS = logins.get(customerLogin).size();
	
	boolean hasCheckingAccount = false;
	boolean hasSavingsAccount = false;
	boolean hasMarketAccount = false;
	
	int checkingAccountIndex = -1;
	int savingsAccountIndex = -1;
	int marketAccountIndex = -1;
	
	//identify what type of accounts already exist for the customer
	for(int i = 0; i<NUM_ACCOUNTS; i++)
	{
		//if the account in the vector is a checking account, set boolean hasCheckingAccount to true
		if(logins.get(customerLogin).get(i) instanceof Checking)
		{
			hasCheckingAccount = true;
			checkingAccountIndex = i;
		}
		else if(logins.get(customerLogin).get(i) instanceof Savings)
		{
			hasSavingsAccount = true;
			savingsAccountIndex = i;
		}
		else if(logins.get(customerLogin).get(i) instanceof MarketAccount)
		{
			hasMarketAccount = true;
			marketAccountIndex = i;
		}
	}
	
	
	 do {

	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 Object[] options = optionList.toArray();
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "View an Account:\n 1. Checking Account \n 2. Money Market Account \n3. Savings Account \n4. Exit",
	                 "Pick",
	                 JOptionPane.YES_NO_OPTION,
	                 JOptionPane.QUESTION_MESSAGE,
	                 null,
	                 options,
	                 optionList.get(0));

	 String opt = optionList.get(value);
	 
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1: //If the user selects 1 -- display Checking acount
		 //if the customer has checking account, find it, and return toString
			 if(hasCheckingAccount && (checkingAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(checkingAccountIndex).toString());
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a checking account");
			 }
			break;
	 case 2:
			//display MarketAccount account
			 if(hasMarketAccount && (marketAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(marketAccountIndex).toString());
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a money market account");
			 }
			break;
	 case 3: //If the user selects 3 
			//display savings account
			 if(hasSavingsAccount&& (savingsAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(savingsAccountIndex).toString());
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a savings account");
			 }
		 break;
	 case 4: //Exit menu
		 break;
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to view another account ?", "Please select",JOptionPane.YES_NO_OPTION)==0);
}



/*
 * Customer can make a deposit into the selected account
 * @param Login customerLogin, HashMap<Login ,Vector<Accoutn>> logins
 * @return none
 * */
public static void MakeDeposit(String pathEx, Login customerLogin, HashMap<Login, Vector<Account>> logins) {
	//count how many accounts customer has
		final int NUM_ACCOUNTS = logins.get(customerLogin).size();
		
		boolean hasCheckingAccount = false;
		boolean hasSavingsAccount = false;
		boolean hasMarketAccount = false;
		
		int checkingAccountIndex = -1;
		int savingsAccountIndex = -1;
		int marketAccountIndex = -1;
		
		//identify what type of accounts already exist for the customer
		for(int i = 0; i<NUM_ACCOUNTS; i++)
		{
			//if the account in the vector is a checking account, set boolean hasCheckingAccount to true
			if(logins.get(customerLogin).get(i) instanceof Checking)
			{
				hasCheckingAccount = true;
				checkingAccountIndex = i;
			}
			else if(logins.get(customerLogin).get(i) instanceof Savings)
			{
				hasSavingsAccount = true;
				savingsAccountIndex = i;
			}
			else if(logins.get(customerLogin).get(i) instanceof MarketAccount)
			{
				hasMarketAccount = true;
				marketAccountIndex = i;
			}
		}
		
		
		 do {

		 ArrayList<String> optionList = new ArrayList<String>();
			
		 optionList.add("1");
		 optionList.add("2");
		 optionList.add("3");
		 optionList.add("4");
		 Object[] options = optionList.toArray();
		 int value = JOptionPane.showOptionDialog(
		                 null,
		                 "Make a deposit:\n 1. Checking Account \n 2. Money Market Account \n3. Savings Account \n4. Cancel",
		                 "Pick",
		                 JOptionPane.YES_NO_OPTION,
		                 JOptionPane.QUESTION_MESSAGE,
		                 null,
		                 options,
		                 optionList.get(0));

		 String opt = optionList.get(value);
		 
		 
		 switch(Integer.parseInt(opt))
		 {
		 case 1: //If the user selects 1 -- display Checking acount
			 //if the customer has checking account, find it, and return toString
				 if(hasCheckingAccount && (checkingAccountIndex != -1))
				 {
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(checkingAccountIndex).toString());
					 	String date;
					 	Double amount;
					 	try {
						 	date = JOptionPane.showInputDialog("Transaction Date: ");
						 	
						 	amount = Double.parseDouble(JOptionPane.showInputDialog("Deposit Amount: "));
						 	
						 	Transaction t = new Transaction(date, amount);
						 	
						 	//FIX -- make changes to account/checking classes to set deposit for single not total account
						 	//logins.get(customerLogin).get(checkingAccountIndex).setDeposit(t);
						 	logins.get(customerLogin).get(checkingAccountIndex).setDepositTest(t);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						 	//---------------
						 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(checkingAccountIndex).toString());
						 	
					 	}
					 	catch(IllegalArgumentException e)
					 	{
					 		JOptionPane.showMessageDialog(null, e.getMessage());
					 	}
					 	
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "You don't have a checking account");
				 }
				break;
		 case 2:
				//display MarketAccount account
				 if(hasMarketAccount && (marketAccountIndex != -1))
				 {
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(marketAccountIndex).toString());
					 	String date;
					 	Double amount;
					 	try {
						 	date = JOptionPane.showInputDialog("Transaction Date: ");
						 	
						 	amount = Double.parseDouble(JOptionPane.showInputDialog("Deposit Amount: "));
						 	
						 	Transaction t = new Transaction(date, amount);
						 	
						 	//FIX -- make changes to account/market classes to set deposit for single not total account
						 	//logins.get(customerLogin).get(marketAccountIndex).setDeposit(t);
						 	logins.get(customerLogin).get(marketAccountIndex).setDepositTest(t);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						 	
						 	//---------------
						 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(marketAccountIndex).toString());
						 	
					 	}
					 	catch(IllegalArgumentException e)
					 	{
					 		JOptionPane.showMessageDialog(null, e.getMessage());
					 	}
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "You don't have a money market account");
				 }
				break;
		 case 3: //If the user selects 3 
				//display savings account
				 if(hasSavingsAccount&& (savingsAccountIndex != -1))
				 {
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(savingsAccountIndex).toString());
					 	String date;
					 	Double amount;
					 	try {
						 	date = JOptionPane.showInputDialog("Transaction Date: ");
						 	
						 	amount = Double.parseDouble(JOptionPane.showInputDialog("Deposit Amount: "));
						 	
						 	Transaction t = new Transaction(date, amount);
						 	
						 	//FIX -- make changes to account/savings classes to set deposit for single not total account
						 	//logins.get(customerLogin).get(savingsAccountIndex).setDeposit(t);
						 	logins.get(customerLogin).get(savingsAccountIndex).setDepositTest(t);
							//-----------------------------------
							 ImportFiles.writeToFile(pathEx, logins);
						 	
						 	//---------------
						 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(savingsAccountIndex).toString());
						 	
					 	}
					 	catch(IllegalArgumentException e)
					 	{
					 		JOptionPane.showMessageDialog(null, e.getMessage());
					 	}
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "You don't have a savings account");
				 }
			 break;
		 case 4: //Exit, exit the program
			 break;
		 default:
			 break;
		 }
		
		 }while(JOptionPane.showConfirmDialog(null,"Do you want to deposit into another account ?", "Please select",JOptionPane.YES_NO_OPTION)==0);
}


/*
 * Customer can take withdrawal from the selected account
 * @param Login customerLogin, HashMap<Login ,Vector<Accoutn>> logins
 * @return none
 * */
public static void MakeWithdrawal(String pathEx, Login customerLogin, HashMap<Login, Vector<Account>> logins) {
	//count how many accounts customer has
	final int NUM_ACCOUNTS = logins.get(customerLogin).size();
	
	boolean hasCheckingAccount = false;
	boolean hasSavingsAccount = false;
	boolean hasMarketAccount = false;
	
	int checkingAccountIndex = -1;
	int savingsAccountIndex = -1;
	int marketAccountIndex = -1;
	
	//identify what type of accounts already exist for the customer
	for(int i = 0; i<NUM_ACCOUNTS; i++)
	{
		//if the account in the vector is a checking account, set boolean hasCheckingAccount to true
		if(logins.get(customerLogin).get(i) instanceof Checking)
		{
			hasCheckingAccount = true;
			checkingAccountIndex = i;
		}
		else if(logins.get(customerLogin).get(i) instanceof Savings)
		{
			hasSavingsAccount = true;
			savingsAccountIndex = i;
		}
		else if(logins.get(customerLogin).get(i) instanceof MarketAccount)
		{
			hasMarketAccount = true;
			marketAccountIndex = i;
		}
	}
	
	
	 do {

	 ArrayList<String> optionList = new ArrayList<String>();
		
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 Object[] options = optionList.toArray();
	 int value = JOptionPane.showOptionDialog(
	                 null,
	                 "Make a withdrawal:\n 1. Checking Account \n 2. Money Market Account \n3. Savings Account \n4. Cancel",
	                 "Pick",
	                 JOptionPane.YES_NO_OPTION,
	                 JOptionPane.QUESTION_MESSAGE,
	                 null,
	                 options,
	                 optionList.get(0));

	 String opt = optionList.get(value);
	 
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1: //If the user selects 1 -- display Checking acount
		 //if the customer has checking account, find it, and return toString
			 if(hasCheckingAccount && (checkingAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(checkingAccountIndex).toString());
				 	String date;
				 	Double amount;
				 	try {
					 	date = JOptionPane.showInputDialog("Transaction Date: ");
					 	
					 	amount = Double.parseDouble(JOptionPane.showInputDialog("Withdrawal Amount: "));
					 	
					 	Transaction t = new Transaction(date, amount);
					 	
					 	//FIX -- make changes to account/checking classes to set withdrawal for single not total account
					 	//logins.get(customerLogin).get(checkingAccountIndex).setWithdrawal(t);
					 	logins.get(customerLogin).get(checkingAccountIndex).setWithdrawTest(t);
						//-----------------------------------
						 ImportFiles.writeToFile(pathEx, logins);
					 	//---------------
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(checkingAccountIndex).toString());
					 	
				 	}
				 	catch(IllegalArgumentException e)
				 	{
				 		JOptionPane.showMessageDialog(null, e.getMessage());
				 	}
				 	
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a checking account");
			 }
			break;
	 case 2:
			//display MarketAccount account
			 if(hasMarketAccount && (marketAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(marketAccountIndex).toString());
				 	String date;
				 	Double amount;
				 	try {
					 	date = JOptionPane.showInputDialog("Transaction Date: ");
					 	
					 	amount = Double.parseDouble(JOptionPane.showInputDialog("Withdrawal Amount: "));
					 	
					 	Transaction t = new Transaction(date, amount);
					 	
					 	//FIX -- make changes to account/market classes to set deposit for single not total account
					 	//logins.get(customerLogin).get(marketAccountIndex).setWithdrawal(t);
					 	logins.get(customerLogin).get(marketAccountIndex).setWithdrawTest(t);

						//-----------------------------------
						 ImportFiles.writeToFile(pathEx, logins);
					 	//---------------
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(marketAccountIndex).toString());
					 	
				 	}
				 	catch(IllegalArgumentException e)
				 	{
				 		JOptionPane.showMessageDialog(null, e.getMessage());
				 	}
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a money market account");
			 }
			break;
	 case 3: //If the user selects 3 
			//display savings account
			 if(hasSavingsAccount&& (savingsAccountIndex != -1))
			 {
				 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(savingsAccountIndex).toString());
				 	String date;
				 	Double amount;
				 	try {
					 	date = JOptionPane.showInputDialog("Transaction Date: ");
					 	
					 	amount = Double.parseDouble(JOptionPane.showInputDialog("Withdrawal Amount: "));
					 	
					 	Transaction t = new Transaction(date, amount);
					 	
					 	//FIX -- make changes to account/savings classes to set deposit for single not total account
					 	//logins.get(customerLogin).get(savingsAccountIndex).setWithdrawal(t);
					 	logins.get(customerLogin).get(savingsAccountIndex).setWithdrawTest(t);

						//-----------------------------------
						 ImportFiles.writeToFile(pathEx, logins);
					 	
					 	//---------------
					 	JOptionPane.showMessageDialog(null, logins.get(customerLogin).get(savingsAccountIndex).toString());
					 	
				 	}
				 	catch(IllegalArgumentException e)
				 	{
				 		JOptionPane.showMessageDialog(null, e.getMessage());
				 	}
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "You don't have a savings account");
			 }
		 break;
	 case 4: //Exit, exit the program
		 break;
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to withdraw from another account ?", "Please select",JOptionPane.YES_NO_OPTION)==0);
}

 
}
