package objects;
// Yzerman Scukanec, Jakob Wiley 
// Scene class for Deadwood

//import java.util.ArrayList;

import javax.management.relation.Role;

import java.util.List;

public class Scene {

    private int id;
    private String name;
    private int budget;
    private int shotCounters;
    private int remainingBudget;
    private List<Role> starRoles;
    private boolean revealed;

    public Scene(int id, String name, int budget, int remainingBudget, List<Role> starRoles, int shotCounters){
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.remainingBudget = remainingBudget;
        this.starRoles = starRoles;
        this.revealed = false;
        this.shotCounters = shotCounters;
    }

    // deincrements remaining budget by 1
    public void removeBudgetDice(){
        // COME BACK TO IMPLEMENT
    }

    // checks remaining budget dice on card
    public int checkRemainingBudget() {
        // COME BACK TO IMPLEMENT
        return 0;
    }

    // flips when player takes a starRole
    public void reveal() {
        // COME BACK TO IMPLEMENT
    }

    public void decrementShots() {
        this.shotCounters--;
    }


    // Setters
    public void addStarRole(Role r) {
        starRoles.add(r);
    }


    // Getters
    public int getBudget() {
        // COME BACK TO IMPLEMENT
        return budget;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRemainingBudget() {
        return remainingBudget;
    }

    public List<Role> getStarRoles() {
        return starRoles;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public int getShotCounters() {
        return shotCounters;
    }

}