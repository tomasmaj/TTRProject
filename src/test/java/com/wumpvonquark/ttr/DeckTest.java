package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.decks.Deck;
import main.java.com.wumpvonquark.ttr.decks.RouteDeck;
import main.java.com.wumpvonquark.ttr.decks.TicketDeck;
import main.java.com.wumpvonquark.ttr.decks.TrainDeck;
import main.java.com.wumpvonquark.ttr.items.Item;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
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
        TicketDeck deck = new TicketDeck();
        deck.generate();
        Collections.sort(deck.getAllItems());
        List<TicketCard> tc = Arrays.asList(TicketCard.values());
        assertEquals(tc, deck.getAllItems());
    }

    @Test
    public void deckOfTrainCardsShouldBe110InSize() throws Exception {
        TrainDeck deck = new TrainDeck();
        deck.generate();
        assertEquals(110, deck.getAllItems().size());
    }

    @Test
    public void deckOfTrainCardsShouldHaveRightNumberOfColors() throws Exception {
        TrainDeck deck = new TrainDeck();
        deck.generate();
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
        RouteDeck deck = new RouteDeck();
        deck.generate();
        assertEquals(Route.values().length, deck.getAllItems().size());
    }

    @Test
    public void deckOfRoutesShouldNotContainDoubles() throws Exception {
        RouteDeck deck = new RouteDeck();
        deck.generate();
        assertTrue(deckContainsNoDoubles(deck));
    }

    @Test
    public void ticketShouldNotContainDoubles() throws Exception {
        TicketDeck deck = new TicketDeck();
        deck.generate();
        assertTrue(deckContainsNoDoubles(deck));
        }

     private <T> boolean deckContainsNoDoubles(Deck<T> deck) {
        Stack<Item> itemDeck = (Stack<Item>) deck.getAllItems();
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