//public abstract class Account extends Login{
public abstract class Account{
	public static final int MAX_NUM_ACC= 3;
	public static final int MAX_ACC_EACH = 1;
	private int totalNumAcc;
    private static int accountNumberCreator = 100;
    private int accountNumber;
    double interest;
    private Transaction deposit;
    private Transaction withdrawal;
    private Customer customer;
    public static int MIN_TRANS = 1;
	private static double totalDeposits = 0;
	private static double totalWithdrawals = 0;
	
	private double accountBalance;
	private double totalBalance;
	
	private double accountWithdrawals;
	
	private Transaction testDeposit;
	private String openedDate;

	
    public Account(Customer customer, String date){
    	this.customer = customer;
    	accountNumberCreator++;//
    	//increase the account number and stores it in account number
    	this.accountNumber = accountNumberCreator;//
    	this.totalBalance = accountBalance - accountWithdrawals;
    	this.openedDate = date;
    	//total number of accounts for each customer
    	totalNumAcc++;
    }
    //getting balance for each account
    public double getAccountBalance() {
    	this.accountBalance = deposit.getAmount();
    	return this.accountBalance;
    }
    public double getAccountWithdrawals() {
    	return this.accountWithdrawals = withdrawal.getAmount();
    }
    //geting total balance
    public double getTotalBalanceAccount() {
    	this.totalBalance = accountBalance - accountWithdrawals;
    	return this.totalBalance;
    }
    public static void setDeposit(Transaction deposit) {
    	// storing total deposits
    	if(deposit.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}

    	totalDeposits= totalDeposits + deposit.getAmount();
    }
    
    //----------------------------CHANGE TEST--------------
    //-----------------------------------------------------
    public void setOpenedDate(String date)
    {
    	this.openedDate = date;
    }
    
    public abstract void setDepositTest(Transaction deposit);
    
    public abstract void setWithdrawTest(Transaction withdrawal);
    
    
    public void setWithdrawal(Transaction withdrawal) {
    	if(withdrawal.getAmount() <= MIN_TRANS) {throw new IllegalArgumentException("Must a be a positive number");}
    	if(withdrawal.getAmount() <= getTotalBalance()) {
    		this.withdrawal = withdrawal;
    		totalWithdrawals = totalWithdrawals + withdrawal.getAmount();
    	}else {
    		throw new IllegalArgumentException("Error! You do not have enough money to make this transaction!");
    	}
    }
    //this abstract method validates the maximum number of accounts
    public abstract void compareTo(Object o);
    public static double totalWithdrawals() {
    	return totalWithdrawals;
    	
    }  
    public int getTotalNumAcc() {
    	return this.totalNumAcc;
    }
    // total deposits for each acc
    public double totalDeposits() {
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
    
    public String getOpenedDate()
    {
    	return this.openedDate;
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
    public int getAccountNumber()
    {
    	return this.accountNumber;
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
    
    /*
     * To String method
     * The program should display the customer account number, first name, last name,
     *  total account balance before interest, total interest, and grand total balance for each account for each type of account.
     * */
    public String toString() {
    	return "Account information"
    			+ "\nAccount Number: " + getAccountNumber()
    			+ "\nFirst Name: " + getCustomer().getFirstName()
    			+ "\nLast Name: " + getCustomer().getLastName();
    		//	+ "\nTotal Balance: " + getBalance()
    			//+ "\nTotal Interest: " + getInterestAmount()
    			//+ "\nGrand Total Balance: " + getTotalBalanceAccount()
    			//+ "\nGrand Total Balance All users: " + getTotalBalance();
    }
   
}