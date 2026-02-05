class CabinetGame extends ArcadeGame {

    // extra field
    private boolean paysOutReward;

    // Constructor taking values of all fields
    public CabinetGame(String gameID, String gameName, int price, boolean paysOutReward) throws InvalidGameIdException {
        super(gameID, gameName, price);
        if (!gameID.startsWith("C")){
            throw new InvalidGameIdException("Invalid Cabinet Game ID: must start with 'C' and be 10 alphanumeric characters");
        }
        this.paysOutReward = paysOutReward;
    }

    // Accessor Methods
    public boolean getPaysOutReward() {
        return paysOutReward;
    }

    @Override
    public int calculatePrice(boolean peak) {

        // returns price during peak hours
        if (peak) {
            return getPrice();
        }
        // Off-peak price with/without rewards + discount
        return paysOutReward ? (int) (getPrice() * 0.8) : (int) (getPrice() * 0.5);
    }

    @Override
    public String toString() {
        return super.toString() + ", Pays Out Reward: " + paysOutReward;
    }

    // Test Harness
    public static void main(String[] args) {
        System.out.println("====== CABINET GAME TEST HARNESS ======\n");

        try {
            String testID1 = "C123456789"; // Valid ID
            String testID2 = "C987654321"; // Valid ID

            // Creates a new Cabinet Game
            CabinetGame game1 = new CabinetGame(testID1, "Pinball", 406, true);
            CabinetGame game2 = new CabinetGame(testID2, "Pac-Man", 215, false);

            System.out.println("Cabinet Game Instances: " + "\n");

            System.out.println(game1);
            System.out.println("Peak Price: " + game1.calculatePrice(true));
            System.out.println("Off-peak Price: " + game1.calculatePrice(false) + "\n");

            System.out.println(game2);
            System.out.println("Peak Price: " + game2.calculatePrice(true));
            System.out.println("Off-peak Price: " + game2.calculatePrice(false) + "\n");

            System.out.println("====== END OF TESTS ======");

        } catch (InvalidGameIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
