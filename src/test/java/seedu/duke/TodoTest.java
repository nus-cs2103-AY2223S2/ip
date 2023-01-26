package seedu.duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todo_emptyDescription_exceptionThrown() {
        try {
            new Todo("");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
