public class MarketAccount extends Account{ 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
	private final double BALANCE_INTEREST = 0.04;// for now
	private final double BALANCE_INTEREST_MAX = 0.30;
	public final int INTEREST_BALANCE_FIXED = 75000;
	private static double totalDeposits = 0;
	private static double totalWithdrawals = 0;
    public MarketAccount(Customer customer, String date, int accountNumber){
    	super(customer, date, accountNumber);
    }
    //returns interest according to the balance
    public void setInterest(double balance) {
    		if(balance < INTEREST_BALANCE_FIXED) {
    			 this.interest = BALANCE_INTEREST;//0.04%
    		}else {
    			this.interest = BALANCE_INTEREST_MAX;//0.30
    		}
    }
    public double getInterest(){
    	return this.interest;
    }
	@Override
	public String toString() {
		return "Market Account Transaction: ";
		
	}
    
}
