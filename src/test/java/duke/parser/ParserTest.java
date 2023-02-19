package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.UI.Parser;
public class ParserTest {
    @Test
    public void parseInput_invalidInput_exceptionThrown() {
        try {
            new Parser().parseInput("hello");
            fail();
        } catch (DukeException error) {
            assertEquals("I am sorry. I have failed to comprehend your command. Please try again.",
                    error.getMessage());
        }
    }
}