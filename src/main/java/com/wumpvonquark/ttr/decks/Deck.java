package main.java.com.wumpvonquark.ttr.decks;

import java.util.*;

public abstract class Deck<T> {

    Stack<T> items;

    Deck() {
        this.items = new Stack<>();
        generate();
        shuffle();
    }

    public Stack<T> getAllItems() {
        return items;
    }

    public List<T> getItems(int numberOfCards) {
        List<T> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            drawnCards.add(items.pop());
        }
        return drawnCards;
    }

    protected abstract void generate();

    private void shuffle() {
        Collections.shuffle(items);
    }

}
