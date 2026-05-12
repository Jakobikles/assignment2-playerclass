package Locations;
import Action.*;
import objects.*;
import java.util.*;



public abstract class Location {
    protected String name;
    protected ArrayList<Location> adjacentLocations;
    protected ArrayList<Player> PlayerList;


    //constructor
    public Location(String s, ArrayList<Location> l) {
        this.name = s;
        this.adjacentLocations = l;
        this.PlayerList = new ArrayList<Player>();
    }
    public Location(String s) {
        this.name = s;
        this.adjacentLocations = new ArrayList<Location>();
        this.PlayerList = new ArrayList<Player>();
    }

    //add a neighbor location
    public void addNeighbor (Location l){
        if (!this.adjacentLocations.contains(l)){
            this.adjacentLocations.add(l);
        }
        
    }
    public void removeFromLocation (Player p){
        if (this.PlayerList.contains(p)){
            this.PlayerList.remove(p);
        }
        return;
    }

    public void addAdjacentSet(Location l){
        this.adjacentLocations.add(l);
    }

    public boolean validateLocation (){
        return false;
    }

    public ArrayList<Location> getadjacentLocations(){
        return this.adjacentLocations;
    }

    public void setadjacentLocations(ArrayList<Location> a){
        this.adjacentLocations = a;
    }

}
