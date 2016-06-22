package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.City;
import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.Rules;
import main.java.com.wumpvonquark.ttr.TicketCard;
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
    public void routeIsConnectedWithDifferentStartAndEndCity() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.LON_AMS);
        routes.add(Route.ESS_BER);
        routes.add(Route.AMS_ESS);
        assertTrue(rules.validateTicket(TicketCard.LON_BER, routes));
    }

    @Test
    public void longRouteVerification() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.EDI_LON_BLACK);
        routes.add(Route.LON_AMS);
        routes.add(Route.ESS_BER);
        routes.add(Route.AMS_ESS);
        routes.add(Route.BRE_PAR);
        routes.add(Route.BRE_PAM);
        routes.add(Route.ZUR_MAR);
        routes.add(Route.KOB_STO_WHITE);
        routes.add(Route.STO_PET);
        routes.add(Route.FRA_MUN);
        routes.add(Route.PAM_MAD_BLACK);
        routes.add(Route.MAD_BAR);
        routes.add(Route.BAR_MAR);
        routes.add(Route.ZUR_MUN);
        routes.add(Route.FRA_BER_RED);
        routes.add(Route.MAD_LIS);
        routes.add(Route.LIS_CAD);
        assertTrue(rules.validateTicket(TicketCard.EDI_PAR, routes));
    }
}
