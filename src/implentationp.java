import java.util.Map;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Write a description of class implentationp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class implentationp
{
    // instance variables - replace the example below with your own
 public static void main(String[] args){
	 
	 //map with login as key, and arraylist of customer and accounts as value
	 Map<Login, Vector<Object> > logins = new HashMap<Login, Vector<Object>>();
	 
	 //populate the map
	 Customer customer1 = new Customer("Yamil","Castro","Manassas","123","y@gmu.edu");
	 Customer customer2 = new Customer("Gene","Lee","Manassas","124","g@gmu.edu");
	 Customer customer3 = new Customer("John","Smith","Manassas","125","j@gmu.edu");
	 
	 logins.put(new Login("customer1", "123"),new Vector<Object>(){{
		    add(customer1);
		    add(new Checking(customer1, "12/12/12", new Transaction("12/12/12",600)));
		    add(new Savings(customer1, "12/13/12", new Transaction("12/13/12",800)));
		    add(new MarketAccount(customer,"12/14/12"));
		}});
	 
	 logins.put(new Login("customer2", "124"),new Vector<Object>(){{
		    add(customer1);
		    add(new MarketAccount(customer,"12/14/12"));
		}});
	 

	 logins.put(new Login("customer3", "125"),customer3);

	 
	 boolean validLogin = false;
	 
	 //while validLogin is false
	 while(!validLogin)
	 {
		 //validate login and set boolean to validLogin
		 validLogin = Login(logins);
		 
		 if(!validLogin)
		 {
			 JOptionPane.showInputDialog("You're login is incorrect. Would you like to try again? 'Y' or 'N'");
		 }
	 }
	 
	 
	 
     //if true
     //customer1, john, etc,
     //tokens
     Customer c = new Customer (//get data from file);
     //menu
     Account s = new Checkings(///);
     // for the other ones
     print(linkedlist);
     
    }
 // change parameters
 public static Account[] createAccount(Customer customer) {
	 /*Customer c = new Customer(JOptionPane.showInputDialog(null,"First Name"),JOptionPane.showInputDialog(null,"Last Name"), 
			 					JOptionPane.showInputDialog(null,"Address"), JOptionPane.showInputDialog(null,"Phone"), 
			 							JOptionPane.showInputDialog(null,"email"));*/

	/* Account a = new Checking(c,new Transaction(JOptionPane.showInputDialog(null,"Transaction Date")), 
			     JOptionPane.showInputDialog(null,"Enter deposit"));	*/
	 Account a = new Checking(customer, "12/12/12", new Transaction("12/12/12",600));
	 Account b = new Savings(customer, "12/13/12", new Transaction("12/13/12",800));
	 Account c = new MarketAccount(customer,"12/14/12");
	 
	 
 	return a;
 }
 public static void Menu(///) {
 }

/*
 * Static method that handles logins
 * */
public static boolean Login(Map<Login, Customer> logins)
{
	 String username = JOptionPane.showInputDialog("Enter Username: ");
	 String password = JOptionPane.showInputDialog("Enter password: ");
	 
	 Login log = new Login(username, password);
	 
	 return log.validate(log, logins);
}
 
}
