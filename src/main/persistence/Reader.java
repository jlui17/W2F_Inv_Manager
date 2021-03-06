// Code based off of Reader class in TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/master/src/main/ca/ubc/cpsc210/bank/persistence/Reader.java

package persistence;

import model.Container;
import model.Exceptions.DuplicateIDException;
import model.StockBag;
import model.Storable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";

    public Reader() {
        // do nothing
    }

    // EFFECTS: returns a list of containers parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Container> readContainers(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of containers parsed from list of strings
    // where each string contains data for one account
    private static ArrayList<Container> parseContent(List<String> fileContent) {
        // Container containerA = new Container();     // TODO: ...
        // Container containerB = new Container();     // TODO: ...
        // ArrayList<Container> containers = new ArrayList<>(Arrays.asList(containerA, containerB));
        ArrayList<Container> containers = new ArrayList<>();
        // boolean flag = false;
        Container temp = null;

        try {
            for (String line : fileContent) {
                if (line.charAt(0) == '#' || temp == null) {
                    if (temp != null) {
                        containers.add(temp);
                    }
                    temp = new Container(line.charAt(2));
                    System.out.println(line.charAt(2));
                } else {
                    ArrayList<String> lineComponents = splitString(line);
                    temp.addItem(parseStorable(lineComponents));
                }
            }
            if (temp != null) {
                containers.add(temp);
            }
            return containers;
        } catch (DuplicateIDException e) {
            return containers;
        }
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 5 where element 0 represents the
    // description of the bag to be constructed, element 1 represents
    // the id number, elements 2 represents the size and element 3 represents
    // the quantity of prizes in the bag, and element 4 represents the game
    // EFFECTS: returns an StockBag constructed from components
    private static Storable parseStorable(List<String> components) {
        String description = components.get(0);
        int number = Integer.parseInt(components.get(1));
        int size = Integer.parseInt(components.get(2));
        int quantity = Integer.parseInt(components.get(3));
        String game = components.get(4);
        return new StockBag(description, number, size, quantity, game);
    }
}