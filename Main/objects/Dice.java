package objects;
// Yzerman Scukanec, Jakob Wiley 
// Dice Class for Deadwood

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Dice {

    private int face;
    private Random roll;

    // dice object 
    public Dice () {
        this.face = 6;
        this.roll = new Random();
    }   

    // roll method 
    public int roll(int rank) {
        int total = 0;

        for(int i = 0; i < rank; i++){
            total += rollOne(); 
        }
        return total;
    }

    // bonus dice method
    public List<Integer> rollBonus(int budget) {
        List<Integer> results = new ArrayList<Integer>();
        for(int i = 0; i < budget; i++) {
            results.add(rollOne());
        }
        return results;
    }

    // rehearsal roll method
    public int addRehearsal(int baseRoll, int tokens) {
        return (baseRoll + tokens);
    }

    // rolls a single dice; returns [1, faces]
    private int rollOne() {
        return roll.nextInt(face) + 1;
    }

    // getter
    public int getFaces() {
        return this.face;
    }
}