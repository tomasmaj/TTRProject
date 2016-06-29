package main.java.com.wumpvonquark.ttr.decks;

import main.java.com.wumpvonquark.ttr.Color;
import main.java.com.wumpvonquark.ttr.items.TrainCard;

import java.util.ArrayList;
import java.util.List;

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

    public List<TrainCard> getCardsWithColor(Color color, int numberOfCards) {

        List<TrainCard> trainCards = new ArrayList<>();
        int counter = 0;

        for(TrainCard tc : super.items) {
            if(tc.getColor().equals(color)) {
                trainCards.add(tc);
                counter++;
            }
            if(numberOfCards == counter) {
                super.items.removeAll(trainCards);
                return trainCards;
            }
        }

        return null;
    }

}
