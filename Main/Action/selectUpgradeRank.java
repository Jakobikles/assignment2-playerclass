package Action;
import objects.*;

import java.util.Scanner;

public class selectUpgradeRank implements Action  {
    @Override

    public int performAction(Player p){
        Scanner scan = new Scanner(System.in);
        int requestedRank;
        int[] cost = {4, 10, 18, 28, 40};
        
        System.out.print("Select desired rank 2-6: ");
        try {
            requestedRank = Integer.parseInt(scan.nextLine());
            if (requestedRank > 6 || requestedRank <= p.getRank()){
                System.out.println("Invalid rank request.");
                scan.close();
                return 0;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid number input.");
            scan.close();
            return 0;
        }

        System.out.println("Select currencym dollars ('d') or credits ('c'): ");
        char c = scan.nextLine().toLowerCase().charAt(0);
        scan.close();
        switch (c){
            case 'd':
                if (p.getMoney() >= cost[requestedRank-2]){
                    p.setMoney(p.getMoney() - cost[requestedRank-2]) ;
                    p.setRank(requestedRank);
                    return 1;
                } 
                System.out.println("Not enough money.");
                return 0;
            case 'c':
                if (p.getCredits() >= (requestedRank-1) * 5){
                    p.setCredits(p.getCredits() - ((requestedRank-1) * 5));
                    p.setRank(requestedRank);
                    return 1;
                }
                System.out.println("Not enough credits.");
                return 0;
        }

        scan.close();
        return 0;
    }
    }
