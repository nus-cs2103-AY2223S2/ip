package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {

    @Test
    public void getDescription() throws DukeException {
        // A unit test for Task#getDescription
        // test setup
        String description = "Hello World";
        String from = "2024-01-01";
        String to = "2024-02-01";
        Event task = new Event(description, from, to);

        // automatically verify the response
        assertEquals(task.getDescription(),
                description);
    }

    @Test
    public void getStatusIcon() throws DukeException {
        // A unit test for Task#getStatusIcon
        // test setup
        String description = "Hello World";
        String from = "2024-01-01";
        String to = "2024-02-01";
        Event task = new Event(description, from, to);

        // automatically verify the response
        assertEquals(task.getStatusIcon(),
                " ");
    }
}
