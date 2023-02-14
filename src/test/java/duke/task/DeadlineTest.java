package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    @Test
    public void getDescription() throws DukeException {
        // A unit test for Task#getDescription
        // test setup
        String description = "Hello World";
        String deadline = "2024-01-01";
        Deadline task = new Deadline(description, deadline);

        // automatically verify the response
        assertEquals(task.getDescription(),
                description);
    }

    @Test
    public void getStatusIcon() throws DukeException {
        // A unit test for Task#getStatusIcon
        // test setup
        String description = "Hello World";
        String deadline = "2024-01-01";
        Deadline task = new Deadline(description, deadline);

        // automatically verify the response
        assertEquals(task.getStatusIcon(),
                " ");
    }
}
