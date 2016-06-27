package main.java.com.wumpvonquark.ttr;

/**
 * Created by Tomas Majling on 2016-06-27.
 */
public class StationSet extends Deck<StationPiece> {

    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void generate() {

        StationPiece tp = getStationColor();

        for(int i = 0; i < Rules.numberOfStartStations; i++) {
            super.items.add(tp);
        }
    }

    private StationPiece getStationColor() {
        for(StationPiece sp : StationPiece.values()) {
            if(sp.getColor().equals(color))
                return sp;
        }
        return null;
    }

}
