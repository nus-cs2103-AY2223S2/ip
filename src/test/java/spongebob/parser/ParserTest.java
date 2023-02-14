package spongebob.parser;

import spongebob.command.ExitCommand;
import spongebob.command.ListCommand;
import spongebob.command.MarkCommand;
import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobUnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_CapitalLetterCommand_createCommandCorrectly() throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobUnknownCommandException, SpongebobEventOverlapException {
        assertEquals(ExitCommand.class, Parser.parse("EXIT").getClass());
        assertEquals(ListCommand.class, Parser.parse("liST").getClass());
        assertEquals(MarkCommand.class, Parser.parse("mArK").getClass());
    }

    @Test
    public void parse_UnknownCommand_exceptionThrown() throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobUnknownCommandException, SpongebobEventOverlapException {
        assertThrows(SpongebobUnknownCommandException.class, () -> Parser.parse("unknownCommand"));
        assertThrows(SpongebobUnknownCommandException.class, () -> Parser.parse("undo"));
    }
}
