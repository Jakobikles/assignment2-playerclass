package objects;

import java.util.List;

public class Board {
    private List<Set> sets;

    public Board (List<Set> sets){
        this.sets = sets;
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

    public List<Set> getSets(){
        return this.sets;
    }

    public void setSets(List<Set> s){
        this.sets = s;
    }
}
