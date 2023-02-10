package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteCommandTest {
    @Test
    public void testDeleteCommandExit() {
        Command test = new DeleteCommand(1);
        Assertions.assertEquals(false, test.isExit());
    }

    @Test
    public void testDeleteCommandFail() {
        try {
            new DeleteCommand();
        } catch (AssertionError e) {
            // Check if assert message is expected
            String expected = "Hey, you did not enter any numbers";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an AssertionError, this line will be reached
        fail("Expected an AssertionError to be thrown");
    }
}
