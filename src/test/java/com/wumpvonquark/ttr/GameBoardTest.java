package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        assertFalse(gameBoard.getPlayers().get(0).getTicketDeck().getAllItems().isEmpty());
    }

    @Test
    public void playerShouldStartWithRightAmountOfItems() throws Exception {
        assertEquals(Rules.numberOfStartTicketCards, gameBoard.getPlayers().get(0).getTicketDeck().getSize());
        assertEquals(Rules.numberOfStartTrainCards, getPlayerTrainDeckSize());
        assertEquals(Rules.numberOfStartTrains, gameBoard.getPlayers().get(0).getTrainSet().getSize());
        assertEquals(Rules.numberOfStartStations, gameBoard.getPlayers().get(0).getStationSet().getSize());
    }

    @Test
    public void addCardToPlayerTrainDeck() throws Exception {
        List<TrainCard> tc = new ArrayList<>();
        tc.add(getAllItemsFromGameBoardTrainDeck().get(0));
        tc.add(getAllItemsFromGameBoardTrainDeck().get(2));
        int gameBoardDeckSize = gameBoard.getTrainDeck().getSize();
        int playerDeckSize = getPlayerTrainDeckSize();

        gameBoard.dealTrainCard(tc);

        assertEquals(gameBoardDeckSize - tc.size(), getAllItemsFromGameBoardTrainDeck().size());
        assertEquals(playerDeckSize + tc.size(), getPlayerTrainDeckSize());

    }

    @Test
    public void playerShouldClaimTrainRoute() throws Exception {
        Player player = gameBoard.getPlayers().get(0);
        player.addCardToTrainDeck(gameBoard.getTrainDeck().getCardsWithColor(Color.YELLOW, 4));
        List<TrainCard> drawnCards = player.getTrainDeck().getCardsWithColor(Color.YELLOW, 3);
        assertEquals(3 , player.getTrainDeck().getSize());
        assertEquals(3, drawnCards.size());
    }

    @Test
    public void usedTrainCardsShouldBeInGameBoardGarbagePile() throws Exception {
        Player player = gameBoard.getPlayers().get(0);
        player.addCardToTrainDeck(gameBoard.getTrainDeck().getCardsWithColor(Color.RED, 4));
        List<TrainCard> usedTrainCards = player.getTrainDeck().getCardsWithColor(Color.RED, 4);
        gameBoard.addCardsToGarbageDeck(usedTrainCards);
        assertTrue(gameBoard.getTrainGarbagePile().getAllItems().containsAll(usedTrainCards));
    }

    private int getPlayerTrainDeckSize() {
        return gameBoard.getPlayers().get(0).getTrainDeck().getSize();
    }

    private Stack<TrainCard> getAllItemsFromGameBoardTrainDeck() {
        return gameBoard.getTrainDeck().getAllItems();
    }
}
