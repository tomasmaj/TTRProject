package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.TicketDeck;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Rules {

    public static final int numberOfStartTrainCards = 2;
    public static final int numberOfStartTicketCards = 4;
    public static final int numberOfStartTrains = 45;
    public static final int numberOfStartStations = 3;

    private List<TrainCard> trainCardDeck;
    private Route route;
    private Color optionalColor;
    private int routeCost;
    private int drawnCards;

    public Rules() {
        this.trainCardDeck = new ArrayList<>();
    }

    public boolean isTicketValid(TicketCard ticket, List<Route> playerRoutes) {
        return connectedCities(ticket.getStartCity(), playerRoutes).contains(ticket.getEndCity());
    }

    private List<City> connectedCities(City startCity, List<Route> routes) {
        List<City> connectedCities = new ArrayList<>();
        connectedCities.add(startCity);
        int length;
        do {
            length = connectedCities.size();
            for (Route route : routes) {
                addConnectedRoute(connectedCities, route);
            }
        } while (connectedCities.size() != length);
        return connectedCities;
    }

    private void addConnectedRoute(List<City> connectedCities, Route route) {
        if (connectedCities.contains(route.getCity1()) && !connectedCities.contains(route.getCity2())) {
            connectedCities.add(route.getCity2());
        }
        else if (connectedCities.contains(route.getCity2()) && !connectedCities.contains(route.getCity1())) {
            connectedCities.add(route.getCity1());
        }
    }

    public int countTrainCardColor(Stack<TrainCard> deck, Color color) {
        int numbersInDeck = 0;
        for(TrainCard tc : deck) {
            if(color.equals(tc.getColor())) {
                numbersInDeck += 1;
            }
        }
        return numbersInDeck;
    }

    public void setTunnelRouteCost(List<TrainCard> drawnCards) {
        routeCost = route.getLength();
        for (TrainCard trainCard : drawnCards) {
            if (trainCard.getColor().equals(getRouteColor()) || trainCard.getColor().equals(Color.OPTIONAL))
                routeCost++;
        }
    }

    public boolean haveTrainCardsForFerryRoute() {
        routeCost = routeCost - route.getFerry();
        List<TrainCard> optionalsForFerryRoute = new ArrayList<>();
        for (TrainCard trainCard : trainCardDeck) {
            if (optionalsForFerryRoute.size() < route.getFerry() && isOptional(trainCard)) {
                optionalsForFerryRoute.add(trainCard);
            }
        }
        if (optionalsForFerryRoute.size() < route.getFerry())
            return false;
        trainCardDeck.removeAll(optionalsForFerryRoute);
        return haveTrainCardsForRoute();
    }

    public boolean haveTrainCardsForRoute() {
        int counter = routeCost;
        for (TrainCard trainCard : trainCardDeck) {
            counter -= isValidColor(trainCard) ? 1 : 0;
        }
        return counter <= 0;
    }

    public boolean drawCards(TrainCard trainCard) {
        if (isOptional(trainCard)) {
            if (drawnCards != 0)
                return false;
            drawnCards += 2;
        }
        else
            drawnCards++;
        return drawnCards < 3;
    }

    public boolean discardTicket(List<TicketCard> ticketsToDiscard, int numberOfTickets) {
        return numberOfTickets - ticketsToDiscard.size() < 1;
    }

    private Color getRouteColor() {
        return isOptional(route.getColor()) ? optionalColor :  route.getColor();
    }

    private Color getTrainCardColor(TrainCard trainCard) {
        return isOptional(trainCard.getColor()) ? optionalColor :  trainCard.getColor();
    }

    public boolean isValidColor(TrainCard t) {
        return getRouteColor().equals(getTrainCardColor(t));
    }

    public boolean isOptional(TrainCard trainCard) {
        return isOptional(trainCard.getColor());
    }

    private boolean isOptional(Color color) {
        return color.equals(Color.OPTIONAL);
    }

    // Getters and Setters
    public int getDrawnCards() {
        return drawnCards;
    }

    public void setDrawnCards(int drawnCards) {
        this.drawnCards = drawnCards;
    }

    public void setOptionalColor(Color color) {
        this.optionalColor = color;
    }

    public int getRouteCost() {
        return routeCost;
    }

    public void setRouteCost(int routeCost) {
        this.routeCost = routeCost;
    }

    public void setTrainCardDeck(List<TrainCard> trainCardDeck) {
        this.trainCardDeck.addAll(trainCardDeck);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
