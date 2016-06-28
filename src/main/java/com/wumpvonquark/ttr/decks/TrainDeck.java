package main.java.com.wumpvonquark.ttr.decks;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

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
