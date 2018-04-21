
public abstract class Transaction {

	private String transactionDate;
	
	private double amount;
	
	public final double MIN_TRANSACTION = 1.0;
	
	//constructor
	public Transaction(String transactionDate, double amount)
	{
		this.transactionDate = transactionDate;
		setAmount(amount);
	}
	
	//set transaction date
	public void setTransactionDate(String transactionDate)
	{
		this.transactionDate = transactionDate;
	}
	
	//set amount
	public void setAmount(double amount)
	{
		if(amount >= MIN_TRANSACTION)
		{
			this.amount = amount;
		}
		//throw exception
		else {
			throw new IllegalArgumentException("Error, Invalid transaction amount.");
		}
	}
	
	
	//get transaction date
	public String getTransactionDate()
	{
		return this.transactionDate;
	}
	
	//get amount
	public double getAmount()
	{
		return this.amount;
	}
	
	/*
	 * 
	 * @return String
	 * */
	public String toString()
	{
		return "Date: " + getTransactionDate() + "\nAmount: " + getAmount() + "\n";
	}
	
	
	
}
