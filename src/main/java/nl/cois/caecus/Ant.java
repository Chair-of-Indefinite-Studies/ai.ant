package nl.cois.caecus;

import start.Aim;
import start.Ants;
import start.Ilk;
import start.Tile;

public class Ant {
	Tile location;
    Tile target;
    Ants ants;
	public Ant(Ants ants, Tile location) {
        this.ants = ants;
		this.location = location;
	}

	public void setLocation(Tile location) {
		this.location = location;
	}

    public boolean canIGoThere(Ilk i) {
        if(i != null && i.isPassable() && i.isUnoccupied()) {
            return true;
        }
        return false;
    }

    public Aim getOrder() {
        if(target != null) {
            for(Aim a : ants.getDirections(location, target)) {
                if(canIGoThere(ants.getIlk(location, a))) {
                    return a;
                }
            }
            return null;
        } else {
            return getASimpleDirection();
        }
    }

    public Tile getLocation() {
        return location;
    }

    public Tile getTarget() {
        return target;
    }

    public void setTarget(Tile target) {
        this.target = target;
    }

    public Aim getASimpleDirection() {

        Ilk east = ants.getIlk(location, Aim.EAST);
        Ilk north = ants.getIlk(location, Aim.NORTH);
        Ilk west = ants.getIlk(location, Aim.WEST);
        Ilk south = ants.getIlk(location, Aim.SOUTH);

        if(canIGoThere(north)) {
            return Aim.NORTH;
        }

        else if(canIGoThere(west)) {
            return Aim.WEST;
        }

        else if(canIGoThere(south)) {
            return Aim.SOUTH;
        }

        else if(canIGoThere(east)) {
            return Aim.EAST;
        }

        return null;
    }

    public boolean reachedTarget() {
        if(location.equals(target)) {
            return true;
        }
        return false;
    }
}