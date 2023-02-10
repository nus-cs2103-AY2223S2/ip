package duke.task;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {
    @Test
    public void testTodoString() throws DukeException {
        String expected = "[T][ ] TEST";
        Task test = new Todo("TEST");
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testTodoMarkString() throws DukeException {
        String expected = "[T][X] TEST";
        Task test = new Todo("TEST");
        test.markAsDone();
        Assertions.assertEquals(expected, test.toString());
    }

    @Test
    public void testTodoFail() throws DukeException {
        try {
            new Todo("");
        } catch (AssertionError e) {
            // Check if assert message is expected
            String expected = "Hey, ☹ The description of a task cannot be empty.";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an AssertionError, this line will be reached
        fail("Expected an AssertionError to be thrown");
    }
}
