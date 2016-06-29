package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

import java.util.List;

public class GameBoard {

    private final List<Player> players;
    private TicketDeck ticketDeck;
    private TrainDeck trainDeck, trainGarbagePile;
    private RouteDeck routeItems;
    private int playersTurn;

    public GameBoard(List<Player> players) {
        this.players = players;
        this.playersTurn = 0;
        this.ticketDeck = new TicketDeck();
        this.ticketDeck.generate();
        this.ticketDeck.shuffle();
        this.trainDeck = new TrainDeck();
        this.trainDeck.generate();
        this.trainDeck.shuffle();
        this.routeItems = new RouteDeck();
        this.routeItems.generate();
        this.trainGarbagePile = new TrainDeck();
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

    public TrainDeck getTrainGarbagePile() {
        return trainGarbagePile;
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

    public void addCardsToGarbageDeck(List<TrainCard> usedTrainCards) {
        this.trainGarbagePile.getAllItems().addAll(usedTrainCards);
    }

    public void claimRoute(Route route) {
        List<TrainCard> tc = players.get(playersTurn).getTrainDeck().getCardsWithColor(route.getColor(), route.getLength());
        addCardsToGarbageDeck(tc);
        players.get(playersTurn).addRouteToRoutesDeck(route);
        routeItems.getAllItems().remove(route);
    }

    public int currentScoreBoard(List<Player> players) {
        Score score = new Score();
        return score.routesSum(players.get(0).getRouteDeck().getAllItems());
    }
}
