package main.java.com.wumpvonquark.ttr.items;

import main.java.com.wumpvonquark.ttr.Color;

public enum StationPiece implements Item {

    STATION_BLACK(Color.BLACK),
    STATION_BLUE(Color.BLUE),
    STATION_GREEN(Color.GREEN),
    STATION_RED(Color.RED),
    STATION_YELLOW(Color.YELLOW);

    private Color color;

    StationPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
