package main.java.com.wumpvonquark.ttr;

import java.util.Stack;

public class GameBoard {

    private Stack<TicketCard> ticketDeck;

    public GameBoard() {
        this.ticketDeck = new TicketDeck().getAllItems();
    }

    public Stack<TicketCard> getTicketDeck() {
        return ticketDeck;
    }
}
