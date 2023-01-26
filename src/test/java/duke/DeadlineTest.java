package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void parseDeadline_wrongFormat_exceptionThrown() {
        try {
            String[] input = {"deadline", "return book /by 15 Oct 2019"};
            Parser.parseDeadline(input);
        } catch(DukeException e) {
            assertEquals("Give deadline in YYYY-MM-DD format!", e.getMessage());
        }
    }
}
