public class Savings extends Account{
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double interest;
    public final double MIN_DEPOSIT = 500;
	private final double BALANCE_INTEREST = 0.03;// for now
	private final double BALANCE_INTEREST_MAX = 0.25;
	public final int INTEREST_BALANCE_FIXED = 50000;
    public Savings(Customer customer, String date, int accountNumber, Transaction deposit){
    	super(customer, date, accountNumber);
    	this.deposit = validateMinDeposit(deposit);
    	Account.setDeposit(deposit);
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
	@Override
	public String toString() {
		return "Market Account Transaction: ";
	}
}

