package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final String description = "Hello World";
    private final Task task = new Task(description);

    @Test
    public void getDescription() {
        // A unit test for Task#getDescription

        // automatically verify the response
        assertEquals(task.getDescription(),
                description);
    }

    @Test
    public void getStatusIcon() {
        // A unit test for Task#getStatusIcon
        // test setup
        String statusIcon = " ";

        // automatically verify the response
        assertEquals(task.getStatusIcon(),
                statusIcon);
    }

    @Test
    public void mark() {
        // A unit test for Task#mark
        // test setup
        String statusIcon = "X";

        // automatically verify the response
        task.mark();
        assertEquals(task.getStatusIcon(),
                statusIcon);
        task.unmark();
    }

    @Test
    public void unmark() {
        // A unit test for Task#unmark
        // test setup
        String statusIcon = " ";

        // automatically verify the response
        task.mark();
        task.unmark();
        assertEquals(task.getStatusIcon(),
                statusIcon);
    }

    @Test
    public void toSaveData() {
        // A unit test for Task#toSaveData
        // test setup
        String delimiter = " | ";
        String saveData = " "
                + delimiter
                + " "
                + delimiter
                + description;

        // automatically verify the response
        assertEquals(task.toSaveData(delimiter),
                saveData);
    }
}
