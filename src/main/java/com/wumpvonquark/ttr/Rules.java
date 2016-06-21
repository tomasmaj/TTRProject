package main.java.com.wumpvonquark.ttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class Rules {
    public boolean checkRoute(TicketCard ticketToCheck, List<Route> playerRoutes) {

        if(!(isCityInPlayerRoute(ticketToCheck.getStartCity(), playerRoutes) && isCityInPlayerRoute(ticketToCheck.getEndCity(), playerRoutes))) {
            return false;
        }

        return true;

    }

    public boolean isCityInPlayerRoute(City city, List<Route> playerRoutes) {

        for(Route route : playerRoutes) {
            if(route.getCities()[0] == city || route.getCities()[1] == city) {
                return true;
            }
        }

        return false;

    }

    public boolean isRoutesConnected(TicketCard ticket, List<Route> routes) {
       return connectedCities(ticket.getStartCity(), routes).contains(ticket.getEndCity());
    }

    private List<City> connectedCities(City startCity, List<Route> routes) {
        List<City> cities = new ArrayList<>();
        cities.add(startCity);
        int length;
        do {
            length = cities.size();
                for (Route route : routes) {
                    if (cities.contains(route.getCities()[0]) && !cities.contains(route.getCities()[1])) {
                            cities.add(route.getCities()[1]);
                    }
                    else if (cities.contains(route.getCities()[1]) && !cities.contains(route.getCities()[0])) {
                            cities.add(route.getCities()[0]);
                        }
                    }
        } while (cities.size() != length);
        return cities;
    }

//    public boolean isRoutesConnected(List<Route> routes) {
//        City endCity = routes.get(0).getCities()[1];
//
//        for (int i = 1; i < routes.size(); i++) {
//            if(routes.get(i).getCities()[0] == endCity) {
//                endCity = routes.get(i).getCities()[1];
//            } else if(routes.get(i).getCities()[1] == endCity) {
//                endCity = routes.get(i).getCities()[0];
//            } else {
//                return false;
//            }
//        }
//
//        return true;
//    }
}
