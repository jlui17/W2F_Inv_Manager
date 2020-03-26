package model;


import model.Exceptions.DuplicateIDException;

import java.util.ArrayList;
import java.util.List;

// Represents a storage container that stores stock bags.
// The container holds up to 9 bags in a stack of 3 bags and 3 rows and stacks (3x3).
// Position 1 is the bottom right corner, position 9 is the top left corner.
// 9 | 8 | 7
// 6 | 5 | 4
// 3 | 2 | 1
public class Container {
    private static final int MAX_SIZE = 9;

    private ArrayList<StockBag> storage;
    private int indexOfStockBag;
    private String container;
    private ArrayList<Integer> listOfIDs;

    // EFFECTS: creates a new empty container
    public Container() {
        storage = new ArrayList<>();
        listOfIDs = new ArrayList<>();
    }

    // REQUIRES: bag ID isn't same as any other bag in storage
    // MODIFIES: this
    // EFFECTS: adds stock bag into storage
    public void addBag(StockBag bag) throws DuplicateIDException{
        if (!(storage.size() >= MAX_SIZE)) {
            if (!listOfIDs.contains(bag.getBagNumber())) {
                storage.add(bag);
                listOfIDs.add(bag.getBagNumber());
            } else {
                throw new DuplicateIDException();
            }
        }
    }

    // EFFECTS: returns position of bag in storage specified by bag number, else returns -1 if bag not in storage
    public int getIndexOfBag(int id) {
        indexOfStockBag = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getBagNumber() == id) {
                indexOfStockBag = i + 1;
            }
        }
        return indexOfStockBag;
    }

    // REQUIRES: 1 <= index <= storage size
    // EFFECTS: returns bag of specified index in storage
    public StockBag getBag(int index) {
        return storage.get(index - 1);
    }

    // REQUIRES: 1 <= index <= storage size
    // EFFECTS: returns bag ID of specified bag
    public int getBagID(int index) {
        return storage.get(index - 1).getBagNumber();
    }

    // REQUIRES: 1 <= 0 <= storage size
    // MODIFIES: this
    // EFFECTS: removes bag from storage
    public void removeBag(int id) {
        indexOfStockBag = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getBagNumber() == id) {
                indexOfStockBag = i;
            }
        }

        if (indexOfStockBag != -1) {
            storage.remove(indexOfStockBag);
            for (int i = 0; i < listOfIDs.size(); i++) {
                if (listOfIDs.get(i) == id) {
                    listOfIDs.remove(i);
                }
            }
        }
    }

    // REQUIRES: storage isn't full, 1 <= pos <= storage size
    // MODIFIES: this
    // EFFECTS: inserts bag into specified position
    public void insertBag(int pos, StockBag bag) {
        storage.add(pos - 1, bag);
    }

    // EFFECTS: returns amount of stock bags in storage
    public int getAmountOfStockBags() {
        return storage.size();
    }

    // EFFECTS: returns true if storage is empty
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    // EFFECTS: returns true if bag with specified id is in storage
    public boolean contains(int id) {
        for (StockBag b : storage) {
            if (b.getBagNumber() == id) {
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
                    container += storage.get(i - 1).getBagNumber() + "|";
                } else {
                    container += storage.get(i - 1).getBagNumber();
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
                container += storage.get(i - 1).getBagNumber() + "|";
            } else {
                container += storage.get(i - 1).getBagNumber();
            }
            limit--;
        }
    }

    // EFFECTS: returns number of storage bags in container
    public int getSize() {
        return storage.size();
    }

    // EFFECTS: returns list of all bags in container
    public List<StockBag> getBags() {
        return storage;
    }
}
