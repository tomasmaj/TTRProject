package main.java.com.wumpvonquark.ttr;


import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import main.java.com.wumpvonquark.ttr.items.TrainPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

    Scanner scan;
    String name;
    public static GameBoard gameBoard;

    public static void main(String[] args) {
        Start start = new Start();
        List<Player> players = start.addPlayers();
        gameBoard = new GameBoard(players);
        gameBoard.init();
        start.startGame();
    }

    private void startGame() {

        do {

            System.out.println("\nNext up is " + gameBoard.getPlayerTurn().getName());
            displayMenu();

        } while(!gameBoard.nextTurn());

    }

    private void displayMenu() {
        System.out.println("\nWhat you can do:\n" +
                "1. Show My Bank\n" +
                "2. Draw Train Card\n" +
                "3. Claim Route");

        int choice = scan.nextInt();

        switch(choice) {
            case 1:
                showPlayerBank();
                break;
            case 2:
                showGameBoardFiveCardDeck();
                break;
            case 3:
                showAvailableRoutes();
                break;
            default:
                showPlayerBank();
        }
    }

    private void showAvailableRoutes() {

    }

    private void showGameBoardFiveCardDeck() {

        int index = 1;
        System.out.println();
        for(TrainCard trainCard : gameBoard.getFiveCardTrainDeck().getAllItems()) {
            System.out.println(index++ + ": " + trainCard.getColor());
        }

    }

    private void showPlayerBank() {

        Player player = gameBoard.getPlayerTurn();

        System.out.println("You have " + player.getTrainSet().getSize() + " left...");

        System.out.println("You have the following ticket cards");
        for (TicketCard ticketCard : player.getTicketDeck().getAllItems()) {
            System.out.println(ticketCard.getStartCity() + " to " + ticketCard.getEndCity() + " for " + ticketCard.getPoints() + " points.");
        }

        int red = 0;
        int blue = 0;
        int black = 0;
        int green = 0;
        int pink = 0;
        int orange = 0;
        int yellow = 0;
        int white = 0;
        int optional = 0;

        System.out.println("You have the following train cards:");
        for(TrainCard trainCard : player.getTrainDeck().getAllItems()) {
            switch(trainCard.getColor()) {
                case RED:
                    red++;
                    break;
                case BLUE:
                    blue++;
                    break;
                case BLACK:
                    black++;
                    break;
                case GREEN:
                    green++;
                    break;
                case PINK:
                    pink++;
                    break;
                case ORANGE:
                    orange++;
                    break;
                case YELLOW:
                    yellow++;
                    break;
                case WHITE:
                    white++;
                    break;
                case OPTIONAL:
                    optional++;
                    break;
            }
        }

        System.out.println("Red: " + red);
        System.out.println("Blue: " + blue);
        System.out.println("Black: " + black);
        System.out.println("Green: " + green);
        System.out.println("Pink: " + pink);
        System.out.println("Orange: " + orange);
        System.out.println("Yellow: " + yellow);
        System.out.println("White: " + white);
        System.out.println("Optional: " + optional);

    }

    public Start() {
        this.scan = new Scanner(System.in);
    }

    public List<Player> addPlayers() {
        List<Player> players = new ArrayList<>();
        do {
            System.out.print("Enter player name (null entry to proceed): ");
            name = scan.nextLine();
            if(name.equals("done"))
                break;
            System.out.println("\nChoose color:\n" +
                    "1. RED\n" +
                    "2. GREEN\n" +
                    "3. YELLOW\n" +
                    "4. BLACK\n" +
                    "5. BLUE");
            int colorId = scan.nextInt();
            Color color = setColor(colorId);
            players.add(new Player(name, color));
            scan.nextLine();
        } while (players.size() < 5);

        System.out.println("Welcome players!");
        for(Player player : players) {
            System.out.println(player.getName() + " with color " + player.getColor());
        }

        return players;
    }

    private Color setColor(int colorId) {
        switch (colorId) {
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.BLACK;
            case 5:
                return Color.BLUE;
            default:
                return null;
        }
    }
}
