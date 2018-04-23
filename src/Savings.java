public class Savings extends Account{
    private static int accountNumber; 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private int balance;
   // final static int MIN_TRANS = 1;
    public final double MIN_DEPOSIT = 500;
	private final double BALANCE_INTEREST = 0.03;// for now
	private final double BALANCE_INTEREST_MAX = 0.25;
	public final int INTEREST_BALANCE_FIXED = 50000;
    public Savings(Customer customer, String date, int accountNumber){
    	super(customer, date, accountNumber);
    }
    
    //returns interest according to the balance
    public double getInterest(double balance) {
    		if(balance < INTEREST_BALANCE_FIXED) {
    			return BALANCE_INTEREST;//0.03%
    		}else {
    			return BALANCE_INTEREST_MAX;//0.25
    		}
    }
	@Override
	public String toString() {
		return "Savings Account Transaction: ";
		
	}
    
}
