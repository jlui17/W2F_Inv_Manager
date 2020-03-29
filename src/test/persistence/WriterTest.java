package persistence;

import model.Container;
import model.Exceptions.DuplicateIDException;
import model.StockBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/test_save1.txt";
    private Writer testWriter;
    private StockBag bag1;
    private StockBag bag2;
    private StockBag bag3;
    private Container containerA;
    private Container containerB;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            testWriter = new Writer(new File(TEST_FILE));
            bag1 = new StockBag("Kaws", 1111, 3, 100, "Basketball");
            bag2 = new StockBag("Minion", 2222, 4, 50, "Tubs");
            bag3 = new StockBag("BT21", 3333, 4, 150, "Birthday");
            containerA = new Container();
            containerB = new Container();
            containerA.addItem(bag3);
            containerB.addItem(bag2);
            containerB.addItem(bag1);
        } catch (DuplicateIDException e) {
            //
        }
    }

    @Test
    void testWriteContainers() {
        // save containers to file
        for (int i = 1; i <= containerA.getSize(); i++) {
            testWriter.write(containerA.getItem(i));
        }
        testWriter.nextContainer();
        for (int i = 1; i <= containerB.getSize(); i++) {
            testWriter.write(containerB.getItem(i));
        }
        testWriter.close();

        try {
            List<Container> containers = Reader.readContainers(new File(TEST_FILE));
            Container containerC = containers.get(0);
            assertEquals(bag3, containerC.getItem(1));

            Container containerD = containers.get(1);
            assertEquals(bag1, containerD.getItem(2));
            assertEquals(bag2, containerD.getItem(1));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
