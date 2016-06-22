package main.java.com.wumpvonquark.ttr;

import java.util.Stack;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public class TrainDeck extends Deck<TrainCard> {

    @Override
    public void generate() {
        for(Color color : Color.values()) {

            int numberOfCards = color == Color.OPTIONAL ? 14 : 12;

            for (int i = 0; i < numberOfCards; i++) {
                super.items.add(new TrainCard(color));
            }

        }
    }

}
