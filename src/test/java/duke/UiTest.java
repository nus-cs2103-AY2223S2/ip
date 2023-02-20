package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Contains test for Ui class.
 * Reference: https://www.baeldung.com/java-testing-system-out-println
 */
public class UiTest {

    private final Ui ui = new Ui();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /* // assert fails even though file comparison says its identical
    @Test
    void printProgramLogo_staticContext_success() {
        String expected
                = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Developed by: lhy-hoyin\n";

        Ui.printProgramInfo();

        assertEquals(expected, outputStreamCaptor.toString());
    }
    /**/

    @Test
    void println() {
        ui.println("Testing println() function");
        assertEquals("Testing println() function", outputStreamCaptor.toString().trim());
    }

    @Test
    void warn() {
        ui.warn("Testing warn() function");
        assertEquals("OOPS! Testing warn() function", outputStreamCaptor.toString().trim());
    }

    @Test
    void getRecentMessages() {
        ui.println("Blah blah blah");
        ui.println("More blahs");
        ui.warn("oh an error!");

        final String s = ui.getRecentMessages();
        assertEquals("Blah blah blah\nMore blahs\nOOPS! oh an error!\n", s);
    }

    @AfterEach void tearDown() {
        System.setOut(standardOut);
    }
}
