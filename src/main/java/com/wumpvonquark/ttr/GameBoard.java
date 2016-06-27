package main.java.com.wumpvonquark.ttr;

import java.util.Collection;
import java.util.Stack;

public class GameBoard {

    private Stack<TicketCard> ticketDeck;
    private Stack<TrainCard> trainDeck;
    private Stack<Route> routeItems;

    public GameBoard() {
        this.ticketDeck = new TicketDeck().getAllItems();
        this.trainDeck = new TrainDeck().getAllItems();
        this.routeItems = new RouteDeck().getAllItems();
    }

    public Stack<TicketCard> getTicketDeck() {
        return ticketDeck;
    }

    public Stack<TrainCard> getTrainDeck() {
        return trainDeck;
    }

    public Stack<Route> getRouteItems() {
        return routeItems;
    }
}
