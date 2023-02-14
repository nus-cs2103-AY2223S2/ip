package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidCommandDukeException;

public class CommandTypeTest {
    @Test
    public void getCommandType_validCommand() throws DukeException {
        assertEquals(CommandType.getCommandType("bye"), CommandType.EXIT);
        assertEquals(CommandType.getCommandType("list"), CommandType.DISPLAY_LIST);
        assertEquals(CommandType.getCommandType("mark"), CommandType.MARK_TASK_AS_DONE);
        assertEquals(CommandType.getCommandType("unmark"), CommandType.MARK_TASK_AS_UNDONE);
        assertEquals(CommandType.getCommandType("todo"), CommandType.TODO);
        assertEquals(CommandType.getCommandType("deadline"), CommandType.DEADLINE);
        assertEquals(CommandType.getCommandType("event"), CommandType.EVENT);
        assertEquals(CommandType.getCommandType("delete"), CommandType.DELETE);
        assertEquals(CommandType.getCommandType("find"), CommandType.FIND);
        assertEquals(CommandType.getCommandType("remind"), CommandType.REMINDERS);
    }

    @Test
    public void getCommandType_invalidCommand() {
        assertThrows(InvalidCommandDukeException.class, () -> CommandType.getCommandType("blabla"));
    }
}
