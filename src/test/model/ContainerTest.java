package model;

import model.Exceptions.DuplicateIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    Container container;
    StockBag bag;
    StockBag bag1;
    StockBag bag2;
    StockBag bag3;
    StockBag bag4;
    StockBag bag5;
    StockBag bag6;
    StockBag bag7;
    StockBag bag8;
    String containerInfo;

    @BeforeEach
    void runBefore() {
        container = new Container();
        bag = new StockBag("BareBear", 1111, 2, 200, "Toilets");
        bag1 = new StockBag("Space Matter", 2222, 3, 100, "Basketball");
        bag2 = new StockBag("Dino", 3333, 1, 50, "Bowler Roller");
        bag3 = new StockBag("aa", 4444, 1, 1, "aksdja");
        bag4 = new StockBag("aa", 5555, 1, 1, "aksdja");
        bag5 = new StockBag("aa", 6666, 1, 1, "aksdja");
        bag6 = new StockBag("aa", 7777, 1, 1, "aksdja");
        bag7 = new StockBag("aa", 8888, 1, 1, "aksdja");
        bag8 = new StockBag("aa", 9999, 1, 1, "aksdja");
    }

    @Test
    void testConstructor() {
        assertEquals(0, container.getAmountOfItems());
    }

    @Test
    void testAddSingleBag() {
        try {
            container.addItem(bag);
            assertEquals(1, container.getAmountOfItems());
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }

    @Test
    void testAddDuplicateBag() {
        try {
            container.addItem(bag);
            container.addItem(bag);
        } catch (DuplicateIDException e) {
            // expected
        }
    }

    @Test
    void testAddMultipleBags() {
        try {
            assertEquals(0, container.getSize());
            container.addItem(bag);
            assertEquals(1, container.getSize());
            container.addItem(bag1);
            assertEquals(2, container.getAmountOfItems());

            container.addItem(bag2);
            container.addItem(bag3);
            container.addItem(bag4);
            container.addItem(bag5);
            container.addItem(bag6);
            container.addItem(bag7);
            container.addItem(bag8);
            assertEquals(9, container.getAmountOfItems());
            container.addItem(bag);
            assertEquals(9, container.getAmountOfItems());
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }

    @Test
    void testRemoveBag() {
        try {
            container.addItem(bag);
            container.addItem(bag1);
            container.addItem(bag2);
            assertEquals(2, container.getIndexOfItem(2222));
            container.removeItem(2222);
            assertEquals(-1, container.getIndexOfItem(2222));
            container.removeItem(4444);
            assertEquals(2, container.getAmountOfItems());
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }

    @Test
    void testIsEmpty() {
        try {
            assertTrue(container.isEmpty());
            container.addItem(bag);
            assertFalse(container.isEmpty());
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }

    @Test
    void testContains() {
        try {
            assertFalse(container.contains(1111));
            container.addItem(bag);
            assertTrue(container.contains(1111));
            container.addItem(bag1);
            container.addItem(bag2);
            assertTrue(container.contains(3333));
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }

    @Test
    void testToString() {
        try {
            containerInfo = "";
            assertEquals(containerInfo, container.toString());

            container.addItem(bag);
            container.addItem(bag1);
            containerInfo = "2222|1111";
            assertEquals(containerInfo, container.toString());

            container.addItem(bag2);
            container.addItem(bag3);
            container.addItem(bag4);
            container.addItem(bag5);
            container.addItem(bag6);
            container.addItem(bag7);
            containerInfo = "8888|7777<br>6666|5555|4444<br>3333|2222|1111";
            assertEquals(containerInfo, container.toString());

            container.addItem(bag8);
            containerInfo = "9999|8888|7777<br>6666|5555|4444<br>3333|2222|1111";
            assertEquals(containerInfo, container.toString());
        } catch (DuplicateIDException e) {
            fail("Duplicate bags added");
        }
    }
}
