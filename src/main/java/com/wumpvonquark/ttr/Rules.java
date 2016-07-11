package main.java.com.wumpvonquark.ttr;

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

    private List<TrainCard> checkDeck;
    private Route checkRoute;
    private Color optionalColor;
    private int routeCost;

    public Rules() {
        this.checkDeck = new ArrayList<>();
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
        routeCost = checkRoute.getLength();
        for (TrainCard trainCard : drawnCards) {
            if (trainCard.getColor().equals(getRouteColor()) || trainCard.getColor().equals(Color.OPTIONAL))
                routeCost++;
        }
    }

    public boolean haveTrainCardsForFerryRoute() {
        routeCost = checkRoute.getFerry();
        List<TrainCard> removeOptionals = new ArrayList<>();
        for (TrainCard trainCard : checkDeck) {
            if (removeOptionals.size() < checkRoute.getFerry() && isOptional(trainCard))
                removeOptionals.add(trainCard);
        }
        if (removeOptionals.size() < checkRoute.getFerry())
            return false;
        checkDeck.removeAll(removeOptionals);
        return haveTrainCardsForRoute();
    }

    public boolean haveTrainCardsForRoute() {
        for (TrainCard trainCard : checkDeck) {
            routeCost -= isValidColor(trainCard) ? 1 : 0;
        }
        return routeCost <= 0;
    }

    private Color getRouteColor() {
        return isOptionalColor(checkRoute.getColor()) ? optionalColor :  checkRoute.getColor();
    }

    private Color getTrainCardColor(TrainCard trainCard) {
        return isOptionalColor(trainCard.getColor()) ? optionalColor :  trainCard.getColor();
    }

    public boolean isValidColor(TrainCard t) {
        return getRouteColor().equals(getTrainCardColor(t));
    }

    private boolean isOptional(TrainCard trainCard) {
        return trainCard.getColor() == Color.OPTIONAL;
    }

    private boolean isOptionalColor(Color color) {
        return color.equals(Color.OPTIONAL);
    }

    // Getters and Setters
    public void setOptionalColor(Color color) {
        this.optionalColor = color;
    }

    public int getRouteCost() {
        return routeCost;
    }

    public void setRouteCost(int routeCost) {
        this.routeCost = routeCost;
    }

    public void setCheckDeck(List<TrainCard> checkDeck) {
        this.checkDeck.addAll(checkDeck);
    }

    public void setCheckRoute(Route checkRoute) {
        this.checkRoute = checkRoute;
    }
}
