package main.java.com.wumpvonquark.ttr;

import main.java.com.wumpvonquark.ttr.decks.StationSet;
import main.java.com.wumpvonquark.ttr.decks.TicketDeck;
import main.java.com.wumpvonquark.ttr.decks.TrainDeck;
import main.java.com.wumpvonquark.ttr.decks.TrainSet;
import main.java.com.wumpvonquark.ttr.items.StationPiece;
import main.java.com.wumpvonquark.ttr.items.TicketCard;
import main.java.com.wumpvonquark.ttr.items.TrainCard;
import main.java.com.wumpvonquark.ttr.items.TrainPiece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final Color color;
    private TrainSet trainSet;
    private TrainDeck trainDeck;
    private TicketDeck ticketDeck;
    private StationSet stationSet;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.trainDeck = new TrainDeck();
        this.ticketDeck = new TicketDeck();
        this.trainSet = new TrainSet();
        this.stationSet = new StationSet();
    }

    public String getName() {
        return name;
    }

    public TrainDeck getTrainDeck() {
        return trainDeck;
    }

    public TrainSet getTrainSet() {
        return trainSet;
    }

    public TicketDeck getTicketDeck() {
        return ticketDeck;
    }

    public StationSet getStationSet() {
        return stationSet;
    }

    public Color getColor() {
        return color;
    }

    public void addCardToTrainDeck(List<TrainCard> tc) {
        this.trainDeck.getAllItems().addAll(tc);
    }

    public void addCardToTicketDeck(List<TicketCard> tc) {
        this.ticketDeck.getAllItems().addAll(tc);
    }

    public void addTrainsToTrainSet(List<TrainPiece> tp) {
        this.trainSet.getAllItems().addAll(tp);
    }

    public void addStationsToStationSet(List<StationPiece> sp) {
        this.stationSet.getAllItems().addAll(sp);
    }
}
