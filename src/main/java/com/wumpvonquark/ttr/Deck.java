package main.java.com.wumpvonquark.ttr;

import java.util.*;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public abstract class Deck<T> {

    Stack<T> cards;

    /*List<TicketCard> cards;

    public void addCards(TicketCard[] cards) {
        this.cards = new ArrayList<>();
        this.cards.addAll(Arrays.asList(cards));
    }

    public List<TicketCard> getCards() {
        return cards;
    }*/

    public Deck(Stack<T> cards) {
        this.cards = cards;
        generate();
        //shuffle();
    }

    public Stack<T> getDeck() {
        return cards;
    }

    public List<T> getCards(int numberOfCards) {
        List<T> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            drawnCards.add(cards.pop());
        }
        return drawnCards;
    }

    public abstract void generate();

    public void shuffle() {
        Collections.shuffle(cards);
    }



}
