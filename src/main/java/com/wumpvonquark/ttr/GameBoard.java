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

    private TrainDeck fiveCardTrainDeck;

    public GameBoard(List<Player> players) {
        this.players = players;
        Collections.shuffle(this.players);
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
        this.fiveCardTrainDeck = new TrainDeck();
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

    public TrainDeck getFiveCardTrainDeck() {
        return fiveCardTrainDeck;
    }

    public Player getPlayerTurn() {
        return players.get(playersTurn);
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
            addToFiveCardTrainDeck();
        }
    }

    public void dealTrainCard(List<TrainCard> tc) {
        players.get(playersTurn).addCardToTrainDeck((tc));
        //fiveCardTrainDeck.getAllItems().removeAll(tc);
        addToFiveCardTrainDeck();
    }

    public void dealTrainCard(TrainCard tc) {
        TrainCard dealtCard = fiveCardTrainDeck.getAllItems().get(fiveCardTrainDeck.getAllItems().indexOf(tc));
        List<TrainCard> tcList = new ArrayList<>();
        tcList.add(dealtCard);
        players.get(playersTurn).addCardToTrainDeck((tcList));
        addToFiveCardTrainDeck();
    }

    private void addToFiveCardTrainDeck() {
        while(fiveCardTrainDeck.getSize() < 5) {
            fiveCardTrainDeck.getAllItems().addAll(trainDeck.getItems(1));
        }
    }

    public void addCardsToGarbageDeck(List<TrainCard> usedTrainCards) {
        this.trainGarbagePile.getAllItems().addAll(usedTrainCards);
        usedTrainCards.clear();
    }

    public boolean claimRoute(Route route, List<TrainCard> trainCardsToClaimWith, Color optionalColor) {
        if (players.get(playersTurn).getTrainSet().getSize() < route.getLength())
            return false;
        initializeRules(route, trainCardsToClaimWith, optionalColor);

        if (route.getFerry() > 0) {
            if (rules.haveTrainCardsForFerryRoute()) {
                List<TrainCard> optionalsForFerryRoute = new ArrayList<>();
                int ferry = 0;
                int index = 0;
                while(ferry < route.getFerry()) {
                    if (rules.isOptional(trainCardsToClaimWith.get(index))) {
                        optionalsForFerryRoute.add(trainCardsToClaimWith.get(index));
                        ferry++;
                    }
                    index++;
                }
                trainCardsToClaimWith.removeAll(optionalsForFerryRoute);
                useTrainCards(trainCardsToClaimWith);
                discardTrainPieces(route);
                return true;
            }
            return false;
        }
        if (route.isTunnel()) {
            rules.setTunnelRouteCost(trainDeck.getItems(3));
        }
        if (rules.haveTrainCardsForRoute()) {
            useTrainCards(trainCardsToClaimWith);
            discardTrainPieces(route);
            return true;
        }
        return false;
    }

    private void initializeRules(Route route, List<TrainCard> trainCardsToClaimWith, Color optionalColor) {
        rules = new Rules();
        rules.setOptionalColor(optionalColor);
        rules.setRoute(route);
        rules.setTrainCardDeck(trainCardsToClaimWith);
        rules.setRouteCost(route.getLength());
    }

    private void discardTrainPieces(Route route) {
        players.get(playersTurn).addRouteToRoutesDeck(route);
        players.get(playersTurn).getTrainSet().getAllItems().removeAll(players.get(playersTurn).getTrainSet().getItems(rules.getRouteCost()));
        routeItems.getAllItems().remove(route);
    }

    private void useTrainCards(List<TrainCard> trainCardsToClaimWith) {
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
        this.playersTurn = (playersTurn == players.size() - 1) ? 0 : ++this.playersTurn;
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

    public void discardTickets(List<TicketCard> ticketsToDiscard) {
        if (rules.discardTicket(ticketsToDiscard, getPlayerTurn().getTicketDeck().getSize())) {
            getPlayerTurn().removeTickets(ticketsToDiscard);
        }
    }

    private Stack<Route> getPlayerRoutes(Player p) {
        return p.getRouteDeck().getAllItems();
    }

    private Stack<TicketCard> getPlayerTickets(Player player) {
        return player.getTicketDeck().getAllItems();
    }

}
