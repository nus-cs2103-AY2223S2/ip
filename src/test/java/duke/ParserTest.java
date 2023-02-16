package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyCommandException;
import duke.helper.Parser;

public class ParserTest {
    @Test
    public void checkTaskDesc_emptyTodo_exceptionThrown() {
        EmptyCommandException thrown = assertThrows(EmptyCommandException.class, ()
                -> Parser.checkCommandDesc(new String[]{"todo"}));
        assertEquals("OOPS!!! The description of a todo command cannot be empty.",
                thrown.toString());
    }

    @Test
    public void checkTaskDesc_normalTodo_noExceptionThrown() throws EmptyCommandException {
        boolean success = Parser.checkCommandDesc(new String[]{"todo", "ip"});
        assertTrue(success);
    }
}
