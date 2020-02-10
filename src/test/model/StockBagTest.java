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
        assertEquals(1234, bag.getBagNumber());
        assertEquals(4, bag.getSize());
        assertEquals(400, bag.getQuantity());
        assertEquals("Birthday", bag.getGame());
    }

    @Test
    void testChangeGame() {
        bag.changeGame("Basketball");
        assertEquals("Basketball", bag.getGame());
    }

    @Test
    void testToString() {
        bagInfo = "Kaws|1234|4|400|Birthday";
        assertEquals(bagInfo, bag.toString());
    }
}