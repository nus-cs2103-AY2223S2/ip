package duke;

import duke.command.Command;
import duke.command.ByeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {

    @Test
    public void isExitTest() {
        Command c = new ByeCommand(new String[0]);
        assertTrue(c.isExit());
    }
}
