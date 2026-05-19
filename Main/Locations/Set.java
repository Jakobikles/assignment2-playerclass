package Locations;
import java.util.*;

import objects.*;




public class Set extends Location {
    //private String name;
    //private ArrayList<Set> adjacentLocations;
    private static int activeSets = 10;
    private ArrayList<Role> offCardRoles;
    private Scene scene;
    private boolean shootingCompleted;
    private int origShotCounters;
    private int shotCounters;


    public Set (String n, ArrayList<Location> l, int shots, ArrayList<Role> roles){
        super(n, l);
        this.scene = null;
        this.origShotCounters = shots;
        this.shotCounters = shots;
        this.offCardRoles = roles;
    }

    public Set (String n, int shots, ArrayList<Role> roles){
        super(n);
        this.scene = null;

        this.origShotCounters = shots;
        this.shotCounters = shots;
        this.offCardRoles = roles;

    }

    public Set (String n){
        super(n);
        this.scene = null;
        this.offCardRoles = new ArrayList<Role>();
    }


    public void decrementShots() {
        this.shotCounters--;
        if (this.shotCounters == 0) {
            this.shootingCompleted = true;
        }
    }

    //decrements number of current active sets


    //method to wrap set when shot counters reach 0
    public void wrap(){
        // for (Player p: this.PlayerList){
        //     p.setRehearsalChips(0);
        // }
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

    public void addSideRole(Role r){
        this.offCardRoles.add(r);
    }


    //getters

    public List<Role> getAvailableRoles() {
        return this.offCardRoles;
    }
    public Scene getScene(){
        return this.scene;
    }



    public int getShotCounters() {
        return shotCounters;
    }

    public boolean isShootingComplete(){
        return shootingCompleted;
    }

    public List<Role> getSideRoles(){
        return this.offCardRoles;
    }

    public static int getActiveSets(){
        return activeSets;
    }

    //setters

    public void setSideRoles(ArrayList<Role> r){
        this.offCardRoles = r;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setShotCounters(int i){
        this.shotCounters = i;
    }

    public void setScene (Scene s){
        this.scene = s;
    }

    public void setShootingComplete(boolean b){
        this.shootingCompleted = b;
    }

    public char returnType(){
        return 's';
    }
}
