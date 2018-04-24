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
	 Map<String, Login> logins = new HashMap<String, Login>();
	 logins.put("customer1", new Login("customer1", "123"));
	 logins.put("customer2", new Login("customer2", "124"));
	 logins.put("customer3", new Login("customer3", "125"));    
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
 public static Account createAccount(String[] Array) {
	 /*Customer c = new Customer(JOptionPane.showInputDialog(null,"First Name"),JOptionPane.showInputDialog(null,"Last Name"), 
			 					JOptionPane.showInputDialog(null,"Address"), JOptionPane.showInputDialog(null,"Phone"), 
			 							JOptionPane.showInputDialog(null,"email"));*/
	 Customer customer = new Customer("Yamil","Castro","Manassas","123","y@gmu.edu");
	 
	/* Account a = new Checking(c,new Transaction(JOptionPane.showInputDialog(null,"Transaction Date")), 
			     JOptionPane.showInputDialog(null,"Enter deposit"));	*/
	 Account a = new Checking(customer, "12/12/12", new Transaction("12/12/12",100));
 	return a;
 }
 public static void Menu(String[] Array) {
	 
 }
 
}
