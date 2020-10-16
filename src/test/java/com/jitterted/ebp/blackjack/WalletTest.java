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

}
