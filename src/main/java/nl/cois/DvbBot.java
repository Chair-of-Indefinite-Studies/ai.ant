package nl.cois;

import start.Aim;
import start.Ants;
import start.Bot;
import start.Tile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DvbBot extends Bot {
    public static void main(String[] args) throws IOException {
        new DvbBot().readSystemInput();
    }

    private Map<Tile, Tile> orders = new HashMap<Tile, Tile>();

    @Override
    public void doTurn() {
        Ants ants = getAnts();
        orders.clear();

        for (Tile ant: ants.getMyAnts()){
            for (Aim direction: Aim.values()) {
                if (doMoveDirection(ant, direction)) {
                    break;
                }
            }

        }

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
