package duke.parser;

import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeUnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_CapitalLetterCommand_createCommandCorrectly() throws DukeEmptyArgumentException,
            DukeInvalidArgumentException, DukeUnknownCommandException, DukeEventOverlapException {
        assertEquals(ExitCommand.class, Parser.parse("EXIT").getClass());
        assertEquals(ListCommand.class, Parser.parse("liST").getClass());
        assertEquals(MarkCommand.class, Parser.parse("mArK").getClass());
    }

    @Test
    public void parse_UnknownCommand_exceptionThrown() throws DukeEmptyArgumentException,
            DukeInvalidArgumentException, DukeUnknownCommandException, DukeEventOverlapException {
        assertThrows(DukeUnknownCommandException.class, () -> Parser.parse("unknownCommand"));
        assertThrows(DukeUnknownCommandException.class, () -> Parser.parse("undo"));
    }
}
