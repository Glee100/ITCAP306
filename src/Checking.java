public class Checking extends Account{
    //private static int accountNumber; already passed in the constructor
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    public final double MIN_DEPOSIT = 100;
    // must be double
    public final double CHECKING_INTEREST = 0.05;
	public final int INTEREST_BALANCE_FIXED = 50000;
	//total deposits made to checkings acc
	private static double totalDeposits = 0;
	private static double totalWithdrawals = 0;
    public Checking(Customer customer, String date, int accountNumber, Transaction deposit){
    	super(customer, date, accountNumber);
    	//validates min deposit or throws exception
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
    	return getBalance() * CHECKING_INTEREST;
    }
    public double getTotalBalance() {
    	return getBalance() + getInterestAmount();
    }
	@Override
	public String toString() {
		return "Checking Account Transaction: ";
		
	}
    
}
