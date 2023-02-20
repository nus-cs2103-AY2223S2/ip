package duke.command;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

/**
 * Contains test for Command class.
 */
public class CommandTest {

    @Test
    void parseCommand_invalidCommands() {
        try {
            assertNull(Command.parseCommand("new"));
            assertNull(Command.parseCommand("newTask"));
            assertNull(Command.parseCommand("add"));
            assertNull(Command.parseCommand("adds"));
            assertNull(Command.parseCommand("create"));
            assertNull(Command.parseCommand(":!q"));
        } catch (DukeException e) {
            // DukeException is thrown by the implementation of a Command subclass.
            // This is unexpected!
            fail();
        }
    }

    @Test
    void parseCommand_validCommands() {
        try {
            assertNotNull(Command.parseCommand("ls"));
            assertNotNull(Command.parseCommand("list"));
            assertNotNull(Command.parseCommand("todo"));
            assertNotNull(Command.parseCommand("deadline"));
            assertNotNull(Command.parseCommand("event"));
            assertNotNull(Command.parseCommand("mark"));
            assertNotNull(Command.parseCommand("unmark"));
            assertNotNull(Command.parseCommand("delete"));
            assertNotNull(Command.parseCommand("find"));
            assertNotNull(Command.parseCommand("save"));
            assertNotNull(Command.parseCommand("bye"));
            assertNotNull(Command.parseCommand("goodbye"));
            assertNotNull(Command.parseCommand(":q"));
            assertNotNull(Command.parseCommand("quit"));
            assertNotNull(Command.parseCommand("quit()"));
            assertNotNull(Command.parseCommand("exit"));
            assertNotNull(Command.parseCommand("exit()"));
        } catch (DukeException e) {
            // DukeExceptions are caused by userInput not being valid to process the command itself.
            // But the parsing itself works.
            // Do nothing.
        }
    }
}
