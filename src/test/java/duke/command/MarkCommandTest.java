package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {
    @Test
    public void markCommand_invalidInput_exceptionThrown() {
        assertThrows(DukeEmptyArgumentException.class, () -> new MarkCommand(new String[]{"mark"}));
        assertThrows(DukeInvalidArgumentException.class, () -> new MarkCommand(new String[]{"mark", "a"}));
    }

    @Test
    public void isExit() throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        assertEquals(false, new MarkCommand(new String[]{"mark", "1"}).isExit());
        assertEquals(false, new MarkCommand(new String[]{"mark", "2"}).isExit());
    }
}
