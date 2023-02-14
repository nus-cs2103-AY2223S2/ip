package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.enums.Views;

public class UiTest {
    @Test
    public void testPrintBye() {
        String expected = "    ____________________________________________________________\n      Bye. Hope to"
                + " see you again soon!\n    ____________________________________________________________\n\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Test
        Ui.printer(Views.END_STRING);

        System.out.flush();

        // Remove line breaks, both windows or unix styles
        expected = expected.replace("\n", "");
        String actual = outContent.toString().replace("\n", "");
        actual = actual.replace("\r", "");

        // Assert output
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPrintRandom() {
        Random random = new Random();
        String randomString = "";

        for (int i = 0; i < 10; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            randomString += c;
        }
        String expected = "    ____________________________________________________________\n      " + randomString
                + "\n    ____________________________________________________________\n\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Test
        Ui.showError(randomString);

        System.out.flush();

        // Remove line breaks, both windows or unix styles
        expected = expected.replace("\n", "");
        String actual = outContent.toString().replace("\n", "");
        actual = actual.replace("\r", "");

        // Assert output
        Assertions.assertEquals(expected, actual);
    }

}
