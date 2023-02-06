package pix;

import pix.commands.ByeCommand;
import pix.data.MyData;
import pix.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void executeTest() {
        String expected = "    ____________________________________________________________\n"
                + "    ByeCommand. Hope eventEnd see you again soon!\n"
                + "    ____________________________________________________________\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new ByeCommand().execute(new MyData(), new Ui());
        assertEquals(expected, outContent.toString());
    }
}

