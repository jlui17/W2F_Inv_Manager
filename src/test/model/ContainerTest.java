package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container container;
    StockBag bag;
    StockBag bag1;
    StockBag bag2;
    String containerInfo;

    @BeforeEach
    void runBefore() {
        container = new Container();
        bag = new StockBag("BareBear", 1111, 2, 200, "Toilets");
        bag1 = new StockBag("Space Matter", 2222, 3, 100, "Basketball");
        bag2 = new StockBag("Dino", 3333, 1, 50, "Bowler Roller");
    }

    @Test
    void testConstructor() {
        assertEquals(0, container.getAmountOfStockBags());
    }

    @Test
    void testAddSingleBag() {
        container.addBag(bag);
        assertEquals(1, container.getAmountOfStockBags());
    }

    @Test
    void testAddMultipleBags() {
        container.addBag(bag);
        container.addBag(bag1);
        assertEquals(2, container.getAmountOfStockBags());

        container.addBag(bag);
        container.addBag(bag);
        container.addBag(bag);
        container.addBag(bag);
        container.addBag(bag);
        container.addBag(bag);
        container.addBag(bag);
        assertEquals(9, container.getAmountOfStockBags());
        container.addBag(bag);
        assertEquals(9, container.getAmountOfStockBags());
    }

    @Test
    void testRemoveBag() {
        container.addBag(bag);
        container.addBag(bag1);
        container.addBag(bag2);
        assertEquals(2, container.getIndexOfBag(2222));
        container.removeBag(2222);
        assertEquals(-1, container.getIndexOfBag(2222));
        container.removeBag(4444);
        assertEquals(2, container.getAmountOfStockBags());
    }

    @Test
    void testInsertBag() {
        container.addBag(bag);
        container.addBag(bag1);
        assertEquals(2222, container.getBagID(2));
        assertEquals(bag1, container.getBag(2));
        container.insertBag(2, bag2);
        assertEquals(3333, container.getBagID(2));
        assertEquals(2222, container.getBagID(3));
        assertEquals(bag2, container.getBag(2));
        assertEquals(bag1, container.getBag(3));
    }

    @Test
    void testIsEmpty() {
        assertTrue(container.isEmpty());
        container.addBag(bag);
        assertFalse(container.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(container.contains(1111));
        container.addBag(bag);
        assertTrue(container.contains(1111));
        container.addBag(bag1);
        container.addBag(bag2);
        assertTrue(container.contains(3333));
    }

    @Test
    void testToString() {
    containerInfo = "";
    assertEquals(containerInfo, container.toString());

    container.addBag(bag);
    container.addBag(bag1);
    containerInfo = "2222|1111";
    assertEquals(containerInfo, container.toString());

    container.addBag(bag2);
    container.addBag(bag2);
    container.addBag(bag2);
    container.addBag(bag2);
    container.addBag(bag2);
    container.addBag(bag2);
    containerInfo = "3333|3333\n3333|3333|3333\n3333|2222|1111";
    assertEquals(containerInfo, container.toString());

    container.addBag(bag2);
    containerInfo = "3333|3333|3333\n3333|3333|3333\n3333|2222|1111";
    assertEquals(containerInfo, container.toString());
    }
}
