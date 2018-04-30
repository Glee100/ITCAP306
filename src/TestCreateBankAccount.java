import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

public class TestCreateBankAccount {

	
	
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
		
	 	//TEST
	 	Login random = null;
	 	
	 	//while there is a next pair
	 	while(it.hasNext())
	 	{
	 		Map.Entry<Login, Vector<Account>> entry = (Map.Entry<Login, Vector<Account>>)it.next();
	 		
	 		System.out.println(entry.getKey().getUserName()+ "  " + entry.getKey().getPassword() );
	 		
	 		random = entry.getKey();
	 	
	 	}
	 	
	 	JOptionPane.showMessageDialog(null, "You are using: Username: " + random.getUserName());
	 	CreateBankAccount(random, logins);

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
				 optionList.add("Savings Account");
				 optionList.add("Money Market Account");
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
	
}
