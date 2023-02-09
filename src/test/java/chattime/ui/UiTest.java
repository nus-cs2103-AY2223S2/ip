package chattime.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void getExecuteStatusTest() {
        Ui test = new Ui();
        assertTrue(test.getExecuteStatus());
    }

    @Test
    public void printErrorStatusTest() {
        Ui test = new Ui();
        assertEquals("Test Error", test.printError("Test Error"));
    }
}
