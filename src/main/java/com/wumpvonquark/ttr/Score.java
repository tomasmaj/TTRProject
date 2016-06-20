package main.java.com.wumpvonquark.ttr;

/**
 * Created by Kza von Quark on 2016-06-20.
 */
public class Score {

    int score = 0;

    public int trainRouteScore(Route route) {
        switch (route.getLength()) {
            case 1:
                score = 1;
                break;
            case 2:
                score = 2;
                break;
            case 3:
                score = 4;
                break;
            case 4:
                score = 7;
                break;
            case 6:
                score = 15;
                break;
            case 8:
                score = 21;
                break;
            default: score = 0;
        }
        return score;
    }

    public int ticketScore(TicketCard ticket) {
        return ticket.getPoints();
    }

    public int routesSum(int[] routes) {
        int routeSum = 0;
        for (int route : routes) {
            routeSum += trainRouteScore(new Route(route));
        }
        return routeSum;
    }

    public int total(int[] routes, TicketCard[] tickets) {
        for (TicketCard ticket : tickets) {
            score += ticket.getPoints();
        }
        score += routesSum(routes);
        return score;
    }
}