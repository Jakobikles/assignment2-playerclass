package Action;
import objects.Player;


public class getFinalMoney implements Action {
    @Override

    //return player final score
    public int performAction (Player p){
        return p.getMoney()+ p.getCredits() + (p.getRank() * 5);
    }
}
