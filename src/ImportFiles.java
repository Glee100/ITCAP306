import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImportFiles {

	public static void main(String [] args)
	{
	    File file = new File("ImportCustomers.txt");

	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            int i = sc.nextInt();
	            System.out.println(i);
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	
	//STRIP QUOTES
	//string = string.replace("\"", "");
	
	
	public static void readFromFile(String path){
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
				
				
				Account account;
				String date = "";
				Transaction transaction = null;
				
				try {
					String acct;
					acct = scan2.next().trim();
					if(acct.equals("Checking"))
					{
						date = scan2.next().trim();
						transaction = new Transaction(date, Double.parseDouble(scan2.next()));
						account = new Checking(cus, date, transaction);
					}
					else if(acct.equals("Savings"))
					{
						date = scan2.next().trim();
						transaction = new Transaction(date, Double.parseDouble(scan2.next()));
						account = new Savings(cus, date, transaction);
					}
					else if(acct.equals("MarketAccount"))
					{
						date = scan2.next().trim();
						account = new MarketAccount(cus, date);
					}
					
					
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				

				//students[counter++]=s;
				System.out.println(line);
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace(); 
		}
		catch( IOException e){
			e.printStackTrace(); 
		}

	}
}
