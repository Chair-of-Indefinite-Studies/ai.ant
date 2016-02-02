package nl.cois;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import start.Bot;
import start.Ants;
import start.Tile;
import start.Aim;

import nl.cois.caecus.Ant;

/**
 * Starter bot implementation.
 */
public class Caecus extends Bot {
    /**
     * Main method executed by the game engine for starting the bot.
     * 
     * @param args command line arguments
     * 
     * @throws IOException if an I/O error occurs
     */

    public static void main(String[] args) throws IOException {
        new Caecus().readSystemInput();
    }
    

    @Override
    public void doTurn() {
        Ants ants = getAnts();

        ArrayList<Ant> myAnts = new ArrayList<Ant>();

        for(Tile a : ants.getMyAnts()) {
            myAnts.add(new Ant(ants, a));
        }

        Set<Tile> foodTiles = ants.getFoodTiles();

        for(Ant a: myAnts) {
            if(a.getTarget() == null) {
                int closestFoodTileDistance = -1;
                Tile closestFoodTile = a.getLocation();

                for(Tile foodTile : foodTiles) {
                    if(closestFoodTileDistance == -1) {
                        closestFoodTileDistance = ants.getDistance(a.getLocation(), foodTile);
                        closestFoodTile = foodTile;
                        continue;
                    }
                    int distanceFromAnt = ants.getDistance(a.getLocation(), foodTile);
                    if(distanceFromAnt < closestFoodTileDistance) {
                        closestFoodTileDistance = distanceFromAnt;
                        closestFoodTile = foodTile;
                        continue;
                    }

                }

                if(closestFoodTile != null) {
                    a.setTarget(closestFoodTile);

                }

            }
            if(a.getTarget() != null) {
                try {
                    foodTiles.remove(a.getTarget());
                } catch (Exception E) {
                    //don't care
                }
            }

            Aim order = a.getOrder();

            if(order != null) {
                ants.issueOrder(a.getLocation(), order);
            }
            if(a.reachedTarget()) {
                a.setTarget(null);
            }



        }
    }
}

