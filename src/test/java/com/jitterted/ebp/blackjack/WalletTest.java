package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WalletTest {

  @Test
  public void newWalletIsEmpty() throws Exception {
    Wallet wallet = new Wallet();

    assertThat(wallet.isEmpty())
        .isTrue();
  }

  @Test
  public void newWalletAddMoneyIsNotEmpty() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(100);

    assertThat(wallet.isEmpty())
        .isFalse();
  }

  @Test
  public void newWalletBalanceIsZero() throws Exception {
    Wallet wallet = new Wallet();

    assertThat(wallet.balance())
        .isZero();
  }

  @Test
  public void newWalletAdd1BalanceIs1() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(1);

    assertThat(wallet.balance())
        .isEqualTo(1);
  }

  @Test
  public void newWalletAdd15Add45BalanceIs60() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(15);
    wallet.addMoney(45);

    assertThat(wallet.balance())
        .isEqualTo(15 + 45);
  }

  @Test
  public void addMoneyOfZeroThrowsException() throws Exception {
    Wallet wallet = new Wallet();

    assertThatThrownBy(() -> {
      wallet.addMoney(0);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void addMoneyOfNegativeThrowsException() throws Exception {
    Wallet wallet = new Wallet();

    assertThatThrownBy(() -> {
      wallet.addMoney(-1);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void walletWith75BalanceBet40BalanceIs35() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(75);

    wallet.bet(40);

    assertThat(wallet.balance())
        .isEqualTo(75 - 40);
  }

  @Test
  public void walletBalanceIsReducedByMultipleBets() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(85);

    wallet.bet(25);
    wallet.bet(30);

    assertThat(wallet.balance())
        .isEqualTo(85 - 25 - 30);
  }

  @Test
  public void walletBetFullBalanceIsEmpty() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(95);

    wallet.bet(95);

    assertThat(wallet.isEmpty())
        .isTrue();
  }

  @Test
  public void betMoreThanBalanceThrowsException() throws Exception {
    Wallet wallet = new Wallet();
    wallet.addMoney(99);

    assertThatThrownBy(() -> {
      wallet.bet(100);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void betLessThanZeroThrowsException() throws Exception {
    Wallet wallet = new Wallet();

    assertThatThrownBy(() -> {
      wallet.bet(-1);
    }).isInstanceOf(IllegalArgumentException.class);;

  }

}
