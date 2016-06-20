package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kza von Quark on 2016-06-20.
 */
public class GameTest {

    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void scoreZeroTrains() throws Exception {
        assertEquals(0, game.score(0));
    }

    @Test
    public void scoreOneTrain() throws Exception {
        assertEquals(1, game.score(1));
    }

    @Test
    public void scoreThreeTrainsInARow() throws Exception {
        assertEquals(4, game.score(3));
    }

    @Test
    public void scoreAllNumberOfTrains() throws Exception {
        int score = game.score(0);
        score += game.score(1);
        score += game.score(2);
        score += game.score(3);
        score += game.score(4);
        score += game.score(6);
        score += game.score(8);
        assertEquals(50, score);
    }
}
