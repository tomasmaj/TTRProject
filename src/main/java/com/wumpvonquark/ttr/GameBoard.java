package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

import java.util.List;

public class GameBoard {

    private final List<Player> players;
    private Deck<TicketCard> ticketDeck;
    private Deck<TrainCard> trainDeck;
    private Deck<Route> routeItems;

    public GameBoard(List<Player> players) {
        this.players = players;
        this.ticketDeck = new TicketDeck();
        this.trainDeck = new TrainDeck();
        this.routeItems = new RouteDeck();
    }

    public Deck<TicketCard> getTicketDeck() {
        return ticketDeck;
    }

    public Deck<TrainCard> getTrainDeck() {
        return trainDeck;
    }

    public Deck<Route> getRouteItems() {
        return routeItems;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void init() {

        for(Player player : players) {
            player.addCardToTrainDeck(this.trainDeck.getItems(Rules.numberOfStartTrainCards));
            player.addCardToTicketDeck(this.ticketDeck.getItems(Rules.numberOfStartTicketCards));
            player.addTrainsToTrainSet(new TrainSet().getItems(Rules.numberOfStartTrains));
            player.addStationsToStationSet(new StationSet().getItems(Rules.numberOfStartStations));
        }

    }
}
