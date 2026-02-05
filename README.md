# Arcade Games Simulation (Java)

## Overview
This project is a Java-based arcade simulation that models an arcade system with games, customers, and transactions.  
The program reads data from text files, constructs the arcade environment, and simulates customer interactions such as playing games, adding funds, and registering new customers.

The project demonstrates object-oriented programming concepts including:
- Inheritance and polymorphism
- Abstract classes
- Encapsulation
- Exception handling
- File I/O
- Basic data processing and analysis

---

## Features
- Supports multiple game types:
  - Cabinet games
  - Active games (with age restrictions)
  - Virtual reality games (with equipment requirements)
- Manages customers with different discount types (e.g. staff, student)
- Processes transactions from a file:
  - Playing games (peak and off-peak pricing)
  - Adding funds
  - Registering new customers
- Enforces rules using custom exceptions:
  - Age limits
  - Insufficient balance
  - Invalid customer or game IDs
- Calculates arcade statistics such as:
  - Richest customer
  - Median game price

---

## Project Structure

### Core Classes
- `ArcadeGame` (abstract)
- `CabinetGame`
- `ActiveGame`
- `VirtualRealityGame`
- `Arcade`
- `Customer`

### Parsers
- `CustomerParser` – reads customer data from file
- `GameParser` – reads game data from file
- `TransactionParser` – processes transactions

### Simulation
- `Simulation` – contains the `main` method and runs the program

### Exceptions
- `AgeLimitException`
- `InsufficientBalanceException`
- `InvalidCustomerException`
- `InvalidGameIdException`

---

## Input Files

### `customers.txt`
Each line represents a customer using `#` as a delimiter.

**Format:**
ID#Name#Balance#Age[#STAFF or #STUDENT]

**Example:**
748A66#Lucille Swan#800#31#STAFF

---

### `games.txt`
Each line represents a game using `@` as a delimiter.

**Format varies by game type**, but includes:
- Game ID
- Game name
- Game type (`cabinet`, `active`, `virtualReality`)
- Cost
- Minimum age
- Additional attributes (e.g. VR equipment)

---

### `transactions.txt`
Each line represents a transaction.

**Supported transactions:**
- `PLAY`
- `ADD_FUNDS`
- `NEW_CUSTOMER`

**Examples:**
PLAY,A64248,AHW0HK1F03,OFF_PEAK
ADD_FUNDS,174450,1000
NEW_CUSTOMER,P54578,Jacob Peraltera,STUDENT,10,32

---

## How to Run

1. Ensure all `.java` files and `.txt` files are in the same project directory
2. Compile the project:
javac *.java
3. Run the simulation:
java Simulation
The program will:
- Load customers and games from files
- Process all transactions
- Output results and any error messages to the console

---

## Assumptions
- Input files are correctly formatted
- Game IDs and customer IDs are unique
- Peak and off-peak pricing rules are handled internally
- Invalid transactions do not crash the program; they are handled via exceptions

---

## Author
Kurt Canillas
Created as part of a University coursework project for learning Java and object-oriented programming concepts.
