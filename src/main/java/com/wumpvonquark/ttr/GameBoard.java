package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

import java.util.List;

public class GameBoard {

    private final List<Player> players;
    private TicketDeck ticketDeck;
    private TrainDeck trainDeck;
    private RouteDeck routeItems;

    public GameBoard(List<Player> players) {
        this.players = players;
        this.ticketDeck = new TicketDeck();
        this.ticketDeck.generate();
        this.trainDeck = new TrainDeck();
        this.trainDeck.generate();
        this.routeItems = new RouteDeck();
        this.routeItems.generate();
    }

    public TicketDeck getTicketDeck() {
        return ticketDeck;
    }

    public TrainDeck getTrainDeck() {
        return trainDeck;
    }

    public RouteDeck getRouteItems() {
        return routeItems;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void init() {

        TrainSet ts = new TrainSet();
        ts.generate();
        StationSet ss = new StationSet();
        ss.generate();

        for(Player player : players) {
            player.addCardToTrainDeck(this.trainDeck.getItems(Rules.numberOfStartTrainCards));
            player.addCardToTicketDeck(this.ticketDeck.getItems(Rules.numberOfStartTicketCards));
            player.addTrainsToTrainSet(ts.getItems(Rules.numberOfStartTrains));
            player.addStationsToStationSet(ss.getItems(Rules.numberOfStartStations));
        }

    }

    public void dealTrainCard(List<TrainCard> tc) {
        players.get(0).addCardToTrainDeck((tc));
        trainDeck.getAllItems().removeAll(tc);
    }
}
