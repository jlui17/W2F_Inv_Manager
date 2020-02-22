package ui;

import model.Container;
import model.StockBag;
import persistence.Reader;
import persistence.Saveable;
import persistence.Writer;

import java.io.*;
import java.util.List;
import java.util.Scanner;

// Inventory Management Application
public class StorageApp {

    private static final String SAVE_FILE = "./data/save.txt";

    private Scanner input;
    private StockBag bag;
    private String description;
    private int number;
    private int size;
    private int quantity;
    private String game;
    private Container containerA;
    private Container containerB;
    private String choice;

    // EFFECTS: runs the Inventory Management application
    public StorageApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean running = true;
        int command = 0;
        input = new Scanner(System.in);
        loadContainers();
        System.out.println("Welcome to the Inventory Management Application!");
        while (running) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            if (command == 5) {
                running = false;
            } else {
                processCommand(command);
            }
        }
        saveContainers();
        System.out.println("Exiting... goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadContainers() {
        try {
            List<Container> containers = Reader.readContainers(new File(SAVE_FILE));
            containerA = containers.get(0);
            containerB = containers.get(1);
        } catch (IOException e) {
            containerA = new Container();
            containerB = new Container();
        }
    }

    // EFFECTS: saves state of chequing and savings accounts to ACCOUNTS_FILE
    private void saveContainers() {
        try {
            Writer writer = new Writer(new File(SAVE_FILE));
            for (int i = 1; i <= containerA.getSize(); i++) {
                writer.write(containerA.getBag(i));
            }
            writer.nextContainer();
            for (int i = 1; i <= containerB.getSize(); i++) {
                writer.write(containerB.getBag(i));
            }
            writer.close();
            System.out.println("Accounts saved to file " + SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + SAVE_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        // this is due to a programming error
        }
    }

    private void displayMenu() {
        System.out.println("Select one of the following:");
        System.out.println("Press 1 to record a stock bag.");
        System.out.println("Press 2 to move a stock bag from one container to another.");
        System.out.println("Press 3 to delete a stock bag.");
        System.out.println("Press 4 to view a container.");
        System.out.println("Press 5 to exit.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int input) {
        if (input == 1) {
            recordStockBag();
        } else if (input == 2) {
            moveStockBag();
        } else if (input == 3) {
            deleteStockBag();
        } else if (input == 4) {
            viewContainer();
        } else {
            System.out.println("Not a valid input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: records a stock bag and adds it to a container
    private void recordStockBag() {
        System.out.print("Please enter the name of the prize: ");
        description = input.nextLine();
        System.out.print("\nPlease enter a bag ID number (4 integers long): ");
        number = input.nextInt();
        System.out.print("\nPlease enter a prize size (1 - 4): ");
        size = input.nextInt();
        System.out.print("\nPlease enter the quantity of prizes in the bag: ");
        quantity = input.nextInt();
        input.nextLine();
        System.out.print("\nPlease specify which game this bag is for: ");
        game = input.nextLine();
        bag = new StockBag(description, number, size, quantity, game);

        System.out.println("\nSelect a container to store the bag in: A or B?");
        choice = input.nextLine().toLowerCase();
        storeIn(choice);
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: stores a stock bag in a container
    private void storeIn(String c) {
        if (c.equals("a")) {
            containerA.addBag(bag);
            System.out.println("Recorded bag: " + bag.toString() + " and stored in Container A.");
        } else if (c.equals("b")) {
            containerB.addBag(bag);
            System.out.println("Recorded bag: " + bag.toString() + " and stored in Container B.");
        } else {
            System.out.println("Not a valid input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: moves a stock bag from one container to the other
    private void moveStockBag() {
        System.out.println("Select a container to move a stock bag from: A or B?");
        choice = input.nextLine();

        if (choice.toLowerCase().equals("a")) {
            System.out.println("Please specify a bag ID to move (4 int long).");
            number = input.nextInt();

            if (containerA.contains(number)) {
                moving(number, containerA, containerB);
            } else {
                System.out.println("That bag doesn't exist in Container A (4 int long).");
            }
        } else if (choice.toLowerCase().equals("b")) {
            System.out.println("Please specify a bag ID to move.");
            number = input.nextInt();

            if (containerA.contains(number)) {
                moving(number, containerB, containerA);
            } else {
                System.out.println("That bag doesn't exist in Container B.");
            }
        } else {
            System.out.println("Not a valid input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: moves a stock bag from one container to the other
    private void moving(int id, Container thisContainer, Container otherContainer) {
        bag = thisContainer.getBag(thisContainer.getIndexOfBag(id));
        thisContainer.removeBag(id);
        otherContainer.addBag(bag);
    }

    // MODIFIES: this
    // EFFECTS: deletes a stock bag from a container
    private void deleteStockBag() {
        System.out.println("Please specify which container you want to remove a bag from: A or B?");
        choice = input.nextLine().toLowerCase();

        if (choice.equals("a")) {
            System.out.println("Please specify which stock bag you want to delete (ID): ");
            number = input.nextInt();

            if (containerA.contains(number)) {
                containerA.removeBag(number);
            } else {
                System.out.println("That bag doesn't exist in Container A.");
            }
        } else if (choice.equals("b")) {
            System.out.println("Please specify which stock bag you want to delete (ID): ");
            number = input.nextInt();

            if (containerB.contains(number)) {
                containerB.removeBag(number);
            } else {
                System.out.println("That bag doesn't exist in Container B.");
            }
        }
    }

    // EFFECTS: prints a map of a container
    private void viewContainer() {
        System.out.print("\nPlease specify which container you want to view: A or B?\n");
        choice = input.next().toLowerCase();
        if (choice.equals("a")) {
            System.out.println(containerA.toString());
        } else if (choice.equals("b")) {
            System.out.println(containerB.toString());
        } else {
            System.out.println("\nNot a valid input.");
        }
    }

}
