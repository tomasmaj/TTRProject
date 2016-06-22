package main.java.com.wumpvonquark.ttr;

/**
 * Created by Tomas Majling on 2016-06-22.
 */
public enum GamePiece {

    TRAIN_BLACK(Color.BLACK),
    TRAIN_BLUE(Color.BLUE),
    TRAIN_GREEN(Color.GREEN),
    TRAIN_RED(Color.RED),
    TRAIN_YELLOW(Color.YELLOW),

    STATION_BLACK(Color.BLACK),
    STATION_BLUE(Color.BLUE),
    STATION_GREEN(Color.GREEN),
    STATION_RED(Color.RED),
    STATION_YELLOW(Color.YELLOW);

    private Color color;

    GamePiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
