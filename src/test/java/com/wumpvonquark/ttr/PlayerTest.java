package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.decks.Deck;
import main.java.com.wumpvonquark.ttr.Player;
import main.java.com.wumpvonquark.ttr.decks.TrainDeck;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
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
}
