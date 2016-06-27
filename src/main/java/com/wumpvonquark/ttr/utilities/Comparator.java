
package main.java.com.wumpvonquark.ttr.utilities;

import main.java.com.wumpvonquark.ttr.City;
import main.java.com.wumpvonquark.ttr.Route;
import main.java.com.wumpvonquark.ttr.TicketCard;
import test.java.com.wumpvonquark.ttr.Item;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

    private List<City> cities1;
    private List<City> cities2;

    public boolean compareDoubles(Item item, Item item2) {
        if (item.getClass().equals(Route.class))
            return compareRoutes((Route)item, (Route)item2);
        else
            return compareTickets((TicketCard)item, (TicketCard)item2);
    }

    public boolean compareRoutes(Route routeToCheck, Route route) {
        cities1 = new ArrayList<>();
        cities1.add(routeToCheck.getCity1());
        cities1.add(routeToCheck.getCity2());
        cities2 = new ArrayList<>();
        cities2.add(route.getCity1());
        cities2.add(route.getCity2());

        if (isValidRouteDouble(routeToCheck))
            return false;
        if (!isCitiesEqual(cities1, cities2))
            return false;
        return routeToCheck.getColor() == route.getColor();
    }

    public boolean compareTickets(TicketCard ticket1, TicketCard ticket2) {
        cities1 = new ArrayList<>();
        cities1.add(ticket1.getStartCity());
        cities1.add(ticket1.getEndCity());
        cities2 = new ArrayList<>();
        cities2.add(ticket2.getStartCity());
        cities2.add(ticket2.getEndCity());

        return isCitiesEqual(cities1, cities2);
    }

    private boolean isValidRouteDouble(Route route) {
        return getDoubles().contains(route);
    }

    private boolean isCitiesEqual(List<City> cities, List<City> cities2) {
        return cities.containsAll(cities2);
    }

    private List<Route> getDoubles() {
        List<Route> doubleRoutes = new ArrayList<>();
        doubleRoutes.add(Route.LON_DIE_OPT1);
        doubleRoutes.add(Route.LON_DIE_OPT2);
        doubleRoutes.add(Route.ESS_KOB_OPT1);
        doubleRoutes.add(Route.ESS_KOB_OPT2);
        return doubleRoutes;
    }

//    public boolean compareCities(List<City> cities, City city1, City city2) {
//        return (cities.contains(city1) && cities.contains(city2));
//    }
}
