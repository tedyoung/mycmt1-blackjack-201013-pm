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
  public void newWalletAdd40BalanceIs40() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(40);

    assertThat(wallet.balance())
        .isEqualTo(40);
  }

  @Test
  public void newWalletAdd15Add45BalanceIs60() throws Exception {
    Wallet wallet = new Wallet();

    wallet.addMoney(15);
    wallet.addMoney(45);

    assertThat(wallet.balance())
        .isEqualTo(15 + 45);
  }

}