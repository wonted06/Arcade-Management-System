import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// GameParser class - parsing ArcadeGame objects from text input stream
public class GameParser {

    // Parses a list of ArcadeGame objects from BufferedReader using "@" as delimiter
    public static List<ArcadeGame> parseGames(BufferedReader reader) throws IOException {
        List<ArcadeGame> games = new ArrayList<>();
        String line;
        int lineNum = 0;

        while ((line = reader.readLine()) != null) {
            lineNum++;
            try {
                String[] parts = line.split("@");
                if (parts.length < 5) {
                    // Skip lines that are too short to be valid
                    System.err.println("Skipping malformed line " + lineNum + ": " + line);
                    continue;
                }

                // Extract common game fields
                String id = parts[0].trim();
                String name = parts[1].replaceAll("^\"|\"$", "").trim(); // remove surrounding quotes
                String type = parts[2].trim();
                int price = Integer.parseInt(parts[3].trim());

                // Construct appropriate ActiveGame subclass based on type
                switch (type.toLowerCase()) {
                    case "cabinet":
                        boolean multiplayer = parts[4].trim().equalsIgnoreCase("yes");
                        games.add(new CabinetGame(id, name, price, multiplayer));
                        break;

                    case "active":
                        int minAge = Integer.parseInt(parts[4].trim());
                        games.add(new ActiveGame(id, name, price, minAge));
                        break;

                    case "virtualreality":
                        if (parts.length < 6) {
                            // VR games must include equipment
                            System.err.println("Skipping incomplete VR line " + lineNum + ": " + line);
                            continue;
                        }
                        int vrAge = Integer.parseInt(parts[4].trim());
                        String equipment = parts[5].trim(); // e.g., headsetOnly, fullBodyTracking
                        VirtualRealityGame.Equipment equipEnum = VirtualRealityGame.Equipment.fromString(equipment);
                        games.add(new VirtualRealityGame(id, name, price, vrAge, equipEnum));
                        break;

                    default:
                        // Unknown game type encountered
                        System.err.println("Unknown game type on line " + lineNum + ": " + type);
                        break;
                }

            } catch (Exception e) {
                // Catch-all for unexpected errors during parsing
                System.err.println("Error parsing line " + lineNum + ": " + e.getMessage());
            }
        }

        return games;
    }
}
