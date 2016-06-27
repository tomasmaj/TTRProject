package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.TicketDeck;
import main.java.com.wumpvonquark.ttr.TrainDeck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.com.wumpvonquark.ttr.GameBoard;

public class GameBoardTest {

    GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard();
    }

    @Test
    public void shouldHaveTicketCards() throws Exception {
        Assert.assertTrue(gameBoard.getTicketDeck().containsAll(new TicketDeck().getAllItems()));
    }

    @Test
    public void shouldHaveTrainCards() throws Exception {
        gameBoard.getTrainDeck();
    }
}
