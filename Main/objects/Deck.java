package objects;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import Locations.*;

public class Deck {
    private List<Scene> cards;

    public Deck() {
       this.cards = new ArrayList<Scene>(); 
    }
    public Deck(List<Scene> cards){
        this.cards = cards;
    }


    //randomize order of scene cards
    public void shuffle(){
        Collections.shuffle(this.cards);
        return;
    }


    //add scene cards to sets
    public void dealToSets(Board b){
        for (Location l : b.getLocations()){
            if (l.returnType() == 's'){
                Set s = (Set) l;
                if (this.cards.size() > 0){
                    s.setScene(this.cards.remove(0));
                }
            }
        }
        return;
    }


    //clear out the deck of cards
    public void removeRemainingCards(){
        this.cards.clear();
        return;
    }

    public List<Scene> getCards(){
        return this.cards;
    }

    public void setCards(ArrayList<Scene> l){
        this.cards = l;
    }

    public void addCard(Scene c){
        this.cards.add(c);
    }

    public int size(){
        return this.cards.size();
    }
}
