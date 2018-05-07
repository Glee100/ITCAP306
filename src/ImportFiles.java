import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class ImportFiles {

	public static void main(String [] args)
	{
		
		HashMap<Login, Vector<Account>> logins = new HashMap<Login, Vector<Account>>();
		
		String path = "./src/ImportCustomers.txt";
		
		String pathEx = "./src/ExportCustomers.txt";
 
	    readFromFile(path, logins);
	    
	    
		 //print everything in map
	 	Iterator it = logins.entrySet().iterator();
		System.out.println("\n\nLogins Inside Hashmap: \n");
	 	//while there is a next pair
	 	while(it.hasNext())
	 	{
	 		Map.Entry<Login, Vector<Account>> entry = (Map.Entry<Login, Vector<Account>>)it.next();
	 		
	 		System.out.println(entry.getKey().getUserName()+ "  " + entry.getKey().getPassword() );
	 	
	 	}
	 	
	 	//update File
	 	writeToFile(pathEx, logins);

	}
	
	
	public static void readFromFile(String path, HashMap<Login, Vector<Account>> logins){
		try{
			String line =null; 
			Scanner scan =new Scanner(new FileInputStream(new File(path)));  
			Scanner scan2 =null; 
			int counter=0; 
			while(scan.hasNextLine()){
				line = scan.nextLine();
				scan2 = new Scanner(line);
				scan2.useDelimiter(","); 
				//tokenizing the line variable
				
				////String fName,String lName, String address, String phone, String email, String username, String password
				
				String fName = scan2.next().trim();
				String lName = scan2.next().trim();
				String address = scan2.next().trim();
				String phone = scan2.next().trim();
				String email = scan2.next().trim();
				String user = scan2.next().trim();
				String password = scan2.next().trim();
				
				Customer cus = new Customer(fName,lName, address, phone, email);
				
				//TESTING
				System.out.println("\nCUSTOMER NAME " + cus.getFirstName() + " " + cus.getLastName());
				
				Account checkingAccount;
				Account savingsAccount;
				Account marketAccount;
				String date = "";
				Transaction transaction = null;
				
				Login newLogin = new Login(user, password);
				
				//initialize Key Value set in logins hashmap
				logins.put(newLogin, new Vector<Account>() {{

				}});
				
				//in text file, we will always having the order checking, then savings, then market account, so if statements will work
				while(scan2.hasNext()) {
					try {
						if(scan2.hasNext(" Checking"))
						{
							System.out.println("HAS CHECKING ACCOUNT");
							
							//we dont need accountType, it is used to store text we dont need
							String accountType = scan2.next().trim();
							
							date = scan2.next().trim();
							transaction = new Transaction(date, Double.parseDouble(scan2.next()));
							checkingAccount = new Checking(cus, date, transaction);
							
							//add checking account to the hashmap
							if (logins.containsKey(newLogin))
							{
								logins.get(newLogin).add(checkingAccount);
							}

						}
						else if(scan2.hasNext(" Savings"))
						{
							System.out.println("HAS SAVINGS ACCOUNT");
							
							//we dont need accountType, it is used to store text we dont need
							String accountType = scan2.next().trim();
							
							date = scan2.next().trim();
							transaction = new Transaction(date, Double.parseDouble(scan2.next()));
							savingsAccount = new Savings(cus, date, transaction);
							
							//add savings account to the hashmap
							if (logins.containsKey(newLogin))
							{
								logins.get(newLogin).add(savingsAccount);
							}

						}
						else if(scan2.hasNext(" MarketAccount"))
						{
							System.out.println("HAS MARKET ACCOUNT");
							
							//we dont need accountType, it is used to store text we dont need
							String accountType = scan2.next().trim();
							
							date = scan2.next().trim();
							//transaction = new Transaction(date, Double.parseDouble(scan2.next()));
							marketAccount = new MarketAccount(cus, date);
							System.out.println("MAKRET WORKS");
							//add market account to the hashmap
							if (logins.containsKey(newLogin))
							{
								logins.get(newLogin).add(marketAccount);
							}
						}

					}
					catch(Exception e)
					{
						e.getMessage();
					}

				}
				//students[counter++]=s;
				
				System.out.println(line);
			}
			scan.close(); 
		}
		catch(FileNotFoundException e){
			e.printStackTrace(); 
		}
		catch( IOException e){
			e.printStackTrace(); 
		}


	}


	public static void writeToFile(String path, HashMap<Login, Vector<Account>> logins)
	{
		try{
			FileWriter fw = new FileWriter(new File(path));
			FileOutputStream fis =new FileOutputStream(path);
			PrintWriter pw = new PrintWriter(fis);
			
			Iterator it = logins.entrySet().iterator();
			
			while(it.hasNext())
			{
				String linee = "";
				
				Map.Entry<Login, Vector<Account>> entry = (Map.Entry<Login, Vector<Account>>)it.next();
				
				String fname = entry.getValue().firstElement().getCustomer().getFirstName();
				String lname = entry.getValue().firstElement().getCustomer().getLastName();
				String address = entry.getValue().firstElement().getCustomer().getAddress();
				String phone = entry.getValue().firstElement().getCustomer().getPhone();
				String email = entry.getValue().firstElement().getCustomer().getEmail();
				String user = entry.getKey().getUserName();
				String pass = entry.getKey().getPassword();
				
				linee = fname + ", " + lname + ", " + address + ", " + phone + ", " + email + ", " + user + ", " + pass;
				
				//get all accounts for the user
				Vector<Account> accounts = entry.getValue();
				
				String checkingDate;
				String checkingBalance;
				String savingsDate;
				String savingsBalance;
				String marketDate;
				String marketBalance;
				
				//get the date and balance of each account
				for(int i=0; i<accounts.size(); i++)
				{
			        if (entry.getValue().elementAt(i) instanceof Checking) {
			            //checkingDate = entry.getValue().elementAt(i).
			        	//we need date of each transaction???
			        	
			        	Checking ch = (Checking) entry.getValue().elementAt(i);
			        	
			        	checkingBalance = Integer.toString((int)ch.getAccountBalanceTest());
			        	checkingDate = entry.getValue().elementAt(i).getOpenedDate();
			        	
			        	linee += ", " + "Checking, " + checkingDate + ", " + checkingBalance;
			        }
			        else if (entry.getValue().elementAt(i) instanceof Savings) {
			            //checkingDate = entry.getValue().elementAt(i).
			        	//we need date
			        	
			        	Savings sa = (Savings) entry.getValue().elementAt(i);
			        	
			        	savingsBalance = Integer.toString((int)sa.getAccountBalanceTest());
			        	savingsDate = entry.getValue().elementAt(i).getOpenedDate();
			        	
			        	linee += ", " + "Savings, " + savingsDate + ", " + savingsBalance;
			        }
			        else if (entry.getValue().elementAt(i) instanceof MarketAccount) {
			            //checkingDate = entry.getValue().elementAt(i).
			        	//we need date
			        	
			        	MarketAccount ma = (MarketAccount) entry.getValue().elementAt(i);
			        	
			        	//marketBalance = Integer.toString((int)ma.getAccountBalanceTest());
			        	marketDate = entry.getValue().elementAt(i).getOpenedDate();
			        	
			        	linee += ", " + "MarketAccount, " + marketDate;
			        }
				}
				pw.println(linee);
				
				//if(students[i]!=null) pw.write(students[i].toString() +"\n");
			}

			pw.close();
	
		}
		catch(FileNotFoundException e){
			e.printStackTrace(); 
		}
		catch( IOException e){
			e.printStackTrace(); 
		}
	}

}


/*
 * EXAMPLE of how to initialize hashmap
 * 
//create customers
Customer customer1 = new Customer("Yamil","Castro","Manassas","123","y@gmu.edu");
Customer customer2 = new Customer("Gene","Lee","Manassas","124","g@gmu.edu");
Customer customer3 = new Customer("John","Smith","Manassas","125","j@gmu.edu");
Login L = new Login("customer1","123");///

//populate the map
logins.put(new Login("customer1", "123"),new Vector<Account>(){{
	    add(new Checking(customer1, "12/12/12", new Transaction("12/12/12",600)));
	    add(new Savings(customer1, "12/13/12", new Transaction("12/13/12",800)));
	    add(new MarketAccount(customer1,"12/14/12"));
	}});

logins.put(new Login("customer2", "124"),new Vector<Account>(){{

	    add(new MarketAccount(customer2,"12/14/12"));
	}});

logins.put(new Login("customer3", "125"),new Vector<Account>(){{

	    add(new Checking(customer3, "12/12/12", new Transaction("12/12/12",600)));
	    add(new Savings(customer3, "12/13/12", new Transaction("12/13/12",800)));
	}});
*/
