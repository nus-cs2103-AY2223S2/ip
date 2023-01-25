package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parser_todoEmptyDescription_exceptionThrown() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ((
                ) -> Parser.parse("todo ")));
        assertEquals("Command TODO requires argument TODO but was not given",
                exception.getMessage());
    }
}
