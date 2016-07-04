package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import main.java.com.wumpvonquark.ttr.utilities.ComparePlayer;

import java.util.*;

public class GameBoard {

    Score score;

    private List<Player> players;
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
        usedTrainCards.clear();
    }

    public boolean claimRoute(Route route, List<TrainCard> trainCardsToClaimWith, Color colorToUseWhenOptional) {
        if(isCardsValidForRoute(route, trainCardsToClaimWith, colorToUseWhenOptional)) {
            players.get(playersTurn).addRouteToRoutesDeck(route);
            routeItems.getAllItems().remove(route);
            return true;
        }
        return false;
    }

    private boolean isCardsValidForRoute(Route route, List<TrainCard> trainCardsToClaimWith, Color colorToUseWhenOptional) {

        int numberOfCardsToBeClaimed = route.getLength() + route.getFerry();
        List<TrainCard> usedCardsToClaimWith = new ArrayList<>();
        int ferryToGo = route.getFerry();

        if(colorToUseWhenOptional == null) {
            colorToUseWhenOptional = route.getColor();
        }

        if(trainCardsToClaimWith.size() >= route.getLength()) {

            while(ferryToGo > 0) {
                for(TrainCard tc : trainCardsToClaimWith) {
                    if(tc.getColor() == Color.OPTIONAL) {
                        ferryToGo--;
                        numberOfCardsToBeClaimed--;
                        usedCardsToClaimWith.add(tc);
                    }
                }
            }

            trainCardsToClaimWith.removeAll(usedCardsToClaimWith);
            addCardsToGarbageDeck(usedCardsToClaimWith);

            for(TrainCard tc : trainCardsToClaimWith) {
                if((tc.getColor() == route.getColor() || tc.getColor() == Color.OPTIONAL || tc.getColor() == colorToUseWhenOptional) && numberOfCardsToBeClaimed > 0) {
                    numberOfCardsToBeClaimed--;
                    usedCardsToClaimWith.add(tc);
                }
            }

            trainCardsToClaimWith.removeAll(usedCardsToClaimWith);
            addCardsToGarbageDeck(usedCardsToClaimWith);

        }

        if(numberOfCardsToBeClaimed == 0) {
            return true;
        }

        return false;
    }

    public List<Player> currentScore(List<Player> players) {
        score = new Score();
        List<Player> tempPlayers = new ArrayList<>();
        tempPlayers.addAll(players);
        for (Player p : tempPlayers)
            p.setScore(score.routesSum(getPlayerRoutes(p)));
        List<Player> sortedPlayers = new ArrayList<>();
        sortedPlayers.addAll(getLeaderboard(tempPlayers));
        return sortedPlayers;
    }

    public List<Player> finalScore(List<Player> players) {
        score = new Score();
        List<Player> currentStanding = new ArrayList<>();
        currentStanding.addAll(currentScore(players));
        for (Player player : currentStanding)
            player.setScore(player.getScore() + score.ticketSum(getPlayerTickets(player)));
        List<Player> sortedPlayers = new ArrayList<>();
        sortedPlayers.addAll(getLeaderboard(currentStanding));
        return sortedPlayers;
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

    private List<Player> getLeaderboard(List<Player> players) {
        ComparePlayer comp = new ComparePlayer();
        List<Player> tempPlayers = players;
        Collections.sort(tempPlayers, comp);
        return tempPlayers;
    }

    private Stack<Route> getPlayerRoutes(Player p) {
        return p.getRouteDeck().getAllItems();
    }

    private Stack<TicketCard> getPlayerTickets(Player player) {
        return player.getTicketDeck().getAllItems();
    }
}
