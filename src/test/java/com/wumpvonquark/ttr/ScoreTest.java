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
    public void scoreRouteOne() throws Exception {
        Route route = new Route(1);
        assertEquals(1, score.trainRouteScore(route));
    }

    @Test
    public void scoreRouteThreee() throws Exception {
        Route route = new Route(3);
        assertEquals(4, score.trainRouteScore(route));
    }

    @Test
    public void scoreBudapestToSofia() throws Exception {
        assertEquals(5, TicketCard.BUD_SOF.getPoints());
    }
}
