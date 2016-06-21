package main.java.com.wumpvonquark.ttr;

import java.util.List;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class Rules {
    public boolean checkRoute(TicketCard ticketToCheck, List<Route> playersRoutes) {
        while(true) {
            for(Route route : playersRoutes) {
                if(route.getCity1() == ticketToCheck.getStartCity() || route.getCity1() == ticketToCheck.getEndCity()) {
                    return true;
                }
                return false;
            }
        }
    }
}
