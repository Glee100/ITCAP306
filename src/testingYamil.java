import java.util.*;

import javax.swing.JOptionPane;

/**
 * Implementation Class
 *
 * @author Gene Lee
 * @author Yamil Castro
 * @version (a version number or a date)
 */
public class testingYamil
{
    // instance variables - replace the example below with your own
 public static void main(String[] args) {
	 
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
	
 	
 	//Login or create an account
	 ArrayList<String> optionList = new ArrayList<String>();
	 
	 Login customerLogin = null;
	 
	 optionList.add("1");
	 optionList.add("2");
	 optionList.add("3");
	 optionList.add("4");
	 optionList.add("5");
	 Object[] options = optionList.toArray();
	 
	 while(customerLogin == null)
	 {
		 int value = JOptionPane.showOptionDialog(
                 null,
                 "Please select an option:\n 1. Log in \n 2. Create New Account \n4. Sort \n5. Exit",
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
		 case 4://sorting
			 sort(logins);
			 break;
		 case 5: //If the user selects 3 -- Exit, exit the program
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
  * */
 public static void Menu(Login customerLogin, HashMap<Login, Vector<Account> > logins) {
		 
		 ArrayList<String> optionList = new ArrayList<String>();
		
		 optionList.add("1");
		 optionList.add("2");
		 optionList.add("3");

		 Object[] options = optionList.toArray();
		 
		 boolean menuClose = false;
		 
		 do {
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
			 CreateBankAccount(customerLogin, logins);
			 break;
		 case 2: //If the user selects 2 -- view accounts
			 ViewAccounts(customerLogin, logins);
			 break;
		 case 3: //If the user selects 3 -- Exit, exit the program
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
 * */
public static Login CreateLogin()
{
	 String username = JOptionPane.showInputDialog("Enter Username: ");
	 String password = JOptionPane.showInputDialog("Enter password: ");
	 
	 Login log = new Login(username, password);
	 
	 return log;
}

public static Login AttemptLogin(HashMap<Login, Vector<Account> > logins)
{
	
	//initialize temporary Login object
	 	Login x = null;
	 	boolean isValid = false;
		 
		 try {
			 x = CreateLogin();
			 
			 //will return illegal argument exception if login is invalid
			 //isValid = Login.validate(x, logins);
			 
			 Login matchedLogin = Login.validate(x, logins);
			 
			 //call menu method with customer associated with the login
			 
			 //get value of the specific login
			 
			 //if(isValid)
			 if(matchedLogin != null)
			 {
				 //Customer c = (Customer) logins.get(new Login("customer3", "125")).elements();
				//Customer c = (Customer) logins.get(x).firstElement();
				 
				 //get customer
					if (logins.containsKey(matchedLogin))
					{
						//System.out.println("GET CUSTOMER    : " + logins.get(x).firstElement().getCustomer());
						System.out.println("GET CUSTOMER    : " + logins.get(matchedLogin).firstElement().getCustomer());
						System.out.println("GET CUSTOMER NAME   : " + logins.get(matchedLogin).firstElement().getCustomer().getFirstName());

						return matchedLogin;
					}
					else {
						System.out.println("\nKEY DOESNT MATCH");
						return null;
					}
				 
				// System.out.println(c.getFirstName() + " " + c.getLastName());
			 }

			 //Menu(d);


		 }
		 catch(IllegalArgumentException e)
		 {
			 JOptionPane.showMessageDialog(null, e.getMessage());
		 }
		 return null;
		 
		 
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


/*
 * This method creates a new Customer, and allows the customer to create a checking, savings, and money market account
 * */
public static void CreateCustomerAccount(HashMap<Login, Vector<Account> > logins) {
	Customer c;
	Account a = null;
	int totalAccounts = 0;
	 do {
	// needs a try catch here
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
	
		 	c = new Customer("Shaman","Dog", "manassas", "57111111","asd@asd.com");
	//initialize Key Value set in logins hashmap, we will add this customer to the hashmap
	
		 	logins.put(newLogin, new Vector<Account>() {{
		 	
	}});
	do {
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
	 case 1:
		 
		 a = new Checking(c,"12/12/12",new Transaction("transaction Date", 900.00));
		 	/*	a = new Checking(c,JOptionPane.showInputDialog("Transaction Date"),
					 	new Transaction(JOptionPane.showInputDialog("transaction Date"),
						Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		 		
		 		*/
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
		 		
			//}
			
			break;
	 case 2:
			//creates a MarketAccount account
				//a = new MarketAccount(c,JOptionPane.showInputDialog("Transaction Date"));
				a = new MarketAccount(c,"12/12/12");
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
	 		
			break;
	 case 3: //If the user selects 3 
			//creates a savings account
		 /*
				a = new Savings(c,JOptionPane.showInputDialog("Transaction Date"),
					new Transaction(JOptionPane.showInputDialog("transaction Date"),
					Double.parseDouble(JOptionPane.showInputDialog("amount"))));
		   */
		 		a = new Savings(c,"12/12/12", new Transaction("12/12/12",800.00));
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
		 System.out.println("Number of total Accounts: >>>>>>"+totalAccounts);
		}while(JOptionPane.showConfirmDialog(null,"Do you want to add more accounts ?", "Please select",JOptionPane.YES_NO_OPTION)==0 && logins.get(newLogin).size() < Account.MAX_NUM_ACC );

	 // needs to a try catch result inside of the while
	 }while(totalAccounts<= Account.MAX_NUM_ACC ); 
	 if(totalAccounts >= Account.MAX_NUM_ACC ) {
		 JOptionPane.showMessageDialog(null, "You reached the maximum number of accounts");
	 }
	
}
/*
 * The customer can create additional accounts if they dont exist yet.
 * find and return existing accounts for the customer
 * give option to create accounts that dont exist
 * 
 * */
public static void CreateBankAccount(Login customerLogin, HashMap<Login, Vector<Account> > logins)
{
		String customerFirstName = logins.get(customerLogin).firstElement().getCustomer().getFirstName();

		Account a = null;
		
		Customer cust = logins.get(customerLogin).firstElement().getCustomer();
		
		//--------------------------------------------------------------------
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
						
						//add account to hashmap
						if (logins.containsKey(customerLogin))
						{
							logins.get(customerLogin).add(a);
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
	 case 4: //Exit, exit the program
		 break;
	 default:
		 break;
	 }
	
	 }while(JOptionPane.showConfirmDialog(null,"Do you want to view another account ?", "Please select",JOptionPane.YES_NO_OPTION)==0);
}
public static void sort(HashMap<Login, Vector<Account>> logins) {
	
	// number of keys, size of the hash map
	final int NUM_ACCOUNTS = logins.size();
	//creating an iterator
	
	Set<Login> keySet = logins.keySet();
	Iterator<Login> keySetIterator = keySet.iterator();
	
	while(keySetIterator.hasNext()) {
		System.out.println("----------------");
		System.out.println("Iterarting Map");
		
		Login key = keySetIterator.next();
		//logins.get(key) gets the value
		System.out.println("key"+ key + "Value: " + logins.get(key));
	}
	Vector<Account> sortBalance = new Vector<Account>();

/*int [] bucket=new int[maxVal+1];
 
for (int i=0; i<bucket.length; i++) {
   bucket[i]=0;
}

for (int i=0; i<a.length; i++) {
   bucket[a[i]]++;
}

int outPos=0;
for (int i=0; i<bucket.length; i++) {
   for (int j=0; j<bucket[i]; j++) {
      a[outPos++]=i;
   }

}*/

}
 
}
