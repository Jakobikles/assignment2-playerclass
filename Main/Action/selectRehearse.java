package Action;
import objects.*;

public class selectRehearse  implements Action {
    @Override
    public int performAction (Player p) {
        //check if player has role to rehearse; return 1 on success, 0 on failure
        if (p.getPlayerRole() != null){
            p.addRehearsalChip();
            return 1;
        }
        System.out.println("Player does not have role to rehearse.");
        return 0;
    }
}
