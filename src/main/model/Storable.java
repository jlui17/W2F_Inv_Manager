package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

// Represents any object that is storable in a container
public abstract class Storable implements Saveable {
    private String description;
    private int number;
    private int size;
    private int quantity;
    private String game;

    /* REQUIRES: size >= 1 and size <= 4
                 quantity is >= 0
       MODIFIES: this
       EFFECTS: Sets this number to number,
                     this size to size,
                     this quantity to quantity,
                     this game to game.
    */
    public Storable(String description, int number, int size, int quantity, String game) {
        this.description = description;
        this.number = number;
        this.size = size;
        this.quantity = quantity;
        this.game = game;
    }

    // EFFECTS: returns description of stock
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns stock bag number
    public int getNumber() {
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
        Storable storable = (Storable) o;
        return number == storable.number
                &&
                size == storable.size
                &&
                quantity == storable.quantity
                &&
                Objects.equals(description, storable.description)
                &&
                Objects.equals(game, storable.game);
    }
}
