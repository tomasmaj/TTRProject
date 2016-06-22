package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Score;
import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.TicketCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScoreTest {

    Score score;
    TicketCard[] tickets;

    @Before
    public void setUp() throws Exception {
        score = new Score();
    }

   @Test
    public void scoreOneTileRoute() throws Exception {
        assertEquals(1, score.routeScore(Route.AMS_BRU));
    }

    @Test
    public void scoreThreeTilesRoute() throws Exception {
        Route route = Route.PAR_FRA_WHITE;
        assertEquals(4, score.routeScore(route));
    }

    @Test
    public void scoreMultipleRoutes() throws Exception {
        Route[] routes = {Route.AMS_FRA, Route.SMO_MOS, Route.STO_PET, Route.KOB_STO_WHITE, Route.BRI_ATH};
        assertEquals(36, score.routesSum(routes));
    }

    @Test
    public void scoreRouteAndTicketBudapestToSofia() throws Exception {
        Route[] routes = {Route.SAR_BUD, Route.SAR_SOF};
        tickets = new TicketCard[] {TicketCard.BUD_SOF};
        tickets[0].setValid(true);
        assertEquals(11, score.routesSum(routes) + score.ticketSum(tickets));
    }

    @Test
    public void scoreMultipleTicketsAndRoutes() throws Exception {
        Route[] routes = {Route.KOB_STO_WHITE, Route.ZUR_MUN, Route.BRU_FRA, Route.DIE_PAR, Route.BRE_PAR, Route.ATH_SOF};
        tickets = new TicketCard[] {TicketCard.BUD_SOF, TicketCard.LON_BER};
        for (TicketCard t : tickets) {
            t.setValid(true);
        }
        assertEquals(29, score.routesSum(routes) + score.ticketSum(tickets));
    }
}
