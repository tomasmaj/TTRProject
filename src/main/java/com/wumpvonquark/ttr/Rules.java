package main.java.com.wumpvonquark.ttr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class Rules {

    public static final int numberOfStartTrainCards = 2;
    public static final int numberOfStartTicketCards = 4;
    public static final int numberOfStartTrains = 45;
    public static final int numberOfStartStations = 3;

    public boolean isTicketValid(TicketCard ticket, List<Route> routes) {
        return connectedCities(ticket.getStartCity(), routes).contains(ticket.getEndCity());
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
}
