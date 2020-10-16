package com.jitterted.ebp.blackjack;

public class Wallet {
  private int balance = 0;

  public boolean isEmpty() {
    return balance == 0;
  }

  public int balance() {
    return balance;
  }

  public void addMoney(int amount) {
    requireGreaterThanZero(amount);

    balance += amount;
  }

  public void bet(int amount) {
    requireAmountZeroOrMore(amount);
    requireSufficientBalanceFor(amount);

    balance -= amount;
  }

  private void requireAmountZeroOrMore(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }
  }

  private void requireSufficientBalanceFor(int amount) {
    if (balance < amount) {
      throw new IllegalArgumentException();
    }
  }

  private void requireGreaterThanZero(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException();
    }
  }
}
