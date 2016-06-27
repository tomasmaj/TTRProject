package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.utilities.Comparator;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckTest {

    private Comparator comp;

    @Before
    public void setUp() throws Exception {
        comp = new Comparator();

    }

    @Test
    public void deckCanHaveTicketCards() throws Exception {
        Deck<TicketCard> deck = new TicketDeck();
        Collections.sort(deck.getAllItems());
        List<TicketCard> tc = Arrays.asList(TicketCard.values());
        assertEquals(tc, deck.getAllItems());
    }

    @Test
    public void deckOfTrainCardsShouldBe110InSize() throws Exception {
        Deck<TrainCard> deck = new TrainDeck();
        assertEquals(110, deck.getAllItems().size());
    }

    @Test
    public void deckOfTrainCardsShouldHaveRightNumberOfColors() throws Exception {
        Deck<TrainCard> deck = new TrainDeck();
        Rules rules = new Rules();
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.BLACK));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.BLUE));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.GREEN));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.ORANGE));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.PINK));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.RED));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.WHITE));
        assertEquals(12, rules.countTrainCardColor(deck.getAllItems(), Color.YELLOW));
        assertEquals(14, rules.countTrainCardColor(deck.getAllItems(), Color.OPTIONAL));
    }

    @Test
    public void deckOfRoutesShouldBeCorrectSize() throws Exception {
        Deck<Route> deck = new RouteDeck();
        assertEquals(Route.values().length, deck.getAllItems().size());
    }

    @Test
    public void deckOfRoutesShouldNotContainDoubles() throws Exception {
        Deck<Route> deck = new RouteDeck();
        assertTrue(routeDeckContainsNoDoubles(deck.getAllItems()));
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
        Deck<TicketCard> deck = new TicketDeck();
        assertTrue(ticketDeckContainsNoDoubles(deck.getAllItems()));
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
