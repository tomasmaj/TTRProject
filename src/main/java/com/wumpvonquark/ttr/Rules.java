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

        /*while(true) {
            for(Route route : playersRoutes) {
                if(route.getCity1() == ticketToCheck.getStartCity() || route.getCity1() == ticketToCheck.getEndCity()) {
                    return true;
                }
            }
                return false;
        }*/
    }

    private boolean isCityInPlayerRoute(City city, List<Route> playerRoutes) {

        for(Route route : playerRoutes) {
            if(route.getCity1() == city || route.getCity2() == city) {
                return true;
            }
        }

        return false;

    }
}
