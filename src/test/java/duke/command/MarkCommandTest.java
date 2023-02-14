package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class MarkCommandTest {
    @Test
    public void testMarkCommandExit() throws DukeException {
        Command test = new MarkCommand(1);
        Assertions.assertEquals(false, test.isExit());
    }

    @Test
    public void testMarkCommandFail() {
        try {
            new MarkCommand();
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = "Hey, you did not enter any numbers";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }
}
