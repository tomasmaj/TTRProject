package main.java.com.wumpvonquark.ttr;

import sun.util.resources.cldr.fur.CalendarData_fur_IT;

import javax.net.ssl.CertPathTrustManagerParameters;

/**
 * Created by Tomas Majling on 2016-06-20.
 */
public enum Route {

    EDI_LON_BLACK(City.EDINGBURGH, City.LONDON, 4, Color.BLACK, 0, false),
    EDI_LON_ORANGE(City.EDINGBURGH, City.LONDON, 4, Color.ORANGE, 0, false),
    LON_AMS(City.LONDON, City.AMSTERDAM, 2, Color.OPTIONAL, 2, false),
    LON_DIE_OPT1(City.LONDON, City.DIEPPE, 2, Color.OPTIONAL, 1, false),
    LON_DIE_OPT2(City.LONDON, City.DIEPPE, 2, Color.OPTIONAL, 1, false),
    AMS_ESS(City.AMSTERDAM, City.ESSEN, 3, Color.YELLOW, 0, false),
    AMS_BRU(City.AMSTERDAM, City.BRUXELLES, 1, Color.BLACK, 0, false),
    AMS_FRA(City.AMSTERDAM, City.FRANKFURT, 2, Color.WHITE, 0, false),
    DIE_BRE(City.DIEPPE, City.BREST, 2, Color.ORANGE, 0, false),
    DIE_BRU(City.DIEPPE, City.BRUXELLES, 2, Color.GREEN, 0,false),
    DIE_PAR(City.DIEPPE, City.PARIS, 1, Color.PINK, 0, false),
    BRU_PAR_YELLOW(City.BRUXELLES, City.PARIS, 2, Color.YELLOW, 0, false),
    BRU_PAR_RED(City.BRUXELLES, City.PARIS, 2, Color.RED, 0, false),
    BRU_FRA(City.BRUXELLES, City.FRANKFURT, 2, Color.BLUE, 0, false),
    BRE_PAR(City.BREST, City.PARIS, 3, Color.BLACK, 0, false),
    BRE_PAM(City.BREST, City.PAMPLONA, 4, Color.PINK, 0, false),
    PAR_PAM_BLUE(City.PARIS, City.PAMPLONA, 4, Color.BLUE, 0, false),
    PAR_PAM_GREEN(City.PARIS, City.PAMPLONA, 4, Color.GREEN, 0, false),
    PAR_MAR(City.PARIS, City.MARSEILLE, 4, Color.OPTIONAL, 0, false),
    PAR_ZUR(City.PARIS, City.ZURICH, 3, Color.OPTIONAL, 0, true),
    PAR_FRA_WHITE(City.PARIS, City.FRANKFURT, 3, Color.WHITE, 0, false),
    PAR_FRA_ORANGE(City.PARIS, City.FRANKFURT, 3, Color.ORANGE, 0, false),
    PAM_MAD_BLACK(City.PAMPLONA, City.MADRID, 3, Color.BLACK, 0, true),
    PAM_MAD_WHITE(City.PAMPLONA, City.MADRID, 3, Color.WHITE, 0, true),
    PAM_BAR(City.PAMPLONA, City.BARCELONA, 2, Color.OPTIONAL, 0, true),
    PAM_MAR(City.PAMPLONA, City.MARSEILLE, 4, Color.RED, 0, false),
    MAD_LIS(City.MADRID, City.LISBOA, 3, Color.PINK, 0, false),
    MAD_CAD(City.PAMPLONA, City.CADIZ, 3, Color.ORANGE, 0, false),
    MAD_BAR(City.MADRID, City.BARCELONA, 2, Color.YELLOW, 0, false),
    LIS_CAD(City.LISBOA, City.CADIZ, 2, Color.BLUE, 0, false),
    BAR_MAR(City.BARCELONA, City.MARSEILLE, 4, Color.OPTIONAL, 0, false),
    ESS_KOB_OPT1(City.ESSEN, City.KOBENHAVN, 3, Color.OPTIONAL, 1, false),
    ESS_KOB_OPT2(City.ESSEN, City.KOBENHAVN, 3, Color.OPTIONAL, 1, false),
    ESS_BER(City.ESSEN, City.BERLIN, 2, Color.BLUE, 0, false);

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
