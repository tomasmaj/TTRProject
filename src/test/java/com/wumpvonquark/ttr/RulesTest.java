package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.Rules;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RulesTest {

    private Rules rules;
    private List<TrainCard> drawnCards;

    @Before
    public void setUp() throws Exception {
        rules = new Rules();
        drawnCards = new ArrayList<>();
    }

    @Test
    public void routeIsConnectedWithDifferentStartAndEndCity() throws Exception {
        List<Route> routes = new ArrayList<>();
        routes.add(Route.LON_AMS);
        routes.add(Route.ESS_BER);
        routes.add(Route.AMS_ESS);
        assertTrue(rules.isTicketValid(TicketCard.LON_BER, routes));
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
        assertTrue(rules.isTicketValid(TicketCard.EDI_PAR, routes));
    }

    @Test
    public void shouldReturnFullTunnelRouteCost() throws Exception {
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        drawnCards.add(new TrainCard(Color.BLACK));
        rules.setOptionalColor(Color.BLACK);
        rules.setCheckRoute(Route.STO_PET);
        rules.setTunnelRouteCost(drawnCards);
        assertEquals(11, rules.getRouteCost());
    }

    @Test
    public void shouldReturnExtraCostOfOneIfTunnel() throws Exception {
        drawnCards.add(new TrainCard(Color.BLACK));
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.GREEN));
        rules.setOptionalColor(Color.BLACK);
        rules.setCheckRoute(Route.STO_PET);
        rules.setTunnelRouteCost(drawnCards);
        assertEquals(9, rules.getRouteCost());
    }

    @Test
    public void trainCardColorShouldBeSameAsRouteColorAndRightAmount() throws Exception {
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.YELLOW));
        drawnCards.add(new TrainCard(Color.YELLOW));
        drawnCards.add(new TrainCard(Color.YELLOW));
        drawnCards.add(new TrainCard(Color.GREEN));
        rules.setCheckRoute(Route.AMS_ESS);
        rules.setCheckDeck(drawnCards);
        assertTrue(rules.haveTrainCardsForRoute());
    }

    @Test
    public void shouldClaimRouteWithOptionalTrainCard() throws Exception {
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        rules.setCheckRoute(Route.AMS_ESS);
        rules.setCheckDeck(drawnCards);
        rules.setOptionalColor(Color.YELLOW);
        assertTrue(rules.haveTrainCardsForRoute());
    }

    @Test
    public void haveOptionalTrainCardsToClaimFerryRoute() throws Exception {
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.RED));
        drawnCards.add(new TrainCard(Color.YELLOW));
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        drawnCards.add(new TrainCard(Color.OPTIONAL));
        rules.setCheckRoute(Route.PAL_SMY);
        rules.setCheckRoute(Route.LON_AMS);
        rules.setCheckDeck(drawnCards);
        rules.setOptionalColor(Color.RED);
        rules.setRouteCost(Route.LON_AMS.getLength());
        assertTrue(rules.haveTrainCardsForFerryRoute());
    }
}
