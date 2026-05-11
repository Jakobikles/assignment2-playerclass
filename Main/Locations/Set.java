package Locations;
import java.util.*;

import objects.Player;
import objects.Role;
import objects.Scene;



public class Set extends Location {
    //private String name;
    //private ArrayList<Set> adjacentLocations;
    private static int activeSets;
    private ArrayList<Role> offCardRoles;
    private Scene scene;
    private int rehearsalChips;
    private boolean shootingCompleted;
    private int origShotCounters;
    private int shotCounters;


    public Set (String n, ArrayList<Location> l, int r, int shots, ArrayList<Role> roles){
        super(n, l);
        this.scene = null;
        this.rehearsalChips = r;
        this.origShotCounters = shots;
        this.shotCounters = shots;
        this.offCardRoles = roles;
    }

    public Set (String n, int chips, int shots, ArrayList<Role> roles){
        super(n);
        this.scene = null;
        this.rehearsalChips = chips;
        this.origShotCounters = shots;
        this.shotCounters = shots;
        this.offCardRoles = roles;
    }

    public List<Role> getAvailableRoles() {
        return this.offCardRoles;
    }

    public boolean validateLocation() {
        return false;
    }

    public void decrementShots() {
        this.shotCounters--;
    }

    //method to wrap set when shot counters reach 0
    public void clearRehearsalChips(){
        for (Player p: this.PlayerList){
            p.setRehearsalChips(0);
        }
        this.shootingCompleted = true;
        activeSets--;
        return;
    }
    //returns true if only one remaining set
    public boolean checkEndDayCondition() {
        if (activeSets <= 1){
            return true;
        }
        return false;
    }

    public void placeSceneCard(Scene s){
        this.scene = s;
        this.shootingCompleted = false;
        this.shotCounters = this.origShotCounters;
        activeSets++;
        return;
    }


    //getters
    public String getName(){
        return this.name;
    }



    public Scene getScene(){
        return this.scene;
    }

    public int getRehearsalChips(){
        return this.rehearsalChips;
    }

    public int getShotCounters() {
        return shotCounters;
    }

    public boolean isShootingComplete(){
        return shootingCompleted;
    }

    //setters

    public void setName(String n){
        this.name = n;
    }



    public void setScene (Scene s){
        this.scene = s;
    }

    public void setRehearsalChips (int n){
        this.rehearsalChips = n;
    }

    public void setShootingComplete(boolean b){
        this.shootingCompleted = b;
    }
}
