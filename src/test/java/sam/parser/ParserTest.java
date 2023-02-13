package sam.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import sam.SamException;
import sam.command.AddCommand;
import sam.command.DeleteCommand;
import sam.command.ExitCommand;
import sam.command.ListCommand;
import sam.command.MarkCommand;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;

public class ParserTest {
    @Test
    public void parseCommand_validCommands_success() {
        try {
            assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
            assertTrue(Parser.parseCommand("list") instanceof ListCommand);
            assertTrue(Parser.parseCommand("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parseCommand("unmark 1") instanceof MarkCommand);
            assertTrue(Parser.parseCommand("todo task") instanceof AddCommand);
            assertTrue(Parser.parseCommand("event task /from 1/1/2023 /to 2/1/2023") instanceof AddCommand);
            assertTrue(Parser.parseCommand("deadline task /by 1/1/2023") instanceof AddCommand);
            assertTrue(Parser.parseCommand("delete 1") instanceof DeleteCommand);
        } catch (SamException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unknownCommands_exceptionThrown() {
        assertThrows(SamUnknownCommandException.class, () -> Parser.parseCommand("hello"));
        assertThrows(SamUnknownCommandException.class, () -> Parser.parseCommand(""));
    }

    @Test
    public void parseTaskArgs_validInputs_success() {
        try {
            Map<String, String> argsMap = new HashMap<>();
            Parser.parseTaskArgs(argsMap, "by 1/1/2023");
            assertEquals("1/1/2023", argsMap.get("by"));
            argsMap.clear();
            Parser.parseTaskArgs(argsMap, "from 1/1/2023 /to 2/1/2023");
            assertEquals("1/1/2023", argsMap.get("from"));
            assertEquals("2/1/2023", argsMap.get("to"));
        } catch (SamException e) {
            fail();
        }
    }

    @Test
    public void parseTaskArgs_missingValue_exceptionThrown() {
        Map<String, String> argsMap = new HashMap<>();
        assertThrows(SamMissingTaskValueException.class, () -> Parser.parseTaskArgs(argsMap, "to"));
        assertThrows(SamMissingTaskValueException.class, () -> Parser.parseTaskArgs(argsMap, "from /to 1/1/2023"));
    }
}
