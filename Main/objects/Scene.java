// Yzerman Scukanec, Jakob Wiley 
// Scene class for Deadwood
package objects;
import Locations.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {

    private int id;
    private String name;
    private String desc;
    private int budget;
    private int remainingBudget;
    private List<Role> starRoles;
    private boolean revealed;
    private Set set;

    public Scene(int id, String name, int budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.remainingBudget = budget;
        this.starRoles = new ArrayList<>();
        this.revealed = false;
    }

    // deincrements remaining budget by 1
    public void removeBudgetDice() {
        if (this.remainingBudget > 0) {
            this.remainingBudget--;
        }
    }

    // checks remaining budget dice on card
    public int checkRemainingBudget() {
        return this.remainingBudget;
    }
    //assign scene card to a set
    public void placeCard(Set s){
        this.set = s;
    }

    // flips when player takes a starRole
    public void reveal() {
        this.revealed = true;
    }

    // Setters
    public void addStarRole(Role r) {
        starRoles.add(r);
    }

    // Getters
    public int getBudget() {
        return this.budget;
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

    public Set getSet(){
        return this.set;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public String getDescription(){
        return this.desc;
    }

    public void setDescription(String s){
        this.desc = s;
    }
}