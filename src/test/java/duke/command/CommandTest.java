package duke.command;


import duke.util.State;
import duke.util.Stateful;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private final Command command = new Command("test","test help", stateful -> new Stateful(new LinkedList<>(List.of("test")), new State(false)));
    private final NestCommand nestedCommand = new NestCommand("test2","test2 help", new Command[]{command});

    @Test
    void testCommand() {
        assertEquals("test", command.getName());
        assertEquals("\ttest : test help", command.getHelpText());
        assertFalse(command.hasSubCommands());
        assertEquals("test", command.execute(new Stateful(new LinkedList<>(), new State(false)),new LinkedList<>()).getOutputs().poll());
        assertFalse(command.execute(new Stateful(new LinkedList<>(), new State(false)),new LinkedList<>()).getState().isDoQuit());
    }

    @Test
    void testNestCommand() {
        assertEquals("test2", nestedCommand.getName());
        assertEquals("\ttest2 : test2 help", nestedCommand.getHelpText());
        assertTrue(nestedCommand.hasSubCommands());
        assertEquals(command, nestedCommand.getSubCommands().get("test"));
    }
}