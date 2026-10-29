package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void shouldCreateAccountWithValidBalance() {
        BankAccount account = new BankAccount(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void shouldThrowExceptionForNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50));
    }

    @Test
    void shouldSuccessfullyWithdraw() {
        BankAccount account = new BankAccount(100);
        BankAccount updated = account.withdraw(40);
        assertEquals(60, updated.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenWithdrawingMoreThanBalance() {
        BankAccount account = new BankAccount(50);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100));
    }

    @Test
    void shouldSuccessfullyDeposit() {
        BankAccount account = new BankAccount(50);
        BankAccount updated = account.deposit(30);
        assertEquals(80, updated.getBalance());
    }
}