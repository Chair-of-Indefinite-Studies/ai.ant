package nl.cois.dvberkel;

import start.Ants;
import start.Tile;

public class Route implements Comparable<Route> {
    public final Tile start;
    public final Tile end;
    private final int distance;

    public Route(Tile start, Tile end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return start.hashCode() * Ants.MAX_MAP_SIZE * Ants.MAX_MAP_SIZE + end.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Route) {
            Route other = (Route) o;
            if (this.start.equals(other.start) && this.end.equals(other.end)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Route route) {
        return this.distance - route.distance;
    }
}
