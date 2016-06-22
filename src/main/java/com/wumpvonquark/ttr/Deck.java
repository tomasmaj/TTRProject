package main.java.com.wumpvonquark.ttr;

import java.util.*;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class Deck {

    List<TicketCard> cards;

    public void addCards(TicketCard[] cards) {
        this.cards = new ArrayList<>();
        this.cards.addAll(Arrays.asList(cards));
    }

    public List<TicketCard> getCards() {
        return cards;
    }
}
