package objects;
// Yzerman Scukanec, Jakob Wiley
// Bank Class for Deadwood

import java.util.List;

public class Bank {
    private int totalMoney;

    // Constructor
    public Bank() {
        this.totalMoney = (Integer.MAX_VALUE / 2);
    }

    // Pays the player
    public void awardPay(Player player, int amount) {
        if(amount < 0) {
            System.out.println("[Bank] Warning: Negative Payment Ignored");
            return;
        }

        player.setMoney(player.getMoney() + amount);
        totalMoney -= amount;
        System.out.println("[Bank] Awarded $" + amount + " to " + player.getName() +
        ". New Balance: $" + player.getMoney());
    }

    // Awards a $1 consolation payment
    public void awardConsolation(Player player) {
        awardPay(player, 1);
        System.out.println("[Bank]: Consolation Prize Paid to " + player.getName());
    }

    // Deducts payment from the player for an upgrade
    public boolean deductPayment(Player player, int amount, boolean credits) {
        if(credits) {
            if(player.getCredits() < amount) {
                System.out.println("[Bank]: " + player.getName() + " Has Insufficient Credits ("
                + player.getCredits() + " < " + amount + ").");
                return false;
            }

            player.setCredits(player.getCredits() - amount);
            System.out.println("[Bank]: Deducted " + amount + " Credits From " +
            player.getName() + ". Remaining Credits: " + player.getCredits());
        }

        else {
            if(player.getMoney() < amount) {
                System.out.println("[Bank]: " + player.getName() + 
                " Has Insufficient Funds ($ " + player.getMoney() +
                " < $" + amount + ").");
                return false;
            }

            player.setMoney(player.getMoney() - amount);
            totalMoney += amount;
            System.out.println("[Bank]: Deducted $" + amount + " from " +
            player.getName() + " Remaining: $" + player.getMoney());
        }
        return true;
    }

    // Distributes bonus dice results to starring players
    public void distributeStarPayouts(List<Player> starPlayers, List<Integer> rolls) {
        if(starPlayers == null || starPlayers.isEmpty()) {
            System.out.println("[Bank]: No Starring Role Players, Skipping Bonus Payouts.");
            return;
        }
        rolls.sort((a,b) -> b - a);
        System.out.println("[Bank]: Distributing Wrap Bonus Among " + starPlayers.size() + 
        " Starring Player(s).");

        for(int i = 0; i < rolls.size(); i++) {
            Player receiver = starPlayers.get(i % starPlayers.size());
            awardPay(receiver, rolls.get(i));
        }
    }

    // Awards $1 payments to player in side roles
    public void awardSidePay(List<Player> sidePlayers) {
        if(sidePlayers == null || sidePlayers.isEmpty()) {
            return;
        }

        System.out.println("[Bank]: Awarding Side Role Wrap Payments.");
        for(Player p : sidePlayers) {
            awardPay(p, 1);
        }
    }


    // Converts money to credits
    public int convertM2C(int money) {
        return (money / 2);
    }

    // Getters
    public int getTotalMoney() {
        return totalMoney;
    }
}