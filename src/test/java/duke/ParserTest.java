package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.MissingInputsException;
import duke.helper.Parser;

public class ParserTest {
    @Test
    public void checkTaskDesc_emptyTodo_exceptionThrown() {
        MissingInputsException thrown = assertThrows(MissingInputsException.class, ()
                -> Parser.checkMissingInputs("todo", new String[]{"todo"}));
        assertEquals("OOPS!!! The todo command has missing inputs.",
                thrown.toString());
    }

    @Test
    public void checkTaskDesc_normalTodo_noExceptionThrown() throws MissingInputsException {
        boolean success = Parser.checkMissingInputs("todo", new String[]{"todo", "ip"});
        assertTrue(success);
    }
}
