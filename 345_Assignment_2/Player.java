import java.util.Scanner;
import java.util.Random;


public class Player{
    public String name;
    public int rank;
    public int money;
    public int credits;
    public int rehearsalChips;
    public Role playerRole;
    public Token token;

    public Player (String name) {
        this.name = name;
    }

    private int selectAct(){
        Random r = new Random();
        int diceroll = r.nextInt(6) + 1 + this.rehearsalChips;
        if (this.playerRole.scene.budget <= diceroll){ //success
            if (this.playerRole.isStar){
                this.credits = this.credits + 2;
                this.playerRole.scene.shotCounters--;
            } else {
                this.credits++;
                this.money++;
                this.playerRole.scene.shotCounters--;
            }
        } else {
            if (!this.playerRole.isStar){
                this.money++;
            }
        }
        return  this.playerRole.scene.shotCounters;
    }
    private void selectMove(){
        int answer;
        Scanner scan = new Scanner(System.in);
        for (Set item : this.token.currentLocation.adjacentSets){
            System.out.println(item);
        }
        System.out.println("Select the number of the desired set to move to (starting at 1)");
        while (true){
        try {
            answer = Integer.parseInt(scan.nextLine());
            if (answer > 0 && answer < this.token.currentLocation.adjacentSets.size()){
                break;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid number input.");
        }

        }
        
        }
        
        this.token.moveToLocation();
    }

}
