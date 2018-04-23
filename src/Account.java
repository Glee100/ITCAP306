//public abstract class Account extends Login{
public abstract class Account{
    private static int accountNumber; 
    double interest;
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private double balance;
    final static int MIN_TRANS = 1;
    public Account(Customer customer, String date, int accountNumber){
    	this.customer = customer;
    	this.accountNumber = accountNumber;
    	
    }
    
    
    /*public abstract void withDrawAmount(int amount);
    public abstract void transactionDate(String date);*/
    
    /*
     * update the account balance by adding deposit to the account, and subtracting a withdrawal from the account
     * 
     * */
    public void updateBalance() 
    {
    	this.balance = (getBalance() + getDeposit().getAmount() - getWithdrawal().getAmount());
    }
    
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
    
    
    //set deposit ///
    public void setDeposit(Transaction deposit)
    {
    	if(deposit.getAmount()>= MIN_TRANS)
    	{
    		this.deposit = deposit;
    	}

    }
    
    
    //set withdrawal
    public void setWithdrawal(Transaction withdrawal)
    {
    	if(withdrawal.getAmount()>= MIN_TRANS && withdrawal.getAmount() <= getBalance() )
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
    public Transaction getDeposit()
    {
    	return this.deposit;
    }
    
    //get withdrawal
    public Transaction getWithdrawal()
    {
    	return this.withdrawal;
    }
    
    //get interest
    public double getInterest()
    {
    	return this.interest;
    }
    
    //get balance
    public double getBalance()
    {
    	return this.balance;
    }
    
    public String toString() {
    	return "Account information";
    }
   
}