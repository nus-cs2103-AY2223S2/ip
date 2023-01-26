package duke.ui;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UiTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private Ui ui;
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(out));
        ui = new Ui();
    }

    @AfterEach
    public void reset() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Ensure Ui object is created")
    public void testUi() {
        assertNotNull(ui);
    }

    @Test
    @DisplayName("Ensure Ui prints the string provided")
    public void testPrintResponse() {
        ui.printResponse("\t test");
        assertEquals("\t____________________________________________________________\n"
                        .concat("\t test\n")
                        .concat("\t____________________________________________________________\n\n"),
                out.toString());
    }

    @Test
    @DisplayName("Ensure Ui prints the error message")
    public void testPrintException() {
        DukeException e = new DukeException("test");
        ui.printException(e);
        assertEquals("\t____________________________________________________________\n"
                        .concat("test\n")
                        .concat("\t____________________________________________________________\n\n"),
                out.toString());
    }

    @Test
    @DisplayName("Ensure Ui prints greeting correctly")
    public void testGreet() {
        ui.greet();
        assertEquals("\t____________________________________________________________\n"
                        .concat("\t Hello! I'm Duke\n\t What can I do for you?\n")
                        .concat("\t____________________________________________________________\n\n"),
                out.toString());
    }
}
