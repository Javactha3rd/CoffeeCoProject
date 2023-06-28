/**
 * @author Yousif Sabri
 * @Version 1.0
 * @date 6/28/2023
 * CS 160 Lab Summer 2023
 */

import java.io.*;
import java.util.*;

public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static Map<String, Integer> customers = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";
    private static String customerLoyalty = "CustomerLoyalty.txt";

    /**
     * Method Description Line 1: This is the main method. It starts off the program by asking displaying the menu for the user and incorporates a DO-WHILE loop to loop through a switch statement which displays the manu after each case has been implemented.
     * Method Description Line 2: It uses a switch statement to identify which option the user has chosen and continues on that path.
     *
     * @param args
     * @throws throws Exception
     */

    public static void main(String[] args) {
        System.out.println("Welcome to Java Coffee Co.!\n");
        String choice;
        try (Scanner input = new Scanner(System.in)) {
            inventory = readInventory(inventoryFile); // Calling readInventory method to assign the inventory Map to the ingredient from the inventory file
            printInventory(); // Printing the inventory Map
            System.out.println();

            customers = readCustomerFile(customerLoyalty);

            boolean addOrder = false;

            System.out.println("1. New Order");
            System.out.println("\n2. Reload Inventory");
            System.out.println("\n3. Update Inventory");
            System.out.println("\n4. Update Order Log");
            System.out.println("\n5. Customer Points");
            System.out.println("\n6. Add New Customer");
            System.out.println("\n7. Gift Shop");
            System.out.println("\n8. Exit Application");
            choice = input.next();

            // In this do-while loop it loops the menu until the user has entered 5
            do {
                switch (choice) {
                    case "1":
                        do {
                            CoffeeOrder order = buildOrder();
                            orders.add(order);
                            System.out.println(order.printOrder());
                            System.out.println("\nWould you like to enter another order (Y or N)?");
                            String yn = input.nextLine();
                            while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                                System.out.println("Please enter Y or N.");
                                yn = input.nextLine();
                                addOrder = !yn.equalsIgnoreCase("N");
                            }
                            if (yn.equalsIgnoreCase("N")) {
                                break;
                            }
                        } while (addOrder);
                        break;

                    case "2":
                        printInventory();
                        System.out.println("");
                        break;

                    case "3":
                        writeInventory(inventory, inventoryFile);
                        break;

                    case "4":
                        writeOrderLog(logFile);
                        System.out.println("Order log has been updated successfully!");
                        break;

                    case "5":
                        customerPoints();
                        break;

                    case "6":
                        addCustomer(customerLoyalty);
                        break;

                    case "7":
                        redeemPoints(customerLoyalty);

                    case "8": // Exits the program
                        System.out.println("Exiting");
                        break;

                    default:
                        System.out.println("Please enter a valid option.\n");
                        break;
                }

                if (!choice.equals("8")) {
                    System.out.println("1. New Order");
                    System.out.println("\n2. Reload Inventory");
                    System.out.println("\n3. Update Inventory");
                    System.out.println("\n4. Update Order Log");
                    System.out.println("\n5. Customer Points");
                    System.out.println("\n6. Add New Customer");
                    System.out.println("\n7. Gift Shop");
                    System.out.println("\n8. Exit Application\n");
                    choice = input.next();

                }
            } while (!choice.equals("8"));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * @param - Method Description Line 1: In this method it builds the CoffeeOrder object by having the user modify how they would like their order from start to finish
     *          Method Description Line 2: This method also sends the Coffee object to the addCoffee()  method in Coffee Order to add it to the coffee array list
     *          Method Description Line 3: Additionally, this method also send the coffee object to updateInventory() method and if the customer name is in the customer map then it also calls addCustomerPoints().
     * @return CoffeeOrder object
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            boolean avaliable = false;
            System.out.println("Please enter your name:");
            String name = input.nextLine();


            while (addCoffee) {
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee = null;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2)
                            System.out.println("Please enter a valid option.");
                    }
                }


                input.nextLine(); // nextInt() doesn't consume newline
                while (!avaliable) {
                    if (option == 2) {
                        coffee = new Espresso();
                        if (isInInventory("Espresso")) { //Checking if Espresso is available
                            avaliable = true;
                        } else {
                            System.out.println("Item not available");
                            option = input.nextInt();
                            input.nextLine();
                        }
                    } else if (option == 1) {
                        coffee = new BlackCoffee();
                        if (isInInventory("Black Coffee")) { //Checking if Black Coffee is available
                            avaliable = true;

                        } else {
                            System.out.println("Item not available");
                            option = input.nextInt();
                            input.nextLine();
                        }
                    } else {
                        System.out.println("please enter a valid number");
                        option = input.nextInt();
                        input.nextLine();
                    }
                }


                // prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();


                    switch (option) {
                        case 1: {
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max)
                                        System.out.println("Please enter a valid option.");

                                    else { // If a valid option from the syrups is inputted
                                        WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                                        if (flavor.name().equalsIgnoreCase("CARAMEL")) {
                                            if (isInInventory("CARAMEL Syrup")) { //Checking if CARAMEL Syrup is available
                                                coffee = new WithFlavor(coffee, flavor);
                                            } else {
                                                System.out.println("Item not available");
                                            }
                                        } else if (flavor.name().equalsIgnoreCase("MOCHA")) {
                                            if (isInInventory("MOCHA Syrup")) { //Checking if MOCHA Syrup is available
                                                coffee = new WithFlavor(coffee, flavor);
                                            } else {
                                                System.out.println("Item not available");
                                            }
                                        } else if (flavor.name().equalsIgnoreCase("VANILLA")) {
                                            if (isInInventory("VANILLA Syrup")) { //Checking if VANILLA Syrup is available
                                                coffee = new WithFlavor(coffee, flavor);
                                            } else {
                                                System.out.println("Item not available");
                                            }
                                        } else {
                                            System.out.println("Please enter a valid number");
                                            option = 1;
                                        }
                                    }
                                }
                            }
                            input.nextLine();
                            break;
                        }

                        case 2: {
                            if (isInInventory("Hot Water")) {  //Checking if Hot Water is available
                                coffee = new WithHotWater(coffee);
                            } else {
                                System.out.println("Item not available");
                                option = input.nextInt();
                            }

                            break;
                        }

                        case 3: {
                            if (isInInventory("Milk")) {  //Checking if Milk is available
                                coffee = new WithMilk(coffee);
                            } else {
                                System.out.println("Item not available");
                                option = input.nextInt();
                            }

                            break;
                        }

                        case 4: {
                            if (isInInventory("Sugar")) { //Checking if Sugar is available
                                coffee = new WithSugar(coffee);
                            } else {
                                System.out.println("Item not available");
                                option = input.nextInt();
                            }

                            break;
                        }
                        case 5: {
                            if (isInInventory("Whipped Cream")) { //Checking if Whipped Cream is available
                                coffee = new WithWhippedCream(coffee);
                            } else {
                                System.out.println("Item not available");
                                option = input.nextInt();
                            }

                            break;
                        }
                        default: {
                            if (option != 0) System.out.println("Please enter valid option.");
                            break;
                        }
                    }
                }

                order.addCoffee(coffee);
                updateInventory(coffee);

                if (customers.containsKey(name)) {
                    addCustomerPoints(coffee, name, customerLoyalty);
                }

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }

        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * Method Description Line 1: In this method it declares a Map called inventory and assigns it to the inventory inside the filepath file.
     *
     * @param - filePath: Name of the file used to read the inventory.
     * @return - inventory: Map assigned to the inventory inside the filepath
     * @throws - exception: Exception is thrown if there is any errors when reading the file
     */
    private static Map<String, Integer> readInventory(String filePath) { // Reads the file for Inventory and assigns it to inventory Map
        Map<String, Integer> inventory = new HashMap<>();
        int quantity;
        String ingredient;
        String[] storing;

        try {
            File file = new File(filePath);
            Scanner scnr = new Scanner(file);

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                storing = line.split(" = ");
                ingredient = storing[0];
                quantity = Integer.parseInt(storing[1]);
                inventory.put(ingredient, quantity); // Adding the ingredient and quantity from the file to the inventory Map
            }

        } catch (Exception e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }

        return inventory;
    }

    /**
     * Method Description Line 1: This method is used to add anything to the inventory or just update the inventory from the inventory map to the inventory file.
     *
     * @param - inventory: Map that contains all the ingredients and quantities.
     * @param - filePath: Name of the file used to read the inventory.
     * @throws - exception: Exception is thrown if there is any errors when writing to the file
     */
    private static void writeInventory(Map<String, Integer> inventory, String filePath) {
        Scanner input = new Scanner(System.in);
        String choice;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            do {
                System.out.println("Would you like to add a new item to the inventory? Y/N");
                String answer = input.nextLine();
                if (!answer.equalsIgnoreCase("N")) {
                    System.out.println("What would you like to add to the inventory.");
                    String add = input.nextLine();
                    System.out.println("What is the quantity of " + add);
                    int quantity = input.nextInt();
                    inventory.put(add, quantity); // Adding the new ingredient, and it's quantity to the map
                    input.nextLine();

                    writer.write(add + " = " + quantity); // Writing the new ingredient and quantity to the file
                    writer.newLine();

                    writer.flush();

                    readInventory(inventoryFile); // Calling readInventory method to add the new ingredient and quantity from the file to the inventory map
                }

                for (Map.Entry<String, Integer> entry : inventory.entrySet()) { // Enhanced for-loop to loop the inventory map print the updated inventory to the file
                    String ingredient = entry.getKey();
                    int amount = entry.getValue();
                    writer.write(ingredient + " = " + amount);
                    writer.newLine();
                }

                System.out.println("Is there more modifications you would like to make?");
                choice = input.next();

                writer.flush();
            } while (!choice.equalsIgnoreCase("N"));

        } catch (Exception e) {
            System.out.println("Error writing to inventory " + e.getMessage());
        }
        System.out.println("Successfully updated inventory.\n");
    }

    /**
     * Method Description Line 1: This method checks if an ingredient is available
     *
     * @param - String i: Name of the specific ingredient to be checked
     * @return- returns true if the item is avaliable, else will return false
     */
    private static boolean isInInventory(String i) {
        Integer amount = inventory.get(i);
        if (inventory.containsKey(i) && amount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Method Description Line 1: This method is used to update any ingredient used in the orders
     *
     * @param - Coffee object: The Coffee object used get the ingredients and decrement each one the inventory
     */
    private static void updateInventory(Coffee coffee) {
        List<String> ingredients = coffee.getIngredients(); // Gets the ingredients used in the coffee object

        for (String ingredient : ingredients) {
            if (inventory.containsKey(ingredient)) {
                inventory.replace(ingredient, inventory.get(ingredient) - 1);
            }
        }
    }

    /**
     * Method Description Line 1: This method is used to print the inventory map
     */
    private static void printInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) { // Enhanced for-loop to loop the inventory map and printing them to the console
            String ingredient = entry.getKey();
            int amount = entry.getValue();
            System.out.println(ingredient + " = " + amount);
        }
    }

    /**
     * Method Description Line 1: This method prints the log to the filePath file
     *
     * @param - filePath: Name of the file used to write the log.
     * @throws - Exception thrown if there is an error writing to the filepath file.
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            writer.flush();
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Method Description Line 1: This method is used to add a new customer to the customer inventory.
     *
     * @param - customerFile: Name of the file used to write to the customer file.
     * @throws - Exception thrown if there is an error writing to the customerFile file.
     */
    private static void addCustomer(String customerFile) {
        Scanner input = new Scanner(System.in);
        System.out.println("Every order you make will add points to your account, you will be able to redeem those points for great prizes!!");
        System.out.println("What is your name? ");
        String name = input.nextLine();
        customers.put(name, 0);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile, true))) {
            writer.write(name + " " + 0);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println("Error adding new customer to customer loyalty file.");
        }

        System.out.println("Great you have been added, order now to start getting points!!!\n");
    }

    /**
     * Method Description Line 1: This method is used to add points to an existing customer.
     *
     * @param - customerFile: Name of the file used to write to the customer file.
     * @param - coffee: The coffee object used to get the cost.
     * @param - name: name of the customer.
     * @throws - Exception thrown if there is an error writing to the customerFile file.
     */
    private static void addCustomerPoints(Coffee coffee, String name, String customerFile) {
        int updatePoints;
        if (coffee.getCost() >= 3) {
            int points = customers.get(name);
            updatePoints = points + 15;
            customers.put(name, updatePoints);
        } else if (coffee.getCost() >= 2) {
            int points = customers.get(name);
            updatePoints = points + 10;
            customers.put(name, updatePoints);
        } else if (coffee.getCost() >= 1) {
            int points = customers.get(name);
            updatePoints = points + 5;
            customers.put(name, updatePoints);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile))) {
            for (Map.Entry<String, Integer> entry : customers.entrySet()) {
                String ingredient = entry.getKey();
                int amount = entry.getValue();

                writer.write(ingredient + " " + amount);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error adding customer points to customer loyalty file: " + e.getMessage());
        }
    }

    /**
     * Method Description Line 1: This method is used to check an existing customers points.
     */
    private static void customerPoints() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = input.nextLine();
        if (customers.containsKey(name)) {
            int points = customers.get(name);
            System.out.println("You " + name + " have " + points + " points!!");
        } else {
            System.out.println("No name found in system. Order today to get points!");
        }
    }

    /**
     * Method Description Line 1: In this method it reads the customer file right when the program starts.
     *
     * @param - customerFile: Name of the file used to write to the customer file.
     * @throws - Exception thrown if there is an error reading from the customerFile file.
     */
    private static Map<String, Integer> readCustomerFile(String customerFile) {
        String firstName;
        String lastName;
        Integer points;
        String[] storing;
        try {
            File file = new File(customerFile);
            Scanner scnr = new Scanner(file);

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                storing = line.split(" ");
                firstName = storing[0];
                lastName = storing[1];
                points = Integer.valueOf(storing[2]);
                customers.put(firstName + " " + lastName, points); // Adding the full name and points from the file to the customer Map.
            }

        } catch (Exception e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return customers;
    }

    /**
     * Method Description Line 1: In this method it allows an existing customer redeem their points for prizes.
     *
     * @param - customerFile: Name of the file used to write to the customer file.
     * @throws - Exception thrown if there is an error writing to the customerFile file.
     */
    private static void redeemPoints(String customerFile) {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = input.nextLine();

        if (customers.containsKey(name)) {
            boolean loop = true;
            while (loop) {
                System.out.println("1. Free Drink = 4 points");
                System.out.println("2. Free limited addition cup = 5 points");
                System.out.println("3. 10$ Gift Card = 6 points");
                System.out.println("4. Coffee Co merchandise = 7 points");
                System.out.println("5. Exit gift shop");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        if (customers.containsKey(name) && customers.get(name) >= 4) {
                            customers.put(name, customers.get(name) - 4);
                            System.out.println("Congratulations! Enjoy your FREE DRINK!!");
                        } else {
                            System.out.println("Not enough points. Order now to earn MORE!!");
                        }
                        break;
                    case 2:
                        if (customers.containsKey(name) && customers.get(name) >= 5) {
                            customers.put(name, customers.get(name) - 5);
                            System.out.println("WOW! You've collected a limited edition cup!!");
                        } else {
                            System.out.println("Not enough points. Order now to get more!!");
                        }

                        break;
                    case 3:
                        if (customers.containsKey(name) && customers.get(name) >= 6) {
                            customers.put(name, customers.get(name) - 6);
                            System.out.println("Awesome! Enjoy your gift card!!!");
                        } else {
                            System.out.println("Not enough points. Order now to get more!!");
                        }

                        break;
                    case 4:
                        if (customers.containsKey(name) && customers.get(name) >= 7) {
                            customers.put(name, customers.get(name) - 7);
                            System.out.println("WOOHOO! Awesome merchandise!!");
                        } else {
                            System.out.println("Not enough points. Order now to get more!!");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting gift shop");
                        loop = false;
                        break;
                    default:
                        System.out.println("Please enter one of the options listed above");
                        break;
                }
            }

        } else {
            System.out.println("No name found in system. Make an account today and earn points!\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile))) {
            for (Map.Entry<String, Integer> entry : customers.entrySet()) {
                String ingredient = entry.getKey();
                int amount = entry.getValue();

                writer.write(ingredient + " " + amount);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error updating updating redeemed points from customer loyalty file: " + e.getMessage());
        }

    }
}