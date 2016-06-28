package main.java.com.wumpvonquark.ttr.decks;

import main.java.com.wumpvonquark.ttr.items.Route;

import java.util.Arrays;

public class RouteDeck extends Deck<Route> {

    @Override
    public void generate() {
        this.items.addAll(Arrays.asList(Route.values()));
    }
}
