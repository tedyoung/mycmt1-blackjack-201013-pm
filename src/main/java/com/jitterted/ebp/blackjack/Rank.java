package com.jitterted.ebp.blackjack;

import java.util.List;

public class Rank {
  static final List<String> CARD_VALUES = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
  private final String rankValue;

  public Rank(String rankValue) {
    this.rankValue = rankValue;
  }

  public static Rank of(String rankValue) {
    return new Rank(rankValue);
  }

  public int value() {
    if ("JQK".contains(rankValue)) {
      return 10;
    } else if (rankValue.equals("A")) {
      return 1;
    } else {
      return Integer.parseInt(rankValue);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Rank rank = (Rank) o;

    return rankValue.equals(rank.rankValue);
  }

  @Override
  public int hashCode() {
    return rankValue.hashCode();
  }

  String displayRank() {
    return rankValue.equals("10") ? "" : " ";
  }
}
