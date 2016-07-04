package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;

import java.util.List;

public class Score {

    public int routesSum(List<Route> routes) {
        int routeSum = 0;
        for (Route route : routes) {
            routeSum += routeScore(route);
        }
        return routeSum;
    }

    public int routeScore(Route route) {
        int routeScore;
        switch (route.getLength()) {
            case 1:
                routeScore = 1;
                break;
            case 2:
                routeScore = 2;
                break;
            case 3:
                routeScore = 4;
                break;
            case 4:
                routeScore = 7;
                break;
            case 6:
                routeScore = 15;
                break;
            case 8:
                routeScore = 21;
                break;
            default: routeScore = 0;
        }
        return routeScore;
    }

    public int ticketSum(List<TicketCard> tickets) {
        int ticketSum = 0;
        for (TicketCard ticket : tickets) {
            if (ticket.isValid())
                ticketSum += ticket.getPoints();
        }
        return ticketSum;
    }
}