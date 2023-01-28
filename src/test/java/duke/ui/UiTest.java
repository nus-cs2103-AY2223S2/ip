package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void setUpObject() {
        ui = new Ui();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testFormatMessage() {
        ui.printMessage("bla bla bla");
        assertEquals("___________________________\n"
                + "bla bla bla\n"
                + "___________________________", outContent.toString().trim());
    }

    @Test
    public void testPromptForInput() {
        ui.printPromptForInput();
        assertEquals(">", outContent.toString().trim());
    }

    @Test
    public void testGreet() {
        ui.greet();
        assertEquals("___________________________\n"
                + "Hello, I am Duke.\n"
                + "What can I do for you?\n"
                + "___________________________", outContent.toString().trim());
    }

    @Test
    public void testGoodbye() {
        ui.sayGoodbye();
        assertEquals("___________________________\n"
                + "Goodbye. I hope to see you again.\n"
                + "___________________________", outContent.toString().trim());
    }
}
