package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private final List<Card> cards = new ArrayList<>();

  public Deck() {
    for (Suit suit : Suit.values()) {
      for (String cardValue : Rank.CARD_VALUES) {
        cards.add(new Card(suit, Rank.of(cardValue)));
      }
    }
    Collections.shuffle(cards);
  }

  public int size() {
    return cards.size();
  }

  public Card draw() {
    return cards.remove(0);
  }
}
