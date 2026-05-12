package objects;

import java.util.List;

public class Deck {
    private List<Scene> cards;

    public Deck() {
       this.cards = null; 
    }
    public Deck(List<Scene> cards){
        this.cards = cards;
    }


    //randomize order of scene cards
    public void shuffle(){
        return;
    }


    //add scene cards to sets
    public void dealToSets(Board b){
        return;
    }

    public void removeRemainingCards(){
        return;
    }

    public List<Scene> getCards(){
        return this.cards;
    }

    public void setCards(List<Scene> l){
        this.cards = l;
    }

    public void addCard(Scene c){
        this.cards.add(c);
    }

    public int size(){
        return this.cards.size();
    }
}
