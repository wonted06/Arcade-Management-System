import java.io.BufferedReader;
import java.io.IOException;

// TransactionParser class - processing transaction commands from input data
public class TransactionParser {

    // Processes a sequence of transactions read form BufferedReader and applies them
    public static void processTransactions(BufferedReader reader, Arcade arcade) throws IOException {
        String line;
        int lineNumber = 0;

        while ((line = reader.readLine()) != null) {
            lineNumber++;
            try {
                String[] parts = line.split(",");

                String action = parts[0].trim().toUpperCase();

                switch (action) {
                    case "PLAY":
                        // Format: PLAY,<customerId>,<gameId>,<PEAK/OFFPEAK>
                        if (parts.length != 4) throw new IllegalArgumentException("Invalid PLAY format");
                        String customerId = parts[1].trim();
                        String gameId = parts[2].trim();
                        boolean isPeak = parts[3].trim().equalsIgnoreCase("PEAK");
                        arcade.processTransaction(customerId, gameId, isPeak);
                        break;

                    case "ADD_FUNDS":
                        // Format: ADD_FUNDS,<customerId>,<amount>
                        if (parts.length != 3) throw new IllegalArgumentException("Invalid ADD_FUNDS format");
                        customerId = parts[1].trim();
                        int amount = Integer.parseInt(parts[2].trim());
                        arcade.addFunds(customerId, amount);
                        break;

                    case "NEW_CUSTOMER":
                        // Format: NEW_CUSTOMER,<id>,<name>,[STAFF|STUDENT],<balance>,<age>
                        if (parts.length != 6) throw new IllegalArgumentException("Invalid NEW_CUSTOMER format");
                        String id = parts[1].trim();
                        String name = parts[2].trim();
                        String type = parts[3].trim().toUpperCase();
                        int balance = Integer.parseInt(parts[4].trim());
                        int age = Integer.parseInt(parts[5].trim());

                        // Determines discount type
                        Customer.DiscountType discountType;
                        switch (type) {
                            case "STAFF":
                                discountType = Customer.DiscountType.CMP_STAFF;
                                break;
                            case "STUDENT":
                                discountType = Customer.DiscountType.STUDENT;
                                break;
                            default:
                                discountType = Customer.DiscountType.NONE;
                        }

                        // Create and register new customer
                        Customer customer = new Customer(id, name, age, discountType, balance);
                        arcade.registerCustomer(customer);
                        break;

                    default:
                        System.err.println("Unknown transaction type on line " + lineNumber + ": " + action);
                }

            } catch (Exception e) {
                System.out.println("Error processing transaction " + lineNumber + ": " + e.getMessage());
            }
        }
    }
}
