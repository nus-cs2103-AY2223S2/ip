package pix;

import pix.data.MyData;
import pix.ui.Ui;
import org.junit.jupiter.api.Test;
import pix.commands.Bye;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeTest {
    @Test
    public void executeTest() {
        String expected = "    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Bye().execute(new MyData(), new Ui());
        assertEquals(expected, outContent.toString());
    }
}

