package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import main.java.com.wumpvonquark.ttr.utilities.ComparePlayer;

import java.util.*;

public class GameBoard {

    Score score;
    Rules rules;

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

    public boolean claimRoute(Route route, List<TrainCard> trainCardsToClaimWith, Color optionalColor) {
        if (players.get(playersTurn).getTrainSet().getSize() < route.getLength())
            return false;
        rules = new Rules();
        rules.setOptionalColor(optionalColor);
        rules.setCheckRoute(route);
        rules.setCheckDeck(trainCardsToClaimWith);

        if (route.getFerry() > 0) {
            System.out.println("Ferry");
            if (rules.haveTrainCardsForFerryRoute()) {
                List<TrainCard> removeOptionals = new ArrayList<>();
                for (int i = 0; i < route.getFerry(); i++) {
                    if (trainCardsToClaimWith.get(i).getColor().equals(Color.OPTIONAL))
                        removeOptionals.add(trainCardsToClaimWith.get(i));
                }
                trainCardsToClaimWith.removeAll(removeOptionals);
                playTrainCards(trainCardsToClaimWith);
                discardTrainPieces(route);
                return true;
            }
        }

        if (route.isTunnel()) {
            System.out.println("Tunnel");
            rules.setTunnelRouteCost(trainDeck.getItems(3));
        }
        System.out.println("Route cost: " + rules.getRouteCost());
        if (rules.haveTrainCardsForRoute()) {
            System.out.println("Normal");
            playTrainCards(trainCardsToClaimWith);
            discardTrainPieces(route);
            return true;
        }
        return false;
    }

    private void discardTrainPieces(Route route) {
        players.get(playersTurn).addRouteToRoutesDeck(route);
        players.get(playersTurn).getTrainSet().getAllItems().removeAll(players.get(playersTurn).getTrainSet().getItems(rules.getRouteCost()));
        routeItems.getAllItems().remove(route);
    }

    private void playTrainCards(List<TrainCard> trainCardsToClaimWith) {
        List<TrainCard> useCards = new ArrayList<>();
        int counter = 0;
        for (TrainCard trainCard : trainCardsToClaimWith){
            if (rules.isValidColor(trainCard) && counter < rules.getRouteCost()) {
                useCards.add(trainCard);
                counter++;
            }
        }
        trainCardsToClaimWith.removeAll(useCards);
        addCardsToGarbageDeck(useCards);
    }
/*
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
                    if(tc.getColor().equals(Color.OPTIONAL)) {
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

        return numberOfCardsToBeClaimed == 0;
    }
*/

    public List<Player> setCurrentScore() {
        score = new Score();
        for (Player p : getPlayers())
            p.setScore(score.routesSum(getPlayerRoutes(p)));
        return getLeaderboard();
    }

    public List<Player> setFinalScore() {
        score = new Score();
        setCurrentScore();
        for (Player player : getPlayers())
            player.setScore(player.getScore() + score.ticketSum(getPlayerTickets(player)));
        return getLeaderboard();
    }

    private List<Player> getLeaderboard() {
        ComparePlayer comp = new ComparePlayer();
        List<Player> tempPlayers = new ArrayList<>();
        tempPlayers.addAll(getPlayers());
        Collections.sort(tempPlayers, comp);
        return tempPlayers;
    }

    public boolean nextTurn() {
        boolean gameOver = isGameOver();
        this.playersTurn = (playersTurn == players.size() - 1) ? 0 : this.playersTurn++;
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

    private Stack<Route> getPlayerRoutes(Player p) {
        return p.getRouteDeck().getAllItems();
    }

    private Stack<TicketCard> getPlayerTickets(Player player) {
        return player.getTicketDeck().getAllItems();
    }
}
