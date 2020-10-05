package ui;

import model.Container;
import model.Exceptions.DuplicateIDException;
import model.Exceptions.InvalidContainerIDException;
import model.StockBag;
import model.Storable;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

// Inventory Management Application
public class StorageApp extends JPanel {

    private static final String SAVE_FILE = "./data/save.txt";
    private int numID = 65;     // (char)65 = 'A', (char)66 = 'B', etc...

//    private Scanner input;
//    private String description;
//    private int number;
//    private int size;
//    private int quantity;
//    private String game;
    private ArrayList<Container> containers;
//    private String choice;

    private JFrame main;
    private String img;
    private JButton record;
    private JButton move;
    private JButton delete;
    private JButton view;
    private JButton save;
    private JButton load;


    // EFFECTS: runs the Inventory Management application
    public StorageApp() {
        initiateStartProcesses();
        initiateRecordButton();
        initiateMoveButton();
        initiateDeleteButton();
        initiateViewButton();
        initiateSaveButton();
        initiateLoadButton();

        addToFrame();
        //runApp();
    }

    private void initiateStartProcesses() {
        containers = new ArrayList<>();
        main = new JFrame("Storage App");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null); //centers in middle of screen
        main.getContentPane().setLayout(new BoxLayout(main.getContentPane(), BoxLayout.Y_AXIS));
    }

    private void initiateRecordButton() {
        record = new JButton("Record an Item");
        record.setAlignmentX(Component.CENTER_ALIGNMENT);
        record.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                recordItem();
            }
        });
    }

    private void initiateMoveButton() {
        move = new JButton("Move an item from one Container to another");
        move.setAlignmentX(Component.CENTER_ALIGNMENT);
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                moveItem();
            }
        });
    }

    private void initiateDeleteButton() {
        delete = new JButton("Delete an Item");
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                deleteItem();
            }
        });
    }

    private void initiateViewButton() {
        view = new JButton("View a Container");
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                String msg = "";
                for (int i = 0; i < containers.size(); i++) {
                    msg += containers.get(i).toString() + "<br>";
                }
                displayMessage(msg);
            }
        });
    }

    private void initiateSaveButton() {
        save = new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                saveContainers();
            }
        });
    }

    private void initiateLoadButton() {
        load = new JButton("Load Containers");
        load.setAlignmentX(Component.CENTER_ALIGNMENT);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound();
                loadContainers();
                displayMessage("Containers loaded.");
            }
        });
    }

    private void addToFrame() {
        img = "C:\\Users\\speew\\IdeaProjects\\project_x8z2b\\data\\logo.png";
        JLabel image = new JLabel(new ImageIcon(img));
        main.add(record);
        main.add(move);
        main.add(delete);
        main.add(view);
        main.add(save);
        main.add(load);
        main.add(image);
        main.pack();
        main.setVisible(true);
    }

    private void playSound() {
        File soundFile = new File("./data/hmm.wav");
        try { // Open an audio input stream.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
              // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
//    private void runApp() {
//        boolean running = true;
//        int command = 0;
//        input = new Scanner(System.in);
//        loadContainers();
//        System.out.println("Welcome to the Inventory Management Application!");
//        while (running) {
//            displayMenu();
//            command = input.nextInt();
//            input.nextLine();
//
//            if (command == 5) {
//                running = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        saveContainers();
//        System.out.println("Exiting... goodbye!");
//    }

    // EFFECTS: reset ID back to 'A' as an int
    public void resetID() {
        numID = 65;
    }

    private void addContainer() {
        Container temp = new Container((char)numID);
        numID++;
        containers.add(temp);
    }

    // MODIFIES: this
    // EFFECTS: loads containers from SAVE_FILE, if that file exists;
    // otherwise start with two empty containers
    private void loadContainers() {
        resetID();
        try {
            List<Container> containers = Reader.readContainers(new File(SAVE_FILE));
            for (Container c : containers) {
                this.containers.add(c);
            }
            numID += containers.size();
            if (this.containers.size() == 0) {
                addContainer();
            }
            System.out.println(this.containers.size());
        } catch (IOException e) {
            addContainer();
            addContainer();
        }
    }

    // EFFECTS: saves state of containers to SAVE_FILE
    private void saveContainers() {
        try {
            Writer writer = new Writer(new File(SAVE_FILE));
            Container temp;

            for (int i = 0; i < containers.size(); i++) {
                temp = containers.get(i);
                writer.nextContainer(temp.getID());
                for (int j = 1; j <= temp.getSize(); j++) {
                    System.out.println(temp.getItem(j));
                    writer.write(temp.getItem(j));
                }
            }

//            for (int i = 1; i <= containerA.getSize(); i++) {
//                writer.write(containerA.getItem(i));
//            }
//            writer.nextContainer();
//            for (int i = 1; i <= containerB.getSize(); i++) {
//                writer.write(containerB.getItem(i));
//            }
            writer.close();
            displayMessage("Containers saved to file " + SAVE_FILE);
        } catch (FileNotFoundException e) {
            displayMessage("Unable to save containers to " + SAVE_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        // this is due to a programming error
        }
    }

//    private void displayMenu() {
//        System.out.println("Select one of the following:");
//        System.out.println("Press 1 to record a stock bag.");
//        System.out.println("Press 2 to move a stock bag from one container to another.");
//        System.out.println("Press 3 to delete a stock bag.");
//        System.out.println("Press 4 to view a container.");
//        System.out.println("Press 5 to exit.");
//    }

    // MODIFIES: this
    // EFFECTS: processes user command
//    private void processCommand(int input) {
//        if (input == 1) {
//            recordStockBag();
//        } else if (input == 2) {
//            moveStockBag();
//        } else if (input == 3) {
//            deleteStockBag();
//        } else if (input == 4) {
//            viewContainer();
//        } else {
//            System.out.println("Not a valid input.");
//        }
//    }

    // MODIFIES: this
    // EFFECTS: records a stock bag and adds it to a container
    private void recordItem() {
        JFrame recordWindow = new JFrame("Record an Item");
        recordWindow.setLocationRelativeTo(null);
        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateRecordFields(recordWindow);

        recordWindow.pack();
        recordWindow.setVisible(true);

        /*System.out.print("Please enter the name of the prize: ");
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
        System.out.println();*/
    }

    // EFFECTS: Initiates texts fields for recordStockBag()
    private void initiateRecordFields(JFrame frame) {
        frame.add(new JLabel("Description of item:"));
        JTextField name = new JTextField();
        frame.add(name);

        frame.add(new JLabel("ID number:"));
        JTextField id = new JTextField();
        frame.add(id);

        frame.add(new JLabel("Size (1-4)"));
        JTextField size = new JTextField();
        frame.add(size);

        frame.add(new JLabel("Quantity:"));
        JTextField quantity = new JTextField();
        frame.add(quantity);

        frame.add(new JLabel("For which game?:"));
        JTextField game = new JTextField();
        frame.add(game);

        frame.add(new JLabel("Store in which container?"));
        JTextField container = new JTextField();
        frame.add(container);

        initiateRecordEnter(frame, name, id, size, quantity, game, container);
    }

    // EFFECTS: initiates enter button in recordItem() and records and stores a storable item
    private void initiateRecordEnter(JFrame frame, JTextField name, JTextField id, JTextField size,
                                     JTextField quantity, JTextField game, JTextField container) {
        JButton enter = new JButton("Record");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Storable item = new StockBag(name.getText(), Integer.parseInt(id.getText()),
                            Integer.parseInt(size.getText()), Integer.parseInt(quantity.getText()), game.getText());
                    storeIn(item, container.getText().toUpperCase());
                } catch (Exception e) {
                    displayMessage("Invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    private void displayMessage(String str) {
        JFrame window = new JFrame("Message");
        window.setLayout(new BorderLayout(5, 5));

        JLabel msg = new JLabel("<html>" + str + "</html>", SwingConstants.CENTER);
        window.setMinimumSize(new Dimension(200, 100));
        window.add(msg, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    // EFFECTS: returns container corresponding to ID
    private Container getContainer(char id) throws InvalidContainerIDException {
        for (int j = 0; j < containers.size(); j++) {
            if (containers.get(j).getID() == id) {
                return containers.get(j);
            }
        }
        throw new InvalidContainerIDException();
    }

    // MODIFIES: this
    // REQUIRES: item is valid
    // EFFECTS: stores an item in a container
    private void storeIn(Storable item, String c) {
        try {
            if (c.length() != 1) {
                displayMessage("Invalid container");
            } else {
                char d = c.charAt(0);
                Container container = getContainer(d);
                container.addItem(item);
                displayMessage("Recorded: " + item.toString() + " and stored in Container " + d + ".");
            }
        } catch (DuplicateIDException e) {
            displayMessage("Bag ID already exists!");
        } catch (InvalidContainerIDException e) {
            displayMessage("Container does not exist!");
        }
    }

    // EFFECTS: initiates text fields for moveItem()
    private void initiateMoveFields(JFrame frame) {
        frame.add(new JLabel("ID number:"));
        JTextField id = new JTextField();
        frame.add(id);

        frame.add(new JLabel("Source container:"));
        JTextField s = new JTextField();
        frame.add(s);
        frame.add(new JLabel("Destination container:"));
        JTextField d = new JTextField();
        frame.add(d);

        initiateMoveEnter(frame, id, s, d);
    }

    // MODIFIES: this
    // EFFECTS: initiates enter button in moveItem() and moves an item
    private void initiateMoveEnter(JFrame frame, JTextField id, JTextField s, JTextField d) {
        JButton enter = new JButton("Move");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (s.getText().length() != 1 || d.getText().length() != 1) {
                        throw new Exception();      // TODO: temporary; implement way of checking if ID is valid
                    }
                    Container containerS = getContainer(s.getText().toUpperCase().charAt(0));
                    Container containerD = getContainer(d.getText().toUpperCase().charAt(0));

                    if (containerS.contains(Integer.parseInt(id.getText()))) {
                        moving(Integer.parseInt(id.getText()), containerS, containerD);
                    } else {
                        displayMessage("Item not found!");
                    }
                } catch (Exception e) {
                    displayMessage("Invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // MODIFIES: this
    // EFFECTS: moves a stock bag from one container to the other
    private void moveItem() {
        JFrame recordWindow = new JFrame("Move an item");
        recordWindow.setLocationRelativeTo(null);
        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateMoveFields(recordWindow);

        recordWindow.pack();
        recordWindow.setVisible(true);

//        System.out.println("Select a container to move a stock bag from: A or B?");
//        choice = input.nextLine();
//
//        if (choice.toLowerCase().equals("a")) {
//            System.out.println("Please specify a bag ID to move (4 int long).");
//            number = input.nextInt();
//
//            if (containerA.contains(number)) {
//                moving(number, containerA, containerB);
//            } else {
//                System.out.println("That bag doesn't exist in Container A (4 int long).");
//            }
//        } else if (choice.toLowerCase().equals("b")) {
//            System.out.println("Please specify a bag ID to move.");
//            number = input.nextInt();
//
//            if (containerA.contains(number)) {
//                moving(number, containerB, containerA);
//            } else {
//                System.out.println("That bag doesn't exist in Container B.");
//            }
//        } else {
//            System.out.println("Not a valid input.");
//        }
    }

    // MODIFIES: this
    // EFFECTS: moves an item from one container to the other
    private void moving(int id, Container thisContainer, Container otherContainer) {
        try {
            Storable item = thisContainer.getItem(thisContainer.getIndexOfItem(id));
            otherContainer.addItem(item);
            thisContainer.removeItem(id);
            displayMessage("Moved " + item.toString() + " from Container " + thisContainer.getID()
                    + " to Container " + otherContainer.getID() + ".");
        } catch (DuplicateIDException e) {
            displayMessage("Can't add bags with duplicate IDs into the same container!");
        }
    }

    // EFFECTS: initiates fields for deleteItem()
    private void initiateDeleteFields(JFrame frame) {
        frame.add(new JLabel("ID number:"));
        JTextField id = new JTextField();
        frame.add(id);

        frame.add(new JLabel("From which container?"));
        JTextField c = new JTextField();
        frame.add(c);

        initiateDeleteEnter(frame, id, c);
    }

    // MODIFIES: this
    // EFFECTS: initiates enter button for deleteItem() and deletes an item
    private void initiateDeleteEnter(JFrame frame, JTextField id, JTextField c) {
        JButton enter = new JButton("Delete");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    deleteItemFrom(c.getText().toUpperCase(), Integer.parseInt(id.getText()));
                } catch (Exception e) {
                    displayMessage("Invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: deletes item from container
    private void deleteItemFrom(String c, int id) throws InvalidContainerIDException {
        if (c.length() != 1) {
            throw new RuntimeException();   // TODO: temporary; implement this as helper function
        }
        char d = c.charAt(0);
        Container container = getContainer(d);
        displayRemovedMessage(container, id);
        container.removeItem(id);
    }

    // displays message when item is removed from a container
    private void displayRemovedMessage(Container container, int id) {
        displayMessage("Removed " + container.getItem(container.getIndexOfItem(id)).toString()
                    + " from container " + container.getID());
    }

    // MODIFIES: this
    // EFFECTS: deletes an item from a container
    private void deleteItem() {
        JFrame deleteWindow = new JFrame("Delete an Item");
        deleteWindow.setLocationRelativeTo(null);
        deleteWindow.getContentPane().setLayout(new BoxLayout(deleteWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateDeleteFields(deleteWindow);

        deleteWindow.pack();
        deleteWindow.setVisible(true);
//        System.out.println("Please specify which container you want to remove a bag from: A or B?");
//        choice = input.nextLine().toLowerCase();
//
//        if (choice.equals("a")) {
//            System.out.println("Please specify which stock bag you want to delete (ID): ");
//            number = input.nextInt();
//
//            if (containerA.contains(number)) {
//                containerA.removeBag(number);
//            } else {
//                System.out.println("That bag doesn't exist in Container A.");
//            }
//        } else if (choice.equals("b")) {
//            System.out.println("Please specify which stock bag you want to delete (ID): ");
//            number = input.nextInt();
//
//            if (containerB.contains(number)) {
//                containerB.removeBag(number);
//            } else {
//                System.out.println("That bag doesn't exist in Container B.");
//            }
//        }
    }

    // EFFECTS: prints a map of a container
//    private void viewContainer() {
//        System.out.print("\nPlease specify which container you want to view: A or B?\n");
//        choice = input.next().toLowerCase();
//        if (choice.equals("a")) {
//            System.out.println(containerA.toString());
//        } else if (choice.equals("b")) {
//            System.out.println(containerB.toString());
//        } else {
//            System.out.println("\nNot a valid input.");
//        }
//    }

}
