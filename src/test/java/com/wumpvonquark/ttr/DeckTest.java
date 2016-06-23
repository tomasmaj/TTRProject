package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class DeckTest {

    Deck deck;

    @Test
    public void deckCanHaveTicketCards() throws Exception {
        deck = new TicketDeck();
        Collections.sort(deck.getDeck());
        List<TicketCard> tc = Arrays.asList(TicketCard.values());
        assertEquals(tc, deck.getDeck());
    }

    @Test
    public void deckOfTrainCardsShouldBe110InSize() throws Exception {
        deck = new TrainDeck();
        assertEquals(110, deck.getDeck().size());
    }

    @Test
    public void deckOfTrainCardsShouldHaveRightNumberOfColors() throws Exception {
        deck = new TrainDeck();
        Rules rules = new Rules();
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.BLACK));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.BLUE));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.GREEN));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.ORANGE));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.PINK));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.RED));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.WHITE));
        assertEquals(12, rules.countTrainCardColor(deck.getDeck(), Color.YELLOW));
        assertEquals(14, rules.countTrainCardColor(deck.getDeck(), Color.OPTIONAL));
    }

    @Test
    public void deckOfRoutesShouldBeCorrectSize() throws Exception {
        deck = new RouteDeck();
        assertEquals(Route.values().length, deck.getDeck().size());
    }

    @Test
    public void deckShouldNotContainDoubles() throws Exception {
        deck = new RouteDeck();
        assertTrue(containsDoubles(deck.getDeck()));
    }

    private boolean containsDoubles(Stack<Route> deck) {
        int startPoint = 1;
        List<City> cities = new ArrayList<>();
        Color routeColor;
        List<Route> doubleRoutes = new ArrayList<>();
        doubleRoutes = getDoubles(doubleRoutes);
        City city1;
        City city2;
        for (Route route : deck) {
            cities.add(route.getCity1());
            cities.add(route.getCity2());
            routeColor = route.getColor();
            for (int i = startPoint; i < deck.size(); i++) {
                city1 = deck.elementAt(i).getCity1();
                city2 = deck.elementAt(i).getCity2();
                Color color = deck.elementAt(i).getColor();
                if (cities.contains(city1) && cities.contains(city2) && routeColor == color && !doubleRoutes.contains(route) && !doubleRoutes.contains(route)) {
                    System.out.println(deck.elementAt(i).toString());
                    return false;
                }
            }
            startPoint++;
            cities.clear();
        }
        return true;
    }

    private List<Route> getDoubles(List<Route> doubleRoutes) {
        doubleRoutes.add(Route.LON_DIE_OPT1);
        doubleRoutes.add(Route.LON_DIE_OPT2);
        doubleRoutes.add(Route.ESS_KOB_OPT1);
        doubleRoutes.add(Route.ESS_KOB_OPT2);
        return doubleRoutes;
    }
}
