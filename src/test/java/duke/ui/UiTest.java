package duke.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testShowStartUp() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outContent));
        ui.showStartUp();
        String expectedOutput = "  /\\_/\\\n"
                + " /\u25DE   \u25DF\\\n"
                + "( \u25d5   \u25d5 )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    \u25CF\n" + "BorzAI\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintUi() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outContent));
        ui.printUi("Test message");
        assertEquals("\tTest message\n", outContent.toString());
    }

    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.showWelcome();
        String expectedOutput = "\tWhen all I do is for you, Kermie \u2665\n\tWhat can I do for you?\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowLoadingError() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.showLoadingError();
        String expectedOutput = "\tError: Unable to load tasks from file.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testShowLine() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui.showLine();
        String expectedOutput = "\t__________________________________________________________\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
