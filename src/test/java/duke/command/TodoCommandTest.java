package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoCommandTest {
    @Test
    public void testTodoCommandExit() {
        Command test = new TodoCommand("TEST");
        Assertions.assertEquals(false, test.isExit());
    }
}
