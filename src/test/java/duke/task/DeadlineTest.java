package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    private final String description;
    private final String deadline;
    private final Deadline task;

    public DeadlineTest() throws DukeException {
        description = "Hello World";
        deadline = "2024-01-01";
        task = new Deadline(description, deadline);
    }

    @Test
    public void getDeadline() {
        // A unit test for Deadline#getDeadline

        // automatically verify the response
        assertEquals(task.getDeadline(),
                deadline);
    }

    @Test
    public void toSaveData() {
        // A unit test for Deadline#toSaveData
        // test setup
        String delimiter = " | ";
        String saveData = "D"
                + delimiter + " "
                + delimiter + description
                + delimiter + deadline;

        // automatically verify the response
        assertEquals(task.toSaveData(delimiter),
                saveData);
    }

    @Test
    public void generate() throws DukeException {
        // A unit test for Deadline#generate
        // test setup
        Deadline task = Deadline.generate("deadline " + description
                + " /by " + deadline);

        // automatically verify the response
        assertEquals(task.getDescription(),
                this.task.getDescription());
        assertEquals(task.getStatusIcon(),
                this.task.getStatusIcon());
        assertEquals(task.getDeadline(),
                this.task.getDeadline());
    }
}
