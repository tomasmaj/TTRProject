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
        Deck deck = new RouteDeck();
        assertTrue(deckContainsNoDoubles(deck));
    }


    @Test
    public void ticketShouldNotContainDoubles() throws Exception {
        Deck deck = new TicketDeck();
        assertTrue(deckContainsNoDoubles(deck));
        }

     private boolean deckContainsNoDoubles(Deck deck) {
        Stack<Item> itemDeck = deck.getAllItems();
        int start = 1;
        for (Item item : itemDeck) {
            for (int i = start; i < itemDeck.size(); i++) {
                if (comp.compareDoubles(item, itemDeck.elementAt(i))) {
                    System.out.println(item);
                    System.out.println(itemDeck.elementAt(i));
                    return false;
                }
            }
            start++;
        }
        return true;
    }
}
