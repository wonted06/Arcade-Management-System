class ActiveGame extends ArcadeGame {

    // extra field
    private int minimumAge;

    // Constructor taking values of all fields
    public ActiveGame(String gameID, String gameName, int price, int minimumAge) throws InvalidGameIdException {
        super(gameID, gameName, price);
        if (!gameID.startsWith("A")){
            throw new InvalidGameIdException("Invalid Active Game ID: must start with 'A' and be 10 alphanumeric characters");
        }
        this.minimumAge = minimumAge;
    }

    // Accessor method
    public int getMinimumAge() {

        return minimumAge;
    }

    @Override
    public int calculatePrice(boolean peak){
        // Active games are discounted 20% off
        return peak ? getPrice() : (int) (getPrice() * 0.8);
    }

    // toString method
    @Override
    public String toString(){
        return super.toString() + ", Minimum Age: " + minimumAge;
    }

    public static void main(String[] args){
        System.out.println("====== ACTIVE GAME TEST HARNESS ======\n");

        try{
            String testID1 = "A123456789";
            String testID2 = "A987654321";

            // Creates a new Active Game
            ActiveGame game1 = new ActiveGame(testID1, "Pool", 247, 12);
            ActiveGame game2 = new ActiveGame(testID2, "Darts", 150, 16);

            System.out.println("Active Game Instances: " + "\n");

            System.out.println(game1);
            System.out.println("Peak price: " + game1.calculatePrice(true));
            System.out.println("Off-price: " + game1.calculatePrice(false) + "\n");

            System.out.println(game2);
            System.out.println("Peak price: " + game2.calculatePrice(true));
            System.out.println("Off-price: " + game2.calculatePrice(false) + "\n");

            System.out.println("====== END OF TESTS ======");

        } catch(InvalidGameIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}