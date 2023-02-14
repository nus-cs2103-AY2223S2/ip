package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoCommandTest {
    @Test
    public void testTodoCommandExit() throws DukeException {
        Command test = new TodoCommand("TEST");
        Assertions.assertEquals(false, test.isExit());
    }
}
