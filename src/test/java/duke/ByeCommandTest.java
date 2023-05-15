package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.Command;

public class ByeCommandTest {

    @Test
    public void isExitTest() {
        Command c = new ByeCommand(new String[0]);
        assertTrue(c.isExit());
    }
}
