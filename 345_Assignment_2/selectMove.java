import java.util.Scanner;

public class selectMove implements Action {
    @Override
    public int performAction (Player p) {
        int answer;
        Scanner scan = new Scanner(System.in);
        for (Set item : p.token.currentLocation.adjacentSets){
            System.out.println(item);
        }
        System.out.println("Select the number of the desired set to move to (starting at 1)");
        try {
            answer = Integer.parseInt(scan.nextLine());
            if (answer > 0 && answer < p.token.currentLocation.adjacentSets.size()){
                p.token.moveToLocation(p.token.currentLocation.adjacentSets.get(answer));
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
