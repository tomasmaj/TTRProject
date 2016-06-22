package main.java.com.wumpvonquark.ttr;

public enum TicketCard {
    ATH_ANG(City.ATHINA, City.ANCORA, 5),
    ATH_WIL(City.ATHINA, City.WILNO, 11),
    EDI_PAR(City.EDINGBURGH, City.PARIS, 7),
    PAR_ZAG(City.PARIS, City.ZAGREB, 7),
    ESS_KYI(City.ESSEN, City.KYIV, 10),
    BUD_SOF(City.BUDAPEST, City.SOFIA, 5),
    BRE_VEN(City.BREST, City.VENEZIA, 8),
    VEN_CON(City.VENEZIA, City.CONSTANTINOPLE, 10),
    ZUR_BUD(City.ZURICH, City.BUDAPEST, 6),
    ANG_KHA(City.ANCORA, City.KHARKOV, 10),
    MAD_ZUR(City.MADRID, City.ZURICH, 8),
    BER_MOS(City.BERLIN, City.MOSKVA, 12),
    ROS_ERZ(City.ROSTOV, City.ERZURUM, 5),
    LON_BER(City.LONDON, City.BERLIN, 7),
    FRA_KOB(City.FRANKFURT, City.KOBENHAVN, 5),
    PAL_CON(City.PALERMO, City.CONSTANTINOPLE, 8),
    MAD_DIE(City.MADRID, City.DIEPPE, 8),
    AMS_WIL(City.AMSTERDAM, City.WILNO, 12),
    ZAG_BRI(City.ZAGREB, City.BRINDISI, 6),
    FRA_SMO(City.FRANKFURT, City.SMOLENSK, 13),
    KYI_PET(City.KYIV, City.PETROGRAD, 6),
    MAR_ESS(City.MARSEILLE, City.ESSEN, 8),
    RIG_BUC(City.RIGA, City.BUCURESTI, 10),
    PAR_WIE(City.PARIS, City.WIEN, 8);

    private City startCity;
    private City endCity;
    private int points;
    private boolean valid;

    TicketCard(City startCity, City endCity, int points) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.points = points;
        this.valid = false;
    }

    public City getStartCity() {
        return startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public int getPoints() {
        return points;
    }

    public boolean isValid() { return valid; }

    public void setValid(boolean valid) { this.valid = valid; }
}
