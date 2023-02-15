package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobInvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void deleteCommand_invalidInput_exceptionThrown() {
        assertThrows(SpongebobEmptyArgumentException.class, () -> new DeleteCommand(new String[]{"delete"}));
        assertThrows(SpongebobInvalidArgumentException.class, () -> new DeleteCommand(new String[]{"delete",
                "a"}));
    }

    @Test
    public void isExit() throws SpongebobEmptyArgumentException, SpongebobInvalidArgumentException {
        assertEquals(false, new DeleteCommand(new String[]{"delete", "1"}).isExit());
        assertEquals(false, new DeleteCommand(new String[]{"delete", "2"}).isExit());
    }
}
