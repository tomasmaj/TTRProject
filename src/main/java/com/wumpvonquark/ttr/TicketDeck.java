package main.java.com.wumpvonquark.ttr;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class TicketDeck extends Deck<TicketCard> {

    public TicketDeck() {
        super(new Stack<TicketCard>());
    }

    @Override
    public void generate() {
        this.cards.addAll(Arrays.asList(TicketCard.values()));
    }
}
