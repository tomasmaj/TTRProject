package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.Deck;
import main.java.com.wumpvonquark.ttr.Player;
import main.java.com.wumpvonquark.ttr.TrainDeck;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tomas Majling on 2016-06-27.
 */
public class PlayerTest {

    @Test
    public void createNewPlayer() throws Exception {
        Player player = new Player("NameOfPlayer", Color.YELLOW);
    }

    @Test
    public void playerShouldHaveAName() throws Exception {
        Player player = new Player("NameOfPlayer", Color.YELLOW);
        assertEquals("NameOfPlayer", player.getName());
    }

    @Test
    public void playerShouldStartWithTwoCards() throws Exception {
        Player player = new Player("NameOfPlayer", Color.YELLOW);
        Deck tr = new TrainDeck();
        player.addCardToTrainDeck(tr.getItems(2));
        assertEquals(2, player.getTrainDeck().size());
    }
}
