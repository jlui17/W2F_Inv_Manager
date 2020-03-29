package model;


import model.Exceptions.DuplicateIDException;

import java.util.ArrayList;

// Represents a storage container that stores stock bags.
// The container holds up to 9 bags in a stack of 3 bags and 3 rows and stacks (3x3).
// Position 1 is the bottom right corner, position 9 is the top left corner.
// 9 | 8 | 7
// 6 | 5 | 4
// 3 | 2 | 1
public class Container {
    private static final int MAX_SIZE = 9;

    private ArrayList<Storable> storage;
    private int indexOfItem;
    private String container;
    private ArrayList<Integer> listOfIDs;

    // EFFECTS: creates a new empty container
    public Container() {
        storage = new ArrayList<>();
        listOfIDs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds stock bag into storage
    public void addItem(Storable item) throws DuplicateIDException {
        if (!(storage.size() >= MAX_SIZE)) {
            if (!listOfIDs.contains(item.getNumber())) {
                storage.add(item);
                listOfIDs.add(item.getNumber());
            } else {
                throw new DuplicateIDException();
            }
        }
    }

    // EFFECTS: returns position of bag in storage specified by bag number, else returns -1 if bag not in storage
    public int getIndexOfItem(int id) {
        indexOfItem = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getNumber() == id) {
                indexOfItem = i + 1;
            }
        }
        return indexOfItem;
    }

    // REQUIRES: 1 <= index <= storage size
    // EFFECTS: returns bag of specified index in storage
    public Storable getItem(int index) {
        return storage.get(index - 1);
    }

    // REQUIRES: 1 <= 0 <= storage size
    // MODIFIES: this
    // EFFECTS: removes bag from storage
    public void removeItem(int id) {
        indexOfItem = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getNumber() == id) {
                indexOfItem = i;
            }
        }

        if (indexOfItem != -1) {
            storage.remove(indexOfItem);
            for (int i = 0; i < listOfIDs.size(); i++) {
                if (listOfIDs.get(i) == id) {
                    listOfIDs.remove(i);
                }
            }
        }
    }

    // EFFECTS: returns amount of stock bags in storage
    public int getAmountOfItems() {
        return storage.size();
    }

    // EFFECTS: returns true if storage is empty
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    // EFFECTS: returns true if bag with specified id is in storage
    public boolean contains(int id) {
        for (Storable i : storage) {
            if (i.getNumber() == id) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: prints a view of a container
    public String toString() {
        container = "";
        int limit = storage.size() % 3;

        if (storage.size() == 9) {
            printMaxRows();
        } else {
            for (int i = storage.size(); i > 0; i--) {
                if (limit == 0) {
                    container += "<br>";
                    limit = 3;
                }
                if (!(i == 1 || i == 4 || i == 7)) {
                    container += storage.get(i - 1).getNumber() + "|";
                } else {
                    container += storage.get(i - 1).getNumber();
                }
                limit--;
            }
        }
        return container;
    }

    // prints a view of a container when container is full
    public void printMaxRows() {
        int limit = 3;
        for (int i = 9; i > 0; i--) {
            if (limit == 0) {
                container += "<br>";
                limit = 3;
            }
            if (!(i == 1 || i == 4 || i == 7)) {
                container += storage.get(i - 1).getNumber() + "|";
            } else {
                container += storage.get(i - 1).getNumber();
            }
            limit--;
        }
    }

    // EFFECTS: returns number of storage bags in container
    public int getSize() {
        return storage.size();
    }
}
