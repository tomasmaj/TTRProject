package main.java.com.wumpvonquark.ttr;

/**
 * Created by Kza von Quark on 2016-06-20.
 */
public class Score {

    public int trainRouteScore(Route route) {
        int score = 0;
        switch (route.getLength()) {
            case 1:
                score = 1;
                break;
            case 2:
                score = 2;
                break;
            case 3:
                score = 4;
                break;
            case 4:
                score = 7;
                break;
            case 6:
                score = 15;
                break;
            case 8:
                score = 21;
                break;
            default: score = 0;
        }
        return score;
    }
}
