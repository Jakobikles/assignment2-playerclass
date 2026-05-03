package objects;

public class Role {
    private String name;
    private int rankRequirement;
    private boolean isStar;
    private Player assignedPlayer;
    private Scene scene;

    public boolean checkRankRequirement (int rank){
        if (this.rankRequirement > rank){
            return false;
        }
        return true;
    }

    //implement later
    public void assignPlayer(Player p){
        return;
    }

    //implement later
    public boolean isOccupied() {
        return false;
    }

    public String getName(){
        return this.name;
    }

    public int getRankRequirement(){
        return this.rankRequirement;
    }

    public boolean getisStar(){
        return this.isStar;
    }

    public Player getAssignedPlayer(){
        return this.assignedPlayer;
    }

    public Scene getScene(){
        return this.scene;
    }

    //setters

    public void setName(String newname){
        this.name = newname;
    }

    public void setRankRequirement(int r){
        this.rankRequirement = r;
    }

    public void setisStar(boolean b){
        this.isStar = b;
    }

    public void setAssignedPlayer(Player p){
        this.assignedPlayer = p;
    }

    public void setScene(Scene s){
        this.scene = s;
    }
}
