package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import main.java.com.wumpvonquark.ttr.utilities.ComparePlayer;

import java.util.*;

public class GameBoard {

    Score score;

    private final List<Player> players;
    private TicketDeck ticketDeck;
    private TrainDeck trainDeck, trainGarbagePile;
    private RouteDeck routeItems;
    private int playersTurn;
    private int lastTurn;

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
        this.lastTurn = -1;
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
        StationSet ss = new StationSet();

        for(Player player : players) {
            ts.setColor(player.getColor());
            ts.generate();
            ss.setColor(player.getColor());
            ss.generate();
            player.addCardToTrainDeck(this.trainDeck.getItems(Rules.numberOfStartTrainCards));
            player.addCardToTicketDeck(this.ticketDeck.getItems(Rules.numberOfStartTicketCards));
            player.addTrainsToTrainSet(ts.getItems(Rules.numberOfStartTrains));
            player.addStationsToStationSet(ss.getItems(Rules.numberOfStartStations));
        }

    }

    public void dealTrainCard(List<TrainCard> tc) {
        players.get(playersTurn).addCardToTrainDeck((tc));
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

    public List<Player> currentScore(List<Player> players) {
        score = new Score();
        for (Player p : players)
            p.setScore(score.routesSum(getPlayerRoutes(p)));
        getLeaderboard(players);
        return players;
    }

    public List<Player> finalScore(List<Player> players) {
        score = new Score();
        currentScore(players);
        for (Player player : players)
            player.setScore(player.getScore() + score.ticketSum(getPlayerTickets(player)));
        getLeaderboard(players);
        return players;
    }

    public boolean nextTurn() {

        boolean gameOver = isGameOver();

        if(playersTurn == players.size() - 1)
            this.playersTurn = 0;
        else
            this.playersTurn++;

        return gameOver;
    }

    private boolean isGameOver() {
        Player currentPlayer = players.get(playersTurn);
        if(currentPlayer.getTrainSet().getAllItems().size() <= 2 && lastTurn == -1) {
            lastTurn = players.size() - 1;
        } else if(lastTurn > 0) {
            lastTurn--;
        } else if(lastTurn == 0) {
            return true;
        }
        return false;
    }

    private void getLeaderboard(List<Player> players) {
        ComparePlayer comp = new ComparePlayer();
        Collections.sort(players, comp);
    }

    private Stack<Route> getPlayerRoutes(Player p) {
        return p.getRouteDeck().getAllItems();
    }

    private Stack<TicketCard> getPlayerTickets(Player player) {
        return player.getTicketDeck().getAllItems();
    }
}
