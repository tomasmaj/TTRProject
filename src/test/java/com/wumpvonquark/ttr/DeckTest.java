package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.Deck;
import main.java.com.wumpvonquark.ttr.TicketCard;
import main.java.com.wumpvonquark.ttr.TrainCard;
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
        deck = new Deck();
    }

    @Test
    public void deckCanHaveTicketCards() throws Exception {
        deck.addCards(TicketCard.values());
        Assert.assertEquals(Arrays.asList(TicketCard.values()), deck.getCards());
    }

    @Test
    public void deckCanHaveTrainCards() throws Exception {
        TrainCard trainCard = new TrainCard(Color.BLUE);
    }
}
