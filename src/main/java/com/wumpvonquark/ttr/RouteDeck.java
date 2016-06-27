package main.java.com.wumpvonquark.ttr;

import java.util.Arrays;
import java.util.Stack;

public class RouteDeck extends Deck<Route> {

    @Override
    public void generate() {
        this.items.addAll(Arrays.asList(Route.values()));
    }
}
