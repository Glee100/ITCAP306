public class Checking extends Account{
    private static int accountNumber; 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private int balance;
    final static int MIN_TRANS = 1;
	private double BALANCE_INTEREST;
	
    public Checking(Customer customer, String date, int accountNumber){
    	super(customer, date, accountNumber);
    }
    
	
	@Override
	public String toString() {
		return "Checking Account Transaction: ";
		
	}
    
}
