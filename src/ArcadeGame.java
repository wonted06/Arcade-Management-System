// Custom exception for invalid game ID
class InvalidGameIdException extends Exception {
    public InvalidGameIdException(String message) {
        super(message);
    }
}

// Abstract base class for Arcade games
public abstract class ArcadeGame {
    // Instance Variables
    private String gameID;
    private String gameName;
    private int price;

    // Constructor taking values of all fields
    public ArcadeGame(String gameID, String gameName, int price) throws InvalidGameIdException {
        if (!isValidGameID(gameID)) {
            throw new InvalidGameIdException("Invalid Game ID" + gameID);
        }
        this.gameID = gameID;
        this.gameName = gameName;
        this.price = price;
    }

    // Validation method for Game ID
    private boolean isValidGameID(String gameID) {
        return gameID != null && gameID.matches("[a-zA-Z0-9]{10}");
    }

    // Accessor Methods
    public String getGameID() {
        return gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public int getPrice() {
        return price;
    }

    // Abstract method for calculating price based on peak hours
    public abstract int calculatePrice(boolean peak);

    // toString method
    @Override
    public String toString() {
        return "GameID: " + gameID + ", Name: " + gameName + ", Price: " + price;
    }
}