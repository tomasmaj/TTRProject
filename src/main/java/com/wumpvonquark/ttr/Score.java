package main.java.com.wumpvonquark.ttr;

/**
 * Created by Kza von Quark on 2016-06-20.
 */
public class Score {

    public int routesSum(int[] routes) {
        int routeSum = 0;
        for (int route : routes) {
            routeSum += routeScore(new Route(route));
        }
        return routeSum;
    }

        public int routeScore(Route route) {
        int routeScore = 0;
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

    public int ticketSum(TicketCard[] tickets) {
        int ticketSum = 0;
        for (TicketCard ticket : tickets) {
            ticketSum += ticket.getPoints();
        }
        return ticketSum;
    }
}