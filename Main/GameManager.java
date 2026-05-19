// Yzerman Scukanec, Jakob Wiley
// Game Manager for Deadwood

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import objects.*;
import ParseXML.*;
import Locations.*;
//import Action.*;

public class GameManager {

    private Board board;
    private Deck deck;
    private Bank bank;
    private Dice dice;
    private List<Player> players;
    private int currentPlayerIndex;
    private int currentDay;
    private int totalDays;
    private Scanner scanner;
    private boolean gameOver;

    public GameManager() {
        this.bank = new Bank();
        this.dice = new Dice();
        this.players = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.gameOver = false;
    }

    public void startNewGame(int numPlayers) {
        // Parse board and deck from xml file
        XMLParser parser = new XMLParser();
        this.board = parser.parseBoard("board.xml");
        this.deck = parser.parseDeck("cards.xml");

        if (board == null || deck == null) {
            System.out.println("Could not load game data. Exiting.");
            return;
        }

        setupPlayers(numPlayers);
        assignStartingResources(numPlayers);
        totalDays = computeTotalDays(numPlayers);
        currentDay = 1;
        startDay();

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║     Welcome to  D E A D W O O D  ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("Players: " + numPlayers
                + "  |  Days: " + totalDays + "\n");
        determineStartingPlayer();
        runGameLoop();
    }

    // Prompts for player names and creates players
    private void setupPlayers(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for player " + i + " (press Enter for 'Player " + i + "'): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty())
                name = "Player " + i;
            players.add(new Player(name));
        }
    }

    private void assignStartingResources(int numPlayers) {
        for (Player p : players) {
            if (numPlayers == 4) {
                p.setCredits(2);
            } else if (numPlayers == 5) {
                p.setMoney(2);
            } else if (numPlayers >= 6) {
                p.setMoney(4);
            }
            p.getToken().moveToLocation(board.getTrailers());
        }
    }

    // Randomly picks which player goes first
    private void determineStartingPlayer() {
        currentPlayerIndex = (int) (Math.random() * players.size());
        System.out.println(players.get(currentPlayerIndex).getName() + " goes first!\n");
    }

    // Determines the num days based off player count
    private int computeTotalDays(int numPlayers) {
        return (numPlayers <= 3) ? 3 : 4;
    }

    // Iterates player turns until all days are complete or quit is issued
    private void runGameLoop() {
        while (!gameOver) {
            Player active = players.get(currentPlayerIndex);
            System.out.println("── " + active.getName() + "'s turn ──");
            runPlayerTurn(active);

            if (!gameOver) {
                advanceTurn();
            }
        }
        tallyFinalScores();
    }

    private void runPlayerTurn(Player player) {
        boolean turnOver = false;
        boolean hasMoved = false;
        boolean hasActed = false;

        while (!turnOver) {
            System.out.print("> ");
            String raw = scanner.nextLine().trim();
            String[] parts = raw.split("\\s+", 2);
            String cmd = parts[0].toLowerCase();
            String arg = (parts.length > 1) ? parts[1] : "";

            switch (cmd) {
                case "who" -> cmdWho(player);
                case "where" -> cmdWhere(player);
                case "board" -> board.displayUpdatedBoard();
                case "move" -> {
                    if (hasMoved) {
                        System.out.println("You have already moved this turn.");
                    } else if (cmdMove(player, arg)) {
                        hasMoved = true;
                    }
                }
                case "work" -> cmdWork(player, arg);
                case "act" -> {
                    if (hasActed) {
                        System.out.println("You have already acted or rehearsed this turn.");
                    } else if (hasMoved) {
                        System.out.println("You cannot act after moving.");
                    }
                     else if (cmdAct(player)) {
                        hasActed = true;
                    }
                }
                case "rehearse" -> {
                    if (hasActed) {
                        System.out.println("You have already acted or rehearsed this turn.");
                    } else if (cmdRehearse(player)) {
                        hasActed = true;
                    }
                }
                case "upgrade" -> cmdUpgrade(player);
                case "end" -> turnOver = true;
                case "quit" -> {
                    gameOver = true;
                    turnOver = true;
                }
                default -> System.out.println("Unknown command. Commands: who, where, board, move <location>, "
                        + "work <role>, act, rehearse, upgrade, end, quit");
            }
        }
    }

    // prints the active players stats
    private void cmdWho(Player player) {
        player.displayInfo();
    }

    // prints the player's current location and scene info
    private void cmdWhere(Player player) {
        Location loc = player.getToken().getCurrentLocation();
        if (loc == null) {
            System.out.println("Unknown location.");
            return;
        }
        StringBuilder sb = new StringBuilder(loc.getName());

        switch (loc.returnType()) {
            case 's' :
                Set s = (Set) loc;
                Scene card = s.getScene();
                if (card != null && !(s.isShootingComplete())) {
                    sb.append(" shooting ").append(card.getName())
                    .append(" scene ").append(card.getID());
                } else if (s.isShootingComplete()) {
                sb.append(" wrapped");
            } break;
            case 'c':
                sb.append(" (Casting Office)");
                break;

        }
        

        // Show the players role if they have one
        if (player.getPlayerRole() != null) {
            sb.append("  |  role: ").append(player.getPlayerRole().getName());
        }
        System.out.println(sb);
    }

    // moves the active player to an adjacent location
    private boolean cmdMove(Player player, String destination) {
        if (player.getPlayerRole() != null) {
            System.out.println("You can't move while working a role.");
            return false;
        }
        if (destination.isEmpty()) {
            System.out.println("Usage: move <location name>");
            return false;
        }

        Location current = player.getToken().getCurrentLocation();
        Location target = board.findSetByName(destination);

        if (target == null) {
            System.out.println("Location '" + destination + "' not found.");
            return false;
        }
        if (target == current) {
            System.out.println("You are already at " + target.getName() + ".");
            return false;
        }
        if (!current.getAdjacentLocations().contains(target)) {
            System.out.println(target.getName()
                    + " is not adjacent to " + current.getName() + ".");
            return false;
        }

        // remove from old set, add to new set, then update token
        current.removeFromLocation(player);
        target.addPlayer(player);
        player.getToken().moveToLocation(target);
        System.out.println(player.getName() + " moves to " + target.getName() + ".");
        return true;
    }

    // assigns the player to the named role at their current set
    private void cmdWork(Player player, String roleName) {
        if (player.getPlayerRole() != null) {
            System.out.println("You are already working a role.");
            return;
        }
        if (roleName.isEmpty()) {
            System.out.println("Usage: work <role name>");
            return;
        }

        Location loc = player.getToken().getCurrentLocation();

        if (loc.returnType() != 's') {
            System.out.println("You need to be on a set to work a role.");
            return;
        }

        Set locSet = (Set) loc;
        Scene card = locSet.getScene();

        if (card == null || locSet.isShootingComplete()) {
            System.out.println("There is no active scene at " + locSet.getName() + ".");
            return;
        }

        // Search side roles first then starring roles on the card
        Role target = findRoleByName(locSet.getSideRoles(), roleName);
        if (target == null) {
            target = findRoleByName(card.getStarRoles(), roleName);
        }

        if (target == null) {
            System.out.println("Role '" + roleName + "' not found here.");
            listAvailableRoles(locSet, card, player);
            return;
        }
        if (target.isOccupied()) {
            System.out.println("That role is already taken.");
            return;
        }
        if (!target.checkRankRequirement(player.getRank())) {
            System.out.println("Your rank (" + player.getRank()
                    + ") is too low for this role (requires "
                    + target.getRankRequirement() + ").");
            return;
        }

        target.assignPlayer(player);
        player.setPlayerRole(target);
        player.getToken().placeOnRoleSpot(target);

        // Reveal the card the first time a player takes a starring role
        if (target.getisStar() && !card.isRevealed()) {
            card.reveal();
            System.out.println("Scene revealed: " + card.getName()
                    + " (budget " + card.getBudget() + ")");
        }

        System.out.println(player.getName() + " is now working: "
                + target.getName() + " ("
                + (target.getisStar() ? "starring" : "side") + " role, rank "
                + target.getRankRequirement() + ")");
    }

    // player rolls dice + rehearsal chips and compares to scene budget
    private boolean cmdAct(Player player) {
        if (player.getPlayerRole() == null) {
            System.out.println("You need to be working a role to act.");
            return false;
        }

        Set loc = (Set) player.getToken().getCurrentLocation();
        Scene card = loc.getScene();
        int budget = card.getBudget();
        boolean isStar = player.getPlayerRole().getisStar();

        // Roll dice and add rehearsal bonus
        int rawRoll = dice.roll(player.getRank());
        int total = dice.addRehearsal(rawRoll, player.getRehearsalChips());
        System.out.println(player.getName() + " rolls " + rawRoll
                + " + " + player.getRehearsalChips() + " chips = " + total
                + " (need ≥ " + budget + ")");

        if (total >= budget) {
            // Success
            System.out.println("Success!");
            if (isStar) {
                bank.awardPay(player, 2); // starring: $2 on success
            } else {
                bank.awardPay(player, 1); // side: $1 on success
                player.setCredits(player.getCredits() + 1); // + 1 credit
            }
            loc.decrementShots();

            // Check if the scene wraps
            if (loc.getShotCounters() == 0) {
                wrapScene(loc, card);
            }
        } else {
            System.out.println("Failure.");
            if (!isStar) {
                bank.awardConsolation(player);
            }
        }
        return true;
    }

    // adds one rehearsal chip
    private boolean cmdRehearse(Player player) {
        if (player.getPlayerRole() == null) {
            System.out.println("You need to be working a role to rehearse.");
            return false;
        }
        Set loc = (Set) player.getToken().getCurrentLocation();
        Scene card = loc.getScene();
        int maxChips = card.getBudget() - 1;
        if (player.getRehearsalChips() >= maxChips) {
            System.out.println("You already have the maximum rehearsal chips ("
                    + maxChips + ") for this scene.");
            return false;
        }

        player.addRehearsalChip();
        System.out.println(player.getName() + " rehearses. Chips: "
                + player.getRehearsalChips() + "/" + maxChips);
        return true;
    }

    // lets the player buy a rank upgrade at the Casting Office.
    private void cmdUpgrade(Player player) {
        Location loc = player.getToken().getCurrentLocation();
        if (!(loc instanceof CastingOffice)) {
            System.out.println("You must be at the Casting Office to upgrade.");
            return;
        }
        if (player.getRank() >= 6) {
            System.out.println("You are already at the maximum rank (6).");
            return;
        }

        CastingOffice office = (CastingOffice) loc;
        office.displayCostOptions(player.getRank());

        System.out.print("Enter target rank (or 0 to cancel): ");
        int targetRank;
        try {
            targetRank = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (targetRank == 0)
            return;
        if (targetRank <= player.getRank() || targetRank > 6) {
            System.out.println("Invalid rank choice.");
            return;
        }

        CastingOffice.Cost cost = office.getUpgradeCost(targetRank);
        if (cost == null) {
            System.out.println("No cost data for rank " + targetRank + ".");
            return;
        }

        System.out.println("Pay with: (1) $" + cost.getDollarCost()
                + " dollars  or  (2) " + cost.getCreditCost() + " credits");
        System.out.print("Choice (1 or 2): ");
        String choice = scanner.nextLine().trim();

        boolean success;
        if (choice.equals("1")) {
            success = bank.deductPayment(player, cost.getDollarCost(), false);
        } else if (choice.equals("2")) {
            success = bank.deductPayment(player, cost.getCreditCost(), true);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        if (success) {
            player.setRank(targetRank);
            System.out.println(player.getName() + " upgraded to rank " + targetRank + "!");
        }
    }

    // Wraps scene
    private void wrapScene(Set set, Scene card) {
        System.out.println("\n═══ Scene wraps: " + card.getName() + " ═══");

        List<Player> starPlayers = new ArrayList<>();
        List<Player> sidePlayers = new ArrayList<>();

        for (Player p : set.getPlayerList()) {
            if (p.getPlayerRole() != null) {
                if (p.getPlayerRole().getisStar()) {
                    starPlayers.add(p);
                } else {
                    sidePlayers.add(p);
                }
            }
        }

        // Bonus dice for starring players
        if (!starPlayers.isEmpty()) {
            List<Integer> rolls = dice.rollBonus(card.getBudget());
            System.out.print("Bonus dice (" + card.getBudget() + " dice): ");
            for (int r : rolls)
                System.out.print(r + " ");
            System.out.println();
            bank.distributeStarPayouts(starPlayers, rolls);
        }

        // Flat $1 for side players
        bank.awardSidePay(sidePlayers);

        // Vacate all roles and clear player role state
        for (Role r : card.getStarRoles())
            r.setAssignedPlayer(null);
        for (Role r : set.getSideRoles())
            r.setAssignedPlayer(null);

        for (Player p : set.getPlayerList()) {
            p.setPlayerRole(null);
            p.setRehearsalChips(0);
            p.getToken().setOnRole(false);
        }

        set.wrap();
        System.out.println("═══════════════════════════════════════\n");
    }

    // Advances to the next player triggers end of day when one scene remains
    private void advanceTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        if (detectOneSceneRemaining()) {
            endDay();
        }
    }

    private boolean detectOneSceneRemaining() {
        return Set.getActiveSets() <= 1;
    }

    // Ends the current day
    private void endDay() {
        System.out.println("\n══ End of Day " + currentDay + " ══");

        // Remove leftover cards and reset sets
        for (Location s : board.getLocations()) {
            if (s.returnType() == 's') {
                Set set = (Set) s;
                set.setScene(null);
                set.setShootingComplete(false);

            }
        }

        // Return all players to trailers clear roles and chips
        Trailers trailers = board.getTrailers();
        for (Player p : players) {
            if (p.getPlayerRole() != null) {
                p.getPlayerRole().setAssignedPlayer(null);
                p.setPlayerRole(null);
            }
            p.setRehearsalChips(0);
            Location old = p.getToken().getCurrentLocation();
            if (old != null)
                old.removeFromLocation(p);
            trailers.addPlayer(p);
            p.getToken().moveToLocation(trailers);
        }

        currentDay++;
        if (currentDay > totalDays) {
            System.out.println("All days complete!");
            gameOver = true;
        } else {
            startDay();
        }
    }

    // Shuffles the deck and deals one card to each shooting set
    private void startDay() {
        System.out.println("\n══ Day " + currentDay + " begins ══");
        deck.shuffle();
        board.dealToSets(deck);
    }

    // Computes final scores
    private void tallyFinalScores() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║         F I N A L  S C O R E S   ║");
        System.out.println("╚══════════════════════════════════╝");

        // Converted remaining money to credits
        //for (Player p : players) {
            //int bonus = Bank.convertM2C(p.getMoney());
            //p.setCredits(p.getCredits() + bonus);
            //p.setMoney(0);
        //}

        List<Player> ranked = new ArrayList<>(players);
        ranked.sort(Comparator.comparingInt(Player::computeFinalScore).reversed());

        int rank = 1;
        int prevScore = -1;
        for (Player p : ranked) {
            int score = p.computeFinalScore();
            if (score != prevScore) {
                rank = ranked.indexOf(p) + 1;
            }
            System.out.printf("%d. %-20s %d pts  ($%d + %dcr + %drank*5)%n",
                    rank, p.getName(), score, p.getMoney(), p.getCredits(), p.getRank());
            prevScore = score;
        }

        // Announce winner
        int topScore = ranked.get(0).computeFinalScore();
        List<String> winners = new ArrayList<>();
        for (Player p : ranked) {
            if (p.computeFinalScore() == topScore)
                winners.add(p.getName());
        }

        if (winners.size() == 1) {
            System.out.println("\nWinner: " + winners.get(0) + "!");
        } else {
            System.out.println("\nTie between: " + String.join(", ", winners) + "!");
        }
    }

    private Role findRoleByName(List<Role> roles, String name) {
        for (Role r : roles) {
            if (r.getName().equalsIgnoreCase(name))
                return r;
        }
        return null;
    }

    // Prints all roles at the current set that the player can legally take
    private void listAvailableRoles(Set loc, Scene card, Player player) {
        System.out.println("Available roles at " + loc.getName() + ":");
        boolean any = false;

        for (Role r : loc.getSideRoles()) {
            if (!r.isOccupied()) {
                System.out.println("  [side]     " + r.getName()
                        + " (rank " + r.getRankRequirement() + ")"
                        + (r.checkRankRequirement(player.getRank()) ? "" : " -- rank too low"));
                any = true;
            }
        }
        if (card != null) {
            for (Role r : card.getStarRoles()) {
                if (!r.isOccupied()) {
                    System.out.println("  [starring] " + r.getName()
                            + " (rank " + r.getRankRequirement() + ")"
                            + (r.checkRankRequirement(player.getRank()) ? "" : " -- rank too low"));
                    any = true;
                }
            }
        }
        if (!any)
            System.out.println("  (none available)");
    }

    // Getters
    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Bank getBank() {
        return bank;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getDay() {
        return currentDay;
    }
}