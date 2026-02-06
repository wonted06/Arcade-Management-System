# Arcade Games Simulation System  
*A Java-Based Object-Oriented Simulation Project*

![Java](https://img.shields.io/badge/Java-OOP-orange)
![Status](https://img.shields.io/badge/Status-Complete-success)
![Paradigm](https://img.shields.io/badge/Paradigm-Object--Oriented-blue)

---

## Overview

This project is a **Java-based arcade simulation system** that models the core operations of a modern arcade, including **games, customers, and transactions**.  
The application reads structured data from text files, constructs an in-memory arcade environment, and simulates real customer interactions such as playing games, adding funds, and registering new customers.

The project focuses heavily on **object-oriented design**, clean architecture, and robust error handling, reflecting how a real-world simulation or backend system might be structured.

---

## Key Features

- Supports multiple **arcade game types** with shared and specialised behaviour
- Simulates **customer transactions** via input files
- Enforces business rules using **custom exceptions**
- Applies **peak and off-peak pricing**
- Performs **basic analytics** on arcade data
- Fully modular and extensible design

---

## Core Functionality

The simulation supports the following operations:

- Loading arcade games and customers from files
- Processing transactions:
  - Playing games
  - Adding funds
  - Registering new customers
- Applying customer discounts (e.g. staff, student)
- Enforcing:
  - Minimum age restrictions
  - Sufficient balance checks
  - Valid customer and game identifiers
- Producing summary statistics such as:
  - Richest customer
  - Median game price

All invalid or rule-breaking actions are handled gracefully using **custom exceptions**, ensuring the program never crashes due to bad input.

---

## Object-Oriented Design

### Game Hierarchy
- `ArcadeGame` (abstract base class)
  - `CabinetGame`
  - `ActiveGame` (age-restricted)
  - `VirtualRealityGame` (equipment requirements)

### Core Classes
- `Arcade` – central manager for games, customers, and transactions
- `Customer` – represents arcade users and handles balance & discounts
- `Simulation` – program entry point and execution controller

### Parsers
- `CustomerParser` – loads customer data
- `GameParser` – loads game data
- `TransactionParser` – processes transactions

### Custom Exceptions
- `AgeLimitException`
- `InsufficientBalanceException`
- `InvalidCustomerException`
- `InvalidGameIdException`

---

## Input Files

### `customers.txt`
Stores customer data using `#` as a delimiter.

**Format**
ID#Name#Balance#Age[#STAFF or #STUDENT]

**Example**
748A66#Lucille Swan#800#31#STAFF

---

### `games.txt`
Stores arcade game data using `@` as a delimiter.

Each entry includes:
- Game ID
- Game name
- Game type (`cabinet`, `active`, `virtualReality`)
- Cost
- Minimum age
- Additional attributes (e.g. VR equipment)

---

### `transactions.txt`
Defines the actions performed during the simulation.

**Supported transactions**
- `PLAY`
- `ADD_FUNDS`
- `NEW_CUSTOMER`

**Examples**
PLAY,A64248,AHW0HK1F03,OFF_PEAK
ADD_FUNDS,174450,1000
NEW_CUSTOMER,P54578,Jacob Peraltera,STUDENT,10,32

---

## Tech Stack
- **Java** – core programming language
- **Object-Oriented Design** – abstraction, inheritance, polymorphism
- **Java Standard Library** – file I/O, collections

---

## Project Structure
| File | Description |
|-----|------------|
| `ArcadeGame.java` | Abstract base class for all arcade games |
| `CabinetGame.java` | Standard arcade cabinet game |
| `ActiveGame.java` | Age-restricted physical arcade game |
| `VirtualRealityGame.java` | VR game with equipment requirements |
| `Arcade.java` | Core system manager for games and customers |
| `Customer.java` | Represents arcade customers and discounts |
| `Simulation.java` | Program entry point and execution controller |
| `CustomerParser.java` | Loads and validates customer data |
| `GameParser.java` | Loads and validates game data |
| `TransactionParser.java` | Processes transaction files |
| `AgeLimitException.java` | Thrown when age rules are violated |
| `InsufficientBalanceException.java` | Thrown when balance is insufficient |
| `InvalidCustomerException.java` | Invalid customer ID handling |
| `InvalidGameIdException.java` | Invalid game ID handling |
| `customers.txt` | Customer input data |
| `games.txt` | Game input data |
| `transactions.txt` | Transaction input data |
| `README.md` | Project documentation |

---

## How to Run

1. Ensure all `.java` and `.txt` files are in the same project directory
2. Compile the project:
   ```bash
   javac *.java
3. Run the simulation:
   ```bash
   java Simulation

The program will:
	•	Load all customers and games
	•	Process every transaction in sequence
	•	Output results, summaries, and error messages to the console

⸻

## Assumptions & Design Decisions
	•	Input files are correctly formatted
	•	Customer IDs and game IDs are unique
	•	Pricing rules are handled internally by the game classes
	•	Invalid transactions do not terminate the program
	•	The system prioritises robustness and clarity over UI

⸻

## Skills Demonstrated
	•	Object-oriented programming (inheritance, polymorphism, abstraction)
	•	Java exception handling
	•	File I/O and parsing
	•	Modular system design
	•	Data validation and rule enforcement
	•	Basic analytical computations

⸻

## Academic Context

This project was developed as part of a University coursework assignment to demonstrate strong understanding of Java and object-oriented programming principles.
While academic in origin, the architecture mirrors patterns used in real-world simulation and backend systems.

⸻

## License

This project is shared for educational and portfolio purposes only.
Please do not submit this work (or derivatives) as your own for academic assessment.

⸻

## Author

Kurt Canillas
Computer Science Undergraduate
