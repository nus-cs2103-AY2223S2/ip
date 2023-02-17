package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.parser.Parser;

public class ParserTest {
    @Test
    public void parseInput_invalidInput_exceptionThrown() {
        try {
            new Parser().parseInput("hello");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
