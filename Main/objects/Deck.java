package objects;

import java.util.List;

public class Deck {
    private List<Scene> cards;

    public Deck(List<Scene> cards){
        this.cards = cards;
    }

    public void shuffle(){
        return;
    }

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
}
