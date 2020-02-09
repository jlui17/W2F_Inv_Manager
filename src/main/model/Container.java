package model;

import java.util.ArrayList;

// Represents a storage container that stores stock bags.
// The container holds up to 9 bags in a stack of 3 bags and 3 rows and stacks (3x3).
// Position 1 is the bottom right corner, position 9 is the top left corner.
public class Container {
    private static final int MAX_SIZE = 9;

    private ArrayList<StockBag> storage;
    private int indexOfStockBag;

    // EFFECTS: creates a new empty container
    public Container() {
        storage = new ArrayList<>();
    }

    // REQUIRES: bag ID isn't same as any other bag in storage
    // MODIFIES: this
    // EFFECTS: adds stock bag into storage
    public void addBag(StockBag bag) {
        if (!(storage.size() >= MAX_SIZE)) {
            storage.add(bag);
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
}
