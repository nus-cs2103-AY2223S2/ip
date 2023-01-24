package duke.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    @Test
    public void print_printSingleLineMessage_printsOutFormattedMessage() {
        String expected = "    ____________________________________________________________\n     Hello World! "
                + "Lorem ipsum\n    ____________________________________________________________\n\n";

        // Setup
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        Ui ui = new Ui(printStream);

        // Test
        ui.print("Hello World! Lorem ipsum");

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void print_printMultiLineMessage_printsOutFormattedMessage() {
        String expected = "    ____________________________________________________________\n     Hello World!\n     "
                + "Lorem ipsum\n    ____________________________________________________________\n\n";

        // Setup
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        Ui ui = new Ui(printStream);

        // Test
        ui.print("Hello World!\nLorem ipsum");

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }
}
