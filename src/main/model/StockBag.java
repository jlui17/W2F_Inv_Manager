package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

// Represents a stock bag with a bag number, size of stock, total quantity and which game it's for
public class StockBag implements Saveable {
    private String description;
    private int number;
    private int size;
    private int quantity;
    private String game;

    /* REQUIRES: number is of length 4 and is unique
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

    public String toString() {
        String bagInfo = description + "|" + number + "|" + size + "|" + quantity + "|" + game;
        return bagInfo;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(description);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(number);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(size);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(quantity);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StockBag stockBag = (StockBag) o;
        return number == stockBag.number
                &&
                size == stockBag.size
                &&
                quantity == stockBag.quantity
                &&
                Objects.equals(description, stockBag.description)
                &&
                Objects.equals(game, stockBag.game);
    }
}
