package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.Player;
import main.java.com.wumpvonquark.ttr.decks.TrainDeck;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("NameOfPlayer", Color.YELLOW);
    }

    @Test
    public void playerShouldHaveAName() throws Exception {
        assertEquals("NameOfPlayer", player.getName());
    }

    @Test
    public void playerShouldStartWithTwoCards() throws Exception {
        TrainDeck tr = new TrainDeck();
        tr.generate();
        player.addCardToTrainDeck(tr.getItems(2));
        assertEquals(2, player.getTrainDeck().getAllItems().size());
    }

    @Test
    public void shouldBeAbleToChooseTicketCards() throws Exception {
        player.getTicketDeck().getAllItems().add(TicketCard.LON_BER);
        player.getTicketDeck().getAllItems().add(TicketCard.BER_MOS);
        player.getTicketDeck().getAllItems().add(TicketCard.FRA_SMO);

        List<TicketCard> removeCards = new ArrayList<>();
        removeCards.add(TicketCard.BER_MOS);
        removeCards.add(TicketCard.LON_BER);
        player.removeTickets(removeCards);
        assertEquals(TicketCard.FRA_SMO, player.getTicketDeck().getAllItems().get(0));
    }
}
