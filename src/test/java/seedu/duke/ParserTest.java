package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_invalidInput_exceptionThrown() {
        try {
            new Parser().parseInput("hello", new TaskList(new ArrayList<>()), new Storage());
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
