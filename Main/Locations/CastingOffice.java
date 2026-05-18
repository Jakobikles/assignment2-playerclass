package Locations;
//import Action.*;
import objects.*;
import java.util.*;

public class CastingOffice extends Location {
    private Map<Integer, Cost> costMap;


    public CastingOffice (Map<Integer, Integer> credMap, Map<Integer, Integer> moneyMap){
        super("Casting Office");
    }
    public CastingOffice (){
        super("Casting Office");
        this.costMap = new HashMap<Integer, Cost>();
        for (int i = 2; i <= 6; i++){
            this.costMap.put(i, new Cost(i));
        }
    }

    //display all costs for ranks
    public void displayCostOptions(int r) {
        System.out.println("Your current rank: " + r);
        if (r == 6) {
            System.out.println("You are already at the maximum rank.");
            return;
        }
        System.out.println("Rank costs: ");
        for (int i = r + 1; i <= 6; i++) {
            Cost cost = this.costMap.get(i);
            System.out.println("Rank " + cost.getRank() + ": $" + cost.getDollarCost() + " or " + cost.getCreditCost() + " credits");
        }
    }

    public void setUpgradeCost(int level, String currency, int amount){
        if (currency.toLowerCase() == "dollar"){
            this.costMap.get(level).setDollarCost(amount);
        } else {
            this.costMap.get(level).setCreditCost(amount);
        }
    }

    public Cost getUpgradeCost(int level){
        return this.costMap.get(level);
    }

    public char returnType(){
        return 'c';
    }

    public class Cost {
        private int rank;
        private int dollarCost;
        private int creditCost;

        public Cost (int rank){
            this.rank = rank;
        }

        public Cost(int rank, int dollars, int credits) {
            this.rank = rank;
            this.dollarCost = dollars;
            this.creditCost = credits;
        }

        public int getRank() {
            return rank;
        }

        public int getDollarCost() {
            return dollarCost;

        }

        public int getCreditCost() {
            return creditCost;
        }

        public void setDollarCost(int dollarCost) {
            this.dollarCost = dollarCost;
        }
        
        public void setCreditCost(int creditCost) {
            this.creditCost = creditCost;
        }

    }

}
