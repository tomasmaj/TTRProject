package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard gameBoard;
    private Player player;

    @Before
    public void setUp() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("PlayerName", Color.BLUE));
        playerList.add(new Player("PlayerName2", Color.RED));
        playerList.add(new Player("PlayerName3", Color.GREEN));
        playerList.add(new Player("PlayerName4", Color.YELLOW));
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
        assertEquals(1, gameBoard.currentScore(gameBoard.getPlayers()).get(0).getScore());
    }

    @Test
    public void playerInGameScoreShouldBeSumOfAllRoutes() throws Exception {
        player.getRouteDeck().getAllItems().add(Route.DIE_PAR);
        player.getRouteDeck().getAllItems().add(Route.PAR_MAR);
        player.getRouteDeck().getAllItems().add(Route.MAD_LIS);
        assertEquals(12, gameBoard.currentScore(gameBoard.getPlayers()).get(0).getScore());
    }

    @Ignore
    @Test
    public void shouldReturnPlayersWithHighestToLowestRouteScore() throws Exception {
        addPlayerRoutes();
        Player[] actual = {player(1), player(3), player(2), player(0)};
        assertArrayEquals(actual, gameBoard.currentScore(gameBoard.getPlayers()).toArray());
    }

    @Test
    public void whenPlayerTurnEndsGoToNextPlayer() throws Exception {
        gameBoard.nextTurn();
        assertEquals("PlayerName2", gameBoard.getPlayers().get(1).getName());
    }

    @Test
    public void checkIfGameIsOverWithFourPlayers() throws Exception {
        player.getTrainSet().getItems(44);
        gameBoard.nextTurn();
        gameBoard.nextTurn();
        gameBoard.nextTurn();
        gameBoard.nextTurn();
        boolean gameOver = gameBoard.nextTurn();
        assertEquals(true, gameOver);
    }

    @Test
    public void shouldReturnTotalScore() throws Exception {
        addPlayerRoutes();
        player(3).getTicketDeck().getAllItems().add(TicketCard.LON_BER);
        TicketCard.LON_BER.setValid(true);
        List<Player> scoreFinal = gameBoard.finalScore(gameBoard.getPlayers());
        assertEquals(gameBoard.getPlayers().get(3), scoreFinal.get(0));
        assertEquals(gameBoard.getPlayers().get(1), scoreFinal.get(1));
        assertEquals(gameBoard.getPlayers().get(2), scoreFinal.get(2));
        assertEquals(gameBoard.getPlayers().get(0), scoreFinal.get(3));
    }

    private void addPlayerRoutes() {
        player(3).getRouteDeck().getAllItems().add(Route.DIE_PAR);
        player(3).getRouteDeck().getAllItems().add(Route.PAR_MAR);
        player(3).getRouteDeck().getAllItems().add(Route.PAL_SMY);
        player(3).getRouteDeck().getAllItems().add(Route.ATH_SMY);
        player(3).getRouteDeck().getAllItems().add(Route.EDI_LON_BLACK);

        player(1).getRouteDeck().getAllItems().add(Route.LON_DIE_OPT1);
        player(1).getRouteDeck().getAllItems().add(Route.PAM_BAR);
        player(1).getRouteDeck().getAllItems().add(Route.MAD_LIS);
        player(1).getRouteDeck().getAllItems().add(Route.STO_PET);

        player(2).getRouteDeck().getAllItems().add(Route.LON_AMS);
        player(2).getRouteDeck().getAllItems().add(Route.AMS_ESS);
        player(2).getRouteDeck().getAllItems().add(Route.ESS_BER);
        player(2).getRouteDeck().getAllItems().add(Route.BUD_KYI);
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
