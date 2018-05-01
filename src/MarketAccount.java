public class MarketAccount extends Account{ 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
	private final double BALANCE_INTEREST = 0.04;// for now
	private final double BALANCE_INTEREST_MAX = 0.30;
	public final int INTEREST_BALANCE_FIXED = 75000;
	private  int numMarketAcc = 0;
    public MarketAccount(Customer customer, String date){
    	super(customer, date);
    	this.numMarketAcc++;
    	validateNumAcc(numMarketAcc);
    }
    //returns interest according to the balance
    public void setInterest(double balance) {
    		if(balance < INTEREST_BALANCE_FIXED) {
    			 this.interest = BALANCE_INTEREST;//0.04%
    		}else {
    			this.interest = BALANCE_INTEREST_MAX;//0.30
    		}
    }
    // validates number of checking accounts
    public void validateNumAcc(int numMarketAcc) {
    	if(numMarketAcc > Account.MAX_ACC_EACH) {
    		throw new IllegalArgumentException("Error! You cannot have more than "+Account.MAX_ACC_EACH+"(s) account(s)");
    	}
    }
    public int getNumAcc() {
    	return numMarketAcc;
    }
    
    public double getInterest(){
    	return this.interest;
    }
	@Override
	public String toString() {
    	return "Market Account Information"
    			+ "\nAccount Number: " + getAccountNumber()
    			+ "\nFirst Name: " + getCustomer().getFirstName()
    			+ "\nLast Name: " + getCustomer().getLastName()
    			+ "\nTotal Balance: " + getBalance()
    			+ "\nTotal Interest: " + getInterestAmount()
    			+ "\nGrand Total Balance: " + getTotalBalance();
	}
    
}
