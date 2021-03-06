import java.util.*;

import javax.swing.JOptionPane;

/**
 * Implementation Class
 *
 * @author Gene Lee
 * @author Yamil Castro
 * @version (a version number or a date)
 */
public class GeneImpClass
{
    // instance variables - replace the example below with your own
 public static void main(String[] args){
	 
	//map with login as key, and arraylist of customer and accounts as value
	HashMap<Login, Vector<Account> > logins = new HashMap<Login, Vector<Account>>();
	 
	String path = "./src/ImportCustomers.txt";
	
	//call method to read files from path
    ImportFiles.readFromFile(path, logins);
 
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
				 Menu(customerLogin, logins);
			 }
			 
			 break;
		 case 2: //If the user selects 2 -- create a new user account in the system
			 CreateCustomerAccount(logins);
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
 public static void Menu(Login customerLogin, HashMap<Login, Vector<Account> > logins) {
		 
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
		 CreateBankAccount(customerLogin, logins);
		 break;
	 case 2: //If the user selects 2 -- view accounts
		 ViewAccounts(customerLogin, logins);
		 break;
	 case 3: //If the user selects 2 -- view accounts
		 MakeDeposit(customerLogin, logins);
		 break;
	 case 4: //If the user selects 2 -- view accounts
		 MakeWithdrawal(customerLogin, logins);
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
						System.out.println("GET CUSTOMER    : " + logins.get(matchedLogin).firstElement().getCustomer());
						System.out.println("GET CUSTOMER NAME   : " + logins.get(matchedLogin).firstElement().getCustomer().getFirstName());

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
 * @return Vector<Account>
 * */
public static Vector<Account> CreateCustomerAccount(HashMap<Login, Vector<Account> > logins) {
	 Vector<Account> objVector = new Vector<Account>();
	Customer c;
	Account a = null;
	 do {
		 
	//create login
	/*	 
	String user = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your username? ");	 
	String password = JOptionPane.showInputDialog("---Create an Account---\n\nWhat is your password? ");	 
	*/
		 String user = "Yamil01";
		 String password = "123";	
	Login newLogin = new Login(user, password);	 
		 
	 // creates a customer
	/*
	c = new Customer(JOptionPane.showInputDialog("First Name"),JOptionPane.showInputDialog("Last Name"), 
			 					JOptionPane.showInputDialog("Address"), JOptionPane.showInputDialog("Phone"), 
			 							JOptionPane.showInputDialog("email"));
	
	*/
	c = new Customer("Yamil","Castro", "manassas", "57111111","asd@asd.com");
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
	 
	 JOptionPane.showMessageDialog(null, a.getTotalNumAcc());
	 
	 switch(Integer.parseInt(opt))
	 {
	 case 1: //If the user selects 1 -- 
			//creates a checking account
		 	if(a.getNumAcc()> Account.MAX_ACC_EACH) {
		 		JOptionPane.showMessageDialog(null, "You can only have"+ Account.MAX_ACC_EACH+"Checkings account!");
		 		
		 	}else {
		 		a = new Checking(c,JOptionPane.showInputDialog("Transaction Date"),
					 	new Transaction(JOptionPane.showInputDialog("transaction Date"),
						Double.parseDouble(JOptionPane.showInputDialog("amount"))));
			//add account to hashmap
		 		if (logins.containsKey(newLogin))
		 		{
				logins.get(newLogin).add(a);
		 		}
			}
			
			break;
	 case 2:
			//creates a MarketAccount account
			if(a.getNumAcc()> Account.MAX_ACC_EACH) {
				JOptionPane.showMessageDialog(null, "You can only have"+ Account.MAX_ACC_EACH+"market account!");
			}else{
				a = new MarketAccount(c,JOptionPane.showInputDialog("Transaction Date"));
			
			//add account to hashmap
				if (logins.containsKey(newLogin))
				{
				logins.get(newLogin).add(a);
				}
	 		}
			break;
	 case 3: //If the user selects 3 
			//creates a savings account
			if(a.getNumAcc()> Account.MAX_ACC_EACH) {
				JOptionPane.showMessageDialog(null, "You can only have "+ Account.MAX_ACC_EACH +" savings account!");
			}else{
				a = new Savings(c,JOptionPane.showInputDialog("Transaction Date"),
					new Transaction(JOptionPane.showInputDialog("transaction Date"),
					Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		    
				//add account to hashmap
				if (logins.containsKey(newLogin))
				{
				logins.get(newLogin).add(a);
				}
			}
			break;
	 case 4: //Exit, exit the program
	 System.exit(0);
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to add more accounts ?", "Please select",JOptionPane.YES_NO_OPTION)==0
			 && a.getTotalNumAcc() <= Account.MAX_NUM_ACC );

	return objVector;
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
public static void CreateBankAccount(Login customerLogin, HashMap<Login, Vector<Account> > logins)
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
				 		try {
							a = new Checking(cust,date,
								 	new Transaction(date,
									Double.parseDouble(JOptionPane.showInputDialog("amount"))));
						
							//add account to hashmap
							if (logins.containsKey(customerLogin))
							{
								logins.get(customerLogin).add(a);
							}
				 		}
				 		catch(IllegalArgumentException e)
				 		{
				 			JOptionPane.showMessageDialog(null, e.getMessage());
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
				 		try {
							a = new MarketAccount(cust,JOptionPane.showInputDialog("Transaction Date"));
							
							//add account to hashmap
							if (logins.containsKey(customerLogin))
							{
								logins.get(customerLogin).add(a);
							}
				 		}
				 		catch(IllegalArgumentException e)
				 		{
				 			JOptionPane.showMessageDialog(null, e.getMessage());
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
				 		try {
						    a = new Savings(cust,date,
									new Transaction(date,
									Double.parseDouble(JOptionPane.showInputDialog("amount"))));
						    
							//add account to hashmap
							if (logins.containsKey(customerLogin))
							{
								logins.get(customerLogin).add(a);
							}
				 		}
				 		catch(IllegalArgumentException e)
				 		{
				 			JOptionPane.showMessageDialog(null, e.getMessage());
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
public static void MakeDeposit(Login customerLogin, HashMap<Login, Vector<Account>> logins) {
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
						 	logins.get(customerLogin).get(checkingAccountIndex).setDeposit(t);
						 	
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
						 	logins.get(customerLogin).get(marketAccountIndex).setDeposit(t);
						 	
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
						 	logins.get(customerLogin).get(savingsAccountIndex).setDeposit(t);
						 	
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
public static void MakeWithdrawal(Login customerLogin, HashMap<Login, Vector<Account>> logins) {
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
					 	logins.get(customerLogin).get(checkingAccountIndex).setWithdrawal(t);
					 	
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
					 	logins.get(customerLogin).get(marketAccountIndex).setWithdrawal(t);
					 	
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
					 	logins.get(customerLogin).get(savingsAccountIndex).setWithdrawal(t);
					 	
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
