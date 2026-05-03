package Action;
import objects.Player;
public class getFinalMoney implements Action {
    @Override
    public int performAction (Player p){
        return p.getMoney()+ p.getCredits() + (p.getRank() * 5);
    }
}
