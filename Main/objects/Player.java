package objects;


import Action.*;

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
    }

    public int selectAction(Action action){
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
    
    //getters
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

    public void setName(String name){
        this.name = name;
    }
   
    public void getRank(int rank) {
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

    public void setRank(int n){
        this.rank = n;
    }

    }



