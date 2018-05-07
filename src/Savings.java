public class Savings extends Account{
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
    public final double MIN_DEPOSIT = 500;
	private final double BALANCE_INTEREST = 0.03;// for now
	private final double BALANCE_INTEREST_MAX = 0.25;
	public final int INTEREST_BALANCE_FIXED = 50000;
	
	private int savingsBalance = 0;
	
	//should each account have an array of transactions??
	
    public Savings(Customer customer, String date, Transaction deposit){
    	super(customer, date);
    	this.deposit = validateMinDeposit(deposit);
    	Account.setDeposit(deposit);
    	//MY TESTING BELOW-----------------------------------------
    	setDepositTest(deposit);
    }
    // cannot create without min deposit
    public Transaction validateMinDeposit(Transaction deposit) {
    	if( deposit.getAmount()>= MIN_DEPOSIT) {
    		return deposit;
    	}else {
    		throw new IllegalArgumentException("Must make a minimum deposit of " + MIN_DEPOSIT);
    	}
    }
    public void compareTo(Object o) {
    	if(o instanceof Savings) {
    		throw new IllegalArgumentException("Error! You already have a savings account");
    	}
    }
    //returns interest according to the balance
    public void setInterest(double balance) {
    		if(balance < INTEREST_BALANCE_FIXED) {
    			 this.interest = BALANCE_INTEREST;//0.03%
    		}else {
    			this.interest = BALANCE_INTEREST_MAX;//0.25
    		}
    }
    
    public double getInterest(){
    	return this.interest;
    }
    
    
    
    @Override
    public void setDepositTest(Transaction deposit) {
    	if(deposit.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	savingsBalance += deposit.getAmount();
    	
    }
    
    @Override
    public void setWithdrawTest(Transaction withdrawal)
    {
    	if(withdrawal.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	savingsBalance -= withdrawal.getAmount();
    }
    
    @Override
    public double getInterestAmount()
    {
    	setInterest(getAccountBalanceTest());
    	return getAccountBalanceTest() * getInterest();
    }
    
    @Override
    public double getTotalBalance() {
    	return getAccountBalanceTest() + getInterestAmount();
    }
    

    public int getAccountBalanceTest() {return this.savingsBalance;}
    
	@Override
	public String toString() {
		return "Savings Account Information: "
		+ "\nAccount Number: " + getAccountNumber()
		+ "\nFirst Name: " + getCustomer().getFirstName()
		+ "\nLast Name: " + getCustomer().getLastName()
		+ "\nTotal Savings Account Balance Test: " + getAccountBalanceTest()
		//+ "\nTotal Balance: " + getBalance()
		+ "\nTotal Interest: " + getInterestAmount()
		+ "\nGrand Total Balance: " + getTotalBalance();
	}
}

