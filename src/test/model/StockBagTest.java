package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockBagTest {
    StockBag bag;
    String bagInfo;

    @BeforeEach
    void runBefore() {
        bag = new StockBag("Kaws", 1234, 4, 400, "Birthday");
    }

    @Test
    void testConstructor() {
        assertEquals("Kaws", bag.getDescription());
        assertEquals(1234, bag.getNumber());
        assertEquals(4, bag.getSize());
        assertEquals(400, bag.getQuantity());
        assertEquals("Birthday", bag.getGame());
    }

    @Test
    void testToString() {
        bagInfo = "Kaws|1234|4|400|Birthday";
        assertEquals(bagInfo, bag.toString());
    }

    @Test
    void testEquals() {
        StockBag bag1 = new StockBag("Kaws", 1111, 4, 100, "Basketball");
        StockBag bag2 = new StockBag("Kaws", 1111, 4, 100, "Basketball");
        assertTrue(bag1.equals(bag2));
        StockBag bag3 = new StockBag("Not Kaws", 2222, 3, 10, "Birthday");
        assertFalse(bag1.equals(bag3));
        assertTrue(bag1.equals(bag1));
        assertFalse(bag1.equals(null));
        assertFalse(bag1.equals(new Container('A')));
        assertFalse(bag1.equals(new StockBag("kdanalwkdn", 1111, 4 ,100,"Basketball")));
        assertFalse(bag1.equals(new StockBag("Kaws",1111, 4, 100, "Akdnawlkd")));
        assertFalse(bag1.equals(new StockBag("Kaws",1234, 4, 100, "Basketball")));
        assertFalse(bag1.equals(new StockBag("Kaws",1111, 1, 100, "Basketball")));
        assertFalse(bag1.equals(new StockBag("Kaws",1111, 4, 8, "Basketball")));
    }
}