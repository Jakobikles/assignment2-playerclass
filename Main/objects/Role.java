package objects;
// Yzerman Scukanec, Jakob Wiley
// Role class for Deadwood

public class Role {

    private String name;
    private String line;
    private int rankRequirement;
    private boolean isStar;
    private Player assignedPlayer;
    private Scene scene;

    public Role (String partName, int level, boolean isStar){
        this.name = partName;
        this.rankRequirement = level;
        this.isStar = isStar;
    }

    // Checks rank requirement against player rank
    public boolean checkRankRequirement(int rank) {
        if (this.rankRequirement > rank) {
            return false;
        }
        return true;
    }

    public void assignPlayer(Player p) {
        this.assignedPlayer = p;
    }

    public boolean isOccupied() {
        return this.assignedPlayer != null;
    }

    public String getName() {
        return this.name;
    }

    public int getRankRequirement() {
        return this.rankRequirement;
    }

    public boolean getisStar() {
        return this.isStar;
    }

    public Player getAssignedPlayer() {
        return this.assignedPlayer;
    }

    public Scene getScene() {
        return this.scene;
    }

    public String getLine(){
        return this.line;
    }

    // setters
    public void setName(String newname) {
        this.name = newname;
    }

    public void setLine(String line){
        this.line = line;
    }

    public void setRankRequirement(int r) {
        this.rankRequirement = r;
    }

    public void setisStar(boolean b) {
        this.isStar = b;
    }

    public void setAssignedPlayer(Player p) {
        this.assignedPlayer = p;
    }

    public void setScene(Scene s) {
        this.scene = s;
    }
}