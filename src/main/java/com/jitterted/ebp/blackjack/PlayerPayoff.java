package com.jitterted.ebp.blackjack;

public enum PlayerPayoff {
  LOSES(0.0),
  PUSHES(1.0),
  WINS(2.0),
  BLACKJACK(2.5);

  private final double payoffMultiplier;

  PlayerPayoff(double payoffMultiplier) {
    this.payoffMultiplier = payoffMultiplier;
  }

  public int payoff(int playerBetAmount) {
    return (int) (playerBetAmount * payoffMultiplier);
  }
}
