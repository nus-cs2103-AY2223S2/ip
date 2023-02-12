package duke.components;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Parser.parse("This command should throw an error");
        } catch (DukeException e) {
            assertEquals(
                    "I'm sorry, I could not understand that command.",
                    e.getMessage());
        }
    }

}
