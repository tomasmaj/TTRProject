package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.Rules;
import main.java.com.wumpvonquark.ttr.TicketCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class RulesTest {

    @Test
    public void createNewRules() throws  Exception {
        Rules rules = new Rules();
    }

    @Test
    public void routeShouldBeEdingburghToAmsterdam() throws Exception {
        Rules rules = new Rules();
        List<Route> routes = new ArrayList<>();
        routes.add(Route.EDI_LON_ORANGE);
        routes.add(Route.LON_AMS);

        assertTrue(rules.checkRoute(TicketCard.LON_BER, routes));
    }
}
