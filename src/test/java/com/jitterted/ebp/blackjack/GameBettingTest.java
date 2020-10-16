package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameBettingTest {

  @Test
  public void newGamePlayerBalanceIsZero() throws Exception {
    Game game = new Game();

    assertThat(game.playerBalance())
        .isZero();
  }

  @Test
  public void playerDeposits70AndHasBalance70() throws Exception {
    Game game = new Game();

    game.playerDeposits(70);

    assertThat(game.playerBalance())
        .isEqualTo(70);
  }

  @Test
  public void playerBetsAndBalanceIsReduceByBet() throws Exception {
    Game game = new Game();
    game.playerDeposits(50);

    game.playerBets(45);

    assertThat(game.playerBalance())
        .isEqualTo(5);
  }

  @Test
  public void playerWinsGetsTwoTimesTheirBet() throws Exception {
    Game game = new Game();
    game.playerDeposits(100);
    game.playerBets(90);

    game.playerWins();

    assertThat(game.playerBalance())
        .isEqualTo(190);
  }

}