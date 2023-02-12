package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void parseDeadline_wrongFormat_exceptionThrown() {
        try {
            Ui ui = new Ui();
            Command command = Parser.parseUserResponse(ui, null, null, "deadline return book /by 15 Oct 2019");
            command.execute();
        } catch (DukeException e) {
            assertEquals("Give deadline in YYYY-MM-DD format!", e.getMessage());
        }
    }
}
