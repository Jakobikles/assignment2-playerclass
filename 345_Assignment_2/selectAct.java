import java.util.Random;

public class selectAct implements Action {
    @Override
    public int performAction (Player p){
        Random r = new Random();
        int diceroll = r.nextInt(6) + 1 + p.getRehearsalChips();
        if (p.playerRole.scene.budget <= diceroll){ //success
            if (p.playerRole.isStar){
                p.credits = p.credits + 2;
                p.playerRole.scene.shotCounters--;
            } else {
                p.credits++;
                p.money++;
                p.playerRole.scene.shotCounters--;
            }
        } else {
            if (!p.playerRole.isStar){
                p.money++;
            }
        }
        return  p.playerRole.scene.shotCounters;
    }
}
