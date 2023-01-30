package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void initExitTest() {
        ByteArrayOutputStream printedString = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printedString));
        ByteArrayInputStream inputString = new ByteArrayInputStream(("bye\n").getBytes());
        System.setIn(inputString);
        Duke duke = new Duke();
//        duke.run();

        assertEquals("-----------------------------------------------------------" +
                        "Hello! I'm BobWhat can I do for you?" +
                        "----------------------------------------------------------------" +
                        "------------------------------------------------------" +
                        "Bye. Hope to see you again soon!" +
                        "-----------------------------------------------------------",
                printedString.toString()
                        .replace("\n", "")
                        .replace("\r", ""));
    }
}
