package main.java.com.wumpvonquark.ttr;

public class TrainSet extends Deck<TrainPiece> {

    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void generate() {

        TrainPiece tp = getTrainColor();

        for(int i = 0; i < Rules.numberOfStartTrains; i++) {
            super.items.add(tp);
        }
    }

    private TrainPiece getTrainColor() {
        for(TrainPiece tp : TrainPiece.values()) {
            if(tp.getColor().equals(color))
                return tp;
        }
        return null;
    }

}
