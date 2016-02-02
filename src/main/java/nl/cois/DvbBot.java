package nl.cois;

import nl.cois.dvberkel.Route;
import start.Aim;
import start.Ants;
import start.Bot;
import start.Tile;

import java.io.IOException;
import java.util.*;

public class DvbBot extends Bot {
    public static void main(String[] args) throws IOException {
        new DvbBot().readSystemInput();
    }

    private Map<Tile, Tile> orders = new HashMap<Tile, Tile>();

    @Override
    public void doTurn() {
        orders.clear();
        Ants ants = getAnts();

        Map<Tile, Tile> foodTargets = new HashMap<Tile, Tile>();
        Set<Tile> sortedFoodTiles = new TreeSet<Tile>(ants.getFoodTiles());
        Set<Tile> sortedAntsTiles = new TreeSet<Tile>(ants.getMyAnts());
        PriorityQueue<Route> sortedRoutes = new PriorityQueue<Route>();
        for (Tile foodTile: sortedFoodTiles) {
            for (Tile ant: sortedAntsTiles) {
                int distance = ants.getDistance(foodTile, ant);
                Route route = new Route(ant, foodTile, distance);

                sortedRoutes.add(route);
            }
        }

        for (Route route: sortedRoutes) {
            if (!foodTargets.containsKey(route.end) && doMoveLocation(route.start, route.end)) {
                foodTargets.put(route.end, route.start);
            }
        }
    }

    private boolean doMoveLocation(Tile from, Tile to) {
        Ants ants = getAnts();

        for (Aim direction: ants.getDirections(from, to)) {
            if (doMoveDirection(from, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean doMoveDirection(Tile location, Aim direction) {
        Ants ants = getAnts();

        Tile nextLocation = ants.getTile(location, direction);

        if (ants.getIlk(nextLocation).isUnoccupied() && !orders.containsKey(nextLocation)) {
            ants.issueOrder(location, direction);
            orders.put(nextLocation, location);
            return true;
        }

        return false;
    }
}
