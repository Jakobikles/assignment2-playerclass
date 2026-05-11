package Action;
import objects.Player;
import objects.Bank;


public class getFinalMoney implements Action {
    @Override

    //return player final score
    public int performAction (Player p){
        return p.getMoney()+ p.getCredits() + (p.getRank() * 5);
    }
}
