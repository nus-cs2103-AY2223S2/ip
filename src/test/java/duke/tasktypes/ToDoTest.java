package duke.tasktypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testStringConversion_todoTaskDone_success() {
        Task.resetCounter();
        ToDo task = new ToDo("This is a test ToDo task.");
        task.setDone();
        String actualOutput = task.toString();
        assertEquals("[T][X] This is a test ToDo task. #0", actualOutput);
    }

    @Test
    public void testSaveFormatConversion_todoTaskDone_success() {
        ToDo task = new ToDo("This is another test ToDo task.");
        task.setDone();
        String actualOutput = task.getSaveFormat();
        assertEquals("T,,1,,This is another test ToDo task.", actualOutput);
    }
}
