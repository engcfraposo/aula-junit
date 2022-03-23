package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceandDiscountFeeWhenPositiveAmount() {

		double amount = 200.0;
		double expectedValue = 196.0;
		Account acc = AccountFactory.createEmptyAccount();
		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void depositShouldDoNothingWhenPositiveAmount() {

		double amount = -200.0;
		double expectedValue = 100.0;
		Account acc = AccountFactory.createAccount(expectedValue);
		acc.deposit(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void fullWithrawShouldClearBalanceAndReturnFullBalance() {

		double withInitialBalance = 200.0;
		double expectedValue = 0.0;
		Account acc = AccountFactory.createAccount(withInitialBalance);
		double result = acc.fullWithdraw();

		Assertions.assertEquals(expectedValue, acc.getBalance());
		Assertions.assertEquals(result, withInitialBalance);
	}
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {

		double withInitialBalance = 800.0;
		double amount = 500.0;
		double expectedValue = 300.0;
		Account acc = AccountFactory.createAccount(withInitialBalance);
		acc.withdraw(amount);

		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void withdrawShouldThrowExceptionWhenInsufficientBalance() {

		double withInitialBalance = 800.0;
		double amount = 801.0;
		

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Account acc = AccountFactory.createAccount(withInitialBalance);
			acc.withdraw(amount);
		});
	}
	
}
