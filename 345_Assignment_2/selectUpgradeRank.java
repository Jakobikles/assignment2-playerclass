import java.util.Scanner;

public class selectUpgradeRank implements Action  {
    @Override

    public int performAction(Player p){
        Scanner scan = new Scanner(System.in);
        int requestedRank;
        char currency;
        int[] cost = {4, 10, 18, 28, 40};
        
        System.out.print("Select desired rank 2-6: ");
        try {
            requestedRank = Integer.parseInt(scan.nextLine());
            if (requestedRank > 6 || requestedRank <= p.rank){
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
                if (p.money >= cost[requestedRank-2]){
                    p.money = p.money - cost[requestedRank-2];
                    p.rank = requestedRank;
                    return 1;
                } 
                System.out.println("Not enough money.");
                return 0;
            case 'c':
                if (p.credits >= (requestedRank-1) * 5){
                    p.credits = p.credits - ((requestedRank-1) * 5);
                    p.rank = requestedRank;
                    return 1;
                }
                System.out.println("Not enough credits.");
                return 0;
        }

        scan.close();
        return 0;
    }
    }
