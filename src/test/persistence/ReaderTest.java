package persistence;

import model.Container;
import model.StockBag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testParseContainers() {
        try {
            List<Container> containers = Reader.readContainers(new File("./data/test_save.txt"));
            StockBag bag1 = new StockBag("Kaws",1111,3,100,"Basketball");
            StockBag bag2 = new StockBag("Minion",2222,4,50,"Tubs");
            Container containerA = containers.get(0);

            assertEquals(bag1, containerA.getBag(1));
            assertEquals(bag2, containerA.getBag(2));

            Container containerB = containers.get(1);
            StockBag bag3 = new StockBag("BT21",3333,4,150,"Birthday");
            assertEquals(bag3, containerB.getBag(1));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readContainers(new File("./path/non/existent/test_save.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
