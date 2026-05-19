//import Action.*;
import objects.*;
import Locations.*;

public class DeadWood{
    public static void main(String[] args){
        GameManager gm = new GameManager();
        if (args.length == 0){
            System.out.println("USAGE: java DeadWood <number of players>");
            return;
        }
        else if (args.length == 1){
            int numPlayers = Integer.parseInt(args[0]);
            if (numPlayers < 2 || numPlayers > 8){
                System.out.println("Invalid number of players. Please enter a number between 2 and 8.");
                return;
            }
            gm.startNewGame(numPlayers);
        }
        else {
            System.out.println("Too many arguments. Please enter only the number of players.");
        }

        
}
}


