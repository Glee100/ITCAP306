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
	private int numCheckingAcc = 0;
	//total deposits made to checkings acc
    public Checking(Customer customer, String date, Transaction deposit){
    	super(customer, date);
    	//validates min deposit or throws exception
    	this.deposit = validateMinDeposit(deposit);
    	Account.setDeposit(deposit);
    	this.numCheckingAcc++;
    	validateNumAcc(this.numCheckingAcc);
    	
    }
    // validates number of checking accounts
    public void validateNumAcc(int numCheckingAcc) {
    	if(numCheckingAcc > Account.MAX_ACC_EACH) {
    		throw new IllegalArgumentException("Erro! You cannot have more than "+Account.MAX_ACC_EACH+"(s) account(s)");
    	}
    }
    public int getNumAcc() {
    	return numCheckingAcc;
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
    public void setInterest(double interest) {
    	this.interest = CHECKING_INTEREST;
    }
    public double getInterest() {
    	return CHECKING_INTEREST;
    }
	@Override
	public String toString() {
		return "Checking Account Information: "
		+ "\nAccount Number: " + getAccountNumber()
		+ "\nFirst Name: " + getCustomer().getFirstName()
		+ "\nLast Name: " + getCustomer().getLastName()
		+ "\nTotal Balance: " + getBalance()
		+ "\nTotal Interest: " + getInterestAmount()
		+ "\nGrand Total Balance: " + getTotalBalance();
	}
}
