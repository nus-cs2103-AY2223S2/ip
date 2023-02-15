package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void addCommand_invalidInput_exceptionThrown() {
        assertThrows(SpongebobEmptyArgumentException.class, () -> new AddCommand(new String[]{"todo"}));
        assertThrows(SpongebobEmptyArgumentException.class, () -> new AddCommand(new String[]{"deadline"}));
        assertThrows(SpongebobEmptyArgumentException.class, () -> new AddCommand(new String[]{"event"}));
    }

    @Test
    public void isExit() throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        assertEquals(false, new AddCommand(new String[]{"todo", "do ip"}).isExit());
        assertEquals(false, new AddCommand(new String[]{"deadline", "do ip /by 28/01/2023 23:59"}).isExit());
        assertEquals(false, new AddCommand(new String[]{"event",
                "do ip /from 28/01/2023 23:59 /to 29/01/2023 23:59"}).isExit());
    }
}
