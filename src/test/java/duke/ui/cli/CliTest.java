package duke.ui.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CliTest {
    @Test
    public void print_printSingleLineMessage_printsOutFormattedMessage() {
        String expected = "    ______________________________________________________________________\n     Hello "
                + "World! Lorem ipsum\n    ______________________________________________________________________\n\n";

        // Setup
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = new ByteArrayInputStream(new byte[0]);

        Cli cli = new Cli(printStream, inputStream, null, null);

        // Test
        cli.print("Hello World! Lorem ipsum");

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void print_printMultiLineMessage_printsOutFormattedMessage() {
        String expected = "    ______________________________________________________________________\n     Hello "
                + "World!\n     Lorem ipsum\n    "
                + "______________________________________________________________________\n\n";

        // Setup
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = new ByteArrayInputStream(new byte[0]);

        Cli cli = new Cli(printStream, inputStream, null, null);

        // Test
        cli.print("Hello World!\nLorem ipsum");

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void start_inputMultipleLinesWithLastLineBeingExitCommand_handleAllLines() {
        String expected = "    ______________________________________________________________________\n"
                +  "     Hello World!\n"
                + "    ______________________________________________________________________\n\n"
                + "    ______________________________________________________________________\n"
                + "     Lorem Ipsum\n"
                + "    ______________________________________________________________________\n\n"
                + "    ______________________________________________________________________\n"
                + "     exit\n"
                + "    ______________________________________________________________________\n\n";

        // Setup
        String input = "Hello World!\nLorem Ipsum\nexit\n";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Cli cli = new Cli(printStream, inputStream, (message) -> message, (message) -> message.equals("exit"));

        // Test
        cli.start();

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }
}
