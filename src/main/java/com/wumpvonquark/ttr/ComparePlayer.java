package main.java.com.wumpvonquark.ttr;

import java.util.Comparator;

class ComparePlayer implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        return p1.getScore() >= p2.getScore() ? -1 : 1;
    }
}