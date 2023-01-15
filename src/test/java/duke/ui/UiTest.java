package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {

    Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ui = new Ui(System.in, new PrintStream(outContent));
    }

    @Test
    void testPrintln() {
        String expected = "test println";
        ui.println(expected);
        assertEquals(expected + "\n", outContent.toString());
    }

    @Test
    void testShowError() {
        String expected = "test showError";
        ui.showError(expected);
        assertEquals("Error! [ " + expected + " ]\n", outContent.toString());
    }

    @Test
    void testShowLine() {
        ui.showLine();
        assertEquals("----------------------------------------\n", outContent.toString());
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();
        String expected = "Hello from\n"
        + " ____       _\n"
        + "|  _ \\ _  _| | ____ _\n"
        + "| | | | | |  | |/ / _ \\\n"
        + "| |_| | |_|  |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n"
        + "What can I do for you?"
        + "\n";
        assertEquals(expected, outContent.toString());
    }
    
}
