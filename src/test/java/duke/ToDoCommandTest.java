package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ToDoCommand;

public class ToDoCommandTest {

    @Test
    public void isExitTest() {
        Command c = new ToDoCommand(new String[0]);
        assertFalse(c.isExit());
    }
}
