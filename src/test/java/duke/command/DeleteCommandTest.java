package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_invalidInput_exceptionThrown() {
        assertThrows(DukeEmptyArgumentException.class, () -> new DeleteCommand(new String[]{"delete"}));
        assertThrows(DukeInvalidArgumentException.class, () -> new DeleteCommand(new String[]{"delete",
                "a"}));
    }

    @Test
    public void isExit() throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        assertEquals(false, new DeleteCommand(new String[]{"delete", "1"}).isExit());
        assertEquals(false, new DeleteCommand(new String[]{"delete", "2"}).isExit());
    }
}
