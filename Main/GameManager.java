import objects.*;
import Action.*;
import java.util.List;

public class GameManager {
    private int currentDay;
    private int totalDays;
    private List<Player> players;
    private int currentPlayerIndex;

    public GameManager(){
        
    }

    public void startNewGame(){
        return;
    }

    public int promptPlayerCount(){
        return 0;
    }

    public void assignStartingResources(){
        return;
    }

    public void determineStartingPlayer(){
        return;
    }

    public boolean detectOneSceneRemaining(){
        return false;
    }

    public void triggerWrapScene(){
        return;
    }

    public void triggerEndDay(){
        return;
    }

    public void triggerTallyScore(){
        return;
    }

    public void incrementDayCounter(){
        return;
    }

    public void announceNewDay(){
        return;
    }

    public int getCurrentDay(){
        return this.currentDay;
    }

    public int getTotalDays(){
        return this.totalDays;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public int getCurrentPlayerIndex(){
        return this.currentPlayerIndex;
    }

    public void setCurrentDay(int i){
        this.currentDay = i;
    }

    public void setTotalDays(int i){
        this.totalDays = i;
    }

    public void setPlayers(List<Player> p){
        this.players = p;
    }

    public void currentPlayerIndex(int i){
        this.currentPlayerIndex = i;
    }
}
