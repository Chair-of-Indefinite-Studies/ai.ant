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

        for (Tile hill: ants.getMyHills()) {
            orders.put(hill, null);
        }

        Map<Tile, Tile> foodTargets = new HashMap<Tile, Tile>();
        Set<Tile> foodTiles = new TreeSet<Tile>(ants.getFoodTiles());
        Set<Tile> antTiles = new TreeSet<Tile>(ants.getMyAnts());
        PriorityQueue<Route> bestRoutes = new PriorityQueue<Route>();
        for (Tile food: foodTiles) {
            for (Tile ant: antTiles) {
                int distance = ants.getDistance(food, ant);
                Route route = new Route(ant, food, distance);

                bestRoutes.add(route);
            }
        }

        for (Tile hill: ants.getMyHills()) {
            if (ants.getMyAnts().contains(hill) && !orders.containsValue(hill)) {
                for (Aim direction: Aim.values()) {
                    if (doMoveDirection(hill, direction)) {
                        break;
                    }
                }
            }

        }

        for (Route route: bestRoutes) {
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
