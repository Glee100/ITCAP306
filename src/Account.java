public abstract class Account extends Login{
    private static int accountNumber; 
    double interest;
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private int balance;
    final static int MIN_TRANS = 1;
    
    public Account(Customer customer, String date, int accountNumber){
    	this.customer = customer;
    	this.accountNumber = accountNumber;
    	
    }
    
    public abstract void withDrawAmount(int amount);
    public abstract void transactionDate(String date);
    
    //getters
    
    //set account number
    public void setAccountNumber(int acctNum)
    {
    	this.accountNumber = acctNum;
    }
    
    //set customer
    public void setCustomer(Customer cus)
    {
    	this.customer = cus;
    }
    
    
    //set deposit
    public void setDeposit(Transaction deposit)
    {
    	if(deposit>= MIN_TRANS)
    	{
    		this.deposit = deposit;
    	}
    	
    }
    
    
    //set withdrawal
    public void setWithdrawal(Transaction withdrawal)
    {
    	if(withdrawal>= MIN_TRANS && withdrawal <= getBalance() )
    	{
    		this.withdrawal = withdrawal;
    	}
    }
    
    //set interest
    public void setInterest(int interest)
    {
    	this.interest = interest;
    }
    
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
    public Deposit getDeposit()
    {
    	return this.deposit;
    }
    
    //get withdrawal
    public Withdrawal getWithdrawal()
    {
    	return this.withdrawal;
    }
    
    //get interest
    public int getInterest()
    {
    	return this.interest;
    }
    
    //get balance
    public int getBalance()
    {
    	return this.balance;
    }
   
}