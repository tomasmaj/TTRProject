package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.City;
import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.Rules;
import main.java.com.wumpvonquark.ttr.TicketCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tomas Majling on 2016-06-21.
 */
public class RulesTest {

    Rules rules;

    @Before
    public void setUp() throws Exception {
        rules = new Rules();
    }

    @Test
    public void routeShouldHaveStartCityAndEndCityOnTicket() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.LON_AMS);
        routes.add(Route.ESS_BER);

        assertTrue(rules.isCityInPlayerRoute(City.LONDON, routes));
        assertTrue(rules.isCityInPlayerRoute(City.BERLIN, routes));
    }

    @Test
    public void routesIsConnectedWithCityInLine() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.EDI_LON_ORANGE);
        routes.add(Route.LON_AMS);
        assertTrue(rules.isRoutesConnected(routes));
    }

    @Test
    public void routeIsConnectedWithDifferentStartAndEndCity() throws Exception {
        List<Route> routes = new ArrayList<>();
    }

    @Ignore
    @Test
    public void routeShouldHaveTicketsRoute() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.LON_AMS);
        routes.add(Route.AMS_ESS);
        routes.add(Route.ESS_BER);

        assertTrue(rules.checkRoute(TicketCard.LON_BER, routes));
    }
}
