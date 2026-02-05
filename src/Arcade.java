import java.util.*;

public class Arcade {

    // Private Fields
    private String arcadeName;
    private int arcadeRevenue;
    private Map<String, ArcadeGame> games = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();

    // Constructor
    public Arcade(String arcadeName){
        this.arcadeName = arcadeName;
        this.arcadeRevenue = 0;

        // HashMaps - more efficient than ArrayLists in this case: allows for fast lookup by ID
        this.games = new HashMap<>();
        this.customers = new HashMap<>();
    }

    // Accessor methods
    public String getArcadeName() {
        return arcadeName;
    }
    public int getArcadeRevenue() {
        return arcadeRevenue;
    }

    // Adds a game to the arcade system
    public void addArcadeGame(ArcadeGame g){
        games.put(g.getGameID(), g);
    }

    // Adds a customer to the arcade system
    public void addCustomer(Customer c){
        customers.put(c.getCustomerID(), c);
    }

    // Retrieve a customer by ID, or throw exception if not found
    public Customer getCustomer(String customerID) throws InvalidCustomerException{
        if (!customers.containsKey(customerID)){
            throw new InvalidCustomerException("Customer with ID " + customerID + " does not exist");
        }
        return customers.get(customerID);
    }

    // Rerieve a game by ID, or throw an exception if not found
    public ArcadeGame getArcadeGame(String gameID) throws InvalidGameIdException{
        if (!games.containsKey(gameID)){
            throw new InvalidGameIdException("Game with ID " + gameID + " does not exist");
        }
        return games.get(gameID);
    }

    // Handles a full game transaction - charges customer and adds payment to arcade revenue
    public boolean processTransaction(String customerID, String gameID, boolean peak) throws InvalidCustomerException, InvalidGameIdException, InsufficientBalanceException, AgeLimitException{
        Customer c = getCustomer(customerID);
        ArcadeGame g = getArcadeGame(gameID);

        int charged = c.chargeAccount(g, peak);
        arcadeRevenue += charged;
        return true;
    }

    // GamesCo required result //

    // Finds customer with the highest balance
    public Customer findRichestCustomer(){
        Customer richest = null;
        int max = Integer.MIN_VALUE;

        for (Customer c : customers.values()){
            if (c.getBalance() > max){
                max = c.getBalance();
                richest = c;
            }
            if (customers.isEmpty()) return null;
        }
        return richest;
    }


    // Calculates median price (off-peak) across all games
    public int getMedianGamePrice(){
        List<Integer> prices = new ArrayList<>();

        for (ArcadeGame g : games.values()){
            prices.add(g.calculatePrice(false)); // Off-peak price
        }

        Collections.sort(prices);
        int size = prices.size();

        // If there is even number of games
        if (size == 0) return 0;
        if (size % 2 == 1){
            return prices.get(size / 2);
        } else {
            int mid1 = prices.get((size / 2) - 1);
            int mid2 = prices.get(size / 2);
            return (mid1 + mid2) / 2; // Averages the price of two middle games
        }
    }

    // Returns number of each game type
    public int[] countArcadeGames(){
        int cabinet = 0 , active = 0, vr = 0;

        for (ArcadeGame g : games.values()){
            if (g instanceof VirtualRealityGame) vr++;
            else if (g instanceof ActiveGame) active++;
            else cabinet++;
        }
        return new int[]{cabinet, active, vr};
    }


    // Returns the formatted total revenue earned by the arcade
    public String getFormattedRevenue() {
        return formatMoney(arcadeRevenue);
    }

    // Corporate Motto
    public static void printCorporateJargon(){
        System.out.println("GamesCo does not take responsibility for any accidents or fits of rage that occur on the premises.");
    }

    // Adds a new customer
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }

    // Increases a customer's balance
    public void addFunds(String customerID, int amount) throws InvalidCustomerException {
        Customer customer = customers.get(customerID);
        if (customer == null) {
            throw new InvalidCustomerException("Customer not found: " + customerID);
        }
            customer.addFunds(amount);
    }

    @Override
    public String toString() {
        return "Arcade{name='" + arcadeName + "', revenue=" + formatMoney(arcadeRevenue) + ", games=" + games.size() + ", customers=" + customers.size() + "}";
    }

    // Converts amount in pence to formatted GBP string
    public String formatMoney(int pence){
        boolean negative = pence < 0;
        pence = Math.abs(pence);
        return (negative? "-£" : "£") + (pence / 100) + "." + String.format("%02d", pence % 100);
    }



    // Test Harness
    public static void main(String[] args)throws InvalidCustomerException, InvalidGameIdException, InsufficientBalanceException, AgeLimitException{
        System.out.println("====== ARCADE TEST HARNESS ======");

        try{
            // Create Arcade
            Arcade myArcade = new Arcade("Galaxian");

            // Games
            ArcadeGame game1 = new CabinetGame("C123456789", "Pac-Man", 300, false);  // No reward, £3.00
            ArcadeGame game2 = new CabinetGame("C987654321", "Mario & Luigi", 500, true); // With reward
            ArcadeGame game3 = new ActiveGame("A123456789", "Just Dance", 400, 10);
            ArcadeGame game4 = new VirtualRealityGame("AV12345678", "VR Labyrinth", 800, 12, VirtualRealityGame.Equipment.HEADSET_ONLY);

            // Add games to arcade
            myArcade.addArcadeGame(game1);
            myArcade.addArcadeGame(game2);
            myArcade.addArcadeGame(game3);
            myArcade.addArcadeGame(game4);

            // Customers
            Customer c1 = new Customer("AB12CD", "Sophia", 20, Customer.DiscountType.NONE, 2000);
            Customer c2 = new Customer("ST1234", "Charlie", 19, Customer.DiscountType.STUDENT, 400);
            Customer c3 = new Customer("CM1234", "Natalie", 10, Customer.DiscountType.CMP_STAFF, 1000);

            // Add customers to arcade
            myArcade.addCustomer(c1);
            myArcade.addCustomer(c2);
            myArcade.addCustomer(c3);

            // Process transactions
            System.out.println("\n ---- TRANSACTION TESTS ----");

            boolean success1 = myArcade.processTransaction("AB12CD", "C123456789", false);
            System.out.println("Transaction 1 (Sophia plays Pac-Man at off-peak): " + success1);

            boolean success2 = myArcade.processTransaction("ST1234", "A123456789", false);
            System.out.println("Transaction 2 (Charlie plays VR Labyrinth at peak): " + success2);

            // Exception Tests
            System.out.println("\n ---- EXCEPTION TESTS ----");

            // AgeLimitException
            try{
                myArcade.processTransaction("CM1234", "AV12345678", false);
            } catch (AgeLimitException e){
                System.out.println("Transaction 3: Failed - " + e.getMessage());
            }

            // InsufficientBalanceException
            try{
                myArcade.processTransaction("ST1234","AV12345678", true);
            } catch (InsufficientBalanceException e){
                System.out.println("Transaction 4: Failed - " + e.getMessage());
            }


            System.out.println("\n ---- ANALYTICS TEST ----");

            // Richest Customer
            System.out.println("Richest Customer: " + myArcade.findRichestCustomer().getCustomerName());

            // Median Game Price
            System.out.println("Median Game Price: "+ myArcade.getMedianGamePrice());

            // Counts Arcade Games
            int[] counts = myArcade.countArcadeGames();
            System.out.println("Cabinet: " + counts[0] + ", Active: " + counts[1] + ", VR: " + counts[2]);

            // Arcade Revenue
            System.out.println("\nArcade Revenue: " + myArcade.getArcadeRevenue()+ "\n");

            // Corporate Jargon
            System.out.println("Corporate Jargon: ");
            Arcade.printCorporateJargon();

            System.out.println("\n====== END OF TESTS ======");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

