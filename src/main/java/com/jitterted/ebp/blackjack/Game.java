package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

  private final Deck deck;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();
  private int playerBalance = 0;
  private int playerBetAmount = 0;

  public static void main(String[] args) {

    displayWelcome();

    playGame();

    resetScreen();
  }

  private static void playGame() {
    Game game = new Game();
    game.initialDeal();
    game.play();
  }

  private static void resetScreen() {
    System.out.println(ansi().reset());
  }

  private static void displayWelcome() {
    System.out.println(ansi()
                           .bgBright(Ansi.Color.WHITE)
                           .eraseScreen()
                           .cursor(1, 1)
                           .fgGreen().a("Welcome to")
                           .fgRed().a(" Jitterted's")
                           .fgBlack().a(" BlackJack"));
  }

  public Game() {
    deck = new Deck();
  }

  public void initialDeal() {
    dealRound();
    dealRound();
  }

  private void dealRound() {
    // players first, because that's the rules of Blackjack
    playerHand.drawCardFrom(deck);
    dealerHand.drawCardFrom(deck);
  }

  public void play() {
    boolean playerBusted = playerPlays();
    dealerPlays(playerBusted);
    displayFinalGameState();
    displayOutcome(playerBusted);
  }

  private void displayOutcome(boolean playerBusted) {
    PlayerPayoff outcome;
    if (playerBusted) {
      outcome = PlayerPayoff.LOSES;
      System.out.println("You Busted, so you lose.  💸");
    } else if (dealerHand.isBusted()) {
      outcome = PlayerPayoff.WINS;
      System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
    } else if (playerHand.beats(dealerHand)) {
      outcome = PlayerPayoff.WINS;
      System.out.println("You beat the Dealer! 💵");
    } else if (playerHand.pushesWith(dealerHand)) {
      outcome = PlayerPayoff.PUSHES;
      System.out.println("Push: The house wins, you Lose. 💸");
    } else {
      outcome = PlayerPayoff.LOSES;
      System.out.println("You lost to the Dealer. 💸");
    }
    playerBalance += outcome.payoff(playerBetAmount);
  }

  private void dealerPlays(boolean playerBusted) {
    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    if (!playerBusted) {
      while (dealerShouldHit()) {
        dealerHand.drawCardFrom(deck);
      }
    }
  }

  private boolean dealerShouldHit() {
    return dealerHand.valueLessThanOrEqualTo(16);
  }

  private boolean playerPlays() {
    // get Player's decision: hit until they stand, then they're done (or they go bust)
    boolean playerBusted = false;
    while (!playerBusted) {
      displayGameState();
      String playerChoice = inputFromPlayer().toLowerCase();
      if (playerChoice.startsWith("s")) {
        break;
      }
      if (playerChoice.startsWith("h")) {
        playerHand.drawCardFrom(deck);
        if (playerHand.isBusted()) {
          playerBusted = true;
        }
      } else {
        System.out.println("You need to [H]it or [S]tand");
      }
    }
    return playerBusted;
  }

  private String inputFromPlayer() {
    System.out.println("[H]it or [S]tand?");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  private void displayGameState() {
    clearScreen();

    displayDealerHand();

    displayPlayerHand();
  }

  private void displayDealerHand() {
    System.out.println("Dealer has: ");
    System.out.println(dealerHand.displayOfFirstCard()); // first card is Face Up

    // second card is the hole card, which is hidden
    displayBackOfCard();
  }

  private void clearScreen() {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
  }

  private void displayPlayerHand() {
    System.out.println();
    System.out.println("Player has: ");
    playerHand.display();
    System.out.println(playerHand.displayValue());
  }

  private void displayBackOfCard() {
    System.out.print(
        ansi()
            .cursorUp(7)
            .cursorRight(12)
            .a("┌─────────┐").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("└─────────┘"));
  }

  private void displayFinalGameState() {
    clearScreen();
    System.out.println("Dealer has: ");
    dealerHand.display();
    System.out.println(dealerHand.displayValue());

    displayPlayerHand();
  }

  public int playerBalance() {
    return playerBalance;
  }

  public void playerDeposits(int amount) {
    playerBalance += amount;
  }

  public void playerBets(int betAmount) {
    playerBalance -= betAmount;
    playerBetAmount = betAmount;
  }

  public void playerWins() {
    playerBalance += playerBetAmount * 2;
  }

  public void playerLoses() {
    playerBalance += playerBetAmount * 0;
  }

  public void playerPushes() {
    playerBalance += playerBetAmount * 1;
  }

  public void playerWinsBlackjack() {
    playerBalance += playerBetAmount * 2.5;
  }

}
