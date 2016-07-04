package main.java.com.wumpvonquark.ttr;

import java.util.Scanner;

public class Start {

    Scanner scan;
    String name;
    int color;
    Player player;

    public Player addPlayers() {
        System.out.print("Enter player name: ");
        //name = scan.nextLine();
        name = "Player";
        System.out.println(name);
        System.out.println("\nChoose color:\n" +
                "1. RED\n" +
                "2. GREEN\n" +
                "3. YELLOW\n" +
                "4. BLACK\n" +
                "5. BLUE");
        Color color = Color.RED;
        player = new Player(name, color);
        return player;
    }
}
