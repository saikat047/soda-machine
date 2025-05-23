package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("AccountTests")
class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    @DisplayName("New account should have zero balance")
    void newAccountShouldHaveZeroBalance() {
        assertEquals(0L, account.getBalance());
    }

    // --- addAmount Tests ---

    @Test
    @DisplayName("addAmount: Adding a positive amount should increase balance")
    void addAmount_positiveAmount_shouldIncreaseBalance() {
        account.addAmount(100L);
        assertEquals(100L, account.getBalance());
        account.addAmount(50L);
        assertEquals(150L, account.getBalance());
    }

    @Test
    @DisplayName("addAmount: Adding zero amount should not change balance")
    void addAmount_zeroAmount_shouldNotChangeBalance() {
        account.addAmount(100L);
        account.addAmount(0L);
        assertEquals(100L, account.getBalance());
    }

    @Test
    @DisplayName("addAmount: Adding a negative amount should throw Exception")
    void addAmount_negativeAmount_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            account.addAmount(-50L);
        }, "Should throw Exception");

        assertEquals("Amount cannot be negative", exception.getMessage());
        assertEquals(0L, account.getBalance(), "Balance should not change");
    }

    @Test
    @DisplayName("deductAmount: Deducting amount should decrease balance")
    void deductAmount_validPositiveAmount_shouldDecreaseBalance() {
        account.addAmount(100L);
        account.deductAmount(70L);
        assertEquals(30L, account.getBalance());
    }

    @Test
    @DisplayName("deductAmount: Deducting an amount equal to balance should result in zero balance")
    void deductAmount_equalToBalance_shouldResultInZeroBalance() {
        account.addAmount(100L);
        account.deductAmount(100L);
        assertEquals(0L, account.getBalance());
    }

    @Test
    @DisplayName("deductAmount: Deducting an amount greater than balance should throw IllegalArgumentException")
    void deductAmount_amountGreaterThanBalance_shouldThrowIllegalArgumentException() {
        account.addAmount(50L);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deductAmount(100L);
        }, "Should throw IllegalArgumentException when deduction amount is greater than balance.");

        assertTrue(exception.getMessage().contains("Balance is too low"), "Exception message is not as expected.");
        assertEquals(50L, account.getBalance(), "Balance should not change after a failed deduction attempt.");
    }

    @Test
    @DisplayName("deductAmount: Deducting from a zero balance account with positive amount should throw IllegalArgumentException")
    void deductAmount_fromZeroBalanceWithPositiveAmount_shouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deductAmount(1L);
        });
        assertTrue(exception.getMessage().contains("Balance is too low"));
        assertEquals(0L, account.getBalance(), "Balance should remain zero.");
    }

    @Test
    @DisplayName("deductAmount: Deducting zero amount should not change balance")
    void deductAmount_zeroAmount_shouldNotChangeBalance() {
        account.addAmount(100L);
        account.deductAmount(0L);
        assertEquals(100L, account.getBalance());
    }

    @Test
    @DisplayName("reset: Should set balance to zero")
    void reset_shouldSetBalanceToZero() {
        account.addAmount(500L);
        account.reset();
        assertEquals(0L, account.getBalance());
    }
}