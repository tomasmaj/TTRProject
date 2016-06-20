package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Score;
import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.TicketCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kza von Quark on 2016-06-20.
 */
public class ScoreTest {

    Score score;

    @Before
    public void setUp() throws Exception {
        score = new Score();
    }

    @Test
    public void scoreOneTileRoute() throws Exception {
        Route route = new Route(1);
        assertEquals(1, score.trainRouteScore(route));
    }

    @Test
    public void scoreThreeTilesRoute() throws Exception {
        Route route = new Route(3);
        assertEquals(4, score.trainRouteScore(route));
    }

    @Test
    public void scoreMultipleRoutes() throws Exception {
        int[] routes = new int[] {6, 2, 3, 4, 1, 3};
        assertEquals(33, score.routesSum(routes));
    }

    @Test
    public void scoreBudapestToSofia() throws Exception {
        TicketCard ticket = TicketCard.BUD_SOF;
        assertEquals(5, score.ticketScore(ticket));
    }

    @Test
    public void scoreRouteAndTicketBudapestToSofia() throws Exception {
        int[] routes = new int[] {3, 2};
        TicketCard[] tickets = new TicketCard[] {TicketCard.BUD_SOF};
        assertEquals(11, score.total(routes, tickets));
    }

}
