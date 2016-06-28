package main.java.com.wumpvonquark.ttr.decks;

import main.java.com.wumpvonquark.ttr.items.TicketCard;

import java.util.Arrays;

public class TicketDeck extends Deck<TicketCard> {

    @Override
    public void generate() {
        this.items.addAll(Arrays.asList(TicketCard.values()));
    }
}
