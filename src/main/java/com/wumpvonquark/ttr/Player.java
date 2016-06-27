package main.java.com.wumpvonquark.ttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Majling on 2016-06-27.
 */
public class Player {

    private final String name;
    private final Color color;
    private List<TrainPiece> trainSet;
    private List<TrainCard> trainDeck;
    private List<TicketCard> ticketDeck;
    private List<StationPiece> stationSet;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.trainDeck = new ArrayList<>();
        this.ticketDeck = new ArrayList<>();
        this.trainSet = new ArrayList<>();
        this.stationSet = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<TrainCard> getTrainDeck() {
        return trainDeck;
    }

    public List<TrainPiece> getTrainSet() {
        return trainSet;
    }

    public List<TicketCard> getTicketDeck() {
        return ticketDeck;
    }

    public List<StationPiece> getStationSet() {
        return stationSet;
    }

    public Color getColor() {
        return color;
    }

    public void addCardToTrainDeck(List<TrainCard> tc) {
        this.trainDeck.addAll(tc);
    }

    public void addCardToTicketDeck(List<TicketCard> tc) {
        this.ticketDeck.addAll(tc);
    }

    public void addTrainsToTrainSet(List<TrainPiece> tp) {
        this.trainSet.addAll(tp);
    }

    public void addStationsToStationSet(List<StationPiece> sp) {
        this.stationSet.addAll(sp);
    }
}
