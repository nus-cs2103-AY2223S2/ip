package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class UiTest {
    private static final PrintStream originalOut = System.out;
    private final Ui ui = new Ui();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testWelcomeMessage() {
        ui.printWelcomeMessage();
        assertEquals("\t____________________________________________________________\r\n"
                + "\tHello! I'm Duke\r\n"
                + "\tWhat can I do for you?\r\n"
                + "\t____________________________________________________________\r\n",
                outContent.toString());
    }

    @Test
    public void testByeMessage() {
        ui.printByeMessage();
        assertEquals("\t____________________________________________________________\r\n"
                + "\tBye. Hope to see you again soon!\r\n"
                + "\t____________________________________________________________\r\n",
                outContent.toString());
    }

}
