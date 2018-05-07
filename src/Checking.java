public class Checking extends Account{
    //private static int accountNumber; already passed in the constructor
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    public double interest;
    public final double MIN_DEPOSIT = 100;
    // must be double
    public final double CHECKING_INTEREST = 0.05;
	public final int INTEREST_BALANCE_FIXED = 50000;
	
	private int checkingBalance = 0;
	
	//should each account have an array of transactions??
	
	//total deposits made to checkings acc
    public Checking(Customer customer, String date, Transaction deposit){
    	super(customer, date);
    	//validates min deposit or throws exception
    	this.deposit = validateMinDeposit(deposit);
    	Account.setDeposit(deposit);
    	
    	//TESTING
    	setDepositTest(deposit);
    }
    // validates number of checking accounts
    public void compareTo(Object o) {
    	if(o instanceof Checking) {
    		throw new IllegalArgumentException("Error! You already have a checkings account");
    	}
    }
    // cannot create without min deposit
    public Transaction validateMinDeposit(Transaction deposit) {
    	if( deposit.getAmount()>= MIN_DEPOSIT) {
    		return deposit;
    	}else {
    		throw new IllegalArgumentException("Must make a minimum deposit of " + MIN_DEPOSIT);
    	}
    }
    //returns interest according to the balance
    public void setInterest(double interest) {
    	this.interest = CHECKING_INTEREST;
    }
    public double getInterest() {
    	return CHECKING_INTEREST;
    }
    
    @Override
    public void setDepositTest(Transaction deposit) {
    	if(deposit.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	checkingBalance += deposit.getAmount();
    	
    }
    
    @Override
    public void setWithdrawTest(Transaction withdrawal)
    {
    	if(withdrawal.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	checkingBalance -= withdrawal.getAmount();
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

    public int getAccountBalanceTest() {return this.checkingBalance;}
    
    
	@Override
	public String toString() {
		return "Checking Account Information: "
		+ "\nAccount Number: " + getAccountNumber()
		+ "\nFirst Name: " + getCustomer().getFirstName()
		+ "\nLast Name: " + getCustomer().getLastName()
		+ "\nTotal Checking Account Balance Test: " + getAccountBalanceTest()
		//+ "\nTotal Balance: " + getBalance()
		+ "\nTotal Interest: " + getInterestAmount()
		+ "\nGrand Total Balance: " + getTotalBalance();
	}
}
