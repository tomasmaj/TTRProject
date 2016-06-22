package main.java.com.wumpvonquark.ttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class Rules {

    public boolean validateTicket(TicketCard ticket, List<Route> routes) {
       return isValid(ticket, routes);
    }

        private boolean isValid(TicketCard ticket, List<Route> routes) {
            return connectedCities(ticket.getStartCity(), routes).contains(ticket.getEndCity());
        }

            private List<City> connectedCities(City startCity, List<Route> routes) {
                List<City> connectedCities = new ArrayList<>();
                connectedCities.add(startCity);
                int length;
                do {
                    length = connectedCities.size();
                        for (Route route : routes) {
                            addIfConnected(connectedCities, route);
                        }
                } while (connectedCities.size() != length);
                return connectedCities;
            }

                    private void addIfConnected(List<City> connectedCities, Route route) {
                        if (connectedCities.contains(route.getCities()[0]) && !connectedCities.contains(route.getCities()[1])) {
                                connectedCities.add(route.getCities()[1]);
                        }
                        else if (connectedCities.contains(route.getCities()[1]) && !connectedCities.contains(route.getCities()[0])) {
                                connectedCities.add(route.getCities()[0]);
                        }
                    }

}
