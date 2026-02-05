import java.io.*;
import java.util.*;

public class Simulation {

    // Entry point for simulation
    public static void main(String[] args) {
        File gamesFile = new File("games.txt");
        File customersFile = new File("customers.txt");
        File transactionsFile = new File("transactions.txt");

        // Initialise arcade wih games and customers
        Arcade arcade = initialiseArcade("Level Up!", gamesFile, customersFile);
        // Simulate transactions and print summary
        simulateFun(arcade, transactionsFile);
    }

    // Loads games and customers from file and sets up arcade
    public static Arcade initialiseArcade(String arcadeName, File gamesFile, File customerFile) {
        Arcade arcade = new Arcade(arcadeName);

        // Load games from file
        try (BufferedReader gameReader = new BufferedReader(new FileReader(gamesFile))) {
            List<ArcadeGame> gameList = GameParser.parseGames(gameReader);
            for (ArcadeGame game : gameList) {
                arcade.addArcadeGame(game);
            }
        } catch (IOException e) {
            System.out.println("Error reading game file: " + e.getMessage());
        }

        // Load customers from file
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            List<Customer> customers = CustomerParser.parseCustomers(reader);
            for (Customer customer : customers) {
                arcade.addCustomer(customer);
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }

        return arcade;
    }


    // Processes transaction from file and prints final arcade statistics
    public static void simulateFun(Arcade arcade, File transactionFile) {

        System.out.println("====== Exception Errors ======\n");

        // Process all transactions
        try (BufferedReader transactionReader = new BufferedReader(new FileReader(transactionFile))) {
            TransactionParser.processTransactions(transactionReader, arcade);
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

        // Generating final report
        System.out.println("\n====== Arcade Simulation Report ======\n");

        // 1. Summary Statistics
        System.out.println("--- Summary Statistics ---");
        System.out.println("\nRichest customer: " + arcade.findRichestCustomer());
        System.out.println("\nMedian game price: " + arcade.formatMoney(arcade.getMedianGamePrice()));

        // Game counts with labels
        int[] gameCounts = arcade.countArcadeGames();
        System.out.println("\nGame counts:" + Arrays.toString(gameCounts));
        System.out.println("--Cabinet Games: " + gameCounts[0]);
        System.out.println("--Active Games: " + gameCounts[1]);
        System.out.println("--Virtual Reality Games: " + gameCounts[2]);

        System.out.println("\nTotal Revenue: " + arcade.getFormattedRevenue());

        // 2. Closing Message
        System.out.println("\nThank you for using GamesCo Arcade System.\n");
        Arcade.printCorporateJargon();
        System.out.println("\n===========================================\n");
    }
}