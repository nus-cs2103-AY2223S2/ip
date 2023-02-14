package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void addCommand_invalidInput_exceptionThrown() {
        assertThrows(DukeEmptyArgumentException.class, () -> new AddCommand(new String[]{"todo"}));
        assertThrows(DukeEmptyArgumentException.class, () -> new AddCommand(new String[]{"deadline"}));
        assertThrows(DukeEmptyArgumentException.class, () -> new AddCommand(new String[]{"event"}));
    }

    @Test
    public void isExit() throws DukeEmptyArgumentException,
            DukeInvalidArgumentException, DukeEventOverlapException {
        assertEquals(false, new AddCommand(new String[]{"todo", "do ip"}).isExit());
        assertEquals(false, new AddCommand(new String[]{"deadline", "do ip /by 2023-01-17T23:59:59"}).isExit());
        assertEquals(false, new AddCommand(new String[]{"event", "do ip /from 2023-01-01T00:00:00 /to " +
                "2023-01-17T23:59:59"}).isExit());
    }
}
