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
	 Map<Login, Customer > logins = new HashMap<Login, Customer>();
	 
	 Customer customer1 = new Customer("Yamil","Castro","Manassas","123","y@gmu.edu");
	 Customer customer2 = new Customer("Gene","Lee","Manassas","124","g@gmu.edu");
	 Customer customer3 = new Customer("John","Smith","Manassas","125","j@gmu.edu");
	 
	 logins.put(new Login("customer1", "123"),customer1 );
	 logins.put(new Login("customer2", "124"),customer2);
	 logins.put(new Login("customer3", "125"),customer3);

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
 
}
