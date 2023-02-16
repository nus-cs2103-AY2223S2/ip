package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ParserTest {
    @Test
    public void parseTodo_normalInput_returnTodo() throws BobException {
        String input = "todo homework";
        Todo expected = new Todo("homework");
        Todo actual = Parser.parseTodo(input);
        assertEquals(expected, actual);
    }
    @Test
    public void parseTodo_invalidInput_throwException() {
        String invalidInput = "todo";
        BobException e = assertThrows(BobException.class, () -> Parser.parseTodo(invalidInput));
        assertEquals("Error: Invalid todo command!", e.getMessage());
    }

    @Test
    public void parseDeadline_normalInput_returnDeadline() throws BobException {
        String input = "deadline submission /by 2023-02-17";
        Deadline expected = new Deadline("submission", LocalDate.parse("2023-02-17"));
        Deadline actual = Parser.parseDeadline(input);
        assertEquals(expected.description, actual.description);
    }

    @Test
    public void parseDeadline_invalidInput_throwException() {
        String invalidInput = "deadline /by 2011-03-11";
        BobException e = assertThrows(BobException.class, () -> Parser.parseDeadline(invalidInput));
        assertEquals("Error: Invalid deadline command!", e.getMessage());
    }

    @Test
    public void parseEvent_normalInput_returnEvent() throws BobException {
        String input = "event iP /from 2023-01-01 /to 2023-02-16";
        Event expected = new Event("iP", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-02-16"));
        Event actual = Parser.parseEvent(input);
        assertEquals(expected, actual);
    }

    @Test
    public void parseEvent_invalidInput_throwException() {
        String invalidInput = "event iP /to 2011-01-01 /from 2010-01-01";
        BobException e = assertThrows(BobException.class, () -> Parser.parseEvent(invalidInput));
        assertEquals("Error: Invalid event command!", e.getMessage());
    }
}
