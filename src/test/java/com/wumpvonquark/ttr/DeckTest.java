package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.internal.Ticket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

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
}
