
package main.java.com.wumpvonquark.ttr.utilities;

import main.java.com.wumpvonquark.ttr.City;
import main.java.com.wumpvonquark.ttr.Route;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

    List<City> cities;
    List<City> cities2;

    public boolean compareRoute(Route routeToCheck, Route route) {
        cities = new ArrayList<>();
        cities.add(routeToCheck.getCity1());
        cities.add(routeToCheck.getCity2());
        cities2 = new ArrayList<>();
        cities2.add(route.getCity1());
        cities2.add(route.getCity2());

        if (isValidRouteDouble(routeToCheck))
            return false;

        if (!isCitiesEqual(cities, cities2))
            return false;

        if (routeToCheck.getColor() != route.getColor())
            return false;

        return true;
    }

    public boolean isCitiesEqual(List<City> cities, List<City> cities2) {
        return cities.containsAll(cities2);
    }

    private boolean isValidRouteDouble(Route route) {
        return getDoubles().contains(route);
    }

    private List<Route> getDoubles() {
        List<Route> doubleRoutes = new ArrayList<>();
        doubleRoutes.add(Route.LON_DIE_OPT1);
        doubleRoutes.add(Route.LON_DIE_OPT2);
        doubleRoutes.add(Route.ESS_KOB_OPT1);
        doubleRoutes.add(Route.ESS_KOB_OPT2);
        return doubleRoutes;
    }

    public boolean compareCities(List<City> cities, City city1, City city2) {
        return (cities.contains(city1) && cities.contains(city2));
    }
}
