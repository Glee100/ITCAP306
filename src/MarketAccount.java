public class MarketAccount extends Account{
    private static int accountNumber; 
    Transaction deposit;
    Transaction withdrawal;
    Customer customer;
    private int balance;
    final static int MIN_TRANS = 1;
	private double BALANCE_INTEREST;
	
    public MarketAccount(Customer customer, String date, int accountNumber){
    	super(customer, date, accountNumber);
    }
    

	@Override
	public void withDrawAmount(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transactionDate(String date) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Market Account Transaction: ";
		
	}
    
}
