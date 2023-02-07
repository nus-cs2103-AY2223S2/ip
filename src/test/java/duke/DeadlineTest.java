package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    public void parseDeadline_wrongFormat_exceptionThrown() {
        try {
            Parser.parseUserResponse("deadline return book /by 15 Oct 2019");
            Parser.parseDeadline();
        } catch (DukeException e) {
            assertEquals("Give deadline in YYYY-MM-DD format!", e.getMessage());
        }
    }
}
