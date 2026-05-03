//import java.util.Scanner;
//import java.util.Set;

//import javax.management.relation.Role;

//import java.util.Random;


public class Player{
    public String name;
    public int rank;
    public int money;
    public int credits;
    private int rehearsalChips;
    public Role playerRole;
    public Token token;

    public Player (String name) {
        this.name = name;
    }

    private int selectAction(Action action){
       return action.performAction(this);
    }

    public void addRehearsalChip (){
        this.rehearsalChips++;
    }

    public void clearRehearsalChips (){
        this.rehearsalChips = 0;
    }

    public int getRehearsalChips() {
        return this.rehearsalChips;
    }
    
    //returns 1 on success, 0 on failure
   
        

    }



