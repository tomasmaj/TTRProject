package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.internal.Ticket;

import java.util.Arrays;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class DeckTest {

    Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new TicketDeck();
    }

    @Test
    public void deckCanHaveTicketCards() throws Exception {
        Assert.assertEquals(Arrays.asList(TicketCard.values()), deck.getDeck());
    }

    @Test
    public void deckCanHaveTrainCards() throws Exception {
        // TrainCard trainCard = new TrainCard(Color.BLUE);
        // deck.addCards(trainCard);
    }
}
