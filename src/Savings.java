public class Savings extends Account{
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
    public final double MIN_DEPOSIT = 500;
	private final double BALANCE_INTEREST = 0.03;// for now
	private final double BALANCE_INTEREST_MAX = 0.25;
	public final int INTEREST_BALANCE_FIXED = 50000;
	private static double totalDeposits = 0;
	private static double totalWithdrawals = 0;
    public Savings(Customer customer, String date, int accountNumber, Transaction deposit){
    	super(customer, date, accountNumber);
    	this.deposit = validateMinDeposit(deposit);
    	totalDeposits= totalDeposits + deposit.getAmount();
    }
    // cannot create without min deposit
    public Transaction validateMinDeposit(Transaction deposit) {
    	if( deposit.getAmount()>= Account.MIN_TRANS) {
    		return deposit;
    	}else {
    		throw new IllegalArgumentException("Must make a minimum deposit of " + MIN_DEPOSIT);
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
    public void makeDeposit(Transaction deposit) {
    	this.deposit = deposit;
    	// storing total deposits
    	totalDeposits= totalDeposits + deposit.getAmount();
    }
    public void makeWithdrawal(Transaction withdrawal) {
    	this.withdrawal = withdrawal; // might not need it
    	totalWithdrawals = totalWithdrawals + withdrawal.getAmount();
    }
    public static double totalWithdrawals() {
    	return totalWithdrawals;    	
    }
    public static double totalDeposits() {
    	return totalDeposits;
    }
    public double getBalance() {
    	return totalDeposits - totalWithdrawals;
    }
    //returns interest according to the balance
    public double getInterestAmount() {
    	return getBalance() * getInterest();
    }
    public double getTotalBalance() {
    	return getBalance() + getInterestAmount();
    }
	@Override
	public String toString() {
		return "Market Account Transaction: ";
	}
}

