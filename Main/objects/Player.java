package objects;


//import Action.*;

public class Player{
    private String name;
    private int rank;
    private int money;
    private int credits;
    private int rehearsalChips;
    private Role playerRole;
    private Token token;

    public Player (String name) {
        this.name = name;
        this.rank = 1;
        this.money = 0;
        this.credits = 0;
        this.rehearsalChips = 0;
        this.playerRole = null;
        this.token = new Token();
    }

    //player carries out an action on their turn
    // public int selectAction(Action action){
    //    return action.performAction(this);
    // }

    //player gains rehearsal chip
    public void addRehearsalChip (){
        this.rehearsalChips++;
    }

    public void displayInfo(){
        System.out.println("Player: " + this.name);
        System.out.println("Rank: " + this.rank);
        System.out.println("Money: " + this.money);
        System.out.println("Credits: " + this.credits);
        if (this.playerRole != null) {
            System.out.println("Current Role: " + this.playerRole.getName());
            System.out.println("Rehearsal Chips: " + this.rehearsalChips);
        } else {
            System.out.println("Current Role: None");
        }
    }

    public int computeFinalScore() {
        return this.rank * 5 + this.money + this.credits;
    }



    //getters
    public int getRehearsalChips() {
        return this.rehearsalChips;
    }
    
    public String getName(){
        return this.name;
    }
   
    public int getRank() {
        return this.rank;
    }
    
    public int getMoney(){
        return this.money;
    }

    public int getCredits(){
        return this.credits;
    }
    
    public Role getPlayerRole(){
        return this.playerRole;
    }

    public Token getToken() {
        return this.token;
    }

    //setters
    public void setRehearsalChips(int r){
        this.rehearsalChips = r;
    }

    public void setName(String name){
        this.name = name;
    }
   
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public void setMoney(int money){
        this.money = money;
    }

    public void setCredits(int Credits){
        this.credits = Credits;
    }
    
    public void setPlayerRole(Role r){
        this.playerRole = r;
    }

    public void setToken(Token t) {
        this.token = t;
    }


    }



