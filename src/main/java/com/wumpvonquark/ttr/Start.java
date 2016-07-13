package main.java.com.wumpvonquark.ttr;


import main.java.com.wumpvonquark.ttr.items.Route;
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
            System.out.println("--------------------------------------------------------------");
            System.out.println("\nNext up is " + gameBoard.getPlayerTurn().getName());
            displayMenu();

        } while(!gameBoard.nextTurn());

        System.out.println("\nGame is over...");

        int standing = 1;
        for(Player player : gameBoard.setFinalScore()) {
            System.out.println((standing++) + ". " + player.getName() + " with " + player.getScore() + " points");
        }

        System.out.println("Winner is " + gameBoard.setFinalScore().get(0).getName());

    }

    private void displayMenu() {

        while(gameBoard.getMovesLeft()) {
            System.out.println("\nWhat you can do:\n" +
                    "1. Show My Bank\n" +
                    "2. Draw Train Card\n" +
                    "3. Claim Route");

            int choice = scan.nextInt();

            switch (choice) {
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
            scan.nextLine();
        }
    }

    private void showAvailableRoutes() {

        System.out.println("\nAvailable routes to claim:");
        System.out.println("|\tNumber\t|\t\tFrom\t\t|\t\tTo\t\t|\tLength\t|\tColor\t|\tFerry\t|\tTunnel\t");
        int counter = 1;
        for(Route route : gameBoard.getRouteItems().getAllItems()) {
            String city1 = cityLayout(route.getCity1().toString());
            String city2 = cityLayout(route.getCity2().toString());
            String length = String.valueOf(route.getLength());
            String color = colorLayout(route.getColor().toString());
            String ferry = String.valueOf(route.getFerry());
            String tunnel = route.isTunnel() ? "Yes" : "No";

            System.out.println("\t" + (counter++) + "\t\t" + city1 + city2 + length + "\t\t" + color + "\t" + ferry + "\t\t\t" + tunnel + "\t");
        }

        System.out.println("Type the number of the route you want to claim or type a 0(zero) to go back to main menu:");
        int choice = scan.nextInt();

        if(choice <= counter-1 && choice >= 1) {
            Route route = gameBoard.getRouteItems().getAllItems().get(choice-1);
            List<TrainCard> playerTrainCards = gameBoard.getPlayerTurn().getTrainDeck().getAllItems();
            Color color = route.getColor();

            if(route.getColor().equals(Color.OPTIONAL)) {
                System.out.println("\nChoose color you want to use:\n" +
                        "1. RED\n" +
                        "2. GREEN\n" +
                        "3. YELLOW\n" +
                        "4. BLACK\n" +
                        "5. BLUE\n" +
                        "6. WHITE\n" +
                        "7. PINK\n" +
                        "8. ORANGE\n");
                int choosenColor = scan.nextInt();
                System.out.println(choosenColor);
                color = setColor(choosenColor);
            }



            if(gameBoard.claimRoute(route, playerTrainCards, color)) {
                System.out.println("You claimed the " + route.getCity1() + " to " + route.getCity2() + " route!");
            } else {
                System.out.println("You do not have enough train cards to claim this route.");
            }
        }
    }

    private String colorLayout(String color) {
        if(color.length() >= 8)
            return "\t" + color;
        else if(color.length() >= 4)
            return "\t" + color + "\t";
        else
            return "\t" + color + "\t\t";
    }

    private String cityLayout(String city) {

        if(city.length() >= 12)
            return "\t" + city + "\t";
        else if(city.length() >= 8)
            return "\t" + city + "\t\t";
        else
            return "\t" + city + "\t\t\t";

    }

    private void showGameBoardFiveCardDeck() {
        while(gameBoard.getMovesLeft()) {

            int index = 1;
            System.out.println("\nSelect a card on the gameboard:");
            for (TrainCard trainCard : gameBoard.getFiveCardTrainDeck().getAllItems()) {
                System.out.println(index++ + ": " + trainCard.getColor());
            }

            int choice = scan.nextInt();

            gameBoard.dealTrainCard(gameBoard.getFiveCardTrainDeck().getAllItems().get(choice-1));

        }

    }

    private void showPlayerBank() {

        Player player = gameBoard.getPlayerTurn();

        System.out.println("\nYou have " + player.getTrainSet().getSize() + " trains left...");

        System.out.println("\nYou have the following ticket cards:");
        for (TicketCard ticketCard : player.getTicketDeck().getAllItems()) {
            System.out.println(ticketCard.getStartCity() + " to " + ticketCard.getEndCity() + " for " + ticketCard.getPoints() + " points.");
        }

        System.out.println("\nYou have claimed the following routes:");
        for(Route route : player.getRouteDeck().getAllItems()) {
            System.out.println(route.getCity1() + " to " + route.getCity2());
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

        System.out.println("\nYou have the following train cards:");
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
            case 6:
                return Color.WHITE;
            case 7:
                return Color.PINK;
            case 8:
                return Color.ORANGE;
            default:
                return null;
        }
    }
}
