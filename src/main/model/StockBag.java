package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

import ui.StorageApp;

// Represents a stock bag with a bag number, size of stock, total quantity and which game it's for
public class StockBag extends Storable {
//    private String description;
//    private int number;
//    private int size;
//    private int quantity;
//    private String game;

    /* REQUIRES: size >= 1 and size <= 4
                 quantity is >= 0
       MODIFIES: this
       EFFECTS: Sets this number to number,
                     this size to size,
                     this quantity to quantity,
                     this game to game.
    */
    public StockBag(String description, int number, int size, int quantity, String game) {
        super(description, number, size, quantity, game);

//        this.description = description;
//        this.number = number;
//        this.size = size;
//        this.quantity = quantity;
//        this.game = game;
    }
}
