package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard gameBoard;
    Player player;

    @Before
    public void setUp() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("PlayerName", Color.BLUE));
        playerList.add(new Player("PlayerName2", Color.RED));
        gameBoard = new GameBoard(playerList);
        gameBoard.init();
        player = player(0);
    }

    @Test
    public void shouldBePlayersInGame() throws Exception {
        assertEquals("PlayerName", player(0).getName());
    }

    @Test
    public void playerTicketCardsShouldNotBeEmpty() throws Exception {
        assertFalse(player(0).getTicketDeck().getAllItems().isEmpty());
    }

    @Test
    public void playerShouldStartWithRightAmountOfItems() throws Exception {
        assertEquals(Rules.numberOfStartTicketCards, player(0).getTicketDeck().getSize());
        assertEquals(Rules.numberOfStartTrainCards, getPlayerTrainDeckSize());
        assertEquals(Rules.numberOfStartTrains, player(0).getTrainSet().getSize());
        assertEquals(Rules.numberOfStartStations, player(0).getStationSet().getSize());
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
        player.addCardToTrainDeck(gameBoard.getTrainDeck().getCardsWithColor(Color.YELLOW, 4));
        gameBoard.claimRoute(Route.AMS_ESS);
        assertTrue(player.getRouteDeck().getAllItems().contains(Route.AMS_ESS));
    }

    @Test
    public void usedTrainCardsShouldBeInGameBoardGarbagePile() throws Exception {
        player.addCardToTrainDeck(gameBoard.getTrainDeck().getCardsWithColor(Color.RED, 4));
        List<TrainCard> usedTrainCards = player.getTrainDeck().getCardsWithColor(Color.RED, 4);
        gameBoard.addCardsToGarbageDeck(usedTrainCards);
        assertTrue(gameBoard.getTrainGarbagePile().getAllItems().containsAll(usedTrainCards));
    }

    @Test
    public void playerInGameScoreShouldBeOne() throws Exception {
        player.getRouteDeck().getAllItems().add(Route.DIE_PAR);
        assertEquals(1, gameBoard.currentScoreBoard(gameBoard.getPlayers()));
    }

    @Test
    public void playerInGameScoreShouldBeSumOfAllRoutes() throws Exception {
        player.getRouteDeck().getAllItems().add(Route.DIE_PAR);
        player.getRouteDeck().getAllItems().add(Route.PAR_MAR);
        player.getRouteDeck().getAllItems().add(Route.MAD_LIS);
        assertEquals(12, gameBoard.currentScoreBoard(gameBoard.getPlayers()));
    }

    @Test
    public void shouldReturnPlayersWithHighestToLowestScore() throws Exception {
        player.getRouteDeck().getAllItems().add(Route.DIE_PAR);
        player.getRouteDeck().getAllItems().add(Route.PAR_MAR);
        player.getRouteDeck().getAllItems().add(Route.MAD_LIS);
        player(1).getRouteDeck().getAllItems().add(Route.LON_DIE_OPT1);
        player(1).getRouteDeck().getAllItems().add(Route.PAM_BAR);
        player(1).getRouteDeck().getAllItems().add(Route.BRU_FRA);
        player(1).getRouteDeck().getAllItems().add(Route.WIE_BUD_RED);
        assertEquals(player, gameBoard.currentScoreBoard(gameBoard.getPlayers()));

    }

    private int getPlayerTrainDeckSize() {
        return player.getTrainDeck().getSize();
    }

    private Stack<TrainCard> getAllItemsFromGameBoardTrainDeck() {
        return gameBoard.getTrainDeck().getAllItems();
    }

    private Player player(int index) {
        return gameBoard.getPlayers().get(index);
    }
}
