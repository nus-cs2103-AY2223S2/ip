package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void testPrintBye() {
        String expected = "    ____________________________________________________________\n      Bye. Hope to"
                + " see you again soon!\n    ____________________________________________________________\n\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Test
        Ui ui = new Ui();
        ui.showEnd();

        System.out.flush();

        // Assert output
        Assertions.assertEquals(expected, outContent.toString());
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
        Ui ui = new Ui();
        ui.showError(randomString);

        System.out.flush();

        // Assert output
        Assertions.assertEquals(expected, outContent.toString());
    }

}
