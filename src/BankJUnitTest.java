import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BankJUnitTest {


	
	//Test Customer
	@Test
	void testCustomer() {
		Customer c1 = new Customer("Jim", "Bob", "123 Chicken Road", "703-123-2345", "jimbob@gmail.com");
		
		assertEquals("Jim", c1.getFirstName());
		assertEquals("Bob", c1.getLastName());
		assertEquals("123 Chicken Road", c1.getAddress());
		assertEquals("703-123-2345", c1.getPhone());
		assertEquals("jimbob@gmail.com", c1.getEmail());
		
		Customer c2 = new Customer();
		
		c2.setFirstName("Jesse");
		c2.setLastName("James");
		c2.setAddress("100 Man St");
		c2.setPhone("7034561234");
		c2.setEmail("jessejames@gmail.com");

	}
	
	
	@Test
	void testTransaction() {
		Transaction tr = new Transaction("1/30/17", 700);

		Assert.assertTrue(700.0 == tr.getAmount());
		
		//test toString of Transaction
		assertEquals("Date: 1/30/17\nAmount: 700.0\n", tr.toString()); 

	}
	
	@Test
	void testMarketAccount() {
		Transaction deposit = new Transaction("12/15/17", 0);
		Customer c3 = new Customer("Jaha", "G", "123 Dark Souls", "703-123-9424", "jox@gmail.com");
		MarketAccount marketAccount = new MarketAccount(c3, "1/24/18", deposit);
		
		Transaction t1 = new Transaction("1/25/18", 10000);
		Transaction t2 = new Transaction("1/30/18", 500);
		
		marketAccount.setDepositTest(t1);
		marketAccount.setWithdrawTest(t2);
		
		assertEquals(9500, marketAccount.getAccountBalanceTest());
		
		
	}
	
	@Test
	void testSavingsAccount() {
		Transaction deposit = new Transaction("12/15/17", 700);
		
		Customer c3 = new Customer("Ali", "G", "123 Borat Road", "7034121234", "bb@gmail.com");
		
		Savings savings = new Savings(c3, "1/24/18", deposit);
		
		Transaction t1 = new Transaction("1/25/18", 50);
		Transaction t2 = new Transaction("1/30/18", 500);
		
		savings.setDepositTest(t1);
		savings.setWithdrawTest(t2);
		
		assertEquals(250, savings.getAccountBalanceTest());
		
		
	}
	
	@Test
	void testCheckingAccount() {
		Transaction deposit = new Transaction("12/15/17", 700);
		
		Customer c3 = new Customer("Ali", "G", "123 Borat Road", "7034121234", "bb@gmail.com");
		
		Checking checking = new Checking(c3, "1/24/18", deposit);
		
		Transaction t1 = new Transaction("1/25/18", 50);
		Transaction t2 = new Transaction("1/30/18", 500);
		
		checking.setDepositTest(t1);
		checking.setWithdrawTest(t2);
		
		assertEquals(250, checking.getAccountBalanceTest());
		
		
	}
	
	
	


}
