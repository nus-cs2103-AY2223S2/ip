package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {
    @Test
    public void markCommand_invalidInput_exceptionThrown() {
        assertThrows(SpongebobEmptyArgumentException.class, () -> new MarkCommand(new String[]{"mark"}));
        assertThrows(SpongebobInvalidArgumentException.class, () -> new MarkCommand(new String[]{"mark", "a"}));
    }

    @Test
    public void isExit() throws SpongebobEmptyArgumentException, SpongebobInvalidArgumentException {
        assertEquals(false, new MarkCommand(new String[]{"mark", "1"}).isExit());
        assertEquals(false, new MarkCommand(new String[]{"mark", "2"}).isExit());
    }
}
