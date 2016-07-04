package main.java.com.wumpvonquark.ttr;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

    Scanner scan;
    String name;
    Player player;

    public List<Player> addPlayers() {
        List<Player> players = new ArrayList<>();
        do {
            System.out.print("Enter player name (null entry to proceed): ");
            name = scan.nextLine();
            name = "Player";
            System.out.println(name);
            System.out.println("\nChoose color:\n" +
                    "1. RED\n" +
                    "2. GREEN\n" +
                    "3. YELLOW\n" +
                    "4. BLACK\n" +
                    "5. BLUE");
            int colorId = scan.nextInt();
            Color color = setColor(colorId);
            players.add(new Player(name, color));
        } while (name != null);

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
