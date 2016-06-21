package main.java.com.wumpvonquark.ttr;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomas Majling on 2016-06-20.
 */
public enum City {

    ATHINA, ANCORA, EDINGBURGH, ESSEN, KYIV, PARIS, WILNO, ZAGREB, BUDAPEST, SOFIA, BREST, VENEZIA,
    CONSTANTINOPLE, ZURICH, KHARKOV, MADRID, BERLIN, MOSKVA, ERZURUM, ROSTOV, LONDON, FRANKFURT,
    KOBENHAVN, PARLERMO, DIEPPE, AMSTERDAM, BRINDISI, SMOLENSK, FRANKFRUT, PETROGRAD, MARSEILLE, RIGA, BUCURESTI, WIEN,
    STOCKHOLM(KOBENHAVN, PETROGRAD);

    private City[] cities;

    City(City... cities) {
        this.cities = cities;
    }

    public City[] getCities() {
        return cities;
    }
}