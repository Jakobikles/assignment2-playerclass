package objects;
import java.util.*;



public class Set {
    private String name;
    private ArrayList<Set> adjacentSets;
    private ArrayList<Scene> scenes;
    private int rehearsalChips;
    private boolean shootingCompleted;


    public Set (){

    }
    //implement later
    public List<Role> getAvailableRoles() {
        return null;
    }

    public boolean validateLocation() {
        return false;
    }

    public void removeFromSet(Player p){
        return;
    }

    public void clearRehearsalChips(){
        return;
    }

    public boolean checkEndDayCondition() {
        return false;
    }

    public void placeSceneCard(Scene s){
        return;
    }


    //getters
    public String getName(){
        return this.name;
    }

    public ArrayList<Set> getAdjacentSets(){
        return this.adjacentSets;
    }

    public ArrayList<Scene> getScenes(){
        return this.scenes;
    }

    public int getRehearsalChips(){
        return this.rehearsalChips;
    }

    public boolean isShootingComplete(){
        return shootingCompleted;
    }

    //setters

    public void setName(String n){
        this.name = n;
    }

    public void setAdjacentSets(ArrayList<Set> a){
        this.adjacentSets = a;
    }

    public void setScenes (ArrayList<Scene> s){
        this.scenes = s;
    }

    public void setRehearsalChips (int n){
        this.rehearsalChips = n;
    }

    public void setShootingComplete(boolean b){
        this.shootingCompleted = b;
    }
}
