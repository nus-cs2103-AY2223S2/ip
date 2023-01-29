package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTodo_threeWordTodo_parseCorrectly() {
        HashMap<String, String> parsed = Parser.parseTodo("todo buy ten oranges ");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("", "");
        expected.put("todo", "buy ten oranges");
        assertEquals(parsed, expected);
    }

    @Test
    public void parseEvent_longEvent_parseCorrectly() {
        HashMap<String, String> parsed = Parser.parseEvent(
                "event attend jim's birthday party " +
                        "/from monday 9.30am before breakfast " +
                        "/to tuesday midnight ");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("", "");
        expected.put("event", "attend jim's birthday party");
        expected.put("/from", "monday 9.30am before breakfast");
        expected.put("/to", "tuesday midnight");
        assertEquals(parsed, expected);
    }
}
