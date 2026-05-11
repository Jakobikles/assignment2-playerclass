package Action;
import objects.*;

import java.lang.reflect.AccessFlag.Location;
import java.util.Scanner;

import Locations.Set;

public class selectMove implements Action {
    @Override
    public int performAction (Player p) {
        int answer;
        Scanner scan = new Scanner(System.in);
        for (Locations.Location item : p.getToken().currentLocation.getadjacentLocations()){
            System.out.println(item);
        }
        System.out.println("Select the number of the desired set to move to (starting at 1)");
        try {
            answer = Integer.parseInt(scan.nextLine());
            if (answer > 0 && answer < p.getToken().currentLocation.getadjacentLocations().size()){
                p.getToken().moveToLocation(p.getToken().currentLocation.getadjacentLocations().get(answer));
                scan.close();
                return 1;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid number input.");
            scan.close();
            return 0;
        }
        System.out.println("Something unexpected happened.");
        scan.close();
        return 0;

        
            
    }
}
