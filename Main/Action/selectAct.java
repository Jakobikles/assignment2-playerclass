
package Action;
import java.util.Random;

import objects.Player;
public class selectAct implements Action {
    @Override
    public int performAction (Player p){
        Random r = new Random();
        int diceroll = r.nextInt(6) + 1 + p.getRehearsalChips();
        if (p.getPlayerRole().getScene().getBudget() <= diceroll){ //success
            if (p.getPlayerRole().getisStar()){
                p.setCredits(p.getCredits() + 2) ;
                p.getPlayerRole().getScene().decrementShots();;
            } else {
                p.setCredits(p.getCredits() + 1);
                p.setMoney(p.getMoney()+1);
                p.getPlayerRole().getScene().decrementShots();
            }
        } else {
            if (!p.getPlayerRole().getisStar()){
                p.setMoney(p.getMoney()+1);
            }
        }
        return  p.getPlayerRole().getScene().getShotCounters();
    }
}
