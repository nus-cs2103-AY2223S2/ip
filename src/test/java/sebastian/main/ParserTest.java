package sebastian.main;

import org.junit.jupiter.api.Test;
import sebastian.command.*;
import sebastian.sebastianExceptions.IllegalInputException;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testAddCommand(){
        assertAll(() -> {
            Command todo = Parser.parse("todo");
            assertTrue(todo instanceof AddTodoCommand);
        }, () -> {
            Command deadline = Parser.parse("deadline");
            assertTrue(deadline instanceof AddDeadlineCommand);
        }, () -> {
            Command event = Parser.parse("event");
            assertTrue(event instanceof AddEventCommand);
        }, () -> {
            Command mark = Parser.parse("mark");
            assertTrue(mark instanceof MarkCommand);
        }, () -> {
            Command unmark = Parser.parse("unmark");
            assertTrue(unmark instanceof UnmarkCommand);
        }, () -> {
            Command delete = Parser.parse("delete");
            assertTrue(delete instanceof DeleteCommand);
        }, () -> {
            Command exit = Parser.parse("bye");
            assertTrue(exit instanceof ExitCommand);
        }, () -> {
            Command list = Parser.parse("list");
            assertTrue(list instanceof ListCommand);
        }, () -> {
            Command get = Parser.parse("get");
            assertTrue(get instanceof GetCommand);
        });
    }

    @Test
    public void testException() {
        assertThrows(IllegalInputException.class, () -> {
            Parser.parse("some random command");
        });
    }
}
