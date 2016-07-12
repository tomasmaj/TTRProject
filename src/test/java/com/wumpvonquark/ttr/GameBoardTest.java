package test.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.*;
import main.java.com.wumpvonquark.ttr.items.Route;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import org.junit.Before;
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
        player = playerIndex(0);
    }

    @Test
    public void shouldBePlayersInGame() throws Exception {
        assertEquals("PlayerName", playerIndex(0).getName());
    }

    @Test
    public void playerTicketCardsShouldNotBeEmpty() throws Exception {
        assertFalse(playerIndex(0).getTicketDeck().getAllItems().isEmpty());
    }

    @Test
    public void playerShouldStartWithRightAmountOfItems() throws Exception {
        assertEquals(Rules.numberOfStartTicketCards, playerIndex(0).getTicketDeck().getSize());
        assertEquals(Rules.numberOfStartTrainCards, getPlayerTrainDeckSize());
        assertEquals(Rules.numberOfStartTrains, playerIndex(0).getTrainSet().getSize());
        assertEquals(Rules.numberOfStartStations, playerIndex(0).getStationSet().getSize());
    }

    @Test
    public void addCardToPlayerTrainDeck() throws Exception {
        List<TrainCard> tc = new ArrayList<>();
        tc.add(getAllItemsFromGameBoardTrainDeck().get(0));
        tc.add(getAllItemsFromGameBoardTrainDeck().get(2));
        int playerDeckSize = getPlayerTrainDeckSize();

        gameBoard.dealTrainCard(tc);

        assertEquals(5, getAllItemsFromGameBoardTrainDeck().size());
        assertEquals(playerDeckSize + tc.size(), getPlayerTrainDeckSize());

    }

    @Test
    public void playerShouldClaimAnyTrainRoute() throws Exception {
        player.addCardToTrainDeck(gameBoard.getTrainDeck().getCardsWithColor(Color.YELLOW, 3));
        assertTrue(gameBoard.claimRoute(Route.AMS_ESS, player.getTrainDeck().getAllItems(), null));
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
        assertEquals(1, gameBoard.setCurrentScore().get(0).getScore());
    }

    @Test
    public void playerInGameScoreShouldBeSumOfAllRoutes() throws Exception {
        player.getRouteDeck().getAllItems().add(Route.DIE_PAR);
        player.getRouteDeck().getAllItems().add(Route.PAR_MAR);
        player.getRouteDeck().getAllItems().add(Route.MAD_LIS);
        assertEquals(12, gameBoard.setCurrentScore().get(0).getScore());
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
        for (Player player : gameBoard.getPlayers()) {
            player.getTicketDeck().getAllItems().clear();
        }
        addPlayerRoutes();
        playerIndex(3).getTicketDeck().getAllItems().add(TicketCard.LON_BER);
        TicketCard.LON_BER.setValid(true);
        List<Player> scoreFinal = gameBoard.setFinalScore();
        assertEquals(gameBoard.getPlayers().get(3), scoreFinal.get(0));
        assertEquals(gameBoard.getPlayers().get(1), scoreFinal.get(1));
        assertEquals(gameBoard.getPlayers().get(2), scoreFinal.get(2));
        assertEquals(gameBoard.getPlayers().get(0), scoreFinal.get(3));
    }

    private void addPlayerRoutes() {
        playerIndex(3).getRouteDeck().getAllItems().add(Route.DIE_PAR);
        playerIndex(3).getRouteDeck().getAllItems().add(Route.PAR_MAR);
        playerIndex(3).getRouteDeck().getAllItems().add(Route.PAL_SMY);
        playerIndex(3).getRouteDeck().getAllItems().add(Route.ATH_SMY);
        playerIndex(3).getRouteDeck().getAllItems().add(Route.EDI_LON_BLACK);

        playerIndex(1).getRouteDeck().getAllItems().add(Route.LON_DIE_OPT1);
        playerIndex(1).getRouteDeck().getAllItems().add(Route.PAM_BAR);
        playerIndex(1).getRouteDeck().getAllItems().add(Route.MAD_LIS);
        playerIndex(1).getRouteDeck().getAllItems().add(Route.STO_PET);

        playerIndex(2).getRouteDeck().getAllItems().add(Route.LON_AMS);
        playerIndex(2).getRouteDeck().getAllItems().add(Route.AMS_ESS);
        playerIndex(2).getRouteDeck().getAllItems().add(Route.ESS_BER);
        playerIndex(2).getRouteDeck().getAllItems().add(Route.BUD_KYI);
    }

    private int getPlayerTrainDeckSize() {
        return player.getTrainDeck().getSize();
    }

    private Stack<TrainCard> getAllItemsFromGameBoardTrainDeck() {
        return gameBoard.getFiveCardTrainDeck().getAllItems();
    }

    private Player playerIndex(int index) {
        return gameBoard.getPlayers().get(index);
    }

    @Test
    public void claimFerryRoute() throws Exception {
        List<TrainCard> claimCards = new ArrayList<>();
        claimCards.addAll(gameBoard.getTrainDeck().getCardsWithColor(Color.YELLOW, 6));
        claimCards.addAll(gameBoard.getTrainDeck().getCardsWithColor(Color.OPTIONAL, 2));
        gameBoard.claimRoute(Route.PAL_SMY, claimCards, Color.YELLOW);
        assertEquals(Route.PAL_SMY, gameBoard.getPlayers().get(0).getRouteDeck().getAllItems().get(0));
    }

    @Test
    public void claimTunnelRoute() throws Exception {
        List<TrainCard> claimCards = new ArrayList<>();
        claimCards.addAll(gameBoard.getTrainDeck().getCardsWithColor(Color.WHITE, 3));
        claimCards.addAll(gameBoard.getTrainDeck().getCardsWithColor(Color.OPTIONAL, 3));
        assertTrue(gameBoard.claimRoute(Route.PAM_MAD_WHITE, claimCards, Color.WHITE));
    }

    @Test
    public void checkThatGameWorks() throws Exception {
        Player player1 = new Player("Name1", Color.BLACK);
        Player player2 = new Player("Name2", Color.YELLOW);
        Player player3 = new Player("Name3", Color.WHITE);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        GameBoard gameBoard = new GameBoard(players);
        gameBoard.init();

        System.out.println("Spelare " + gameBoard.getPlayerTurn().getName() + "'s tur...");

        gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getItems(2));

        gameBoard.nextTurn();

        System.out.println("Spelare " + gameBoard.getPlayerTurn().getName() + "'s tur...");

        gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getItems(2));

        gameBoard.nextTurn();

        System.out.println("Spelare " + gameBoard.getPlayerTurn().getName() + "'s tur...");

        gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getItems(5));

        System.out.println("Före");
        for(TrainCard tc : gameBoard.getPlayerTurn().getTrainDeck().getAllItems())
            System.out.println(tc.getColor());

        if(gameBoard.claimRoute(Route.LON_AMS, gameBoard.getPlayerTurn().getTrainDeck().getAllItems(), Color.YELLOW)) {
            System.out.println("Spelare " + gameBoard.getPlayerTurn().getName() + " tar route " + Route.LON_AMS.getCity1() + "-" + Route.LON_AMS.getCity2());
        }

        System.out.println("Efter");
        for(TrainCard tc : gameBoard.getPlayerTurn().getTrainDeck().getAllItems())
            System.out.println(tc.getColor());

        gameBoard.nextTurn();

        System.out.println("Spelare " + gameBoard.getPlayerTurn().getName() + "'s tur...");

        gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getItems(1));
        gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getItems(1));
    }
}
