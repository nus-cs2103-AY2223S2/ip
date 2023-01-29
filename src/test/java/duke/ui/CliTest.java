package duke.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class CliTest {
    @Test
    public void print_printSingleLineMessage_printsOutFormattedMessage() {
        String expected = "    ______________________________________________________________________\n     Hello "
                + "World! Lorem ipsum\n    ______________________________________________________________________\n\n";

        // Setup
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = new ByteArrayInputStream(new byte[0]);

        Cli cli = new Cli(printStream, inputStream);

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

        Cli cli = new Cli(printStream, inputStream);

        // Test
        cli.print("Hello World!\nLorem ipsum");

        String actual = outputStream.toString();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getInput_singleLine_returnLine() {
        String expected = "Hello World!";

        // Setup
        String text = "Hello World!";

        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());

        Cli cli = new Cli(printStream, inputStream);

        // Test
        String actual = cli.getInput();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getInput_multipleLines_returnsFirstLine() {
        String expected = "Hello World!";

        // Setup
        String text = "Hello World!\nLorem Ipsum";

        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());

        Cli cli = new Cli(printStream, inputStream);

        // Test
        String actual = cli.getInput();

        // Check results
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getInput_noInput_throwsNoSuchElementException() {
        // Setup
        String text = "";

        PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());

        Cli cli = new Cli(printStream, inputStream);

        // Test
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            cli.getInput();
        });
    }
}
