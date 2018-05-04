public class MarketAccount extends Account{ 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
	private final double BALANCE_INTEREST = 0.04;// for now
	private final double BALANCE_INTEREST_MAX = 0.30;
	public final int INTEREST_BALANCE_FIXED = 75000;
	private int marketBalance = 0;
	
    public MarketAccount(Customer customer, String date){
    	super(customer, date);
    }
    //returns interest according to the balance
    public void setInterest(double balance) {
    		if(balance < INTEREST_BALANCE_FIXED) {
    			 this.interest = BALANCE_INTEREST;//0.04%
    		}else {
    			this.interest = BALANCE_INTEREST_MAX;//0.30
    		}
    }
    
    
    public void compareTo(Object o) {
    	if(o instanceof MarketAccount) {
    		throw new IllegalArgumentException("Error! You already have a market account");
    	}
    }
    public double getInterest(){
    	return this.interest;
    }
    
    @Override
    public void setDepositTest(Transaction deposit) {
    	if(deposit.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	marketBalance += deposit.getAmount();
    	
    }
    
    @Override
    public void setWithdrawTest(Transaction withdrawal)
    {
    	if(withdrawal.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	marketBalance -= withdrawal.getAmount();
    }
    
    @Override
    public double getInterestAmount()
    {
    	//set interest, then get interest amount
    	setInterest(getAccountBalanceTest());
    	return getAccountBalanceTest() * getInterest();
    }
    
    @Override
    public double getTotalBalance() {
    	return getAccountBalanceTest() + getInterestAmount();
    }
    
    public int getAccountBalanceTest() {return this.marketBalance;}
    
    
	@Override
	public String toString() {
    	return "Market Account Information"
    			+ "\nAccount Number: " + getAccountNumber()
    			+ "\nFirst Name: " + getCustomer().getFirstName()
    			+ "\nLast Name: " + getCustomer().getLastName()
    			+ "\nTotal Market Account Balance Test: " + getAccountBalanceTest()
    			//+ "\nTotal Balance: " + getBalance()
    			+ "\nTotal Interest: " + getInterestAmount()
    			+ "\nGrand Total Balance: " + getTotalBalance();
	}
    
}
