package main.java.com.wumpvonquark.ttr;

/**
 * Created by Tomas Majling on 2016-06-20.
 */
public enum Route {

    EDI_LON_BLACK(City.EDINGBURGH, City.LONDON, 4, Color.BLACK, 0, false),
    EDI_LON_ORANGE(City.EDINGBURGH, City.LONDON, 4, Color.ORANGE, 0, false),
    LON_AMS(City.LONDON, City.AMSTERDAM, 2, Color.OPTIONAL, 2, false);

    private City city1;
    private City city2;
    private int length;
    private Color color;
    private int ferry;
    private boolean tunnel;

    Route(City city1, City city2, int length, Color color, int ferry, boolean tunnel) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.color = color;
        this.ferry = ferry;
        this.tunnel = tunnel;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getLength() {
        return length;
    }

    public Color getColor() {
        return color;
    }

    public int getFerry() {
        return ferry;
    }

    public boolean isTunnel() {
        return tunnel;
    }
}
