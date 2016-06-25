
package main.java.com.wumpvonquark.ttr.utilities;

import main.java.com.wumpvonquark.ttr.City;
import main.java.com.wumpvonquark.ttr.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteComparator {

    List<City> cities;

    public boolean compare(Route routeToCheck, Route route) {
        cities = new ArrayList<>();
        cities.add(routeToCheck.getCity1());
        cities.add(routeToCheck.getCity2());
        if (isValidRouteDouble(routeToCheck))
            return false;

        if (!(cities.contains(route.getCity1()) && cities.contains(route.getCity2())))
            return false;

        if (routeToCheck.getColor() != route.getColor())
            return false;

        return true;
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
}
