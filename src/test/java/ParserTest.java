import duke.Parser;
import duke.ZeroLengthDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void parseCommand_validTodo_noException() throws ZeroLengthDescriptionException{
        String[] expected = new String[] {"todo", "test test test"};
        assertArrayEquals(parser.parseCommand("todo test test test"), expected);
    }

    @Test
    public void parseCommand_emptyTodoDescription_exceptionThrown() {
        Exception exception = assertThrows(ZeroLengthDescriptionException.class, () ->
                parser.parseCommand("todo"));
        assertEquals("The description of this task cannot be empty", exception.getMessage());
    }
}