package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.constant.Message;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Ui ui;

    @BeforeEach
    void setUp() {
        ui = new Ui(System.in, new PrintStream(outContent));
    }

    @Test
    void testPrintln() {
        String expected = "test println";
        ui.printConsole(expected);
        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    void testShowError() {
        String expected = "test showError";
        ui.showError(expected);
        assertEquals("[Error!] " + expected.trim(), outContent.toString().trim());
    }

    @Test
    void testShowLine() {
        ui.showLine();
        assertEquals("----------------------------------------", outContent.toString().trim());
    }

    @Test
    void testShowWelcome() {
        ui.showWelcome();
        String expected = Message.WELCOME;
        assertEquals(expected, outContent.toString().trim());
    }
}
