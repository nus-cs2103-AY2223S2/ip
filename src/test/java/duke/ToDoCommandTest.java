package duke;

import duke.command.Command;
import duke.command.ToDoCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoCommandTest {

    @Test
    public void isExitTest() {
        Command c = new ToDoCommand(new String[0]);
        assertFalse(c.isExit());
    }
}
