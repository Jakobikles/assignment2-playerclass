package objects;

import java.util.List;

import Locations.*;

public class Board {
    private List<Location> sets;
    private CastingOffice office;
    private Trailers trailers;

    public Board (List<Location> sets){
        this.sets = sets;
    }

    public Board (){
        this.sets = null;
    }

    public List<Set> getAdjacentLocations(Set loc){
        return null;
    }

    public void highlightLocations(List<Set> locs){
        return;
    }

    public void displayUpdatedBoard(){
        return;
    }

    public void dealToSets(Deck deck){
        return;
    }

    public List<Location> getSets(){
        return this.sets;
    }

    public void setSets(List<Location> s){
        this.sets = s;
    }

    public CastingOffice getCastingOffice(){
        return this.office;
    }
    public void setCastingOffice(CastingOffice o){
        this.office = o;
    }

    public void addSet(Location s){
        this.sets.add(s);
    }

    public Trailers getTrailers(){
        return this.trailers;
    }

    public void setTrailers(Trailers t){
        this.trailers = t;
    }
}
