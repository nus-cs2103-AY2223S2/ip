package duke.utilities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import duke.exceptions.DukeUnknownActionException;

public class ParserTest {

    @Test
    public void parseUserCommand_listCommand_DukeUnknownActionExceptionNotThrown() {
        assertDoesNotThrow(() -> {
            Parser.parseUserCommand("list");
        });
    }

    @Test
    public void parseUserCommand_byeCommand_DukeUnknownActionExceptionNotThrown() {
        assertDoesNotThrow(() -> {
            Parser.parseUserCommand("bye");
        });
    }

    @Test
    public void parseUserCommand_unknownAction_DukeUnknownActionExceptionThrown() {
        assertThrows(DukeUnknownActionException.class, () -> {
            Parser.parseUserCommand("abc");
        });
    }
}
