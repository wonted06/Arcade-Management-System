class VirtualRealityGame extends ActiveGame {

    // Enum to represent VR equipment
    public enum Equipment{
        HEADSET_ONLY,
        HEADSET_AND_CONTROLLER,
        FULL_BODY_TRACKING;

        public static Equipment fromString(String value) {
            switch (value.trim().toLowerCase()) {
                case "headsetonly":
                    return HEADSET_ONLY;
                case "headsetandcontroller":
                    return HEADSET_AND_CONTROLLER;
                case "fullbodytracking":
                    return FULL_BODY_TRACKING;
                default:
                    throw new IllegalArgumentException("No enum constant VirtualRealityGame.Equipment." + value);
            }
        }
    }

    private Equipment vrEquipment;

    // Constructor taking values of all fields
    public VirtualRealityGame(String gameID, String gameName, int price, int minimumAge, Equipment vrEquipment) throws InvalidGameIdException{
        super(gameID,gameName,price, minimumAge);
        if(!gameID.startsWith("AV")){
            throw new InvalidGameIdException("Invalid Virtual Reality Game ID: must start with 'AV' and be exactly 10 alphanumeric characters");
        }
        this.vrEquipment = vrEquipment;
    }

    // Accessor Method
    public Equipment getVrEquipment(){
        return vrEquipment;
    }

    @Override
    public int calculatePrice(boolean peak){
        if (peak){
            return getPrice(); // No discounts during peak hours
        }
        // Discounts applied based on VR equipment
        switch (vrEquipment){
            case HEADSET_ONLY:
                return(int) (getPrice() * 0.9); // 10% discount
            case HEADSET_AND_CONTROLLER:
                return(int) (getPrice() * 0.95); // 5% discount
            case FULL_BODY_TRACKING:
                return getPrice(); // No discount
            default:
                return getPrice();
        }
    }

    // toString method
    @Override
    public String toString() {
        return super.toString() + ", Vr Equipment: " + vrEquipment;
    }

    // Test harness
    public static void main(String[] args){
        System.out.println("====== VR GAME TEST HARNESS ======\n");


        try{
             // Valid test ID's
             String testID1 = "AV12345678";
             String testID2 = "AV87654321";
             String testID3 = "AV01246810";

             // Test instances with different VR equipment

            // Creates a new Virtual Reality Game
             VirtualRealityGame game1 = new VirtualRealityGame(testID1,"VR Shooter", 500, 16, VirtualRealityGame.Equipment.HEADSET_ONLY);
             VirtualRealityGame game2 = new VirtualRealityGame(testID2,"VR Racing", 800, 18, VirtualRealityGame.Equipment.HEADSET_AND_CONTROLLER);
             VirtualRealityGame game3 = new VirtualRealityGame(testID3,"VR Dancing", 600, 12, VirtualRealityGame.Equipment.FULL_BODY_TRACKING);

             System.out.println("Virtual Reality Game instances\n");

             System.out.println(game1);
             System.out.println("Peak price: " + game1.calculatePrice(true));
             System.out.println("Off-peak price: " + game1.calculatePrice(false) + "\n"); // Off-peak price with discount

             System.out.println(game2);
             System.out.println("Peak price: " + game2.calculatePrice(true));
             System.out.println("Off-peak price: " + game2.calculatePrice(false) + "\n"); // Off-peak price with discount

             System.out.println(game3);
             System.out.println("Peak price: " + game3.calculatePrice(true));
             System.out.println("Off-peak price: " + game3.calculatePrice(false) + "\n"); // Off-peak price with discount

            System.out.println("====== END OF TESTS ======");

         } catch (InvalidGameIdException e){
             System.out.println("Error: " + e.getMessage());
         }
    }
}
