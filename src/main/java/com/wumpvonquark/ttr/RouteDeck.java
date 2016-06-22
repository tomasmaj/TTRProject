package main.java.com.wumpvonquark.ttr;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class RouteDeck extends Deck<Route> {

    @Override
    public void generate() {
        this.items.addAll(Arrays.asList(Route.values()));
    }
}
