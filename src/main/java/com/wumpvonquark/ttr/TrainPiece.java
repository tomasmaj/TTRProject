package main.java.com.wumpvonquark.ttr;

public enum TrainPiece implements Item {

    TRAIN_BLACK(Color.BLACK),
    TRAIN_BLUE(Color.BLUE),
    TRAIN_GREEN(Color.GREEN),
    TRAIN_RED(Color.RED),
    TRAIN_YELLOW(Color.YELLOW);

    private Color color;

    TrainPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
