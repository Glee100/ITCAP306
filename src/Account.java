//public abstract class Account extends Login{
public abstract class Account{
    private static int accountNumber = 100; 
    double interest;
    private Transaction deposit;
    private Transaction withdrawal;
    private Customer customer;
    final static int MIN_TRANS = 1;
	private static double totalDeposits = 0;
	private static double totalWithdrawals = 0;
    public Account(Customer customer, String date, int accountNumber){
    	this.customer = customer;
    	accountNumber = accountNumber + 1;
    	
    }
    public static void setDeposit(Transaction deposit) {
    	// storing total deposits
    	totalDeposits= totalDeposits + deposit.getAmount();
    }
    public void setWithdrawal(Transaction withdrawal) {
    	if(withdrawal.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	if(withdrawal.getAmount() <= getTotalBalance()) {
    		this.withdrawal = withdrawal;
    		totalWithdrawals = totalWithdrawals + withdrawal.getAmount();
    	}else {
    		throw new IllegalArgumentException("Error! You do not have enough money to make this transaction!");
    	}
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
    public double getInterestAmount() {
    	return getBalance() * getInterest();
    }
    public double getTotalBalance() {
    	return getBalance() + getInterestAmount();
    }
    /*public abstract void withDrawAmount(int amount);
    public abstract void transactionDate(String date);*/
    
    /*
     * update the account balance by adding deposit to the account, and subtracting a withdrawal from the account
     * 
     * */
    /*public void updateBalance() 
    {
    	this.balance = (getBalance() + getDeposit().getAmount() - getWithdrawal().getAmount());
    }*/
    
    
    //set customer
    public void setCustomer(Customer cus)
    {
    	this.customer = cus;
    }
    //set interest
    public abstract void setInterest(double interest);
    
    //get account number
    public static int getAccountNumber()
    {
    	return accountNumber;
    }
    
    //get customer
    public Customer getCustomer()
    {
    	return this.customer;
    }
    
    //get deposit
    public Transaction getDeposit()
    {
    	return this.deposit;
    }
    
    //get withdrawal
    public Transaction getWithdrawal()
    {
    	return this.withdrawal;
    }
    
    //get interest abstract method
    public abstract double getInterest();
    
    
    public String toString() {
    	return "Account information";
    }
   
}