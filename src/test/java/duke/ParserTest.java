package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetString() {
        assertEquals("Feb 19 2022", Parser.getParsedDate(Parser.getLocalDateObject("2022-02-19")));
    }
}
