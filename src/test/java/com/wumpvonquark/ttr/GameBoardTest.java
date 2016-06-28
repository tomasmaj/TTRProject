package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.decks.Deck;
import main.java.com.wumpvonquark.ttr.decks.TrainDeck;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("PlayerName", Color.BLUE));
        gameBoard = new GameBoard(playerList);
        gameBoard.init();
    }

    @Test
    public void shouldBePlayersInGame() throws Exception {
        assertEquals("PlayerName", gameBoard.getPlayers().get(0).getName());
    }

    @Test
    public void playerTicketCardsShouldNotBeEmpty() throws Exception {
        assertFalse(gameBoard.getPlayers().get(0).getTicketDeck().isEmpty());
    }

    @Test
    public void playerShouldStartWithRightAmountOfItems() throws Exception {
        assertEquals(Rules.numberOfStartTicketCards, gameBoard.getPlayers().get(0).getTicketDeck().size());
        assertEquals(Rules.numberOfStartTrainCards, gameBoard.getPlayers().get(0).getTrainDeck().size());
        assertEquals(Rules.numberOfStartTrains, gameBoard.getPlayers().get(0).getTrainSet().size());
        assertEquals(Rules.numberOfStartStations, gameBoard.getPlayers().get(0).getStationSet().size());
    }

    @Test
    public void addCardToPlayerTrainDeck() throws Exception {
        List<TrainCard> tc = new ArrayList<>();
        tc.add(gameBoard.getTrainDeck().getAllItems().get(0));
        tc.add(gameBoard.getTrainDeck().getAllItems().get(2));
        int gameBoardDeckSize = gameBoard.getTrainDeck().getAllItems().size();
        int playerDeckSize = gameBoard.getPlayers().get(0).getTrainDeck().size();

        gameBoard.dealCard(tc);

        assertEquals(gameBoardDeckSize - tc.size(), gameBoard.getTrainDeck().getAllItems().size());
        assertEquals(playerDeckSize + tc.size(), gameBoard.getPlayers().get(0).getTrainDeck().size());

    }

}
