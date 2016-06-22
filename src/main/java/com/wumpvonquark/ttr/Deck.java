package main.java.com.wumpvonquark.ttr;

import java.util.*;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public abstract class Deck<T> {

    Stack<T> items;

    public Deck() {
        this.items = new Stack<T>();
        generate();
        shuffle();
    }

    public Stack<T> getDeck() {
        return items;
    }

    public List<T> getCards(int numberOfCards) {
        List<T> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            drawnCards.add(items.pop());
        }
        return drawnCards;
    }

    public abstract void generate();

    public void shuffle() {
        Collections.shuffle(items);
    }

}
