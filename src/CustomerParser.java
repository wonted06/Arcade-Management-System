import java.io.*;
import java.util.*;

// CustomerParser class - parsing Customer objects from input data
public class CustomerParser {

    // Parses a list of Customer objects from BufferedReader using "#" as delimiter
    public static List<Customer> parseCustomers(BufferedReader reader) throws IOException {
        List<Customer> customers = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            // Skip empty lines
            if (line.trim().isEmpty()) continue;

            try {
                String[] tokens = line.split("#");
                // Validate expected format
                if (tokens.length < 4 || tokens.length > 5) {
                    System.err.println("Invalid line format: " + line);
                    continue;
                }

                // Extract customer fields
                String id = tokens[0].trim();
                String name = tokens[1].trim();
                int balance = Integer.parseInt(tokens[2].trim());
                int age = Integer.parseInt(tokens[3].trim());

                // Default to no discounts unless specified
                Customer.DiscountType discount = Customer.DiscountType.NONE;

                if (tokens.length == 5) {
                    String type = tokens[4].trim().toUpperCase();
                    if (type.equals("STAFF")) {
                        discount = Customer.DiscountType.CMP_STAFF;
                    } else if (type.equals("STUDENT")) {
                        discount = Customer.DiscountType.STUDENT;
                    } else {
                        // Unknown discount types are reported and skipped
                        System.err.println("Unknown discount type: " + type + " on line: " + line);
                        continue;
                    }
                }

                // Construct and add valid Customer
                Customer customer = new Customer(id, name, age, discount, balance);
                customers.add(customer);

            } catch (NumberFormatException e) {
                // Handle parse errors for numeric fields
                System.err.println("Error parsing number in line: " + line);
            } catch (InvalidCustomerException e) {
                System.err.println("Invalid customer data: " + e.getMessage() + " in line: " + line);
            }
        }

        return customers;
    }
}