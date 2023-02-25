package yj;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseToDoCommand_validToDo_correctParse() {
        String input = "todo return book";
        String result = Parser.parseToDoCommand(input);
        assertEquals("return book", result);
    }
    @Test
    public void parseDeadlineCommand_validDeadline_correctParse() {
        String input = "return book /by 2/12/2019 1800";
        Map<String, String> result = Parser.parseDeadlineCommand(input);
        assertEquals("return book", result.get("description"));
        assertEquals("2/12/2019 1800", result.get("by"));
    }

    @Test
    public void parseEventCommand_validEvent_correctParse() {
        String input = "project meeting /from 2/12/2019 1800 /to 2/12/2019 1900";
        Map<String, String> result = Parser.parseEventCommand(input);
        assertEquals("project meeting", result.get("description"));
        assertEquals("2/12/2019 1800", result.get("from"));
        assertEquals("2/12/2019 1900", result.get("to"));
    }
}
