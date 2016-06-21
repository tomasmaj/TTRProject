package main.java.com.wumpvonquark.ttr;

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
            if(route.getCity1() == city || route.getCity2() == city) {
                return true;
            }
        }

        return false;

    }

    public boolean isRoutesConnected(List<Route> routes) {
        City tempCity = routes.get(0).getCity1();

        for(Route route : routes) {
            if(route.getCity1() != tempCity)
                return false;
            tempCity = route.getCity2();
        }

        return true;
    }
}
