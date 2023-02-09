package pix;

import pix.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiByeTest {
    @Test
    public void executeTest() {
        String expected = "Bye. Hope to see you again soon!\n\n";
        assertEquals(expected, new Ui().bye());
    }
}

