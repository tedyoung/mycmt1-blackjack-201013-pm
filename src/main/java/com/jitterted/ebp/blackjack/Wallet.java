package com.jitterted.ebp.blackjack;

public class Wallet {
  private int balance = 0;

  public Wallet() {
  }

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

  private void requireGreaterThanZero(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException();
    }
  }
}
