package Locations;
//import Action.*;
//import objects.*;
import java.util.*;

public class CastingOffice extends Location {
    private Map<Integer, Integer> creditMap;
    private Map<Integer, Integer> dollarMap;

    public CastingOffice (Map<Integer, Integer> credMap, Map<Integer, Integer> moneyMap){
        super("Casting Office");
        this.creditMap = credMap;
        this.dollarMap = moneyMap;
    }
    public CastingOffice (){
        super("Casting Office");
        this.creditMap = new HashMap <Integer, Integer>();
        this.dollarMap = new HashMap <Integer, Integer>();
    }

    //display all costs for ranks
    public void displayCostOptions() {
        System.out.println("Rank costs in cash:");
        dollarMap.forEach((key,value) -> {
            System.out.println("Rank:" + key + ", Cost:" + value);
        });

        System.out.println("Rank costs in credits:");
        creditMap.forEach((key,value) -> {
            System.out.println("Rank:" + key + ", Cost:" + value);
        });
    }

    public void setUpgradeCost(int level, String currency, int amount){
        if (currency.toLowerCase() == "dollar"){
            this.dollarMap.put(level, amount);
        } else {
            this.creditMap.put(level, amount);
        }
    }

}
