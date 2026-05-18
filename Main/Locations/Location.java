package Locations;
import java.util.*;
import objects.*;



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
    
    public void addPlayer (Player p){
        if (!this.PlayerList.contains(p)){
            this.PlayerList.add(p);
        }
        return;
    }

    public ArrayList<Player> getPlayerList(){
        return this.PlayerList;
    }

    public ArrayList<Location> getAdjacentLocations(){
        return this.adjacentLocations;
    }

    public void setAdjacentLocations(ArrayList<Location> a){
        this.adjacentLocations = a;
    }

    public String getName(){
        return this.name;
    }

    public abstract char returnType();

}
