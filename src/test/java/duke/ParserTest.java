package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Parser;
public class ParserTest {
    @Test
    public void dateConversion() {
        assertEquals("2000-12-23 2300", Parser.retrieveDate("Dec 23 2000 2300"));
    }
}
