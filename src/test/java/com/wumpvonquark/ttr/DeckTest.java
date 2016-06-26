package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.utilities.Comparator;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckTest {

    private Deck deck;
    private Comparator comp;

    @Before
    public void setUp() throws Exception {
        comp = new Comparator();

    }

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
    public void deckOfRoutesShouldNotContainDoubles() throws Exception {
        deck = new RouteDeck();
        assertTrue(routeDeckContainsNoDoubles(deck.getDeck()));
    }

    private boolean routeDeckContainsNoDoubles(Stack<Route> deck) {
        Comparator comp = new Comparator();
        int start = 1;
        for (Route route : deck) {
            for (int i = start; i < deck.size(); i++) {
                if (comp.compareRoutes(route, deck.elementAt(i))) {
                    System.out.println(route.toString());
                    System.out.println(deck.elementAt(i));
                    return false;
                }
            }
            start++;
        }
        return true;
    }

    @Test
    public void ticketShouldNotContainDoubles() throws Exception {
        deck = new TicketDeck();
        assertTrue(ticketDeckContainsNoDoubles(deck.getDeck()));
        }

    private boolean ticketDeckContainsNoDoubles(Stack<TicketCard> deck) {
        int start = 1;
        for (TicketCard ticket : deck) {
            for (int i = start; i < deck.size(); i++) {
                if (comp.compareTickets(ticket, deck.elementAt(i))) {
                    System.out.println(ticket);
                    System.out.println(deck.elementAt(i));
                    return false;
                }
            }
            start++;
        }
        return true;
    }
}
