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
    
    /**
     * For every ant check every direction in fixed order (N, E, S, W) and move it if the tile is
     * passable.
     */
    @Override
    public void doTurn() {
        Ants ants = getAnts();

        ArrayList<Ant> myAnts = new ArrayList<Ant>();

        for(Tile a : ants.getMyAnts()) {
            myAnts.add(new Ant(a));
        }

        Set<Tile> foodTiles = ants.getFoodTiles();
        
    }
}

