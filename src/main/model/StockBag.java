package model;

// Represents a stock bag with a bag number, size of stock, total quantity and which game it's for
public class StockBag {
    private String description;
    private int number;
    private int size;
    private int quantity;
    private String game;

    /* REQUIRES: number is of length 4
                 size >= 1 and size <= 4
                 quantity is >= 0
       MODIFIES: this
       EFFECTS: Sets this number to number,
                     this size to size,
                     this quantity to quantity,
                     this game to game.
     */
    public StockBag(String description, int number, int size, int quantity, String game) {
        this.description = description;
        this.number = number;
        this.size = size;
        this.quantity = quantity;
        this.game = game;
    }

    // EFFECTS: changes game of stock bag
    public void changeGame(String game) {
        this.game = game;
    }

    // EFFECTS: returns description of stock
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns stock bag number
    public int getBagNumber() {
        return number;
    }

    // EFFECTS: returns size of stock
    public int getSize() {
        return size;
    }

    // EFFECTS: returns total quantity of stock
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns designated game
    public String getGame() {
        return game;
    }
}
