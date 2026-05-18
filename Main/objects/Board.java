package objects;

import java.util.List;
import java.util.ArrayList;
import Locations.*;

public class Board {
    private List<Location> sets;
    private CastingOffice office;
    private Trailers trailers;

    public Board (List<Location> sets){
        this.sets = sets;
    }

    public Board (){
        this.sets = new ArrayList<Location>();
    }

    public List<Location> getAdjacentLocations(Set loc){
        return loc.getAdjacentLocations();
    }

    public void highlightLocations(List<Location> locs){
        return;
    }

    //method to print out the board with all sets, their scenes, shot counters, adjacent locations, and players at each location
    public void displayUpdatedBoard(){
        for (Location l : this.sets){
            System.out.println(l.getName());
            if (l.returnType() == 's'){
                    Set s = (Set) l;
                    System.out.println("Scene: " + s.getScene().getName());
                    System.out.println("Shot Counters Remaining: " + String.valueOf(s.getShotCounters()));
            }
            System.out.println("Adjacent Locations: ");
            for (Location adj : l.getAdjacentLocations()){
                System.out.println(adj.getName());
            }
            System.out.println("Players at location: ");
            for (Player p : l.getPlayerList()){
                System.out.println("Player name: " + p.getName() + ", Role: " + p.getPlayerRole().getName());
            }
            l.getPlayerList().forEach(p -> System.out.println(p.getName()));
            System.out.println("------------------------------");
        }
        return;
    }
        public void addSet(Location s){
            this.sets.add(s);
    }

    public Location findSetByName(String name){
        for (Location s : this.sets){
            if (s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    //method to deal scene cards to sets at the beginning of each day
    public void dealToSets(Deck deck){
        deck.dealToSets(this);
        return;
    }

    //getters
    public List<Location> getLocations(){
        return this.sets;
    }
    public CastingOffice getCastingOffice(){
        return this.office;
    }
    public Trailers getTrailers(){
        return this.trailers;
    }

    //setters
    public void setLocations(List<Location> s){
        this.sets = s;
    }

    public void setCastingOffice(CastingOffice o){
        this.office = o;
    }

    public void setTrailers(Trailers t){
        this.trailers = t;
    }
}
