package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DukeTest {
    @Test
    public void getResponse_invalidCommand_exceptionMessageGiven() {
        Duke duke = new Duke();
        String exceptionString = duke.getResponse("abcd");
        assertEquals("Error: Invalid command.", exceptionString);
    }

    @Test
    public void getResponse_missingDescription_exceptionMessageGiven() {
        Duke duke = new Duke();
        String exceptionString = duke.getResponse("update");
        assertEquals("Error: No command description given.", exceptionString);
    }

    @Test
    public void getResponse_wrongIndex_exceptionMessageGiven() {
        Duke duke = new Duke();
        String exceptionString = duke.getResponse("delete 1");
        assertEquals("Error: LeTask index out of bounds.", exceptionString);
    }
}
